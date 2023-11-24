package interface_adapter.load_album;


import use_case.load_album.LoadAlbumsDataAccessUserInterface;
import use_case.load_album.LoadAlbumsOutputBoundary;
import use_case.load_album.LoadAlbumsOutputData;
import view.LoadAlbumView1;

import java.util.LinkedList;

public class LoadAlbumPresenter implements LoadAlbumsOutputBoundary {
    private final LoadAlbumView1 loadAlbumView;
    private ViewManagerModel viewManagerModel;
    private LoadAlbumsDataAccessUserInterface dataAccessUserInterface;


    public LoadAlbumPresenter(LoadAlbumView1 loadAlbumView, ViewManagerModel viewManagerModel) {
        this.loadAlbumView = loadAlbumView;
        this.viewManagerModel = viewManagerModel;
        this.dataAccessUserInterface = dataAccessUserInterface;
    }


    public void presentSongs(LinkedList<String> songs) {
        // update viewmodel / gui form introduce the second gui

    }

    public void prepareSuccessView(LoadAlbumsOutputData loadAlbumsOutputData) {
        LinkedList<String> songs = loadAlbumsOutputData.getAlbums();

        loadAlbumView.updateAlbums(songs);
    }

    public void loadSongsForAlbum(String albumTitle) {
        LinkedList<String> songs = dataAccessUserInterface.handleRequest(albumTitle);

    }
}