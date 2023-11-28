package interface_adapter.load_songs;

import use_case.load_songs.LoadSongsOutputBoundary;
import use_case.load_songs.LoadSongsOutputData;

public class LoadSongsPresenter implements LoadSongsOutputBoundary {

    public final LoadSongsViewModel loadSongsViewModel;

    public LoadSongsPresenter(LoadSongsViewModel loadSongsViewModel) {
        this.loadSongsViewModel = loadSongsViewModel;
    }

    public void loadSongs(LoadSongsOutputData loadSongsOutputData) {
        LoadSongsState loadSongsState = loadSongsViewModel.getState();
        if (loadSongsState == null) {
            loadSongsViewModel.setState(new LoadSongsState(loadSongsOutputData.getSongs()));
            loadSongsViewModel.firePropertyChanged();
        }
        loadSongsViewModel.setActiveView("loading_album");
        loadSongsViewModel.firePropertyChanged();
    }

    public LoadSongsViewModel getModel() {
        return this.loadSongsViewModel;
    }
}
