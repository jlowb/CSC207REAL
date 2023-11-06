package main.java.com.group113.swiftify.interface_adapter.load_album;

import main.java.com.group113.swiftify.use_case.load_album.LoadAlbumsDataAccessUserInterface;
import main.java.com.group113.swiftify.use_case.load_album.LoadAlbumsOutputBoundary;
import main.java.com.group113.swiftify.use_case.load_album.LoadAlbumsOutputData;
import main.java.com.group113.swiftify.view.LoadAlbumView;

import java.util.LinkedList;

public class LoadAlbumPresenter extends LoadAlbumsOutputBoundary {
    private final LoadAlbumView loadAlbumView;
    private ViewManagerModel viewManagerModel;
    private LoadAlbumsDataAccessUserInterface dataAccessUserInterface;



    public LoadAlbumPresenter(LoadAlbumView loadAlbumView, ViewManagerModel viewManagerModel) {
        this.loadAlbumView = loadAlbumView;
        this.viewManagerModel = viewManagerModel;
        this.dataAccessUserInterface = dataAccessUserInterface;
    }


    public void presentAlbums(LinkedList<String> albums) {

    }


    public void presentSongs(LinkedList<String> songs) {

    }

    public void prepareSuccessView(LoadAlbumsOutputData loadAlbumsOutputData) {
        LinkedList<String> albums = loadAlbumsOutputData.getAlbums();

        loadAlbumView.updateAlbums(albums);
    }

    public void loadSongsForAlbum(String albumTitle) {
        LinkedList<String> songs = dataAccessUserInterface.fetchSongsForAlbum(albumTitle);

    }

}
