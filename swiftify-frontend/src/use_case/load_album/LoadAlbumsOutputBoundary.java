package use_case.load_album;

import interface_adapter.load_album.LoadAlbumViewModel;

import java.util.LinkedList;

public interface LoadAlbumsOutputBoundary {
    void presentAlbums(LinkedList<String> albums);
    public void loadAlbums(LoadAlbumsOutputData loadAlbumsOutputData);
    public LoadAlbumViewModel getModel();
    void prepareSuccessView(LoadAlbumsOutputData loadAlbumsOutputData);

    void displayView(String albumType);

}
