package use_case.load_songs;

import entity.Song;

import java.util.List;

public class LoadSongsOutputData {

    private final List<Song> songs;
    private boolean useCaseFailed;

    public LoadSongsOutputData(List<Song> songs, boolean useCaseFailed) {
        this.songs = songs;
        this.useCaseFailed = useCaseFailed;
    }

    public List<Song> getSongs() { return this.songs; }
}
