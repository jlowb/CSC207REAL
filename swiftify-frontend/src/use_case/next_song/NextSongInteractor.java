package use_case.next_song;

import data_access.URLSongLoader;
import entity.MusicPlayerFacade;
import entity.PlayerState;
import entity.Song;
import interface_adapter.ViewManagerModel;
import interface_adapter.play_song.PlaySongController;
import interface_adapter.play_song.PlaySongPresenter;
import interface_adapter.play_song.PlaySongViewModel;
import javazoom.jl.decoder.JavaLayerException;
import use_case.play_song.PlaySongInputBoundary;
import use_case.play_song.PlaySongInputData;
import use_case.play_song.PlaySongInteractor;
import view.ViewManager;

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
