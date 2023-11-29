package use_case.load_album;

import entity.Album;

import java.util.ArrayList;
import java.util.List;

public class DataAccess implements LoadAlbumsDataAccessUserInterface{
    private List<Album> allAlbums = new ArrayList<>();

    public DataAccess() {

    }
    @Override
    public List<Album> getAlbumsByType(String albumType) {
        List<Album> albumsByType = new ArrayList<>();
        for (Album album : allAlbums) {
            if (albumType.equals(album.getAlbumType())) {
                albumsByType.add(album);
            }
        }
        return albumsByType;
    }
    public LoadAlbumInteractor LoadAlbumInteractor() {
        return new LoadAlbumInteractor(this);
    }


}
