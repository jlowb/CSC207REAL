package interface_adapter.load_songs;

import use_case.load_songs.LoadSongsInputBoundary;

public class LoadSongsController {

    final LoadSongsInputBoundary loadSongsInputBoundary;

    public LoadSongsController(LoadSongsInputBoundary loadSongsInputBoundary) {
        this.loadSongsInputBoundary = loadSongsInputBoundary;
    }

    public void execute() {
        loadSongsInputBoundary.execute();
    }
}
