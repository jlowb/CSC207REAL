package com.group113.swiftify.use_case.navigate_queue;

import java.util.Stack;
import java.util.Random;

public class Navigator {
    private final Stack<Integer> songStack;
    private final int lenOfDiscography;
    private final Random random;
    private boolean shuffle;

    // Constructor
    public Navigator(boolean shuffle, int lenOfDiscography) {
        this.songStack = new Stack<Integer>();
        this.random = new Random();
        this.shuffle = shuffle;
        this.lenOfDiscography = lenOfDiscography;
    }

    // Getter for the shuffle attribute
    public boolean getShuffle() {
        return this.shuffle;
    }

    // Setter for the shuffle attribute
    public void toggleShuffle() {
        this.shuffle = !this.shuffle;
    }

    // Add a song id to the navigator stack
    public void addId(int songId) {
        songStack.add(songId);
    }

    // Getter for the stack attribute
    public void goToPrevious() {
        this.songStack.pop();
    }

    // Add the next song id to the top of the stack
    public void goToNext() {
        int nextId;
        if (this.shuffle){
            nextId = this.random.nextInt(this.lenOfDiscography);
        } else {
            nextId = this.songStack.peek() + 1;
        }

        this.songStack.add(nextId);
    }

    // Returns the SongId on the top of the stack
    public int getCurr() {
        if (this.songStack.isEmpty()) {
            return -1;
        }

        return this.songStack.peek();
    }
}

