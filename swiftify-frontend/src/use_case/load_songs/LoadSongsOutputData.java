package use_case.load_songs;

import java.util.List;

public class LoadSongsOutputData {

    private final List<String> albumNames;
    private boolean useCaseFailed;

    public LoadSongsOutputData(List<String> albumNames, boolean useCaseFailed) {
        this.albumNames = albumNames;
        this.useCaseFailed = useCaseFailed;
    }

    public List<String> getAlbumNames() { return albumNames; }
}
