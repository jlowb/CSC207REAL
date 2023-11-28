package entity;

public class MusicPlayerFacade {
    private final MusicQueue musicQueue;
    private final MusicPlaybackControl playbackControl;

    public MusicPlayerFacade(boolean playing, boolean shuffled, int lengthOfDiscography) {
        this.musicQueue = new MusicQueue(shuffled, lengthOfDiscography);
        this.playbackControl = new MusicPlaybackControl(playing);
    }

    public void play() {
        playbackControl.play();
    }

    public void pause() {
        playbackControl.pause();
    }

    public void playNextSong() {
        musicQueue.next();
        playbackControl.play();
    }

    public void playPreviousSong() {
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