package data_access;

import entity.Album;
import entity.Song;

import java.util.List;


// List<Song> and List<Album> container
public class MusicLibrary {

    private static MusicLibrary instance;
    private List<Song> songs;
    private List<Album> albums;

    private MusicLibrary() {
        songs = SongBuilder.fetchSongs();
        albums = AlbumBuilder.fetchAlbums(songs);
    }

    public static synchronized MusicLibrary getInstance() {
        if (instance == null) {
            instance = new MusicLibrary();
        }
        return instance;
    }

    public List<Song> getSongs() {
        return songs;
    }

    public List<Album> getAlbums() {
        return albums;
    }

    public int getLength() {return songs.size();}

    public static void main(String[] args) {
        MusicLibrary library = MusicLibrary.getInstance();
        List<Song> songs = library.getSongs();
        List<Album> albums = library.getAlbums();

        for (Song song : songs) {
            System.out.println("SongID: " + song.getSongID() + ", Title: " + song.getTitle());
        }

        for (Album album : albums) {
            System.out.println("Album: " + album.getName());
            for (Song song : album.getSongs()) {
                System.out.println("  - " + song.getTitle());
            }
        }
    }
}
