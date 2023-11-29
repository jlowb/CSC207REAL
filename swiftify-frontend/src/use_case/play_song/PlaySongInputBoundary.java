package use_case.play_song;

import java.io.IOException;

public interface PlaySongInputBoundary {

    void execute(PlaySongInputData playSongInputData) throws IOException, InterruptedException;
}
