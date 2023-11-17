package com.group113.swiftify.entity;

import jdk.jshell.spi.ExecutionControl;

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
            return stack.peek();
        }

        public void add(int songId) {
            stack.add(songId);
        }

        public void pop() {
            stack.pop();
        }

    }

    private boolean playing;
    private boolean shuffled;
    private MusicChain musicChain;

    public MusicPlayer(boolean playing, boolean shuffled) {
        this.playing = playing;
        this.shuffled = shuffled;
        this.musicChain = new MusicChain();
    }

    public int getCurrentSong() {
        return musicChain.top();
    }

    public void play() {

    }

    public void pause() {

    }

    public void nextSong() {

    }

    public void prevSong() {

    }

    public void toggleShuffle() {
        this.shuffled = !this.shuffled;
    }

}
