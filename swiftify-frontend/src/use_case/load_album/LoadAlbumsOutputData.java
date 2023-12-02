package use_case.load_album;

public class LoadAlbumsOutputData {

    private final String albumType;
    private final Object view;
    private boolean useCaseFailed;

    public LoadAlbumsOutputData(String albumType, Object view, boolean useCaseFailed) {
        this.albumType = albumType;
        this.useCaseFailed = useCaseFailed;
        this.view = view;
    }

  ;
    public String getAlbumType() {
        return albumType;
    }

}
