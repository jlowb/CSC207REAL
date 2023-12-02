package interface_adapter.load_album;

import entity.Album;
import interface_adapter.State;
import java.util.ArrayList;
import java.util.List;

public class LoadAlbumState extends State {
    private String albumType;
    private List<Album> albums = new ArrayList<Album>();

    public LoadAlbumState(String albumType) {
        this.albumType = albumType;
    }

    public LoadAlbumState(){

    }

    public String getAlbumType() {
        return albumType;
    }


    public List<Album> getAlbums() {
        return this.albums;
    }
}
