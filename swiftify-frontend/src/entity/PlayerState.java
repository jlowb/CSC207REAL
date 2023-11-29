package src.entity;

import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;

import java.io.InputStream;
import java.net.URL;
import java.util.Scanner;

public class PlayerState {
    // core logic of "song being played".
    //
    // play/pause button pressed -> clean architecture stuff -> usecase executes corresponding method in
    // MusicPlayer executes play/pause method, which calls this.
    // ...

    // current Song currentSong
    // current int currentTime

    // play (process command issued by MusicPlayer)
        // if play command is issued, should unfreeze current time and resume song playing. if no resume
        // feature exists within java sound, we can do this by passing currentTime.
    // pause (process command issued by MusicPlayer)
        // if pause command is issued, should somehow freeze currentTime and freeze song playing.

    private String url;
    private Player player;
    private boolean isPaused;
    private Thread playerThread;

    public PlayerState(String url) {
        this.url = url;
        this.isPaused = false;
    }

    public void play() {
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
    }

    public void pause() {
        if (player != null && !isPaused) {
            player.close();
            isPaused = true;
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


    //This is simply a test:
    public static void main(String[] args) throws InterruptedException {
        String url = "https://csc207swiftify.s3.amazonaws.com/100.mp3?X-Amz-Security-Token=IQoJb3JpZ2luX2VjEHEaCXVzLWVhc3QtMSJHMEUCIE94w4blUCK2lO7cY%2BnNbfCpicMd61dsTze2l0An%2BpEaAiEAzz2VEE2ZvXyHPqB%2FB8IWDM7scD1WZV7Sto1gTwxQrDUq%2FQIIyv%2F%2F%2F%2F%2F%2F%2F%2F%2F%2FARAAGgw4NzM4NDY2MTcxNzkiDFd6m81Aef4j340KzSrRAlyBtu6xgSJgZf08Oz7ebSk4LS0JNm04kJ2Go%2BcUr5oQe%2BuXKW0EraOaHMLlaBKZCp02nWNzbiDSdBvvFTBgd0KvwSEpeMDai%2FCuDyqcQ4BaCjtZythTyZK5nyoliUq5VriK38MzWBhsjnk0f5MC6pCgXWjklpvTMN8SdwYgeOdAYfNtvbOZvokLtQhKmvTVDRjHv%2FSkuo0zWc72pQQ1mMqjxVZkvQGrdmGbF6eaJixpovlMd7tpRiBrE%2BRKj51jcne4yH%2BFVypqv5oIWNTsitKq5PbLS%2FRLMdP%2BX0cjymOdOPf%2BQA%2Bm%2B5YcPdoI9rs%2FrwAtCgguciojtZ73JNTNnWpbB87wMfFbcN1gBT9%2FFfUeSfE7CKYQHfGf0pwXqnjeeKL9JldDEC0mqIgUV5BdQ6HRD7k%2F3in9WDZ23q5xvakjOu0s0tMge2nQSPlCtsIvBkcw%2FpmaqwY6ngFPdU7vywSVVvT7K4LrBltkC4n3SbNeJyj1IGNADwm6P8si6xRUpUHS35Vau7NbkQ%2FtJQ2LUJ0Dea7rWdYJv1pq%2BgCQ%2BLbykDM6qLiLbq5paKCS6AB8AmpdFMleNwygKI9GuXpmLu03HUBgVHMBMUocDorWYALlIoBwxg%2BdEQa78xdi8J0gV0PnUMlrk2xiLbIqdt0RXri%2B2xGj4Ebtrw%3D%3D&X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Date=20231129T005946Z&X-Amz-SignedHeaders=host&X-Amz-Expires=3600&X-Amz-Credential=ASIA4W5KRGRNU7NMMVM6%2F20231129%2Fus-east-1%2Fs3%2Faws4_request&X-Amz-Signature=441f614ff773706e4e51861f70fabe67238ff8327ca2555056e1fba07b6c8537";

        //String url = "https://csc207swiftify.s3.amazonaws.com/100.mp3?X-Amz-Security-Token=IQoJb3JpZ2luX2VjECYaCXVzLWVhc3QtMSJHMEUCIFUl7HmGcNU2tb%2BgQGa6HKMTpQv829v1QtRCJn9oTtJYAiEAtLXmhw7vWxqzrDn%2Fs9XKOtdLwfZos9r76e9u97ROqLwq9AIIfhAAGgw4NzM4NDY2MTcxNzkiDLBQ%2BpvWx%2FgY9OozKyrRAkqV1%2Fu07lxc9AJh%2F8M%2FMaJ%2ByQPVnrZE7Qt5zSrQIJKilz0QELdCz24vOED8t3ZozU%2BERM1m6NCQZZh6KL4u1MSb5JKO4rTJtzZfbx4TvZ0uJ%2Bld1xjX7BU5uL%2BHlEAGT0%2BF07w%2Bn0pDTG37G8Eb0svVlMQAmRvSDl98qZiORgQIK7DQVVK4%2BjJnSY9HMvGiFLBIKXBofB0x2PicXcNiJlHrg8NF49Z18rUff8IAjQm%2FflnwHY%2FDTHmhUpcfS4LRxv5MJp7kY3b0n8keQecuCvZ68WqOO3R9XV5PQ7TqtM3NmYUGKxVWpN9uHhz9r7xGqRGHPZqUOh0dELb3zZNvVnKvO8pQnxELETQK3HFdrChkKONxmEKvx4PxmuQ1RF0WFZQ%2Bf8DIgolMU4FgiM%2BjKBYbpqsogZwsR%2B2nUZ%2BDCH4TN3ha3nISz9Sra44nzMuAzzwwksWJqwY6ngEEeo2TEUzMrt9NTWd%2Bfoag53zkF2v013fihKLhxducYrccj0y4f5NWZ%2BKGX1d250XEzZkaUX%2BhFyJcgCwzkleJoBoPJZw0gN44khZGyVDKgoWHeqV1u%2Bg%2BhFWp7YOxtQM6stAr3nkfQIPRZvmmK3Zsa5N163wS8lhImsO8OWz0moePTpowsbJuoGj5uIE49i53dnDD9wsmq7RYW%2BJTZQ%3D%3D&X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Date=20231125T211350Z&X-Amz-SignedHeaders=host&X-Amz-Expires=3600&X-Amz-Credential=ASIA4W5KRGRNSW6FUHVB%2F20231125%2Fus-east-1%2Fs3%2Faws4_request&X-Amz-Signature=fc67e55e2ae30a7d3535358d5924afce8d034d27b131259eb4ce4585e2d22cbd";
        MusicPlaybackControl player = new MusicPlaybackControl(false,url);
        // Example usage
        player.play();
        Thread.sleep(5000); // play for 1 seconds
        player.pause();
        Thread.sleep(5000);
        player.resume();



        //Keeps it running, otherwise it will stop.
        Scanner scanner = new Scanner(System.in);
        String response = scanner.next();
    }
}
