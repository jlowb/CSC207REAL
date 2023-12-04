package use_case.load_album;

import entity.Album;
import entity.MusicLibrary;

import java.util.ArrayList;
import java.util.List;

public class LoadAlbumInteractor implements LoadAlbumsInputBoundary {

    private final LoadAlbumsOutputBoundary loadAlbumsOutputBoundary;

    public LoadAlbumInteractor(LoadAlbumsOutputBoundary loadAlbumsOutputBoundary) {
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
