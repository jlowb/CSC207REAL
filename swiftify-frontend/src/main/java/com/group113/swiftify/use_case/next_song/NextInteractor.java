package com.group113.swiftify.use_case.next_song;

public class NextInteractor implements NextInputBoundary {
    final NextOutputBoundary nextOutputBoundary;


    public NextInteractor(NextOutputBoundary nextOutputBoundary) {
        this.nextOutputBoundary = nextOutputBoundary;
    }
    @Override
    public void execute(NextInputData nextInputData) {
        player.nextsong();

        NextOutputData nextOutputData = new NextOutputData(nextInputData.getSongStack());
        // TODO: add output boundary
    }
}
