package interface_adapter.pause_song;

import entity.Song;
import javazoom.jl.decoder.JavaLayerException;
import use_case.pause_song.PauseSongInputBoundary;
import use_case.pause_song.PauseSongInputData;
import use_case.play_song.PlaySongInputBoundary;

import java.io.IOException;

public class PauseSongController {
    private final PauseSongInputBoundary pauseSongInputBoundary;

    public PauseSongController(PauseSongInputBoundary pauseSongInputBoundary) {
        this.pauseSongInputBoundary = pauseSongInputBoundary;
    }

    public void execute(PauseSongInputData pauseSongInputData) throws IOException, InterruptedException, JavaLayerException {
        pauseSongInputBoundary.execute(pauseSongInputData);
    }
}
