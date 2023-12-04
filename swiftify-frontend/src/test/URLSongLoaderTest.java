package test;

import data_access.URLSongLoader;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

public class URLSongLoaderTest {

    @Test
    public void testFetchPresignedURL() {
        // Provide a valid song ID for testing
        int validSongID = 120;

        try {
            String presignedURL = URLSongLoader.fetchPresignedURL(validSongID);

            assertNotNull(presignedURL);
            assertTrue(presignedURL.startsWith("https://"));

            // You may add more specific checks based on the expected structure or content of the URL
        } catch (IOException | InterruptedException e) {
            fail("Exception thrown while fetching presigned URL: " + e.getMessage());
        }
    }
}

