package use_case.play_song;

import entity.Song;

public class SongInputData {
    private final Integer songId;
    private final String songName;

    public SongInputData(Integer songId, String songName) {
        this.songId = songId;
        this.songName = songName;
    }

    public Integer getSongId() {
        return this.songId;
    }

    public String getSongName() {
        return this.songName;
    }
}