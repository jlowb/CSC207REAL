package entity;

import javazoom.jl.decoder.JavaLayerException;

public class MusicPlayerFacade {
    private final MusicQueue musicQueue;
    private final PlayerState playbackControl;

    public MusicPlayerFacade(boolean playing, boolean shuffled, int lengthOfDiscography, String url) throws JavaLayerException {
        this.musicQueue = new MusicQueue(shuffled, lengthOfDiscography);
        this.playbackControl = new PlayerState(url);
    }

    public void play() throws JavaLayerException {
        playbackControl.play();
    }

    public void pause() {
        playbackControl.pause();
    }

    public void playNextSong() throws JavaLayerException {
        musicQueue.next();
        playbackControl.play();
    }

    public void playPreviousSong() throws JavaLayerException {
        musicQueue.previous();
        playbackControl.play();
    }

    public void toggleShuffle() {
        musicQueue.toggleShuffle();
    }

    public int getCurrentSong() {
        return musicQueue.getCurrentID();
    }
}
