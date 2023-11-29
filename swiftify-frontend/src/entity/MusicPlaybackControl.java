package entity;

import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;

import java.io.InputStream;
import java.net.URL;
import java.util.Scanner;

public class MusicPlaybackControl {
    private boolean playing;
    private String url;
    private Player player;
    private boolean isPaused;
    private Thread playerThread;
    private Song currentSong;

    public MusicPlaybackControl(boolean playing, String url) {

        this.playing = playing;
        this.url = url;
        this.isPaused = false;
    }

    public void play() {
        if (!playing) {
            try {
                if (isPaused) {
                    resume();
                } else {
                    InputStream is = new URL(url).openStream();
                    player = new Player(is);
                    playerThread = new Thread(() -> {
                        try {
                            player.play();
                        } catch (JavaLayerException e) {
                            e.printStackTrace();
                        }
                    });
                    playerThread.start();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            playing = true;
        } else {
            System.out.println("Already playing.");
        }
    }

    public void pause() {
        if (playing) {
            if (player != null && !isPaused) {
                player.close();
                isPaused = true;
            }
            playing = false;
        } else {
            System.out.println("Already paused.");
        }
    }

    public void resume() {
        isPaused = false;
        play();
    }

    public void stop() {
        if (player != null) {
            player.close();
            isPaused = false;
        }
    }

    public boolean isPlaying() {
        return playing;
    }


    //This is simply a test:
    public static void main(String[] args) throws InterruptedException {

        String ul = "https://csc207swiftify.s3.amazonaws.com/100.mp3?X-Amz-Security-Token=IQoJb3JpZ2luX2VjEHIaCXVzLWVhc3QtMSJIMEYCIQDaW%2FizuU40cLz4npyH%2Fu77YKIT6CsYHLHM05WzEK%2F%2FNwIhAJsSo7HqXLl47aQfMpyioR%2F56JSaHG3I0YcElYbjilSDKv0CCMr%2F%2F%2F%2F%2F%2F%2F%2F%2F%2FwEQABoMODczODQ2NjE3MTc5IgyuSGcmZ%2BiTRSFDNzsq0QKAJ7LiKFUS8hE%2BjFLV9xrGvf%2FtUdsLWDK2r8VK9JK%2FFDGAK5AJFGgl0geaA%2BiXANayclCpEMyZuEP6WEMi7b2oC1K9K%2BLpY0SaZDvzXe96DeuxbJA1ax7MGlg%2FU241%2F3eemI8tOPG%2FA9ALADImhsP8TmnAYlJLLpIdJNusNPiq8mOUSsBcTCWm5Rh736iWi18on9864uOjrn0eYTIQlIM8hNawol0Rre56vjNIM%2FHCsqxImoZ6xP3ljtFGN5NjpiDI9rYNisEm5yBkTUHAC4UlzDSMGy6MF1DiGnda9OjsYR2xJiPTDnPOpSL8jfcOOY6oE7tw2A4j6%2BflSe%2F%2BsHuOWrWWf%2BbEe3Mfg9S2t7da0GrXFu393MJ81hFxd3VPWuI4vKIysfztZ%2BADG2BA%2B7umO46fkVDuoRDcRLw5gFuP%2FjB4zRjyCPLh56X0Si9ROTWhMIedmqsGOp0BmsWe1J13K0Wzn7C28ggaynyTL6eKq7y5MG9BvOMMjhy49XW4RHXTQ6pwae6UZjWppmcqq5zP0F%2Fmw3hft576%2BoLTB8aHRBpxz0dhS1%2F16wT1VdmlqBvi%2BS10Pmwz4jaKMbyX0%2FFuPF6XPWXLpCfi09hiUv92zjwX%2FloR5VhLvs9tmXLRvL8LBRoSq7t3LQp3f5gH4rjFtwA2ddny7w%3D%3D&X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Date=20231129T010619Z&X-Amz-SignedHeaders=host&X-Amz-Expires=3600&X-Amz-Credential=ASIA4W5KRGRNRPQY64Z6%2F20231129%2Fus-east-1%2Fs3%2Faws4_request&X-Amz-Signature=3d2f879d618876ffce8b8c52c312028388b3b78ef43f77a4e4c1e105a247349b";
        //String url = "https://csc207swiftify.s3.amazonaws.com/100.mp3?X-Amz-Security-Token=IQoJb3JpZ2luX2VjECYaCXVzLWVhc3QtMSJHMEUCIFUl7HmGcNU2tb%2BgQGa6HKMTpQv829v1QtRCJn9oTtJYAiEAtLXmhw7vWxqzrDn%2Fs9XKOtdLwfZos9r76e9u97ROqLwq9AIIfhAAGgw4NzM4NDY2MTcxNzkiDLBQ%2BpvWx%2FgY9OozKyrRAkqV1%2Fu07lxc9AJh%2F8M%2FMaJ%2ByQPVnrZE7Qt5zSrQIJKilz0QELdCz24vOED8t3ZozU%2BERM1m6NCQZZh6KL4u1MSb5JKO4rTJtzZfbx4TvZ0uJ%2Bld1xjX7BU5uL%2BHlEAGT0%2BF07w%2Bn0pDTG37G8Eb0svVlMQAmRvSDl98qZiORgQIK7DQVVK4%2BjJnSY9HMvGiFLBIKXBofB0x2PicXcNiJlHrg8NF49Z18rUff8IAjQm%2FflnwHY%2FDTHmhUpcfS4LRxv5MJp7kY3b0n8keQecuCvZ68WqOO3R9XV5PQ7TqtM3NmYUGKxVWpN9uHhz9r7xGqRGHPZqUOh0dELb3zZNvVnKvO8pQnxELETQK3HFdrChkKONxmEKvx4PxmuQ1RF0WFZQ%2Bf8DIgolMU4FgiM%2BjKBYbpqsogZwsR%2B2nUZ%2BDCH4TN3ha3nISz9Sra44nzMuAzzwwksWJqwY6ngEEeo2TEUzMrt9NTWd%2Bfoag53zkF2v013fihKLhxducYrccj0y4f5NWZ%2BKGX1d250XEzZkaUX%2BhFyJcgCwzkleJoBoPJZw0gN44khZGyVDKgoWHeqV1u%2Bg%2BhFWp7YOxtQM6stAr3nkfQIPRZvmmK3Zsa5N163wS8lhImsO8OWz0moePTpowsbJuoGj5uIE49i53dnDD9wsmq7RYW%2BJTZQ%3D%3D&X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Date=20231125T211350Z&X-Amz-SignedHeaders=host&X-Amz-Expires=3600&X-Amz-Credential=ASIA4W5KRGRNSW6FUHVB%2F20231125%2Fus-east-1%2Fs3%2Faws4_request&X-Amz-Signature=fc67e55e2ae30a7d3535358d5924afce8d034d27b131259eb4ce4585e2d22cbd";
        PlayerState player = new PlayerState(ul);
        // Example usage
        player.play();
        //Thread.sleep(1000); // play for 1 seconds
        //player.pause();



        //Keeps it running, otherwise it will stop.
        Scanner scanner = new Scanner(System.in);
        String response = scanner.next();
    }
}
