package entity;

import javazoom.jl.decoder.JavaLayerException;

public class MusicPlayerFacade {
    private static MusicPlayerFacade instance;
    private MusicQueue queue;

    private PlayerState playbackControl; // implement methods for this if needed

    private MusicPlayerFacade(boolean shuffled, int discographyLength) {
        this.queue = new MusicQueue(shuffled, discographyLength);
    }

    public static synchronized MusicPlayerFacade getInstance() {
        if (instance == null) {
            MusicLibrary library = MusicLibrary.getInstance();
            instance = new MusicPlayerFacade(false, library.getLength());
        }
        return instance;
    }

    // QUEUE ONLY
    public void toggleShuffle() {
        queue.toggleShuffle();
    }
    public int getCurrentSong() {
        return queue.getCurrentID();
    }
    public int getPrevSong() {
        queue.previous();
        return queue.getCurrentID();
    }
    public int getNextSong() {
        queue.next();
        return queue.getCurrentID();
    }
}