package entity;

import java.util.List;

public class Album {

    private final String name;
    private String albumType;
    private final List<Song> songs;

    public Album(String name, List<Song> songs) {
        this.name = name;
        this.songs = songs;
    }

    public String getName() { return name; }

    public List<Song> getSongs() {return songs; }

    public String getAlbumType() {
        return albumType;
    }
}
