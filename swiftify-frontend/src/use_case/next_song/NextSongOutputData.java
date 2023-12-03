package use_case.next_song;

import entity.PlayerState;
import view.LoadSongsView;

public class NextSongOutputData {
    private final String songName;
    private final PlayerState musicPlayer;
    private final LoadSongsView view;

    public NextSongOutputData(String songName, PlayerState musicPlayer, LoadSongsView view) {
        this.songName = songName;
        this.musicPlayer = musicPlayer;
        this.view = view;
    }

    public String getSong() {
        return this.songName;
    }

    public PlayerState getMusicPlayer() {
        return this.musicPlayer;
    }

    public LoadSongsView getView() {
        return this.view;
    }
}
