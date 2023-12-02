package use_case.resume_song;

import interface_adapter.SongPlaybackState;
import view.LoadSongsView;

public class ResumeSongInputData {

    private final SongPlaybackState state;

    private final LoadSongsView view;

    public ResumeSongInputData(SongPlaybackState state, LoadSongsView view) {
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
