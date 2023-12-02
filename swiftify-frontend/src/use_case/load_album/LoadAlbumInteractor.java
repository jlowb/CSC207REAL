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
        String albumType = loadAlbumsInputData.getAlbumType();
        List<Album> AlbumList = new ArrayList<Album>();
        for (Album album: MusicLibrary.getInstance().getAlbums()) {
            if (album.getAlbumType().equals(loadAlbumsInputData.getAlbumType())) {
                AlbumList.add(album);
            }
        }

        LoadAlbumsOutputData loadAlbumsOutputData = new LoadAlbumsOutputData(loadAlbumsInputData.getAlbumType(), AlbumList, false);
        loadAlbumsOutputBoundary.loadAlbums(loadAlbumsOutputData);

    }

    @Override
    public LoadAlbumsOutputBoundary getOutputBoundary() {
        return this.loadAlbumsOutputBoundary;
    }


}
