package entity;

import javazoom.jl.decoder.JavaLayerException;

public class MusicPlayerFacade {
    private final MusicQueueSingleton musicQueue;
    private final PlayerState playbackControl;

    // QUEUE ONLY
    public void toggleShuffle() {
        musicQueue.getQueue().toggleShuffle();
    }
    public int getCurrentSong() {
        return musicQueue.getQueue().getCurrentID();
    }
    public int getPrevSong() {
        musicQueue.getQueue().previous();
        return musicQueue.getQueue().getCurrentID();
    }
    public int getNextSong() {
        musicQueue.getQueue().next();
        return musicQueue.getQueue().getCurrentID();
    }

    // QUEUE PLUS MUSIC PLAYER (CAN DELETE?)
    public MusicPlayerFacade(String url) throws JavaLayerException {
        MusicQueueSingleton musicQueue = MusicQueueSingleton.getInstance();
        this.musicQueue = musicQueue;
        this.playbackControl = new PlayerState(url);
    }

    public void play() throws JavaLayerException {
        playbackControl.play();
    }

    public void pause() {
        playbackControl.pause();
    }

    public void playNextSong() throws JavaLayerException {
        musicQueue.getQueue().next();
        playbackControl.play();
    }

    public void playPreviousSong() throws JavaLayerException {
        musicQueue.getQueue().previous();
        playbackControl.play();
    }
}
