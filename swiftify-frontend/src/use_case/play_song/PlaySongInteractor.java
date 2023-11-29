package use_case.play_song;

import data_access.URLSongLoader;
import entity.MusicPlaybackControl;

import java.io.IOException;

public class PlaySongInteractor implements PlaySongInputBoundary {


    //Might only need song input data?
    final PlaySongInputData songInputData;
    final URLSongLoader songLoader;
    final PlaySongOutputBoundary songOutputBoundary;

    final MusicPlaybackControl musicPlaybackControl;


    public PlaySongInteractor(PlaySongInputData songInputData,
                              URLSongLoader songLoader,
                              PlaySongOutputBoundary songOutputBoundary,
                              MusicPlaybackControl musicPlaybackControl) {

        this.songInputData = songInputData;
        this.songLoader = songLoader;
        this.songOutputBoundary = songOutputBoundary;
        this.musicPlaybackControl = musicPlaybackControl;
    }


    @Override
    public void execute(PlaySongInputData playSongInputData) throws IOException, InterruptedException {
        String songURL = songLoader.fetchPresignedURL(songInputData.getSongID());
        MusicPlaybackControl player = musicPlaybackControl;
        player.play();

        //PlaySongOutputData songOutputData = new PlaySongOutputData(songInputData);
    }
}
