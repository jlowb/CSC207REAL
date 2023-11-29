package use_case.play_song;

import data_access.URLSongLoader;
import entity.MusicPlaybackControl;
import entity.PlayerState;
import javazoom.jl.decoder.JavaLayerException;

import java.io.IOException;

public class PlaySongInteractor implements PlaySongInputBoundary {


    //Might only need song input data?
    final PlaySongInputData songInputData;
    final URLSongLoader songLoader;
    final PlayerState musicPlaybackControl;


    public PlaySongInteractor(PlaySongInputData songInputData,
                              URLSongLoader songLoader,
                              PlayerState musicPlaybackControl) {

        this.songInputData = songInputData;
        this.songLoader = songLoader;
        //this.songOutputBoundary = songOutputBoundary;
        this.musicPlaybackControl = musicPlaybackControl;
    }


    @Override
    public void execute(PlaySongInputData playSongInputData) throws IOException, InterruptedException, JavaLayerException {
        String songURL = songLoader.fetchPresignedURL(songInputData.getSongID());
        PlayerState player = musicPlaybackControl;
        player.play();

        //PlaySongOutputData songOutputData = new PlaySongOutputData(songInputData);
    }
}
