package interface_adapter;

import entity.PlayerState;

public class SongPlaybackState extends State {

    private PlayerState musicPlayer;
    private boolean playing;

    public SongPlaybackState(PlayerState musicPlayer, boolean playing) {
        this.musicPlayer = musicPlayer;
        this.playing = playing;
    }
}
