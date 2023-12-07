package interface_adapter.load_albums;


import interface_adapter.ViewManagerModel;
import use_case.load_albums.LoadAlbumsOutputBoundary;
import use_case.load_albums.LoadAlbumsOutputData;

public class LoadAlbumsPresenter implements LoadAlbumsOutputBoundary {

    private final LoadAlbumsViewModel loadAlbumViewModel;
    private final ViewManagerModel viewManagerModel;

    public LoadAlbumsPresenter(LoadAlbumsViewModel loadAlbumViewModel, interface_adapter.ViewManagerModel viewManagerModel) {
        this.loadAlbumViewModel = loadAlbumViewModel;
        this.viewManagerModel = viewManagerModel;
    }


    public void loadAlbums(LoadAlbumsOutputData loadAlbumsOutputData) {
        LoadAlbumsState loadAlbumState = (LoadAlbumsState) loadAlbumViewModel.getState();
        if (loadAlbumState == null) {
            switch (loadAlbumsOutputData.getSelection()) {
                case ("Taylor Swift") :
                    loadAlbumViewModel.setState(new LoadAlbumsState("TaylorSwift", loadAlbumsOutputData.getAlbums()));
            }
            loadAlbumViewModel.firePropertyChanged();
        }
        viewManagerModel.setActiveView(loadAlbumViewModel.getViewName());
        viewManagerModel.setViewModel(this.loadAlbumViewModel);
        viewManagerModel.firePropertyChanged();

    }

}