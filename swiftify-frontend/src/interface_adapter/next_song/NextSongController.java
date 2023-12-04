package interface_adapter.next_song;

import javazoom.jl.decoder.JavaLayerException;
import use_case.next_song.NextSongInputBoundary;
import use_case.next_song.NextSongInputData;

import java.io.IOException;

public class NextSongController {
    private final NextSongInputBoundary nextSongInputBoundary;

    public NextSongController(NextSongInputBoundary nextSongInputBoundary) {
        this.nextSongInputBoundary = nextSongInputBoundary;
    }

    public void execute(NextSongInputData nextSongInputData) throws IOException, InterruptedException, JavaLayerException {
        this.nextSongInputBoundary.execute(nextSongInputData);
    }
}
