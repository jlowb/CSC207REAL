package entity;

public class MusicPlaybackControl {
    private boolean playing;

    public MusicPlaybackControl(boolean playing) {
        this.playing = playing;
    }

    public void play() {
        if (!playing) {
            // Add logic to start playing
            playing = true;
        } else {
            System.out.println("Already playing.");
        }
    }

    public void pause() {
        if (playing) {
            // Add logic to pause playback
            playing = false;
        } else {
            System.out.println("Already paused.");
        }
    }

    public boolean isPlaying() {
        return playing;
    }
}
