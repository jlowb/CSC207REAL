package use_case.prev_song;

import javazoom.jl.decoder.JavaLayerException;

import java.io.IOException;

public interface PrevSongInputBoundary {
    void execute(PrevSongInputData prevSongInputData) throws IOException, InterruptedException, JavaLayerException;
}
