package test;

import entity.Album;
import entity.AlbumBuilder;
import entity.Song;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AlbumBuilderTest {

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
        assertEquals(2, albums.size());  // Expecting 2 albums

        // Test album names and song counts
        assertEquals("Red (Taylor's Version)", albums.get(0).getName());
        assertEquals(2, albums.get(0).getSongs().size());

        assertEquals("Speak Now (Deluxe Edition)", albums.get(1).getName());
        assertEquals(1, albums.get(1).getSongs().size());
    }
}
