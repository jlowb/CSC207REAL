package src.data_access;

import src.use_case.display_albums.DisplayAlbumsDataAccessInterface;

import java.util.ArrayList;
import java.util.List;

public class AlbumDataAccessObject implements DisplayAlbumsDataAccessInterface {

    public AlbumDataAccessObject() {

    }

    public List<String> getAlbumNames() {
        // data access object method to get list of album names
        return new ArrayList<>();
    }
}
