package entity;

import java.util.List;

/**
 * The Album class represents a music album, consisting of a collection of songs.
 * It allows setting and retrieving album details such as its name, type, and the list of songs.
 */
public class Album {

    private final String name;
    private String albumType;
    private final List<Song> songs;

    /**
     * Constructs a new Album object with the specified name and list of songs.
     *
     * @param name The name of the album.
     * @param songs The list of songs included in the album.
     */
    public Album(String name, List<Song> songs) {
        this.name = name;
        this.songs = songs;
    }

    /**
     * Returns the name of the album.
     *
     * @return The name of the album.
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the list of songs in the album.
     *
     * @return A list of Song objects representing the songs in the album.
     */
    public List<Song> getSongs() {
        return songs;
    }

    /**
     * Returns the type of the album.
     *
     * @return The type of the album, such as "studio", "live", etc.
     */
    public String getAlbumType() {
        return albumType;
    }

    /**
     * Sets the type of the album.
     *
     * @param albumType The type of the album, such as "studio", "live", etc.
     */
    public void setAlbumType(String albumType) {
        this.albumType = albumType;
    }
}
