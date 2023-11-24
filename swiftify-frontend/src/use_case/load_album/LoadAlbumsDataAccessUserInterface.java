package use_case.load_album;

import java.util.LinkedList;

public interface LoadAlbumsDataAccessUserInterface {
    LinkedList<String> fetchAlbumTitles();

    LinkedList<String> handleRequest(String album_name);

    //get album names , and build the view


}
