package use_case.load_album;

public class LoadAlbumsInputData {
    private String albumName;

    public LoadAlbumsInputData(String albumName) {
        this.albumName = albumName;
    }

    public String getAlbumName() {
        return albumName;
    }

    public void setAlbumName(String albumName) {
        this.albumName = albumName;
    }
}
