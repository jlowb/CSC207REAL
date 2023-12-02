package interface_adapter.load_album;

import use_case.load_album.LoadAlbumsInputBoundary;
import use_case.load_album.LoadAlbumsInputData;

// controller
public class LoadAlbumController {
    final LoadAlbumsInputBoundary loadAlbumsInputBoundary;

    public LoadAlbumController(LoadAlbumsInputBoundary loadAlbumsInputBoundary) {
        this.loadAlbumsInputBoundary = loadAlbumsInputBoundary;
    }

    public void execute(LoadAlbumsInputData loadAlbumsInputData) {
        loadAlbumsInputBoundary.execute(loadAlbumsInputData);
    }
}