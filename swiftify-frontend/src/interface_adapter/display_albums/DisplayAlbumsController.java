package src.interface_adapter.display_albums;

import src.use_case.display_albums.DisplayAlbumsInputBoundary;

public class DisplayAlbumsController {

    final DisplayAlbumsInputBoundary displayAlbumsInputBoundary;

    public DisplayAlbumsController(DisplayAlbumsInputBoundary displayAlbumsInputBoundary) {
        this.displayAlbumsInputBoundary = displayAlbumsInputBoundary;
    }

    public void execute() {
        displayAlbumsInputBoundary.execute();
    }
}
