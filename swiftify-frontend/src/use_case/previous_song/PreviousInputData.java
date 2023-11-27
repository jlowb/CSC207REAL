package src.use_case.previous_song;

import java.util.Stack;
public class PreviousInputData {
    private final Stack<Integer> songStack;

    public PreviousInputData(Stack<Integer> songStack) {
        this.songStack = songStack;
    }

    public Stack<Integer> getSongStack() {
        return songStack;
    }
}
