package use_case.play_song;

import data_access.URLSongLoader;
import entity.PlayerState;
import javazoom.jl.decoder.JavaLayerException;

import java.io.IOException;

public class SongInteractor implements SongInputBoundary {


    //Might only need song input data?
    final SongInputData songInputData;
    final URLSongLoader songLoader;
    final PlayerState musicPlaybackControl;


    public SongInteractor(SongInputData songInputData,
                          URLSongLoader songLoader,
                          PlayerState musicPlaybackControl) {

        this.songInputData = songInputData;
        this.songLoader = songLoader;
        //this.songOutputBoundary = songOutputBoundary;
        this.musicPlaybackControl = musicPlaybackControl;
    }


    @Override
    public void execute(SongInputData songInputData) throws IOException, InterruptedException, JavaLayerException {
        String songURL = songLoader.fetchPresignedURL(this.songInputData.getSongID());
        PlayerState player = musicPlaybackControl;
        player.play();

        //PlaySongOutputData songOutputData = new PlaySongOutputData(songInputData);
    }
}
