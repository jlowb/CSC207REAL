package entity;

public class MusicQueueSingleton {
    private static MusicQueueSingleton instance;

    private MusicQueue queue;

    private MusicQueueSingleton(boolean shuffled, int discographyLength) {
        this.queue = new MusicQueue(shuffled, discographyLength);
    }

    public static synchronized MusicQueueSingleton getInstance() {
        if (instance == null) {
            MusicLibrary library = MusicLibrary.getInstance();
            instance = new MusicQueueSingleton(false, library.getLength());
        }
        return instance;
    }

    public MusicQueue getQueue() {
        return queue;
    }
}
