package interface_adapter.resume_song;

import javazoom.jl.decoder.JavaLayerException;
import use_case.pause_song.PauseSongInputBoundary;
import use_case.pause_song.PauseSongInputData;
import use_case.resume_song.ResumeSongInputBoundary;
import use_case.resume_song.ResumeSongInputData;

import java.io.IOException;

public class ResumeSongController {

    private final ResumeSongInputBoundary resumeSongInputBoundary;

    public ResumeSongController(ResumeSongInputBoundary resumeSongInputBoundary) {
        this.resumeSongInputBoundary = resumeSongInputBoundary;
    }

    public void execute(ResumeSongInputData resumeSongInputData) throws IOException, InterruptedException, JavaLayerException {
        resumeSongInputBoundary.execute(resumeSongInputData);
    }
}
