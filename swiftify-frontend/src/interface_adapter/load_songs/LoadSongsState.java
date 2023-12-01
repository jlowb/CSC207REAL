package interface_adapter.load_songs;

import entity.Song;
import interface_adapter.State;

import java.util.ArrayList;
import java.util.List;

public class LoadSongsState extends State {

    private String albumName;
    private List<Song> songs = new ArrayList<Song>();

    public LoadSongsState(String albumName, List<Song> songs) {
        this.albumName = albumName;
        this.songs = songs;
    }

    public LoadSongsState() {

    }

    public String getAlbumName() { return this.albumName; }

    public List<Song> getSongs() {
        return this.songs;
    }

    public void addSongs(Song song) {
        this.songs.add(song);
    }
}
