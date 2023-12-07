package use_case.load_albums;

import entity.Album;
import data_access.MusicLibrary;

import java.util.ArrayList;
import java.util.List;

public class LoadAlbumsInteractor implements LoadAlbumsInputBoundary {

    private final LoadAlbumsOutputBoundary loadAlbumsOutputBoundary;

    public LoadAlbumsInteractor(LoadAlbumsOutputBoundary loadAlbumsOutputBoundary) {
        this.loadAlbumsOutputBoundary = loadAlbumsOutputBoundary;
    }

    public void execute(LoadAlbumsInputData loadAlbumsInputData) {
        List<Album> albumList = new ArrayList<Album>();
        for (Album album : MusicLibrary.getInstance().getAlbums()) {
            albumList.add(album);
        }
        LoadAlbumsOutputData loadAlbumsOutputData = new LoadAlbumsOutputData(loadAlbumsInputData.getSelection(), albumList);
        loadAlbumsOutputBoundary.loadAlbums(loadAlbumsOutputData);
    }
}
