package use_case.load_album;

import entity.Album;

import java.util.List;

public interface LoadAlbumsInputBoundary {
    List<Album> execute(LoadAlbumsInputData inputData);


}
