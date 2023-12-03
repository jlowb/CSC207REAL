package interface_adapter.prev_song;

import javazoom.jl.decoder.JavaLayerException;
import use_case.prev_song.PrevSongInputBoundary;
import use_case.prev_song.PrevSongInputData;

import java.io.IOException;

public class PrevSongController {
    private final PrevSongInputBoundary prevSongInputBoundary;

    public PrevSongController(PrevSongInputBoundary prevSongInputBoundary) {
        this.prevSongInputBoundary = prevSongInputBoundary;
    }

    public void execute(PrevSongInputData prevSongInputData) throws IOException, InterruptedException, JavaLayerException {
        this.prevSongInputBoundary.execute(prevSongInputData);
    }
}
