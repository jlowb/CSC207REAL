package use_case.pause_song;

import data_access.URLSongLoader;
import entity.MusicPlaybackControl;

import java.io.IOException;

public class PauseSongInteractor implements PauseSongInputBoundary {

    final PauseSongInputData songInputData;

    final URLSongLoader songLoader;

    final PauseSongOutputBoundary songOutputBoundary;

    final MusicPlaybackControl musicPlaybackControl;

    public PauseSongInteractor(PauseSongInputData songInputData, URLSongLoader songLoader, PauseSongOutputBoundary songOutputBoundary, MusicPlaybackControl musicPlaybackControl) {

        this.songInputData = songInputData;
        this.songLoader = songLoader;
        this.songOutputBoundary = songOutputBoundary;
        this.musicPlaybackControl = musicPlaybackControl;
    }
    @Override
    public void execute(PauseSongInputData pauseSongInputData) throws IOException, InterruptedException {
        String songURL = songLoader.fetchPresignedURL(songInputData.getSongID());
        MusicPlaybackControl player = musicPlaybackControl;
        player.pause();
    }
}
