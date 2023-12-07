package test;

import data_access.SongBuilder;
import entity.Song;
import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class SongBuilderTest {

    @Test
    public void testFetchSongsFromApi() {
        // Test if fetchSongs method retrieves songs from the API
        List<Song> songs = null;
        songs = SongBuilder.fetchSongs();

        assertNotNull(songs);
        assertTrue(songs.size() == 175);

        // Test songs sorted properly
        assertTrue(songs.get(0).getSongID() == 1);
        assertTrue(songs.get(1).getSongID() == 2);
    }

    @Test
    public void testFetchSongsBackup() {
        // Test if fetchSongsBackup method reads songs from the backup CSV file
        List<Song> songs = SongBuilder.fetchSongsBackup("src/test/resources/MusicMetadataBackup.csv");

        assertNotNull(songs);
        assertTrue(songs.size() == 175);

        // Test songs sorted properly
        assertTrue(songs.get(0).getSongID() == 1);
        assertTrue(songs.get(1).getSongID() == 2);
    }

    @Test
    public void testFetchSongsBackupWithInvalidFile() {
        // Test if fetchSongsBackup handles an invalid file path
        List<Song> songs = SongBuilder.fetchSongsBackup("nonexistentfile.csv");

        assertNotNull(songs);
        assertEquals(0, songs.size());
    }
}

