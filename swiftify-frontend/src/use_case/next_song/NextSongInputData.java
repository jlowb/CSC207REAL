package use_case.next_song;

import entity.Song;
import view.LoadSongsView;

public class NextSongInputData {

    private Song song;
    private final LoadSongsView view;

    public NextSongInputData(Song song, LoadSongsView view) {
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
