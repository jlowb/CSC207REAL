package test;

import entity.MusicQueue;
import entity.Song;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;
/*
public class MusicQueueTest {

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
*/