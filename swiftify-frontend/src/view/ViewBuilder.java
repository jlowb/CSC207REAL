package view;

import entity.Song;
import interface_adapter.SongPlaybackState;
import interface_adapter.ViewModel;
import interface_adapter.load_album.LoadAlbumState;
import interface_adapter.load_album.LoadAlbumViewModel;
import interface_adapter.load_songs.LoadSongsState;

import javax.swing.*;

public class ViewBuilder {

    private final ViewModel viewModel;

    public ViewBuilder(ViewModel viewModel) {
        this.viewModel = viewModel;
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

        // write switch case for other views later
        return new JFrame();
    }

    private LoadSongsView buildAlbumSongsView() {
        LoadSongsState loadSongsState = (LoadSongsState) this.viewModel.getState();
        LoadSongsView loadSongsView1 = new LoadSongsView(loadSongsState.getAlbumName());
        for (Song song : loadSongsState.getSongs()) {
            loadSongsView1.addSong(song);
        }
        return loadSongsView1;
    }

    public LoadAlbumView buildAlbumView() {
        return new LoadAlbumView();
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
}
