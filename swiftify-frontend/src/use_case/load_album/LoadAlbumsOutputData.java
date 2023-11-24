package use_case.load_album;

import java.util.LinkedList;

public class LoadAlbumsOutputData {
    private LinkedList<String> albums;
    public LinkedList<String> getAlbums() {
        return albums;
    }
    public void setSongs(LinkedList<String> albums) {
        this.albums = albums;
    }
}
