package data_access;

import entity.MusicQueue;
import entity.PlayerState;
import entity.Song;
import javazoom.jl.decoder.JavaLayerException;

public class MusicPlayerFacade {
    private static MusicPlayerFacade instance;
    private MusicQueue queue;

    private MusicPlayerFacade(boolean shuffled, int discographyLength, String albumName) {
        this.queue = new MusicQueue(shuffled, discographyLength, albumName);
    }

    public static synchronized MusicPlayerFacade getInstance(String albumName) {
        if (instance == null) {
            MusicLibrary library = MusicLibrary.getInstance();
            instance = new MusicPlayerFacade(false, library.getLength(), albumName);
        }
        return instance;
    }

    public static synchronized void removeInstance() {
        instance = null;
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