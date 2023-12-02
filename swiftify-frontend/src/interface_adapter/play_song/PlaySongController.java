package interface_adapter.play_song;

import javazoom.jl.decoder.JavaLayerException;
import use_case.play_song.PlaySongInputBoundary;
import use_case.play_song.PlaySongInputData;

import java.io.IOException;

public class PlaySongController {
    private final PlaySongInputBoundary playSongInputBoundary;

    public PlaySongController(PlaySongInputBoundary songInputBoundary) {
        this.playSongInputBoundary = songInputBoundary;
    }

    public void execute(PlaySongInputData playSongInputData) throws IOException, InterruptedException, JavaLayerException {
        playSongInputBoundary.execute(playSongInputData);
    }
}
