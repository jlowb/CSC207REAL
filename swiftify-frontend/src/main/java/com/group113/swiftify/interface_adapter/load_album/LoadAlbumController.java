package main.java.com.group113.swiftify.interface_adapter.load_album;

import main.java.com.group113.swiftify.use_case.load_album.LoadAlbumsInputBoundary;

// controller
public class LoadAlbumController {
    final LoadAlbumsInputBoundary loadAlbumsInputBoundary;

    public LoadAlbumController(LoadAlbumsInputBoundary loadAlbumsInputBoundary) {
        this.loadAlbumsInputBoundary = loadAlbumsInputBoundary;
    }

    public void execute(String albumName) {
        // Pass the albumName to the LoadAlbumsInputBoundary or perform any necessary logic
        loadAlbumsInputBoundary.execute(albumName);
    }
}