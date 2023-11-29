package use_case.load_songs;

import data_access.SongLoader;
import entity.Album;
import entity.Song;

import java.sql.Array;
import java.util.ArrayList;
import java.util.List;

public class LoadSongsInteractor implements LoadSongsInputBoundary {

    public final SongLoader songDataAccessObject;
    public final LoadSongsOutputBoundary loadSongsOutputBoundary;

    public LoadSongsInteractor(SongLoader songDataAccessObject, LoadSongsOutputBoundary loadSongsOutputBoundary) {
        this.songDataAccessObject = songDataAccessObject;
        this.loadSongsOutputBoundary = loadSongsOutputBoundary;
    }

    @Override
    public void execute(LoadSongsInputData loadSongsInputData) {
        List<Song> songList = new ArrayList<Song>();
        songList.add(new Song(1, "test", 1, 1, "test", "testSong1"));
        songList.add(new Song(2, "test", 2, 2, "test", "testSong2"));
        songList.add(new Song(3, "test", 3, 3, "test", "testSong3"));
        LoadSongsOutputData loadSongsOutputData = new LoadSongsOutputData(songList, false);
        loadSongsOutputBoundary.loadSongs(loadSongsOutputData);
    }

    public LoadSongsOutputBoundary getOutputBoundary() {
        return this.loadSongsOutputBoundary;
    }
}
