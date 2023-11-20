package com.group113.swiftify.entity;

import jdk.jshell.spi.ExecutionControl;

import java.util.ArrayList;
import java.util.Random;
import java.util.Stack;

public class MusicPlayer {
    // core logic of "music list"
    // music chain is by default an empty stack. the top of the stack is the current song.
    // hence, the next song is done by adding to the top, prev song is done by removing from the top (history).
    // how to determine what to add?
    // if button is pressed, nextsong will be that exact song.
    // if no button is pressed and not shuffled, nextsong will be song with next song id.
    // if no button and shuffled, nextsong gives random id not previously visited.

    private class MusicChain {
        private final Stack<Integer> stack;

        public MusicChain() {
            this.stack = new Stack<Integer>();
        }

        public int top() {
            if (!stack.isEmpty()) {
                return stack.peek();
            }

            return -1;
        }

        public void add(int songId) {
            stack.add(songId);
        }

        public void pop() {
            if (!stack.isEmpty()){
                stack.pop();
            }
        }

    }

    private final Random random;
    private MusicChain musicChain;
    private ArrayList<Integer> alreadyPlayed;
    private boolean playing;
    private boolean shuffled;
    private int lengthOfDiscography;

    public MusicPlayer(boolean playing, boolean shuffled, int lengthOfDiscography) {
        this.random = new Random();
        this.musicChain = new MusicChain();
        this.playing = playing;
        this.shuffled = shuffled;
        this.lengthOfDiscography = lengthOfDiscography;
    }

    public int getCurrentSong() {
        return musicChain.top();
    }

    public void play() {

    }

    public void pause() {

    }

    public void nextSong() {
        int nextSongId;
        if (shuffled) {
            do {
                nextSongId = random.nextInt(lengthOfDiscography);
            } while (checkAlreadyPlayed(nextSongId));
        } else {
            nextSongId = musicChain.top() + 1;
        }

        musicChain.add(nextSongId);
    }

    public void prevSong() {
        musicChain.pop();
    }

    public void toggleShuffle() {
        this.shuffled = !this.shuffled;
    }

    private boolean checkAlreadyPlayed(int songId) {
        if (alreadyPlayed.size() == lengthOfDiscography){
            alreadyPlayed.clear();
            return false;
        }

        return alreadyPlayed.contains(songId);
    }

}
