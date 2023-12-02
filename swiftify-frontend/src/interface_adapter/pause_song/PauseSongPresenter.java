package interface_adapter.pause_song;

import interface_adapter.ViewManagerModel;
import use_case.pause_song.PauseSongInteractor;
import use_case.pause_song.PauseSongOutputBoundary;
import use_case.pause_song.PauseSongOutputData;

public class PauseSongPresenter implements PauseSongOutputBoundary {

    private final PauseSongViewModel pauseSongViewModel;
    private final ViewManagerModel viewManagerModel;

    public PauseSongPresenter(PauseSongViewModel pauseSongViewModel, ViewManagerModel viewManagerModel) {
        this.pauseSongViewModel = pauseSongViewModel;
        this.viewManagerModel = viewManagerModel;
    }

    public void preparePausedView(PauseSongOutputData pauseSongOutputData) {
        this.pauseSongViewModel.setState(pauseSongOutputData.getState());
        this.pauseSongViewModel.firePropertyChanged();
        this.viewManagerModel.setActiveView(pauseSongViewModel.getViewName());
        this.viewManagerModel.setViewModel(this.pauseSongViewModel);
        this.viewManagerModel.firePropertyChanged();
    }

    public PauseSongViewModel getModel() {
        return this.pauseSongViewModel;
    }
}
