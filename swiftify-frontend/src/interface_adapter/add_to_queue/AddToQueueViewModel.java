package interface_adapter.add_to_queue;

import interface_adapter.SongPlaybackState;
import interface_adapter.State;
import interface_adapter.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class AddToQueueViewModel extends ViewModel {

    public AddToQueueViewModel() {
        super("AddToQueueView", new State());
    }

    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    public void firePropertyChanged() {
        support.firePropertyChange("state", null, getState());
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }
}
