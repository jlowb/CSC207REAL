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
import java.lang.reflect.Field;
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
            Song song1 =  new Song(1, "Speak Now (Deluxe Edition)", 230, 2010, "1.mp3", "Mine");
            Song song2 =  new Song(2, "Speak Now (Deluxe Edition)", 260, 2010, "2.mp3", "Sparks Fly");
            Song song100 = new Song(100, "evermore", 260, 2020, "100.mp3", "ivy");
            Song song150 = new Song(150, "Red (Taylor's Version)", 220, 2021, "150.mp3","Girl At Home (Taylor's Version)");

            // Create a list of songs
            List<Song> songs = new ArrayList<>();
            songs.add(song1);
            songs.add(song2);
            songs.add(song100);
            songs.add(song150);

            // Fetch albums using AlbumBuilder
            List<Album> albums = AlbumBuilder.fetchAlbums(songs);

            // Test cases
            Assertions.assertEquals(3, albums.size());  // Expecting 3 albums

            // Test album names and song counts
            Assertions.assertEquals("Speak Now (Deluxe Edition)", albums.get(0).getName());
            Assertions.assertEquals(2, albums.get(0).getSongs().size());

            Assertions.assertEquals("evermore", albums.get(2).getName());
            Assertions.assertEquals(1, albums.get(1).getSongs().size());

            Assertions.assertEquals("Red (Taylor's Version)", albums.get(1).getName());
            Assertions.assertEquals(1, albums.get(2).getSongs().size());
        }
    }

    public static class MusicPlayerFacadeTest {

        private MusicPlayerFacade musicPlayerFacade;

        @Before
        public void setUp() {
            // Set up a MusicPlayerFacade for testing
            MusicLibrary library = MusicLibrary.getInstance();
            musicPlayerFacade = MusicPlayerFacade.getInstance("Speak Now (Deluxe Edition)");
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

        private MusicPlayerFacade accessSingletonInstance() throws Exception {
            Field instanceField = MusicPlayerFacade.class.getDeclaredField("instance");
            instanceField.setAccessible(true);
            return (MusicPlayerFacade) instanceField.get(null); // null for static fields
        }

        @Test
        public void testRemoveInstance() throws Exception {
            assertNotNull(MusicPlayerFacade.getInstance("Test Album"));
            MusicPlayerFacade.removeInstance();
            MusicPlayerFacade afterRemoveInstance = accessSingletonInstance();
            assertNull("Instance should be null after removeInstance()", afterRemoveInstance);
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
            assertNull(musicPlayerFacade.getCurrentSong());
            assertNull(musicPlayerFacade.getNextSong());
            musicPlayerFacade.addToQueue(1);
            assertEquals(2, musicPlayerFacade.getCurrentSong().getSongID());
        }

        @Test
        public void testMakeState() {
            try {
                String songURL = URLSongLoader.fetchPresignedURL(1);
                PlayerState playerState = musicPlayerFacade.makeState(songURL);
                assertNotNull(playerState);
            } catch (JavaLayerException e) {
                Assert.fail("Exception not expected");
            } catch (IOException e) {
                throw new RuntimeException(e);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
