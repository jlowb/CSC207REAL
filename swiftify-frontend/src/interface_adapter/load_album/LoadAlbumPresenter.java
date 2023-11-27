package interface_adapter.load_album;


import use_case.load_album.LoadAlbumsDataAccessUserInterface;
import use_case.load_album.LoadAlbumsOutputBoundary;
import use_case.load_album.LoadAlbumsOutputData;
import view.DeluxeVersion;
import view.LoadAlbumView1;
import view.TaylorVersion;
import view.Vieww;

import java.util.LinkedList;

public class LoadAlbumPresenter implements LoadAlbumsOutputBoundary {
    private final LoadAlbumView1 loadAlbumView;
    private final Vieww view;
    private ViewManagerModel viewManagerModel;
    private LoadAlbumsDataAccessUserInterface dataAccessUserInterface;


    public LoadAlbumPresenter(Vieww view, LoadAlbumView1 loadAlbumView, ViewManagerModel viewManagerModel) {
        this.loadAlbumView = loadAlbumView;
        this.viewManagerModel = viewManagerModel;
        this.dataAccessUserInterface = dataAccessUserInterface;
        this.view = view;
    }


    public void presentAlbums(LinkedList<String> songs) {
        // update viewmodel / gui form introduce the second gui

    }

    public void displayView(String AlbumType) {
        if ("All".equals(AlbumType)) {
            LoadAlbumView1 page = new LoadAlbumView1();
            page.setVisible(true);
        } else if ("Deluxe / Deluxe Edition".equals(AlbumType)) {
            DeluxeVersion page = new DeluxeVersion();
            page.setVisible(true);
        } else if ("Taylor's Version".equals(AlbumType)) {
            TaylorVersion page = new TaylorVersion();
            page.setVisible(true);
        }
    }
    public void prepareSuccessView(LoadAlbumsOutputData loadAlbumsOutputData) {
        LinkedList<String> albums = loadAlbumsOutputData.getAlbums();

        loadAlbumView.updateAlbums(albums);
    }

    public void loadAlbums(String album) {


    }
}