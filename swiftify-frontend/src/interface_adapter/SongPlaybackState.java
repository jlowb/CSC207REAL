package interface_adapter;

import entity.PlayerState;

public class SongPlaybackState extends State {

    private final PlayerState musicPlayer;
    private final boolean playing;

    public SongPlaybackState(PlayerState musicPlayer, boolean playing) {
        this.musicPlayer = musicPlayer;
        this.playing = playing;
    }
}
