package com.group113.swiftify.data_access;
import com.group113.swiftify.entity.Song;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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

    public static void main(String[] args) {
        List<Song> songs = loadSongsFromCSV("swiftify-frontend/src/MusicMetadataLocal.csv");
        System.out.println(songs);
    }
}
