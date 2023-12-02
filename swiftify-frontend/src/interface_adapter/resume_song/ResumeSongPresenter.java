package interface_adapter.resume_song;

import interface_adapter.ViewManagerModel;
import interface_adapter.pause_song.PauseSongViewModel;
import use_case.pause_song.PauseSongOutputData;
import use_case.resume_song.ResumeSongOutputBoundary;
import use_case.resume_song.ResumeSongOutputData;

public class ResumeSongPresenter implements ResumeSongOutputBoundary {

    private final ResumeSongViewModel resumeSongViewModel;
    private final ViewManagerModel viewManagerModel;

    public ResumeSongPresenter(ResumeSongViewModel resumeSongViewModel, ViewManagerModel viewManagerModel) {
        this.resumeSongViewModel = resumeSongViewModel;
        this.viewManagerModel = viewManagerModel;
    }

    public void prepareResumedView(ResumeSongOutputData resumeSongOutputData) {
        this.resumeSongViewModel.setState(resumeSongOutputData.getState());
        this.resumeSongViewModel.firePropertyChanged();
        this.viewManagerModel.setActiveView(resumeSongViewModel.getViewName());
        this.viewManagerModel.setViewModel(this.resumeSongViewModel);
        this.viewManagerModel.firePropertyChanged();
    }

    public ResumeSongViewModel getModel() {
        return this.resumeSongViewModel;
    }
}
