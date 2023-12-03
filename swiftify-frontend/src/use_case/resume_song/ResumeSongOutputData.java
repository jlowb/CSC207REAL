package use_case.resume_song;

import interface_adapter.SongPlaybackState;
import view.LoadSongsView;

public class ResumeSongOutputData {

    private final SongPlaybackState state;
    private final LoadSongsView loadSongsView;

    public ResumeSongOutputData(SongPlaybackState state, LoadSongsView loadSongsView) {
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
