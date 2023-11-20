package com.group113.swiftify.entity;

import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;

import java.io.InputStream;
import java.net.URL;
import java.util.Scanner;

import com.group113.swiftify.entity.Song;

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
    private int currentTime;
    private Thread playerThread;
    private Song currentSong;

    //TODO: After Alex implement the URL getter fix this:
    //TODO: Replace this with Song currentSong
    public PlayerState(String url) {
        this.url = url;
        this.isPaused = false;
        this.currentTime = 0;
    }

    public void play() {
        try {
            if (isPaused) {
                resume();
            } else {
                //TODO: Here extract the URL from the Song object
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
            currentTime = player.getPosition();
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
            currentTime = 0;
        }
    }


    //This is simply a test:
    public static void main(String[] args) throws InterruptedException {
        String url = "gg";
        PlayerState player = new PlayerState("https://samplelib.com/lib/preview/mp3/sample-12s.mp3");
        // Example usage
        player.play();
        Thread.sleep(1000); // play for 1 seconds
        player.pause();
        Thread.sleep(1000); // pause for 1 seconds
        player.resume();
        Thread.sleep(5000); // play for 5 seconds
        player.pause();
        Thread.sleep(10000); // pause for 10 seconds
        player.resume();


        //Keeps it running, otherwise it will stop.
        Scanner scanner = new Scanner(System.in);
        String response = scanner.next();
    }
}
