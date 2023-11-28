package data_access;

import entity.Album;
import entity.Song;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class SongLoader {

    public static List<Song> loadSongsFromCSV(String filePath) {
        List<Song> songs = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            boolean isFirstLine = true;

            while ((line = br.readLine()) != null) {
                // Skip the header line
                if (isFirstLine) {
                    isFirstLine = false;
                    continue;
                }

                // Split by comma outside of quotes
                String[] values = line.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1);

                // Remove leading and trailing quotes and trim spaces
                for (int i = 0; i < values.length; i++) {
                    values[i] = values[i].trim().replaceAll("^\"|\"$", "");
                }

                int songID = Integer.parseInt(values[0]);
                String album = values[1];
                int duration = Integer.parseInt(values[2]);
                int releaseYear = Integer.parseInt(values[3]);
                String s3Key = values[4];
                String title = values[5];

                Song song = new Song(songID, album, duration, releaseYear, s3Key, title);
                songs.add(song);
            }
        } catch (IOException e) {
            e.printStackTrace();
            // TODO: Exceptions
        }
        return songs;
    }

    public static List<Album> createAlbumsFromSongs(List<Song> songs) {
        Map<String, List<Song>> albumMap = new HashMap<>();

        // Group songs by album
        for (Song song : songs) {
            albumMap.computeIfAbsent(song.getAlbum(), k -> new ArrayList<>()).add(song);
        }

        // Create albums from the grouped songs
        List<Album> albums = new ArrayList<>();
        for (Map.Entry<String, List<Song>> entry : albumMap.entrySet()) {
            albums.add(new Album(entry.getKey(), entry.getValue()));
        }

        return albums;
    }

    public static void main(String[] args) {
        List<Song> songs = loadSongsFromCSV("swiftify-frontend/MusicMetadataLocal.csv");
        Collections.sort(songs);
        // Print songs for testing
        for (Song song : songs) {
            System.out.println("SongID: " + song.getSongID() + ", Title: " + song.getTitle());
        }

        List<Album> albums = createAlbumsFromSongs(songs);

        // Print albums for testing

        for (Album album : albums) {
            System.out.println("Album: " + album.getName());
            for (Song song : album.getSongs()) {
                System.out.println("  - " + song.getTitle());
            }
        }
    }
}
