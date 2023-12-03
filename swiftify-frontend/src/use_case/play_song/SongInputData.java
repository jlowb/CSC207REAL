package use_case.play_song;

import entity.Song;

public class SongInputData {
    private Song song;

    public SongInputData(Song song) {
        this.song = song;
    }

    public int getSongID() {
        return song.getSongID();
    }
}
