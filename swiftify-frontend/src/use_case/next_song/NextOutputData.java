package use_case.next_song;

import java.util.Stack;

public class NextOutputData {
    private final Stack<Integer> songStack;

    public NextOutputData(Stack<Integer> songStack) {
        this.songStack = songStack;
    }

    public Stack<Integer> getSongStack() {
        return songStack;
    }
}
