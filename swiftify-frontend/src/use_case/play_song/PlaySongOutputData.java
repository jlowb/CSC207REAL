package use_case.play_song;

import entity.PlayerState;
import entity.Song;
import view.LoadSongsView;

public class PlaySongOutputData {

    private final String songName;
    private final PlayerState musicPlayer;
    private final LoadSongsView view;

    public PlaySongOutputData(String songName, PlayerState musicPlayer, LoadSongsView view) {
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
