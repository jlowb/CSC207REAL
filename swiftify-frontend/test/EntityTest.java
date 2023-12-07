import entity.AddToQueueButton;
import entity.Album;
import entity.MusicQueue;
import entity.Song;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
/*
public class EntityTest {
    public static class AddToQueueButtonTest {

        @Test
        public void testConstructorAndGetters() {
            AddToQueueButton button = new AddToQueueButton("Test Song", 1);

            assertNotNull(button);
            assertEquals("Test Song", button.getText());
            assertEquals(1, button.getSongId());
        }

        @Test
        public void testSetAndGetSongId() {
            AddToQueueButton button = new AddToQueueButton("Test Song", 1);

            assertEquals(1, button.getSongId());
            button.setSongId(2);
            assertEquals(2, button.getSongId());
        }

    }

    public static class AlbumTest {

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

        @org.junit.jupiter.api.Test
        void testGetName() {
            Assertions.assertEquals("Test Album", testAlbum.getName());
        }

        @org.junit.jupiter.api.Test
        void testGetSongs() {
            Assertions.assertNotNull(testAlbum.getSongs());
            Assertions.assertEquals(2, testAlbum.getSongs().size());
        }
    }

    public static class MusicQueueTest {

        private MusicQueue musicQueue;

        @Before
        public void setUp() {
            // Set up a MusicQueue for testing
            musicQueue = new MusicQueue(false, 10, "Test Album");
        }

        @Test
        public void testAdd() {
            musicQueue.add(1);
            musicQueue.add(2);
            assertEquals(1, musicQueue.head.songID);
            assertEquals(2, musicQueue.tail.songID);
            assertFalse(musicQueue.shuffled());
        }

        @Test
        public void testNext() {
            musicQueue.add(1);
            musicQueue.add(2);
            musicQueue.next();
            assertEquals(2, musicQueue.current.songID);
        }

        @Test
        public void testPrevious() {
            musicQueue.add(1);
            musicQueue.add(2);
            musicQueue.next();
            musicQueue.previous();
            assertEquals(1, musicQueue.current.songID);
        }

        @Test
        public void testToggleShuffle() {
            assertFalse(musicQueue.shuffled());
            musicQueue.toggleShuffle();
            assertTrue(musicQueue.shuffled());
        }

        @Test
        public void testIterator() {
            musicQueue.add(1);
            musicQueue.add(2);

            StringBuilder result = new StringBuilder();
            for (int id : musicQueue) {
                result.append(id).append(" ");
            }

            assertEquals("1 2 ", result.toString());
        }
    }
}
*/