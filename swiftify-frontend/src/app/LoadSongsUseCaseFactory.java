package app;

import interface_adapter.ViewManagerModel;
import interface_adapter.load_songs.LoadSongsController;
import interface_adapter.load_songs.LoadSongsPresenter;
import interface_adapter.load_songs.LoadSongsViewModel;
import use_case.load_songs.LoadSongsInputBoundary;
import use_case.load_songs.LoadSongsInteractor;
import use_case.load_songs.LoadSongsOutputBoundary;

public class LoadSongsUseCaseFactory {

    private LoadSongsUseCaseFactory() {

    }

    public static LoadSongsController createLoadSongsController(ViewManagerModel viewManagerModel) {
        LoadSongsOutputBoundary loadSongsOutputBoundary = new LoadSongsPresenter(new LoadSongsViewModel(), viewManagerModel);
        LoadSongsInputBoundary loadSongsInputBoundary = new LoadSongsInteractor(loadSongsOutputBoundary);
        return new LoadSongsController(loadSongsInputBoundary);
    }
}
