package interface_adapter.pause_song;

import entity.Song;
import interface_adapter.SongPlaybackState;
import interface_adapter.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class PauseSongViewModel extends ViewModel {

    private SongPlaybackState state;

    public PauseSongViewModel(SongPlaybackState state) {
        super("PauseSongView", state);
    }

    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    public void firePropertyChanged() {
        support.firePropertyChange("state", null, getState());
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }
}
