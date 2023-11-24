package src.use_case.next_song;

public class NextInteractor implements NextInputBoundary {
    final NextOutputBoundary nextOutputBoundary;


    public NextInteractor(NextOutputBoundary nextOutputBoundary) {
        this.nextOutputBoundary = nextOutputBoundary;
    }
    @Override
    public void execute(NextInputData nextInputData) {
        // player.nextsong(); // hagen: commenting out this part first since not everyone has this import and the build won't run then

        NextOutputData nextOutputData = new NextOutputData(nextInputData.getSongStack());
        // TODO: add output boundary
    }
}
