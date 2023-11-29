package src.data_access;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import org.json.JSONObject;
public class URLSongLoader {

    public static String readJsonFromUrl(String url) throws IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        return response.body();

    }

    public static String fetchPresignedURL(int songID) throws IOException, InterruptedException {
        String URL = "https://2o3qto08gi.execute-api.us-east-1.amazonaws.com/prod/songs/" + songID;
        try {
            String jsonString = readJsonFromUrl(URL);

            JSONObject jsonObject = new JSONObject(jsonString);
            String presignedUrl = jsonObject.getString("presignedUrl");

            return presignedUrl;
        }
        catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }

    //Test to verify that it returns the URL.
    public static void main(String[] args) throws IOException, InterruptedException {
        int song = 100;
        System.out.println(fetchPresignedURL(song));
    }
}

