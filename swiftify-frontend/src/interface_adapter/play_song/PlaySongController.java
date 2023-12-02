package interface_adapter.play_song;

import entity.Song;
import javazoom.jl.decoder.JavaLayerException;
import use_case.play_song.SongInputBoundary;
import use_case.play_song.SongInputData;

import java.io.IOException;

public class PlaySongController {
    final SongInputBoundary playSongInteractor;

    public PlaySongController(SongInputBoundary playSongInteractor) {
        this.playSongInteractor = playSongInteractor;
    }

    public void execute(Song song) throws IOException, InterruptedException, JavaLayerException {
        SongInputData songInputData = new SongInputData(song);

        playSongInteractor.execute(songInputData);
    }


}
