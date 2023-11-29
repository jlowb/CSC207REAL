package use_case.pause_song;

import entity.Song;

public class PauseSongInputData {

    private Song song;

    public PauseSongInputData(Song song) {
        this.song = song;
    }

    public int getSongID() {
        return song.getSongID();
    }
}
