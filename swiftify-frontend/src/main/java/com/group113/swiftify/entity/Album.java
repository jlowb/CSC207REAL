package src.main.java.com.group113.swiftify.entity;

import java.util.List;
import src.main.java.com.group113.swiftify.entity.Song;

public class Album {

    private final String name;
    private final List<Song> songs;

    Album(String name, List<Song> songs) {
        this.name = name;
        this.songs = songs;
    }

    public String getName() { return name; }

    public List<Song> getSongs() {return songs; }
}
