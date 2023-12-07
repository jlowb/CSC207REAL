package view;

import app.*;
import data_access.URLSongLoader;
import entity.Song;
import interface_adapter.SongPlaybackState;
import interface_adapter.ViewManagerModel;
import interface_adapter.ViewModel;
import interface_adapter.add_to_queue.AddToQueueController;
import interface_adapter.load_albums.LoadAlbumsState;
import interface_adapter.load_songs.LoadSongsController;
import interface_adapter.load_songs.LoadSongsState;
import interface_adapter.next_song.NextSongController;
import interface_adapter.pause_song.PauseSongController;
import interface_adapter.play_song.PlaySongController;
import interface_adapter.prev_song.PrevSongController;
import interface_adapter.resume_song.ResumeSongController;

import javax.swing.*;

public class ViewBuilder {

    private final ViewModel viewModel;
    private final ViewManagerModel viewManagerModel;

    public ViewBuilder(ViewManagerModel viewManagerModel) {
        this.viewManagerModel = viewManagerModel;
        this.viewModel = viewManagerModel.getViewModel();
    }

    public JFrame buildView() {
        if (this.viewModel.getViewName().equalsIgnoreCase("LoadSongsView")) {
            return buildAlbumSongsView();
        }
        if (this.viewModel.getViewName().equalsIgnoreCase("LoadAlbumsView")) {
            return buildAlbumsView();
        }
        if (this.viewModel.getViewName().equalsIgnoreCase("PlaySongView")) {
            return buildPlayingView();
        }
        if (this.viewModel.getViewName().equalsIgnoreCase("PauseSongView")) {
            return buildPausedView();
        }
        if (this.viewModel.getViewName().equalsIgnoreCase("ResumeSongView")) {
            return buildResumedView();
        }
        if (this.viewModel.getViewName().equalsIgnoreCase("AddToQueueView")) {
            return buildAddToQueueView();
        }

        // write switch case for other views later
        return new JFrame();
    }

    public LoadSongsView buildAlbumSongsView() {
        LoadSongsState loadSongsState = (LoadSongsState) this.viewModel.getState();
        URLSongLoader songLoader = new URLSongLoader();
        PlaySongController playSongController = SongPlaybackUseCaseFactory.createPlaySongController(this.viewManagerModel, songLoader);
        PauseSongController pauseSongController = SongPlaybackUseCaseFactory.createPauseSongController(this.viewManagerModel, null);
        ResumeSongController resumeSongController = SongPlaybackUseCaseFactory.createResumeSongController(this.viewManagerModel, null);
        NextSongController nextSongController = SongPlaybackUseCaseFactory.createNextSongController(this.viewManagerModel, songLoader);
        PrevSongController prevSongController = SongPlaybackUseCaseFactory.createPrevSongController(this.viewManagerModel, songLoader);
        AddToQueueController addToQueueController = SongPlaybackUseCaseFactory.createAddToQueueController(this.viewManagerModel);
        LoadSongsView loadSongsView1 = new LoadSongsView(loadSongsState.getAlbumName(), playSongController, pauseSongController, resumeSongController, nextSongController, prevSongController, addToQueueController, this.viewManagerModel);
        for (Song song : loadSongsState.getSongs()) {
            loadSongsView1.addSong(song);
        }
        return loadSongsView1;
    }

    public LoadAlbumsView buildAlbumsView() {
        LoadAlbumsState loadAlbumState = (LoadAlbumsState) this.viewModel.getState();
        LoadSongsController loadSongsController = LoadSongsUseCaseFactory.createLoadSongsController(this.viewManagerModel);
        if (loadAlbumState.getSelection().equalsIgnoreCase("TaylorSwift")) {
            LoadAlbumsView view = new LoadAlbumsView(loadSongsController);
            view.loadAlbumPanels(loadAlbumState);
            return view;
        }
        else {
            return new LoadAlbumsView(loadSongsController);
        }
    }

    public LoadSongsView buildPlayingView() {
        SongPlaybackState songPlaybackState = (SongPlaybackState) this.viewModel.getState();
        LoadSongsView view = songPlaybackState.getView();
        view.setPlayingView(songPlaybackState);
        return view;
    }

    public LoadSongsView buildPausedView() {
        SongPlaybackState songPlaybackState = (SongPlaybackState) this.viewModel.getState();
        LoadSongsView view = songPlaybackState.getView();
        view.setPausedView(songPlaybackState);
        return view;
    }

    public LoadSongsView buildResumedView() {
        SongPlaybackState songPlaybackState = (SongPlaybackState) this.viewModel.getState();
        LoadSongsView view = songPlaybackState.getView();
        view.setResumedView(songPlaybackState);
        return view;
    }

    public LoadSongsView buildAddToQueueView() {
        SongPlaybackState songPlaybackState = (SongPlaybackState) this.viewModel.getState();
        LoadSongsView view = songPlaybackState.getView();
        return view;
    }
}
