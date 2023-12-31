package use_case.pause_song;

import entity.PlayerState;
import entity.Song;
import interface_adapter.SongPlaybackState;
import view.LoadSongsView;

public class PauseSongInputData {

    private final SongPlaybackState state;

    private final LoadSongsView view;

    public PauseSongInputData(SongPlaybackState state, LoadSongsView view) {
        this.state = state;
        this.view = view;
    }

    public SongPlaybackState getState() {
        return this.state;
    }

    public LoadSongsView getView() {
        return this.view;
    }
}
