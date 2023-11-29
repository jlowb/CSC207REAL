package use_case.pause_song;

import data_access.URLSongLoader;
import entity.PlayerState;
import use_case.play_song.PlaySongInputBoundary;
import use_case.play_song.PlaySongInputData;

import java.io.IOException;

public class PauseSongInteractor implements PlaySongInputBoundary {


    //Might only need song input data?
    final PlaySongInputData songInputData;
    final URLSongLoader songLoader;
    final PlayerState musicPlaybackControl;


    public PauseSongInteractor(PlaySongInputData songInputData,
                               URLSongLoader songLoader,
                               PlayerState musicPlaybackControl) {

        this.songInputData = songInputData;
        this.songLoader = songLoader;
        //this.songOutputBoundary = songOutputBoundary;
        this.musicPlaybackControl = musicPlaybackControl;
    }


    @Override
    public void execute(PlaySongInputData playSongInputData) throws IOException, InterruptedException {
        String songURL = songLoader.fetchPresignedURL(songInputData.getSongID());
        PlayerState player = musicPlaybackControl;
        player.pause();

        //PlaySongOutputData songOutputData = new PlaySongOutputData(songInputData);
    }
}
