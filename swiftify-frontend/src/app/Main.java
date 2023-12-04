package app;

import interface_adapter.ViewManagerModel;
import interface_adapter.load_album.LoadAlbumViewModel;
import view.MainView;
import view.ViewManager;

public class Main {

    public static void main(String[] args) {
        ViewManagerModel viewManagerModel = new ViewManagerModel();
        new ViewManager(viewManagerModel);

        MainView mainView = new MainView(LoadAlbumsUseCaseFactory.createLoadAlbumsController(viewManagerModel));
        mainView.setVisible(true);
    }
}