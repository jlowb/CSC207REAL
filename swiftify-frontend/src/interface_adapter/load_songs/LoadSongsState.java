package interface_adapter.load_songs;

import entity.Song;

import java.util.ArrayList;
import java.util.List;

public class LoadSongsState {

    private List<Song> songs = new ArrayList<Song>();

    public LoadSongsState(List<Song> songs) {
        this.songs = songs;
    }

    public LoadSongsState() {

    }

    public List<Song> getSongs() {
        return this.songs;
    }

    public void addSongs(Song song) {
        this.songs.add(song);
    }
}
