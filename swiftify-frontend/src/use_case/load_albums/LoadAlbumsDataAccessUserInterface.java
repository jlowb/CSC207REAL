package use_case.load_albums;

import entity.Album;

import java.util.List;

public interface LoadAlbumsDataAccessUserInterface {


    List<Album> getAlbumsByType(String albumType);

    //get album names , and build the view


}
