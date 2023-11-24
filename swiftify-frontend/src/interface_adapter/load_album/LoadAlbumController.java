package interface_adapter.load_album;

import use_case.load_album.LoadAlbumsInputBoundary;
import use_case.load_album.LoadAlbumsInputData;

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