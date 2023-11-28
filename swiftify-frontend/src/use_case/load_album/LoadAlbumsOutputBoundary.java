package use_case.load_album;

import java.util.LinkedList;

public interface LoadAlbumsOutputBoundary {
    void presentAlbums(LinkedList<String> albums);
    void prepareSuccessView(LoadAlbumsOutputData loadAlbumsOutputData);

    void displayView(String albumType);

}
