package use_case.pause_song;

import entity.Song;

public class PauseSongOutputData {

    private final Song song;

    public PauseSongOutputData(Song song) {
        this.song = song;
    }

    public int getSongID() {
        return this.song.getSongID();
    }

    public Song getSong() {
        return this.song;
    }
}
