package interface_adapter.load_songs;

import interface_adapter.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class LoadSongsViewModel extends ViewModel {

    private LoadSongsState state;

    public LoadSongsViewModel() {
        super("Songs View");
    }

    public void setState(LoadSongsState state) {
        this.state = state;
    }

    public LoadSongsState getState() {
        return this.state;
    }

    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    // This is what the Signup Presenter will call to let the ViewModel know
    // to alert the View
    public void firePropertyChanged() {
        support.firePropertyChange("state", null, this.state);
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }
}
