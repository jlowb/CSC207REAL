package test;

import entity.Album;
import entity.Song;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class AlbumTest {

    private Album testAlbum;
    private List<Song> testSongs;

    @BeforeEach
    void setUp() {
        // Create a sample list of songs for testing
        testSongs = new ArrayList<>();
        testSongs.add(new Song(1, "Test Album", 180, 2022, "test-key-1", "Test Song 1"));
        testSongs.add(new Song(2, "Test Album", 200, 2022, "test-key-2", "Test Song 2"));

        testAlbum = new Album("Test Album", testSongs);
    }

    @Test
    void testGetName() {
        assertEquals("Test Album", testAlbum.getName());
    }

    @Test
    void testGetSongs() {
        assertNotNull(testAlbum.getSongs());
        assertEquals(2, testAlbum.getSongs().size());
    }
}
