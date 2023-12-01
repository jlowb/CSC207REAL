package use_case.play_song;

import data_access.URLSongLoader;
import entity.PlayerState;
import javazoom.jl.decoder.JavaLayerException;

import java.io.IOException;

public class PlaySongInteractor implements PlaySongInputBoundary {

    private final URLSongLoader songLoader;
    private final PlaySongOutputBoundary playSongOutputBoundary;


    public PlaySongInteractor(URLSongLoader songLoader,
                              PlaySongOutputBoundary playSongOutputBoundary) {
        this.songLoader = songLoader;
        this.playSongOutputBoundary = playSongOutputBoundary;
    }

    public void execute(PlaySongInputData songInputData) throws IOException, InterruptedException, JavaLayerException {
        String songURL = songLoader.fetchPresignedURL(songInputData.getSongId());
        PlayerState musicPlayer = new PlayerState(songURL);
        musicPlayer.play();
        PlaySongOutputData playSongOutputData = new PlaySongOutputData(songInputData.getSongName(), musicPlayer, songInputData.getView());
        this.playSongOutputBoundary.preparePlayingView(playSongOutputData);
    }
}
