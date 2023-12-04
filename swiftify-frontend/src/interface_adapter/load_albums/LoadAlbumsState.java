package interface_adapter.load_albums;

import entity.Album;
import interface_adapter.State;
import java.util.ArrayList;
import java.util.List;

public class LoadAlbumsState extends State {
    private String selection;
    private List<Album> albums = new ArrayList<Album>();

    public LoadAlbumsState(String selection, List<Album> albums) {
        this.selection = selection;
        this.albums = albums;
    }

    public LoadAlbumsState(){

    }

    public String getSelection() {
        return selection;
    }

    public List<Album> getAlbums() {
        return this.albums;
    }
}
