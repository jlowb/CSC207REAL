package interface_adapter.load_album;


import interface_adapter.ViewManagerModel;
import use_case.load_album.LoadAlbumsOutputBoundary;
import use_case.load_album.LoadAlbumsOutputData;


import java.util.LinkedList;

public class LoadAlbumPresenter implements LoadAlbumsOutputBoundary {

    private final LoadAlbumViewModel loadAlbumViewModel;
    private final ViewManagerModel viewManagerModel;

    public LoadAlbumPresenter(LoadAlbumViewModel loadAlbumViewModel, interface_adapter.ViewManagerModel viewManagerModel) {
        this.loadAlbumViewModel = loadAlbumViewModel;
        this.viewManagerModel = viewManagerModel;
    }

    @Override
    public void presentAlbums(LinkedList<String> albums) {

    }

    @Override
    public void loadAlbums(LoadAlbumsOutputData loadAlbumsOutputData) {
        LoadAlbumState loadAlbumState = (LoadAlbumState) loadAlbumViewModel.getState();
        if (loadAlbumState == null) {
            loadAlbumViewModel.setState(new LoadAlbumState(loadAlbumsOutputData.getAlbumType()));
            loadAlbumViewModel.firePropertyChanged();
        }
        viewManagerModel.setActiveView(loadAlbumViewModel.getViewName());
        viewManagerModel.setViewModel(this.loadAlbumViewModel);
        viewManagerModel.firePropertyChanged();

    }

    @Override
    public LoadAlbumViewModel getModel() {
        return this.loadAlbumViewModel;
    }

    @Override
    public void prepareSuccessView(LoadAlbumsOutputData loadAlbumsOutputData) {

    }

    @Override
    public void displayView(String albumType) {

    }
}