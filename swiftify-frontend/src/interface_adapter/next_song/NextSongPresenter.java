package interface_adapter.next_song;

import interface_adapter.SongPlaybackState;
import interface_adapter.ViewManagerModel;
import use_case.next_song.NextSongOutputBoundary;
import use_case.next_song.NextSongOutputData;

public class NextSongPresenter implements NextSongOutputBoundary {
    private final NextSongViewModel nextSongViewModel;

    private final ViewManagerModel viewManagerModel;

    public NextSongPresenter(NextSongViewModel nextSongViewModel, ViewManagerModel viewManagerModel) {
        this.nextSongViewModel = nextSongViewModel;
        this.viewManagerModel = viewManagerModel;
    }

    public void prepareNextView(NextSongOutputData nextSongOutputData) {
        SongPlaybackState songPlaybackState = new SongPlaybackState(nextSongOutputData.getView(), nextSongOutputData.getSong(), nextSongOutputData.getMusicPlayer(), true);
        this.nextSongViewModel.setState(songPlaybackState);
        this.nextSongViewModel.firePropertyChanged();
        viewManagerModel.setActiveView(nextSongViewModel.getViewName());
        viewManagerModel.setViewModel(this.nextSongViewModel);
        viewManagerModel.firePropertyChanged();
    }

    public NextSongViewModel getModel() {
        return this.nextSongViewModel;
    }
}
