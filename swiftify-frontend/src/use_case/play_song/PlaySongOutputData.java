package use_case.play_song;

import entity.Song;

public class PlaySongOutputData {

    private final Song song;

    public PlaySongOutputData(Song song) {
        this.song = song;
    }

    public int getSongID() {
        return this.song.getSongID();
    }

    public Song getSong() {
        return this.song;
    }
}
