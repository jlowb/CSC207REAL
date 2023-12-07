import data_access.*;
import entity.Album;
import entity.PlayerState;
import entity.Song;
import javazoom.jl.decoder.JavaLayerException;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

public class DataAccessTest {

    public static class MusicLibraryTest {

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

    public static class SongBuilderTest {

        @Test
        public void testFetchSongsFromApi() {
            // Test if fetchSongs method retrieves songs from the API
            List<Song> songs = null;
            songs = SongBuilder.fetchSongs();

            Assertions.assertNotNull(songs);
            assertTrue(songs.size() == 175);

            // Test songs sorted properly
            assertTrue(songs.get(0).getSongID() == 1);
            assertTrue(songs.get(1).getSongID() == 2);
        }

        @Test
        public void testFetchSongsBackup() {
            // Test if fetchSongsBackup method reads songs from the backup CSV file
            List<Song> songs = SongBuilder.fetchSongsBackup("MusicMetadata2.csv");

            Assertions.assertNotNull(songs);
            assertTrue(songs.size() == 175);

            // Test songs sorted properly
            assertTrue(songs.get(0).getSongID() == 1);
            assertTrue(songs.get(1).getSongID() == 2);
        }

        @Test
        public void testFetchSongsBackupWithInvalidFile() {
            // Test if fetchSongsBackup handles an invalid file path
            List<Song> songs = SongBuilder.fetchSongsBackup("nonexistentfile.csv");

            Assertions.assertNotNull(songs);
            Assertions.assertEquals(0, songs.size());
        }
    }

    public static class URLSongLoaderTest {

        @Test
        public void testFetchPresignedURL() {
            // Provide a valid song ID for testing
            int validSongID = 120;

            try {
                String presignedURL = URLSongLoader.fetchPresignedURL(validSongID);

                Assertions.assertNotNull(presignedURL);
                assertTrue(presignedURL.startsWith("https://"));

                // You may add more specific checks based on the expected structure or content of the URL
            } catch (IOException | InterruptedException e) {
                fail("Exception thrown while fetching presigned URL: " + e.getMessage());
            }
        }
    }

    public static class AlbumBuilderTest {

        @Test
        public void testFetchAlbums() {
            // Create some sample songs
            Song song1 = new Song(154, "Red (Taylor's Version)", 219, 2021, "s3://csc207swiftify/discography/Red/04. I Knew You Were Trouble (Taylor's Version).mp3","I Knew You Were Trouble (Taylor's Version)");
            Song song2 = new Song(7, "Speak Now (Deluxe Edition)", 403, 2010, "s3://csc207swiftify/discography/Speak Now/105-taylor_swift-dear_john.mp3", "Dear John");
            Song song3 = new Song(156, "Red (Taylor's Version)", 223, 2021, "s3://csc207swiftify/discography/Red/02. Red (Taylor's Version).mp3","I Knew You Were Trouble (Taylor's Version)");

            // Create a list of songs
            List<Song> songs = new ArrayList<>();
            songs.add(song1);
            songs.add(song2);
            songs.add(song3);

            // Fetch albums using AlbumBuilder
            List<Album> albums = AlbumBuilder.fetchAlbums(songs);

            // Test cases
            Assertions.assertEquals(2, albums.size());  // Expecting 2 albums

            // Test album names and song counts
            Assertions.assertEquals("Red (Taylor's Version)", albums.get(0).getName());
            Assertions.assertEquals(2, albums.get(0).getSongs().size());

            Assertions.assertEquals("Speak Now (Deluxe Edition)", albums.get(1).getName());
            Assertions.assertEquals(1, albums.get(1).getSongs().size());
        }
    }

    public static class MusicPlayerFacadeTest {

        private MusicPlayerFacade musicPlayerFacade;

        @Before
        public void setUp() {
            // Set up a MusicPlayerFacade for testing
            MusicLibrary library = MusicLibrary.getInstance();
            musicPlayerFacade = MusicPlayerFacade.getInstance("Test Album");
        }

        @After
        public void tearDown() {
            // Clean up after each test
            MusicPlayerFacade.removeInstance();
        }

        @Test
        public void testGetInstance() {
            assertNotNull(musicPlayerFacade);
            MusicPlayerFacade secondInstance = MusicPlayerFacade.getInstance("Test Album");
            assertEquals(musicPlayerFacade, secondInstance);
        }

        @Test
        public void testRemoveInstance() {
            assertNotNull(MusicPlayerFacade.getInstance("Test Album"));
            MusicPlayerFacade.removeInstance();
            assertNull(MusicPlayerFacade.getInstance("Test Album"));
        }

        @Test
        public void testGetQueue() {
            assertNotNull(musicPlayerFacade.getQueue());
        }

        @Test
        public void testToggleShuffle() {
            assertFalse(musicPlayerFacade.getQueue().shuffled());
            musicPlayerFacade.toggleShuffle();
            Assert.assertTrue(musicPlayerFacade.getQueue().shuffled());
        }

        /*
        @Test
        public void testAddToQueue() {
            musicPlayerFacade.addToQueue(1);
            assertEquals(1, musicPlayerFacade.getQueue().head.songID);
        }

         */

        @Test
        public void testGetCurrentSong() {
            assertNull(musicPlayerFacade.getCurrentSong());
            musicPlayerFacade.addToQueue(1);
            assertNotNull(musicPlayerFacade.getCurrentSong());
        }

        /*
        @Test
        public void testGetPrevSong() {
            assertNull(musicPlayerFacade.getPrevSong());
            musicPlayerFacade.addToQueue(1);
            musicPlayerFacade.addToQueue(2);
            musicPlayerFacade.next();
            assertNotNull(musicPlayerFacade.getPrevSong());
        }

         */

        @Test
        public void testGetNextSong() {
            assertNull(musicPlayerFacade.getNextSong());
            musicPlayerFacade.addToQueue(1);
            assertNotNull(musicPlayerFacade.getNextSong());
            assertEquals(1, musicPlayerFacade.getCurrentSong().getSongID());
        }

        @Test
        public void testMakeState() {
            try {
                PlayerState playerState = musicPlayerFacade.makeState("test.mp3");
                assertNotNull(playerState);
            } catch (JavaLayerException e) {
                Assert.fail("Exception not expected");
            }
        }
    }
}
