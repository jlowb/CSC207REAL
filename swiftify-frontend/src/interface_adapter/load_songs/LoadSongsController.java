package interface_adapter.load_songs;

import entity.Album;
import use_case.load_songs.LoadSongsInputBoundary;
import use_case.load_songs.LoadSongsInputData;

public class LoadSongsController {

    public final LoadSongsInputBoundary loadSongsInputBoundary;

    public LoadSongsController(LoadSongsInputBoundary loadSongsInputBoundary) {
        this.loadSongsInputBoundary = loadSongsInputBoundary;
    }

    public void execute(LoadSongsInputData loadSongsInputData) {
        loadSongsInputBoundary.execute(loadSongsInputData);
    }
}
