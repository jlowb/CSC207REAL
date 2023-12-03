package use_case.load_album;

public class LoadAlbumsInputData {
    private String albumType;

    public LoadAlbumsInputData(String albumType) {
        this.albumType = albumType;
    }

    public String getAlbumType() {
        return albumType;
    }

    public void setAlbumType(String albumType) {
        this.albumType = albumType;
    }
}
