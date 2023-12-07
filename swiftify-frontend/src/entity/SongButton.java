package entity;

import javax.swing.*;
/**
 * Custom JButton class representing a song button.
 */

public class SongButton extends JButton {

    private final Integer songId;
    private final String songName;
    /**
     * Constructs a new SongButton with the specified song ID and name.
     *
     * @param songId   The unique identifier of the song.
     * @param songName The name of the song.
     */

    public SongButton(Integer songId, String songName) {
        this.songId = songId;
        this.songName = songName;
        this.setText(songName);
    }
    /**
     * Retrieves the song ID associated with this button.
     *
     * @return The song ID.
     */

    public Integer getSongId() {
        return this.songId;
    }
    public String getSongName() {
        return this.songName;
    }
}