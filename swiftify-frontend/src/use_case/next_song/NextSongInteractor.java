package use_case.next_song;

import data_access.URLSongLoader;
import data_access.MusicPlayerFacade;
import entity.PlayerState;
import entity.Song;
import javazoom.jl.decoder.JavaLayerException;

import java.io.IOException;

public class NextSongInteractor implements NextSongInputBoundary {

    private final URLSongLoader songLoader;
    private final NextSongOutputBoundary nextSongOutputBoundary;

    public NextSongInteractor(URLSongLoader songLoader, NextSongOutputBoundary nextSongOutputBoundary) {
        this.songLoader = songLoader;
        this.nextSongOutputBoundary = nextSongOutputBoundary;
    }
    public void execute(NextSongInputData nextSongInputData) throws IOException, InterruptedException, JavaLayerException {
        MusicPlayerFacade musicPlayer = MusicPlayerFacade.getInstance(nextSongInputData.getView().getAlbumName());
        Song nextSong = nextSongInputData.getSong();
        String songURL = songLoader.fetchPresignedURL(nextSong.getSongID());
        PlayerState audioPlayer = musicPlayer.makeState(songURL);
        audioPlayer.play();
        NextSongOutputData nextSongOutputData = new NextSongOutputData(nextSong.getTitle(), audioPlayer, nextSongInputData.getView());
        this.nextSongOutputBoundary.prepareNextView(nextSongOutputData);
    }
}
