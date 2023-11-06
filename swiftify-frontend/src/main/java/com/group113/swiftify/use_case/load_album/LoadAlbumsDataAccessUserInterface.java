package main.java.com.group113.swiftify.use_case.load_album;

import java.util.LinkedList;

public interface LoadAlbumsDataAccessUserInterface {
    LinkedList<String> fetchAlbumTitles();

    LinkedList<String> fetchSongsForAlbum(String albumTitle);


}
