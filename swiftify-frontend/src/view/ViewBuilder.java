package view;

import app.LoadSongsUseCaseFactory;
import app.SongPlaybackUseCaseFactory;
import data_access.URLSongLoader;
import entity.Song;
import interface_adapter.SongPlaybackState;
import interface_adapter.ViewManagerModel;
import interface_adapter.ViewModel;
import interface_adapter.load_album.LoadAlbumState;
import interface_adapter.load_album.LoadAlbumViewModel;
import interface_adapter.load_songs.LoadSongsState;
import interface_adapter.pause_song.PauseSongController;
import interface_adapter.play_song.PlaySongController;
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
        if (this.viewModel.getViewName().equalsIgnoreCase("LoadAlbumView")) {
            return buildAlbumView();
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

    private LoadSongsView buildAlbumSongsView() {
        LoadSongsState loadSongsState = (LoadSongsState) this.viewModel.getState();
        PlaySongController playSongController = SongPlaybackUseCaseFactory.createPlaySongController(this.viewManagerModel, new URLSongLoader());
        PauseSongController pauseSongController = SongPlaybackUseCaseFactory.createPauseSongController(this.viewManagerModel, null);
        ResumeSongController resumeSongController = SongPlaybackUseCaseFactory.createResumeSongController(this.viewManagerModel, null);
        LoadSongsView loadSongsView1 = new LoadSongsView(loadSongsState.getAlbumName(), playSongController, pauseSongController, resumeSongController);
        for (Song song : loadSongsState.getSongs()) {
            loadSongsView1.addSong(song);
        }
        return loadSongsView1;
    }

    public LoadAlbumView buildAlbumView() {
        return new LoadAlbumView(LoadSongsUseCaseFactory.createLoadSongsController(this.viewManagerModel));
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
