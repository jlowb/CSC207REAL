package use_case.play_song;

import entity.PlayerState;
import entity.Song;

public class PlaySongOutputData {

    private final String songName;
    private final PlayerState musicPlayer;

    public PlaySongOutputData(String songName, PlayerState musicPlayer) {
        this.songName = songName;
        this.musicPlayer = musicPlayer;
    }

    public String getSong() {
        return this.songName;
    }

    public PlayerState getMusicPlayer() {
        return this.musicPlayer;
    }
}
