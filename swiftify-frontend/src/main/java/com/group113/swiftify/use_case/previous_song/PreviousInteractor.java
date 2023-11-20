package com.group113.swiftify.use_case.previous_song;

public class PreviousInteractor implements PreviousInputBoundary {
    final PreviousOutputBoundary previousOutputBoundary;

    public PreviousInteractor(PreviousOutputBoundary previousOutputBoundary) {
        this.previousOutputBoundary = previousOutputBoundary;
    }
    @Override
    public void execute(PreviousInputData previousInputData) {
        player.prevSong();

        PreviousOutputData previousOutputData = new PreviousOutputData(previousInputData.getSongStack());
        // TODO: add output boundary
    }
}
