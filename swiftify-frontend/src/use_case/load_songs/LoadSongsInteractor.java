package use_case.load_songs;

import data_access.MusicLibrary;
import entity.Song;

import java.util.ArrayList;
import java.util.List;

public class LoadSongsInteractor implements LoadSongsInputBoundary {

    private final LoadSongsOutputBoundary loadSongsOutputBoundary;

    public LoadSongsInteractor(LoadSongsOutputBoundary loadSongsOutputBoundary) {
        this.loadSongsOutputBoundary = loadSongsOutputBoundary;
    }

    @Override
    public void execute(LoadSongsInputData loadSongsInputData) {
        List<Song> songList = new ArrayList<Song>();
        for (Song song : MusicLibrary.getInstance().getSongs()) {
            if (song.getAlbum().equals(loadSongsInputData.getAlbumName())) {
                songList.add(song);
            }
        }
        LoadSongsOutputData loadSongsOutputData = new LoadSongsOutputData(loadSongsInputData.getAlbumName(), songList, false);
        loadSongsOutputBoundary.loadSongs(loadSongsOutputData);
    }

    public LoadSongsOutputBoundary getOutputBoundary() {
        return this.loadSongsOutputBoundary;
    }
}
