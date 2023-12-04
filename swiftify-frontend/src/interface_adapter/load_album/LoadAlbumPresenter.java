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


    public void loadAlbums(LoadAlbumsOutputData loadAlbumsOutputData) {
        LoadAlbumState loadAlbumState = (LoadAlbumState) loadAlbumViewModel.getState();
        if (loadAlbumState == null) {
            switch (loadAlbumsOutputData.getSelection()) {
                case ("Taylor Swift") :
                    loadAlbumViewModel.setState(new LoadAlbumState("TaylorSwift", loadAlbumsOutputData.getAlbums()));
            }
            loadAlbumViewModel.firePropertyChanged();
        }
        viewManagerModel.setActiveView(loadAlbumViewModel.getViewName());
        viewManagerModel.setViewModel(this.loadAlbumViewModel);
        viewManagerModel.firePropertyChanged();

    }

}