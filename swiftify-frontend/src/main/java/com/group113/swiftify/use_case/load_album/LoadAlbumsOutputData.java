package main.java.com.group113.swiftify.use_case.load_album;

import java.util.LinkedList;

public class LoadAlbumsOutputData {
    private LinkedList<String> albums;
    public LinkedList<String> getAlbums() {
        return albums;
    }
    public void setAlbums(LinkedList<String> albums) {
        this.albums = albums;
    }
}
