package use_case.load_album;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class LoadAlbumInteractor implements LoadAlbumsInputBoundary {

    public LoadAlbumInteractor() {

    }


    public List<String> execute(String albumName) { // write out
        return getSongsFromCSV(albumName);

        }


    private List<String> getSongsFromCSV(String albumName) {
        List<String> songs = new ArrayList<>();

        String csvFile = "/Users/malaikamalik/Desktop/malik130/CSC207REAL/swiftify-frontend/src/MusicMetadataLocal.csv";

        try (Scanner scanner = new Scanner(new File(csvFile))) {
            scanner.nextLine();

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] songData = line.split(",");


                if (songData[1].equalsIgnoreCase(albumName)) {
                    songs.add(songData[5]);
                }
                System.out.println(songData[1].equalsIgnoreCase(albumName));
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        // Add debug information
        System.out.println("Album name: " + albumName);
        System.out.println("Songs found: " + songs.size());

        return songs;

    }
    public static void main(String[] args) {
        LoadAlbumInteractor interactor = new LoadAlbumInteractor();

        String albumName = "Lover";
        List<String> songs = interactor.execute(albumName);


        System.out.println("Songs for album '" + albumName + "':");
        for (String song : songs) {
            System.out.println(song);
        }


    }


    @Override
    public void execute(LoadAlbumsInputData inputData) {

    }
}
