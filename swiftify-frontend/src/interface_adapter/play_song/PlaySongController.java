package interface_adapter.play_song;

import javazoom.jl.decoder.JavaLayerException;
import use_case.play_song.PlaySongInputBoundary;
import use_case.play_song.PlaySongInputData;

import java.io.IOException;

public class PlaySongController {
    private final PlaySongInputBoundary songInputBoundary;

    public PlaySongController(PlaySongInputBoundary songInputBoundary) {
        this.songInputBoundary = songInputBoundary;
    }

    public void execute(PlaySongInputData songInputData) throws IOException, InterruptedException, JavaLayerException {
        songInputBoundary.execute(songInputData);
    }
}
