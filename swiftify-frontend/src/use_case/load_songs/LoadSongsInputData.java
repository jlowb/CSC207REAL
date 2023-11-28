package use_case.load_songs;

import entity.Album;

public class LoadSongsInputData {

    private final Album album;

    public LoadSongsInputData(Album album) {
        this.album = album;
    }

    public Album getAlbum() {
        return this.album;
    }
}
