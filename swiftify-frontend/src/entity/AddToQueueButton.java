package entity;

import javax.swing.*;

public class AddToQueueButton extends JButton {

    private final Integer songId;

    public AddToQueueButton(String title, Integer songId) {
        this.setText(title);
        this.songId = songId;
    }

    public Integer getSongId() {
        return this.songId;
    }
}
