package src.use_case.next_song;

import java.util.Stack;
public class NextInputData {
    private final Stack<Integer> songStack;
    private final int lengthOfDiscography;
    private final boolean shuffle;

    public NextInputData(Stack<Integer> songStack, int lengthOfDiscography, boolean shuffle) {
        this.songStack = songStack;
        this.lengthOfDiscography = lengthOfDiscography;
        this.shuffle = shuffle;
    }

    public Stack<Integer> getSongStack() {
        return songStack;
    }

    public int getLengthOfDiscography() {
        return lengthOfDiscography;
    }

    public boolean getShuffle() {
        return shuffle;
    }
}
