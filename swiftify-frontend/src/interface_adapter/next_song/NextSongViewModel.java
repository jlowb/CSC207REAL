package interface_adapter.next_song;

import interface_adapter.SongPlaybackState;
import interface_adapter.State;
import interface_adapter.ViewModel;
import use_case.next_song.NextSongInputData;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class NextSongViewModel extends ViewModel {
    private SongPlaybackState state;

    public NextSongViewModel() {
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
