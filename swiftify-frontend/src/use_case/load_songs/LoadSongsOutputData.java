package use_case.load_songs;

import entity.Song;

import java.util.List;

public class LoadSongsOutputData {

    private final String albumName;
    private final List<Song> songs;
    private boolean useCaseFailed;

    public LoadSongsOutputData(String albumName, List<Song> songs, boolean useCaseFailed) {
        this.albumName = albumName;
        this.songs = songs;
        this.useCaseFailed = useCaseFailed;
    }

    public String getAlbumName() { return this.albumName; }

    public List<Song> getSongs() { return this.songs; }
}
