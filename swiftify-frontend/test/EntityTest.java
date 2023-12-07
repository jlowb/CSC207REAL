import interface_adapter.SongPlaybackState;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import entity.*;
import org.junit.jupiter.api.BeforeEach;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.*;

public class EntityTest {

    public static class SongTest {
        private Song song;

        @Before
        public void setUp() {
            song = new Song(1, "Album1", 300, 2020, "s3key1", "Title1");
        }

        @Test
        public void testGetSongID() {
            assertEquals(1, song.getSongID());
        }

        @Test
        public void testGetAlbum() {
            assertEquals("Album1", song.getAlbum());
        }

        @Test
        public void testGetDuration() {
            assertEquals(300, song.getDuration());
        }

        @Test
        public void testGetReleaseYear() {
            assertEquals(2020, song.getReleaseYear());
        }

        @Test
        public void testGetS3Key() {
            assertEquals("s3key1", song.getS3Key());
        }

        @Test
        public void testGetTitle() {
            assertEquals("Title1", song.getTitle());
        }

        @Test
        public void testCompareToEqual() {
            Song sameSong = new Song(1, "Album2", 350, 2021, "s3key2", "Title2");
            assertEquals(0, song.compareTo(sameSong));
        }

        @Test
        public void testCompareToGreater() {
            Song greaterSong = new Song(2, "Album2", 350, 2021, "s3key2", "Title2");
            assertTrue(song.compareTo(greaterSong) < 0);
        }

        @Test
        public void testCompareToLess() {
            Song lesserSong = new Song(0, "Album0", 250, 2019, "s3key0", "Title0");
            assertTrue(song.compareTo(lesserSong) > 0);
        }

        //@Test
        //public void testNegativeDuration() {
        //    assertThrows(IllegalArgumentException.class, () -> {
        //        new Song(1, "Album", -300, 2020, "s3key", "Title");
        //    });
        //}

        @Test
        public void testLargeSongID() {
            Song largeIDSong = new Song(Integer.MAX_VALUE, "Album", 300, 2020, "s3key", "Title");
            assertEquals(Integer.MAX_VALUE, largeIDSong.getSongID());
        }

        @Test
        public void testEmptyAlbumTitle() {
            Song emptyAlbumSong = new Song(1, "", 300, 2020, "s3key", "Title");
            assertEquals("", emptyAlbumSong.getAlbum());
        }
    }

    public static class AlbumTest {
        private Album album;
        private List<Song> songList;

        @Before
        public void setUp() {
            Song song1 = new Song(1, "Album1", 300, 2020, "s3key1", "Title1");
            Song song2 = new Song(2, "Album1", 350, 2021, "s3key2", "Title2");
            songList = Arrays.asList(song1, song2);
            album = new Album("Album1", songList);
        }

        @Test
        public void testGetName() {
            assertEquals("Album1", album.getName());
        }

        @Test
        public void testGetSongs() {
            assertEquals(songList, album.getSongs());
            assertEquals(2, album.getSongs().size());
        }

        @Test
        public void testGetAlbumType() {
            assertNull(album.getAlbumType());
        }

        @Test
        public void testSetAndGetAlbumType() {
            album.setAlbumType("Studio");
            assertEquals("Studio", album.getAlbumType());
        }
    }

    public static class MusicQueueTest {

        private MusicQueue musicQueue;

        @Before
        public void setUp() {
            musicQueue = new MusicQueue(false, 10, "Test Album");
        }

        @Test
        public void testGetDiscographyLength() {
            musicQueue = new MusicQueue(false, 10, "Test Album");
            assertEquals(musicQueue.getDiscographyLength(), 10);
        }

        @Test
        public void testMusicQueueInitialization() {
            assertFalse("Queue should be empty initially", musicQueue.isNotEmpty());
            assertFalse("Shuffle should be initially off", musicQueue.shuffled());
            assertEquals("Initial album name should match", "Test Album", musicQueue.getAlbumName());
            assertEquals("Discography length should match", 10, musicQueue.getDiscographyLength());
        }

        @Test
        public void testShuffle() {
            MusicQueue musicQueue = new MusicQueue(false, 5, "Test Album");

            // Adding songs to the queue
            musicQueue.add(1);
            musicQueue.add(2);
            musicQueue.add(3);
            musicQueue.add(4);
            musicQueue.add(5);

            // Get the original order of songs before shuffling
            List<Integer> originalOrder = new ArrayList<>();
            for (Integer songId : musicQueue) {
                originalOrder.add(songId);
            }

            musicQueue.toggleShuffle(); // Enable shuffle

            // Get the shuffled order after shuffling
            List<Integer> shuffledOrder = new ArrayList<>();
            for (Integer songId : musicQueue) {
                shuffledOrder.add(songId);
            }

            assertNotEquals("Order should change after shuffling", originalOrder, shuffledOrder);

            // Ensure that all songs are still in the queue after shuffling
            Set<Integer> originalSet = new HashSet<>(originalOrder);
            Set<Integer> shuffledSet = new HashSet<>(shuffledOrder);
            assertEquals("All songs should be present after shuffling", originalSet, shuffledSet);
        }

        @Test
        public void testAddAndGetCurrentID() {
            musicQueue = new MusicQueue(false, 10, "Test Album");
            musicQueue.add(1);
            assertEquals("Current song ID should be 1", 1, musicQueue.getCurrentID());
        }

        @Test
        public void testNextOnEmptyQueue() {
            musicQueue = new MusicQueue(false, 10, "Test Album");
            musicQueue.next();
            assertEquals("Next on empty queue should not change current song ID", -1, musicQueue.getCurrentID());
        }

        @Test
        public void testPreviousOnEmptyQueue() {
            musicQueue = new MusicQueue(false, 10, "Test Album");
            musicQueue.previous();
            assertEquals("Previous on empty queue should not change current song ID", -1, musicQueue.getCurrentID());
        }

        @Test
        public void testAddSong() {
            musicQueue.add(5);
            assertEquals("Current song ID should be 5 after adding", 5, musicQueue.getCurrentID());
        }

        @Test
        public void testNextSong() {
            musicQueue.add(1);
            musicQueue.add(2);
            musicQueue.next();
            assertEquals("Current song ID should be 2 after next", 2, musicQueue.getCurrentID());
        }

        @Test
        public void testPreviousSong() {
            musicQueue.add(1);
            musicQueue.add(2);
            musicQueue.next();
            musicQueue.previous();
            assertEquals("Current song ID should revert to 1 after previous", 1, musicQueue.getCurrentID());
        }

        @Test
        public void testToggleShuffle() {
            musicQueue.toggleShuffle();
            assertTrue("Shuffle should be on after toggling", musicQueue.shuffled());
        }

        @Test
        public void testClearQueue() {
            musicQueue = new MusicQueue(false, 10, "Test Album");
            musicQueue.add(1);
            musicQueue.add(2); // Add a second song to test if it's removed from the next position
            musicQueue.clearQueue();
            musicQueue.next(); // Try moving to the next song
            assertEquals("There should be no next song after clearQueue", -1, musicQueue.getCurrentID());
        }

        @Test
        public void testIterator() {
            musicQueue.add(1);
            musicQueue.add(2);
            Iterator<Integer> iterator = musicQueue.iterator();
            assertTrue("Iterator should have next", iterator.hasNext());
            assertEquals("First element should be 1", Integer.valueOf(1), iterator.next());
        }

        //@Test(expected = IllegalArgumentException.class)
        //public void testAddNegativeSongId() {
        //    musicQueue.add(-1);
        //}

        @Test
        public void testDisplayQueue() {
            musicQueue.add(1);
            final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
            System.setOut(new PrintStream(outContent));
            musicQueue.displayQueue();
            assertTrue("Output should contain song ID 1", outContent.toString().contains("1"));
            System.setOut(System.out);
        }
    }

    public static class SongButtonTest {

        private SongButton songButton;

        @Before
        public void setUp() {
            Integer songId = 1;
            String songName = "Test Song";
            songButton = new SongButton(songId, songName);
        }

        @Test
        public void testGetSongId() {
            assertEquals("Song ID should be 1", Integer.valueOf(1), songButton.getSongId());
        }

        @Test
        public void testGetSongName() {
            assertEquals("Song name should be 'Test Song'", "Test Song", songButton.getSongName());
        }

        @Test
        public void testButtonText() {
            assertEquals("Button text should match song name", "Test Song", songButton.getText());
        }
    }

    public static class AddToQueueButtonTest {

        private AddToQueueButton queueButton;

        @Before
        public void setUp() {
            Integer songId = 2;
            String title = "Queue Song";
            queueButton = new AddToQueueButton(title, songId);
        }

        @Test
        public void testGetSongId() {
            assertEquals("Song ID should be 2", Integer.valueOf(2), queueButton.getSongId());
        }

        @Test
        public void testButtonText() {
            assertEquals("Button text should match the title 'Queue Song'", "Queue Song", queueButton.getText());
        }
    }
}
