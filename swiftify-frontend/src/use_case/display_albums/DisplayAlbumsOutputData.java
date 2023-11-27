package src.use_case.display_albums;

import java.util.List;

public class DisplayAlbumsOutputData {

    private final List<String> albumNames;
    private boolean useCaseFailed;

    public DisplayAlbumsOutputData(List<String> albumNames, boolean useCaseFailed) {
        this.albumNames = albumNames;
        this.useCaseFailed = useCaseFailed;
    }

    public List<String> getAlbumNames() { return albumNames; }
}
