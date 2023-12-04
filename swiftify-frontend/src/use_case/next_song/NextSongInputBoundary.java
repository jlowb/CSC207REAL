package use_case.next_song;

import javazoom.jl.decoder.JavaLayerException;

import java.io.IOException;

public interface NextSongInputBoundary {
    void execute(NextSongInputData nextSongInputData) throws IOException, InterruptedException, JavaLayerException;
}
