package interface_adapter.resume_song;

import interface_adapter.SongPlaybackState;
import interface_adapter.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class ResumeSongViewModel extends ViewModel {

    private SongPlaybackState state;

    public ResumeSongViewModel(SongPlaybackState state) {
        super("ResumeSongView", state);
    }

    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    public void firePropertyChanged() {
        support.firePropertyChange("state", null, getState());
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }
}
