package interface_adapter.load_album;

import entity.Album;
import interface_adapter.State;
import java.util.ArrayList;
import java.util.List;

public class LoadAlbumState extends State {
    private String selection;
    private List<Album> albums = new ArrayList<Album>();

    public LoadAlbumState(String selection, List<Album> albums) {
        this.selection = selection;
        this.albums = albums;
    }

    public LoadAlbumState(){

    }

    public String getSelection() {
        return selection;
    }


    public List<Album> getAlbums() {
        return this.albums;
    }
}
