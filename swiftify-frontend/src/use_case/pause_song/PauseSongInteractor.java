package use_case.pause_song;

import data_access.URLSongLoader;
import entity.PlayerState;
import use_case.play_song.SongInputBoundary;
import use_case.play_song.SongInputData;

import java.io.IOException;

public class PauseSongInteractor implements SongInputBoundary {


    //Might only need song input data?
    final SongInputData songInputData;
    final URLSongLoader songLoader;
    final PlayerState musicPlaybackControl;


    public PauseSongInteractor(SongInputData songInputData,
                               URLSongLoader songLoader,
                               PlayerState musicPlaybackControl) {

        this.songInputData = songInputData;
        this.songLoader = songLoader;
        //this.songOutputBoundary = songOutputBoundary;
        this.musicPlaybackControl = musicPlaybackControl;
    }


    @Override
    public void execute(SongInputData songInputData) throws IOException, InterruptedException {
        String songURL = songLoader.fetchPresignedURL(this.songInputData.getSongID());
        PlayerState player = musicPlaybackControl;
        player.pause();

        //PlaySongOutputData songOutputData = new PlaySongOutputData(songInputData);
    }
}
