package entity;

import javax.swing.*;

public class SongButton extends JButton {

    private final Integer songId;
    private final String songName;

    public SongButton(Integer songId, String songName) {
        this.songId = songId;
        this.songName = songName;
        this.setText(songName);
    }

    public Integer getSongId() {
        return this.songId;
    }
    public String getSongName() {
        return this.songName;
    }
}