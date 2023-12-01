package interface_adapter.pause_song;

import entity.Song;
import javazoom.jl.decoder.JavaLayerException;
import use_case.play_song.PlaySongInputBoundary;
import use_case.play_song.PlaySongInputData;

import java.io.IOException;

public class PauseSongController {
    final PlaySongInputBoundary pauseSongInteractor;

    public PauseSongController(PlaySongInputBoundary pauseSongInteractor) {
        this.pauseSongInteractor = pauseSongInteractor;
    }

    public void execute(Song song) throws IOException, InterruptedException, JavaLayerException {
        //PlaySongInputData songInputData = new PlaySongInputData(song.getSongID());

        //pauseSongInteractor.execute(songInputData);
    }


}
