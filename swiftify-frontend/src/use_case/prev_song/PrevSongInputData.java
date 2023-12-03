package use_case.prev_song;

import entity.Song;
import view.LoadSongsView;

public class PrevSongInputData {

    private Song song;
    private final LoadSongsView view;

    public PrevSongInputData(Song song, LoadSongsView view) {
        this.song = song;
        this.view = view;
    }

    public Song getSong() {
        return this.song;
    }

    public LoadSongsView getView() {
        return this.view;
    }
}
