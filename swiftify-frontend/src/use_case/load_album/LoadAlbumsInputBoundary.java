package use_case.load_album;

public interface LoadAlbumsInputBoundary {
    public void execute(LoadAlbumsInputData loadAlbumsInputData);
    public LoadAlbumsOutputBoundary getOutputBoundary();



}
