package use_case.resume_song;

import use_case.pause_song.PauseSongInputData;
import use_case.pause_song.PauseSongOutputBoundary;
import use_case.pause_song.PauseSongOutputData;

import java.io.IOException;

public class ResumeSongInteractor implements ResumeSongInputBoundary {

    private final ResumeSongOutputBoundary resumeSongOutputBoundary;

    public ResumeSongInteractor(ResumeSongOutputBoundary resumeSongOutputBoundary) {
        this.resumeSongOutputBoundary = resumeSongOutputBoundary;
    }

    public void execute(ResumeSongInputData resumeSongInputData) throws IOException, InterruptedException {
        resumeSongInputData.getState().getMusicPlayer().resume();
        resumeSongInputData.getState().setPlaying(true);
        this.resumeSongOutputBoundary.prepareResumedView(new ResumeSongOutputData(resumeSongInputData.getState(), resumeSongInputData.getView()));
    }
}
