package test;

import entity.MusicLibrary;
import entity.MusicPlayerFacade;
import entity.Song;
import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class MusicPlayerFacadeTest {

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
        assertTrue(musicPlayerFacade.getQueue().shuffled());
    }

    @Test
    public void testAddToQueue() {
        musicPlayerFacade.addToQueue(1);
        assertEquals(1, musicPlayerFacade.getQueue().head.songID);
    }

    @Test
    public void testGetCurrentSong() {
        assertNull(musicPlayerFacade.getCurrentSong());
        musicPlayerFacade.addToQueue(1);
        assertNotNull(musicPlayerFacade.getCurrentSong());
    }

    @Test
    public void testGetPrevSong() {
        assertNull(musicPlayerFacade.getPrevSong());
        musicPlayerFacade.addToQueue(1);
        musicPlayerFacade.addToQueue(2);
        musicPlayerFacade.next();
        assertNotNull(musicPlayerFacade.getPrevSong());
    }

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
            fail("Exception not expected");
        }
    }

    // Add more tests as needed for other methods and edge cases

}
