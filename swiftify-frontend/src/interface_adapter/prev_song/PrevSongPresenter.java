package interface_adapter.prev_song;

import interface_adapter.SongPlaybackState;
import interface_adapter.ViewManagerModel;
import interface_adapter.prev_song.PrevSongViewModel;
import use_case.prev_song.PrevSongOutputBoundary;
import use_case.prev_song.PrevSongOutputData;

public class PrevSongPresenter implements PrevSongOutputBoundary {
    private final PrevSongViewModel prevSongViewModel;

    private final ViewManagerModel viewManagerModel;

    public PrevSongPresenter(PrevSongViewModel prevSongViewModel, ViewManagerModel viewManagerModel) {
        this.prevSongViewModel = prevSongViewModel;
        this.viewManagerModel = viewManagerModel;
    }

    public void preparePrevView(PrevSongOutputData prevSongOutputData) {
        SongPlaybackState songPlaybackState = new SongPlaybackState(prevSongOutputData.getView(), prevSongOutputData.getSong(), prevSongOutputData.getMusicPlayer(), true);
        this.prevSongViewModel.setState(songPlaybackState);
        this.prevSongViewModel.firePropertyChanged();
        viewManagerModel.setActiveView(prevSongViewModel.getViewName());
        viewManagerModel.setViewModel(this.prevSongViewModel);
        viewManagerModel.firePropertyChanged();
    }

    public PrevSongViewModel getModel() {
        return this.prevSongViewModel;
    }
}
