package data_access;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import entity.Song;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class SongBuilder {

    public static List<Song> fetchSongs() {
        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            String url = "https://2o3qto08gi.execute-api.us-east-1.amazonaws.com/prod/MusicMetadataHandler";
            HttpGet request = new HttpGet(url);
            HttpResponse response = httpClient.execute(request);
            String json = EntityUtils.toString(response.getEntity());

            ObjectMapper objectMapper = new ObjectMapper();
            List<Song> songs = objectMapper.readValue(json, new TypeReference<List<Song>>() {});
            Collections.sort(songs);
            return songs;

        } catch (IOException e) {
            return fetchSongsBackup("src/MusicMetadata2.csv");
        }
    }

    public static List<Song> fetchSongsBackup(String filePath) {
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
                String title = values[1];
                String album = values[2];
                int duration = Integer.parseInt(values[3]);
                int releaseYear = Integer.parseInt(values[4]);
                String s3Key = values[5];

                Song song = new Song(songID, album, duration, releaseYear, s3Key, title);
                songs.add(song);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        Collections.sort(songs);
        return songs;
    }
}
