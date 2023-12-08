package entity;

import java.io.InputStream;
import java.net.URL;
import javazoom.jl.player.Player;
import javazoom.jl.player.AudioDevice;
import javazoom.jl.decoder.JavaLayerException;

/**
 * Represents the state and control logic for playing audio using the JavaZoom JLayer library.
 */
public class PlayerState {
    private final static int NOTSTARTED = 0;
    private final static int PLAYING = 1;
    private final static int PAUSED = 2;
    private final static int FINISHED = 3;
    private final Player player;
    private final Object playerLock = new Object();
    private int playerStatus = NOTSTARTED;

    /**
     * Constructs a new PlayerState instance with the given input stream.
     *
     * @param inputStream The input stream containing the audio data.
     * @throws JavaLayerException If there is an error initializing the player.
     */

    public PlayerState(final InputStream inputStream) throws JavaLayerException {
        this.player = new Player(inputStream);
    }

    /**
     * Constructs a new PlayerState instance with the given input stream and audio device.
     *
     * @param inputStream  The input stream containing the audio data.
     * @param audioDevice  The audio device to use for playback.
     * @throws JavaLayerException If there is an error initializing the player.
     */

    public PlayerState(final InputStream inputStream, final AudioDevice audioDevice) throws JavaLayerException {
        this.player = new Player(inputStream, audioDevice);
    }

    /**
     * Constructs a new PlayerState instance for playing audio from a URL.
     *
     * @param url The URL of the audio resource to play.
     * @throws JavaLayerException If there is an error initializing the player.
     */

    public PlayerState(final String url) throws JavaLayerException {
        try {
            InputStream inputStream = new URL(url).openStream();
            this.player = new Player(inputStream);
        } catch (Exception e) {
            throw new JavaLayerException("Problem playing MP3 from URL", e);
        }
    }

    /**
     * Starts playing the audio.
     *
     * @throws JavaLayerException If there is an error during playback.
     */

    public void play() throws JavaLayerException {
        synchronized (playerLock) {
            switch (playerStatus) {
                case NOTSTARTED:
                    final Runnable r = new Runnable() {
                        public void run() {
                            playInternal();
                        }
                    };
                    final Thread t = new Thread(r);
                    t.setDaemon(true);
                    t.setPriority(Thread.MAX_PRIORITY);
                    playerStatus = PLAYING;
                    t.start();
                    break;
                case PAUSED:
                    resume();
                    break;
                default:
                    break;
            }
        }
    }

    /**
     * Pauses the audio playback.
     *
     * @return True if the audio was successfully paused, false otherwise.
     */

    public boolean pause() {
        synchronized (playerLock) {
            if (playerStatus == PLAYING) {
                playerStatus = PAUSED;
            }
            return playerStatus == PAUSED;
        }
    }

    /**
     * Resumes audio playback from where it was paused.
     *
     * @return True if the audio was successfully resumed, false otherwise.
     */

    public boolean resume() {
        synchronized (playerLock) {
            if (playerStatus == PAUSED) {
                playerStatus = PLAYING;
                playerLock.notifyAll();
            }
            return playerStatus == PLAYING;
        }
    }

    /**
     * Stops audio playback.
     */

    public void stop() {
        synchronized (playerLock) {
            playerStatus = FINISHED;
            playerLock.notifyAll();
        }
    }

    private void playInternal() {
        while (playerStatus != FINISHED) {
            try {
                if (!player.play(1)) {
                    break;
                }
            } catch (final JavaLayerException e) {
                break;
            }
            synchronized (playerLock) {
                while (playerStatus == PAUSED) {
                    try {
                        playerLock.wait();
                    } catch (final InterruptedException e) {
                        break;
                    }
                }
            }
        }
        close();
    }

    /**
     * Closes the player and releases associated resources.
     */

    public void close() {
        synchronized (playerLock) {
            playerStatus = FINISHED;
        }
        try {
            player.close();
        } catch (final Exception e) {
            // ignore, we are terminating anyway
        }
    }

    /**
     * Checks if audio playback has finished.
     *
     * @return True if audio playback has finished, false otherwise.
     */

    public boolean isFinished() {
        return playerStatus == FINISHED;
    }

    /**
     * Checks if audio is currently playing.
     *
     * @return True if audio is playing, false otherwise.
     *
     */

    public boolean isPlaying() {
        return playerStatus == PLAYING;
    }


    /*
    // demo how to use with URL
    public static void main(String[] argv) {
        try {
            String mp3Url = "https://csc207swiftify.s3.amazonaws.com/120.mp3?X-Amz-Security-Token=IQoJb3JpZ2luX2VjEHUaCXVzLWVhc3QtMSJHMEUCIQDNA4T5OUWVjFF0%2Bwr7r7PDIZ5PjtUoHftDud%2BeSVrUBAIgXUvwMibTqOA%2FchfhG%2FGsnoKyBg9uW%2Bea3sMvyEKzIcoq%2FQIIzv%2F%2F%2F%2F%2F%2F%2F%2F%2F%2FARAAGgw4NzM4NDY2MTcxNzkiDJf36wsoF0Mq7%2BBJMSrRAo%2Fkw4rBt5clqfPqKqYRrlkWYmK10LCs3JUjAZkMdUBtxNslRYUsr%2B0zZmAF6uYgsVQiFjHuKd3zsSHi%2Bgk1i9SIZT5QHHtMATysf2%2BACe8ED3jGjdiT0T6OzMb89Tn0ncb9NxJXzMxI%2BsFNM0AsE1hJEwk7jYU4son6c8cjy0Vc83%2Fb6fEuB%2Fzx6cyYrC%2FWmnqti94rRAavgdPwyw20YIEwf5TPjuEqw9EsbQZK1jh6jNbdGxqpHC88Cy3j8ffY8OyBsr1Dg7cXsb%2Bvf8SuzvslGILrspM1lLzIDf0eMVZ556SpWq0jlnVb6%2BBOGxg7QlES6oxU6h9T6671sIR%2FWgRpipIbj8WGBu7AWqIPjdTCaPwIEkIK4GHusJYmGH33pZtGv5ZLy6AmYB64Em8veD5QdUgF22s7b7urz%2FyQvlWISWyje9EJLjWG%2BunHXNknC30wpoCbqwY6ngG285F%2BqL0bGJw8u86Acx4NUto%2BEHAsp68n4YdQj9Tl1kRfU2bEIcOkiN08TJIZmHZhh3EAr4rh%2B0AmCbAC8QoRuJr2K1wAqlcKx1lB8hjTMgCsi5Hv06jeAxcUtU0l4Beu2d0Z6WKN3ktIrAhm6ivgXXylhBgeZv50T6zCz4y4gX%2F81obWK2Y9WL27hOjnWD0%2BsaNAZkp91s%2FbNelrBg%3D%3D&X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Date=20231129T043958Z&X-Amz-SignedHeaders=host&X-Amz-Expires=3600&X-Amz-Credential=ASIA4W5KRGRNUV5SJPNI%2F20231129%2Fus-east-1%2Fs3%2Faws4_request&X-Amz-Signature=f49fc8cc981cffbf3be7c559dc6738e701c5ea036f606141ca651a37de61e3f3"; // Replace with your MP3 URL
            PlayerState player = new PlayerState(mp3Url);

            player.play();
            System.out.println("Playing Audio...");
            Thread.sleep(15000); // Let the audio play for 15 seconds

            // Test pause functionality
            player.pause();
            System.out.println("Audio Paused...");
            Thread.sleep(3000); // Wait for 3 seconds

            // Test resume functionality
            player.play();
            System.out.println("Audio Resumed...");
            Thread.sleep(5000); // Let the audio play for another 5 seconds

            // Loop until the song is finished
            while (!player.isFinished()) {
                Thread.sleep(1000); // Sleep for a short period (e.g., one second)
            }
        } catch (final Exception e) {
            throw new RuntimeException(e);
        }
    }

     */

}

