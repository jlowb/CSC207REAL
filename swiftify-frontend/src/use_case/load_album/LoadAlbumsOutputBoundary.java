package use_case.load_album;

import interface_adapter.load_album.LoadAlbumViewModel;

import java.util.LinkedList;

public interface LoadAlbumsOutputBoundary {

   void loadAlbums(LoadAlbumsOutputData loadAlbumsOutputData);
}
