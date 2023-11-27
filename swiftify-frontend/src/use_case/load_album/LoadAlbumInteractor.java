package use_case.load_album;

import entity.Album;

import java.util.List;

public class LoadAlbumInteractor implements LoadAlbumsInputBoundary {

    private final LoadAlbumsDataAccessUserInterface albumDataAccess;

    public LoadAlbumInteractor(LoadAlbumsDataAccessUserInterface albumDataAccess) {
        this.albumDataAccess = albumDataAccess;

    }

    @Override
    public List<Album> execute(LoadAlbumsInputData inputData) {
        String albumType = inputData.getAlbumType();
        return albumDataAccess.getAlbumsByType(albumType);
    }
}
