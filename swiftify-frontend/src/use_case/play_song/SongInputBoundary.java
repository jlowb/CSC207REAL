package use_case.play_song;

import javazoom.jl.decoder.JavaLayerException;

import java.io.IOException;

public interface SongInputBoundary {

    void execute(SongInputData songInputData) throws IOException, InterruptedException, JavaLayerException;
}
