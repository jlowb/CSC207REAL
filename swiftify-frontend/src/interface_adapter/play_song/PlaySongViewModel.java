package interface_adapter.play_song;

import interface_adapter.SongPlaybackState;
import interface_adapter.State;
import interface_adapter.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class PlaySongViewModel extends ViewModel {

    private SongPlaybackState state;

    public PlaySongViewModel() {
        super("PlaySongView", new State());
    }

    public void setState(SongPlaybackState state) {
        this.state = state;
    }

    public SongPlaybackState getState() {
        return this.state;
    }

    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    public void firePropertyChanged() {
        support.firePropertyChange("state", null, getState());
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }
}
