package interface_adapter.load_songs;

import interface_adapter.ViewManagerModel;
import use_case.load_songs.LoadSongsOutputBoundary;
import use_case.load_songs.LoadSongsOutputData;

public class LoadSongsPresenter implements LoadSongsOutputBoundary {

    private final LoadSongsViewModel loadSongsViewModel;
    private final ViewManagerModel viewManagerModel;

    public LoadSongsPresenter(LoadSongsViewModel loadSongsViewModel, ViewManagerModel viewManagerModel) {
        this.loadSongsViewModel = loadSongsViewModel;
        this.viewManagerModel = viewManagerModel;
    }

    public void loadSongs(LoadSongsOutputData loadSongsOutputData) {
        LoadSongsState loadSongsState = loadSongsViewModel.getState();
        loadSongsViewModel.setState(new LoadSongsState(loadSongsOutputData.getAlbumName(), loadSongsOutputData.getSongs()));
        loadSongsViewModel.firePropertyChanged();

        viewManagerModel.setActiveView(loadSongsViewModel.getViewName());
        viewManagerModel.setViewModel(this.loadSongsViewModel);
        viewManagerModel.firePropertyChanged();
    }

    public LoadSongsViewModel getModel() {
        return this.loadSongsViewModel;
    }
}
