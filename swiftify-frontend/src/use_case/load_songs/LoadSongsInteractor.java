package use_case.load_songs;

public class LoadSongsInteractor implements LoadSongsInputBoundary {

    public LoadSongsInteractor() {

    }

    @Override
    public void execute() {
        // query S3 database to get list of album names, then present back to view
    }
}
