package entity;

import interface_adapter.SongPlaybackState;

public class SongPlaybackButton {

    private final SongPlaybackState songPlaybackState;

    public SongPlaybackButton(SongPlaybackState songPlaybackState) {
        this.songPlaybackState = songPlaybackState;
    }

    public SongPlaybackState getSongPlaybackState() {
        return this.songPlaybackState;
    }
}
