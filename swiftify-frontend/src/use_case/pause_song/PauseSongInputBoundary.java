package use_case.pause_song;

import java.io.IOException;

public interface PauseSongInputBoundary {

    void execute(PauseSongInputData pauseSongInputData) throws IOException, InterruptedException;
}
