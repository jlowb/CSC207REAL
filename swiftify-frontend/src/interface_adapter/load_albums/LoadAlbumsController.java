package interface_adapter.load_albums;

import use_case.load_albums.LoadAlbumsInputBoundary;
import use_case.load_albums.LoadAlbumsInputData;

// controller
public class LoadAlbumsController {
    private final LoadAlbumsInputBoundary loadAlbumsInputBoundary;

    public LoadAlbumsController(LoadAlbumsInputBoundary loadAlbumsInputBoundary) {
        this.loadAlbumsInputBoundary = loadAlbumsInputBoundary;
    }

    public void execute(LoadAlbumsInputData loadAlbumsInputData) {
        loadAlbumsInputBoundary.execute(loadAlbumsInputData);
    }
}