package app;

import interface_adapter.ViewManagerModel;
import interface_adapter.load_album.LoadAlbumController;
import interface_adapter.load_album.LoadAlbumPresenter;
import interface_adapter.load_album.LoadAlbumViewModel;
import use_case.load_album.LoadAlbumInteractor;
import use_case.load_album.LoadAlbumsInputBoundary;
import use_case.load_album.LoadAlbumsOutputBoundary;

public class LoadAlbumsUseCaseFactory {

    private LoadAlbumsUseCaseFactory() {

    }

    public static LoadAlbumController createLoadAlbumsController(ViewManagerModel viewManagerModel) {
        LoadAlbumsOutputBoundary loadAlbumsOutputBoundary = new LoadAlbumPresenter(new LoadAlbumViewModel(), viewManagerModel);
        LoadAlbumsInputBoundary loadAlbumsInputBoundary = new LoadAlbumInteractor(loadAlbumsOutputBoundary);
        return new LoadAlbumController(loadAlbumsInputBoundary);
    }
}
