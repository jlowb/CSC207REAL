package use_case.pause_song;

import data_access.URLSongLoader;
import entity.PlayerState;
import use_case.play_song.PlaySongInputBoundary;
import use_case.play_song.PlaySongInputData;

import java.io.IOException;

public class PauseSongInteractor implements PauseSongInputBoundary {

    private final PauseSongOutputBoundary pauseSongOutputBoundary;

    public PauseSongInteractor(PauseSongOutputBoundary pauseSongOutputBoundary) {
        this.pauseSongOutputBoundary = pauseSongOutputBoundary;
    }

    public void execute(PauseSongInputData pauseSongInputData) throws IOException, InterruptedException {
        pauseSongInputData.getState().getMusicPlayer().pause();
        pauseSongInputData.getState().setPlaying(false);
        this.pauseSongOutputBoundary.preparePausedView(new PauseSongOutputData(pauseSongInputData.getState(), pauseSongInputData.getView()));
    }
}
