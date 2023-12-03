package entity;

import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;

public class MusicPlayerFacade {
    private static MusicPlayerFacade instance;
    private MusicQueue queue;

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

    public MusicQueue getQueue() {
        return this.queue;
    }
    public void toggleShuffle() {
        queue.toggleShuffle();
    }

    public void addToQueue(int n) {
        queue.add(n);
    }

    public Song getCurrentSong() {
        MusicLibrary library = MusicLibrary.getInstance();
        int n = queue.getCurrentID();
        if (n != -1) {
            return library.getSongs().get(n);
        }
        return null;
    }
    public Song getPrevSong() {
        MusicLibrary library = MusicLibrary.getInstance();
        queue.previous();
        int n = queue.getCurrentID();
        if (n != -1) {
            return library.getSongs().get(n);
        }
        return null;
    }
    public Song getNextSong() {
        MusicLibrary library = MusicLibrary.getInstance();
        queue.next();
        int n = queue.getCurrentID();
        if (n != -1) {
            return library.getSongs().get(n);
        }
        return null;
    }

    // STATE
    public PlayerState makeState(String songURL) throws JavaLayerException {
        return new PlayerState(songURL);
    }
}