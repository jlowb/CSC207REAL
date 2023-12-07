package use_case.load_albums;
import entity.Album;
import java.util.List;

public class LoadAlbumsOutputData {

    private final String selection;
    private final List<Album> albums;

    public LoadAlbumsOutputData(String selection, List<Album> albums) {
        this.selection = selection;
        this.albums = albums;
    }

    public String getSelection() {
        return this.selection;
    }

    public List<Album> getAlbums() {
        return this.albums;
    }
}
