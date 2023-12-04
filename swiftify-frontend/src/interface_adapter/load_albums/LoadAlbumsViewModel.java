package interface_adapter.load_albums;


// import main.java.com.group113.swiftify.entity.Album;

import interface_adapter.State;
import interface_adapter.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class LoadAlbumsViewModel extends ViewModel {

    private LoadAlbumsState state;

    public LoadAlbumsViewModel() {
        super("LoadAlbumsView", new State());
    }

    public LoadAlbumsState getState() {
        return this.state;
    }

    public void setState(LoadAlbumsState state) {
        this.state = state;
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

