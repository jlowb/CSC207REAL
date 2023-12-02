package app;

import interface_adapter.ViewManagerModel;
import interface_adapter.load_album.LoadAlbumViewModel;
import view.ViewManager;

public class Main {

    public static void main(String[] args) {
        ViewManagerModel viewManagerModel = new ViewManagerModel();
        new ViewManager(viewManagerModel);

        LoadAlbumViewModel loadAlbumViewModel = new LoadAlbumViewModel();
        viewManagerModel.setViewModel(loadAlbumViewModel);
        viewManagerModel.firePropertyChanged();
    }
}
