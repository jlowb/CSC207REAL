import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

public class APICallExample {
    public static void main(String[] args) {
        try {
            // Define the API URL
            String apiUrl = "https://api.musixmatch.com/ws/1.1/track.get?commontrack_id=5920049&apikey=6afcfd9ece2e3f72d18da1ad8a04c14f"; // Replace with the actual API URL
            //String urURLSTr = ""
            URL url = new URL(apiUrl);

            // Open a connection
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            // Set the request method and headers
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Authorization", "Bearer 6afcfd9ece2e3f72d18da1ad8a04c14f"); // Replace with your API key if required

            // Handle the response
            int responseCode = connection.getResponseCode();

            if (responseCode == HttpURLConnection.HTTP_OK) {
                BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String inputLine;
                StringBuilder response = new StringBuilder();

                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();

                // Process the response data here (e.g., parse JSON)
                String responseData = response.toString();
                System.out.println("API Response: " + responseData);
            } else {
                // Handle error responses
                System.err.println("API request failed with response code: " + responseCode);
            }

            // Close the connection
            connection.disconnect();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
