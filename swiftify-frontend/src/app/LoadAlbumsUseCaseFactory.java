package app;

import interface_adapter.ViewManagerModel;
import interface_adapter.load_albums.LoadAlbumsController;
import interface_adapter.load_albums.LoadAlbumsPresenter;
import interface_adapter.load_albums.LoadAlbumsViewModel;
import use_case.load_albums.LoadAlbumsInteractor;
import use_case.load_albums.LoadAlbumsInputBoundary;
import use_case.load_albums.LoadAlbumsOutputBoundary;

public class LoadAlbumsUseCaseFactory {

    private LoadAlbumsUseCaseFactory() {

    }

    public static LoadAlbumsController createLoadAlbumsController(ViewManagerModel viewManagerModel) {
        LoadAlbumsOutputBoundary loadAlbumsOutputBoundary = new LoadAlbumsPresenter(new LoadAlbumsViewModel(), viewManagerModel);
        LoadAlbumsInputBoundary loadAlbumsInputBoundary = new LoadAlbumsInteractor(loadAlbumsOutputBoundary);
        return new LoadAlbumsController(loadAlbumsInputBoundary);
    }
}