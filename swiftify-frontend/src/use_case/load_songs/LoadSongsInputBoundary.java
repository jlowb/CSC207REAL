package use_case.load_songs;

import entity.Album;

public interface LoadSongsInputBoundary {

    public void execute(LoadSongsInputData loadSongsInputData);
    public LoadSongsOutputBoundary getOutputBoundary();
}
