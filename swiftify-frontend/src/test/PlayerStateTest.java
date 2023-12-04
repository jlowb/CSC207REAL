package test;

import entity.PlayerState;
import javazoom.jl.decoder.JavaLayerException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

import static org.junit.jupiter.api.Assertions.*;

public class PlayerStateTest {

    private PlayerState player;

    @BeforeEach
    public void setUp() {
        // Setup method runs before each test
        // Create a sample MP3 input stream for testing
        String sampleMp3Data = "Mock MP3 data";
        InputStream inputStream = new ByteArrayInputStream(sampleMp3Data.getBytes(StandardCharsets.UTF_8));

        try {
            player = new PlayerState(inputStream);
        } catch (JavaLayerException e) {
            throw new RuntimeException("Error initializing PlayerState", e);
        }
    }

    @AfterEach
    public void tearDown() {
        // Teardown method runs after each test
        // Close the player to release resources
        player.close();
    }

    @Test
    public void testPlay() {
        // Test if the player starts playing
        assertFalse(player.isPlaying());  // Player should not be playing initially
        player.play();
        assertTrue(player.isPlaying());   // Player should be playing after calling play
    }

    @Test
    public void testPauseAndResume() {
        // Test if the player can be paused and resumed
        assertFalse(player.isPlaying());  // Player should not be playing initially
        player.play();
        assertTrue(player.isPlaying());   // Player should be playing after calling play

        player.pause();
        assertFalse(player.isPlaying());  // Player should be paused

        player.resume();
        assertTrue(player.isPlaying());   // Player should be resumed
    }

    @Test
    public void testStop() {
        // Test if the player can be stopped
        assertFalse(player.isFinished()); // Player should not be finished initially
        player.play();
        assertTrue(player.isPlaying());    // Player should be playing after calling play

        player.stop();
        assertTrue(player.isFinished());   // Player should be finished after calling stop
    }

    @Test
    public void testIsFinished() {
        // Test if isFinished() returns true after the player finishes
        assertFalse(player.isFinished()); // Player should not be finished initially
        player.play();
        assertFalse(player.isFinished()); // Player should not be finished while playing

        // Wait for a while to simulate the player finishing (in a real scenario, this would depend on the audio duration)
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        assertTrue(player.isFinished());  // Player should be finished after a certain time
    }
}

