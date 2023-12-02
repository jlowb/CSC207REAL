package entity;

import interface_adapter.SongPlaybackState;

import javax.swing.*;

public class SongPlaybackButton extends JButton {

    private SongPlaybackState songPlaybackState;

    public SongPlaybackButton(SongPlaybackState songPlaybackState) {
        this.songPlaybackState = songPlaybackState;
    }

    public SongPlaybackState getSongPlaybackState() {
        return this.songPlaybackState;
    }

    public void setSongPlaybackState(SongPlaybackState songPlaybackState) {
        this.songPlaybackState = songPlaybackState;
    }
}
