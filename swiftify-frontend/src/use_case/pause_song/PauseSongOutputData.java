package use_case.pause_song;

import entity.PlayerState;
import entity.Song;
import interface_adapter.SongPlaybackState;
import view.LoadSongsView;

public class PauseSongOutputData {

    private final SongPlaybackState state;
    private final LoadSongsView loadSongsView;

    public PauseSongOutputData(SongPlaybackState state, LoadSongsView loadSongsView) {
        this.state = state;
        this.loadSongsView = loadSongsView;
    }

    public SongPlaybackState getState() {
        return this.state;
    }

    public LoadSongsView getLoadSongsView() {
        return this.loadSongsView;
    }
}
