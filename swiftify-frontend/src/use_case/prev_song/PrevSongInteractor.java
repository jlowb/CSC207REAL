package use_case.prev_song;

import data_access.URLSongLoader;
import data_access.MusicPlayerFacade;
import entity.PlayerState;
import entity.Song;
import javazoom.jl.decoder.JavaLayerException;

import java.io.IOException;

public class PrevSongInteractor implements PrevSongInputBoundary {

    private final URLSongLoader songLoader;
    private final PrevSongOutputBoundary prevSongOutputBoundary;

    public PrevSongInteractor(URLSongLoader songLoader, PrevSongOutputBoundary prevSongOutputBoundary) {
        this.songLoader = songLoader;
        this.prevSongOutputBoundary = prevSongOutputBoundary;
    }
    public void execute(PrevSongInputData prevSongInputData) throws IOException, InterruptedException, JavaLayerException {
        MusicPlayerFacade musicPlayer = MusicPlayerFacade.getInstance(prevSongInputData.getView().getAlbumName());
        Song prevSong = prevSongInputData.getSong();
        String songURL = songLoader.fetchPresignedURL(prevSong.getSongID());
        PlayerState audioPlayer = musicPlayer.makeState(songURL);
        audioPlayer.play();
        PrevSongOutputData prevSongOutputData = new PrevSongOutputData(prevSong.getTitle(), audioPlayer, prevSongInputData.getView());
        this.prevSongOutputBoundary.preparePrevView(prevSongOutputData);
    }
}
