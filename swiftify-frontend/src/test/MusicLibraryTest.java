package test;

import entity.Album;
import entity.MusicLibrary;
import entity.Song;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class MusicLibraryTest {

    private MusicLibrary musicLibrary;

    @Before
    public void setUp() {
        // Set up a MusicLibrary for testing
        musicLibrary = MusicLibrary.getInstance();
    }

    @Test
    public void testGetInstance() {
        assertNotNull(musicLibrary);
        MusicLibrary secondInstance = MusicLibrary.getInstance();
        assertEquals(musicLibrary, secondInstance);
    }

    @Test
    public void testGetSongs() {
        List<Song> songs = musicLibrary.getSongs();
        assertNotNull(songs);
        assertEquals(musicLibrary.getLength(), songs.size());
    }

    @Test
    public void testGetAlbums() {
        List<Album> albums = musicLibrary.getAlbums();
        assertNotNull(albums);
        assertEquals(9, albums.size()); // Ensure that there are exactly 9 albums
    }

    @Test
    public void testGetLength() {
        int length = musicLibrary.getLength();
        List<Song> songs = musicLibrary.getSongs();
        assertEquals(songs.size(), length);
    }

}
