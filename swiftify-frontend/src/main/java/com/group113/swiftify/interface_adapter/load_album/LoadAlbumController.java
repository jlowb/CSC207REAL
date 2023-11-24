package main.java.com.group113.swiftify.interface_adapter.load_album;

import main.java.com.group113.swiftify.use_case.load_album.LoadAlbumsInputBoundary;
import main.java.com.group113.swiftify.use_case.load_album.LoadAlbumsInputData;

// controller
public class LoadAlbumController {
    final LoadAlbumsInputBoundary loadAlbumsInputBoundary;

    public LoadAlbumController(LoadAlbumsInputBoundary loadAlbumsInputBoundary) {
        this.loadAlbumsInputBoundary = loadAlbumsInputBoundary;
    }

    public void execute(String albumName) {
        // Pass the albumName to the LoadAlbumsInputBoundary or perform any necessary logic
        LoadAlbumsInputData inputData = new LoadAlbumsInputData(albumName);
        loadAlbumsInputBoundary.execute(inputData);
    }
}