package use_case.load_songs;

import entity.Album;

public class LoadSongsInputData {

    private final String albumName;

    public LoadSongsInputData(String albumName) {
        this.albumName = albumName;
    }

    public String getAlbumName() {
        return this.albumName;
    }
}
