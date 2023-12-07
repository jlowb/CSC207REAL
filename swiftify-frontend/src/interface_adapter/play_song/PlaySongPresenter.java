/**
 * The {@code PlaySongPresenter} class is responsible for presenting the view
 * and managing the state when playing a song. It implements the
 * {PlaySongOutputBoundary} interface to handle the output from the
 * "Play Song" use case.
 * Provides the Prepare view and to get the playSongViewModel
 * @author [Malaika]
 *
 */

package interface_adapter.play_song;

import interface_adapter.SongPlaybackState;
import interface_adapter.ViewManagerModel;
import use_case.play_song.PlaySongOutputBoundary;
import use_case.play_song.PlaySongOutputData;

import javax.swing.text.View;

public class PlaySongPresenter implements PlaySongOutputBoundary {

    private final PlaySongViewModel playSongViewModel;
    private final ViewManagerModel viewManagerModel;

    public PlaySongPresenter(PlaySongViewModel playSongViewModel, ViewManagerModel viewManagerModel) {
        this.playSongViewModel = playSongViewModel;
        this.viewManagerModel = viewManagerModel;
    }

    public void preparePlayingView(PlaySongOutputData playSongOutputData) {
        SongPlaybackState songPlaybackState = new SongPlaybackState(playSongOutputData.getView(), playSongOutputData.getSong(), playSongOutputData.getMusicPlayer(), true);
        this.playSongViewModel.setState(songPlaybackState);
        this.playSongViewModel.firePropertyChanged();
        viewManagerModel.setActiveView(playSongViewModel.getViewName());
        viewManagerModel.setViewModel(this.playSongViewModel);
        viewManagerModel.firePropertyChanged();
    }

    public PlaySongViewModel getModel() {
        return this.playSongViewModel;
    }
}
