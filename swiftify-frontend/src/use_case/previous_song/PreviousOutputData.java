package use_case.previous_song;

import java.util.Stack;

public class PreviousOutputData {
    private final Stack<Integer> songStack;

    public PreviousOutputData(Stack<Integer> songStack) {
        this.songStack = songStack;
    }

    public Stack<Integer> getSongStack() {
        return songStack;
    }
}
