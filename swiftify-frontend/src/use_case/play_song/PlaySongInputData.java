package use_case.play_song;

import entity.Song;

public class PlaySongInputData {
    private Song song;

    public PlaySongInputData(Song song) {
        this.song = song;
    }

    public int getSongID() {
        return song.getSongID();
    }
}
