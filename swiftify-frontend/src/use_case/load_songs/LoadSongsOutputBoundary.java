package use_case.load_songs;

import interface_adapter.load_songs.LoadSongsViewModel;

public interface LoadSongsOutputBoundary {

    public void loadSongs(LoadSongsOutputData loadSongsOutputData);
    public LoadSongsViewModel getModel();
}
