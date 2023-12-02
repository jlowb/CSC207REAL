package use_case.play_song;

import view.LoadSongsView;

public class PlaySongInputData {
    private final Integer songId;
    private final String songName;
    private final LoadSongsView view;

    public PlaySongInputData(Integer songId, String songName, LoadSongsView view) {
        this.songId = songId;
        this.songName = songName;
        this.view = view;
    }

    public Integer getSongId() {
        return this.songId;
    }

    public String getSongName() {
        return this.songName;
    }

    public LoadSongsView getView() {
        return this.view;
    }
}
