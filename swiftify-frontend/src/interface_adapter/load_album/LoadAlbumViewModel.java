package interface_adapter.load_album;


// import main.java.com.group113.swiftify.entity.Album;

import interface_adapter.State;
import interface_adapter.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class LoadAlbumViewModel extends ViewModel {

    private LoadAlbumState state;

    public LoadAlbumViewModel() {
        super("LoadAlbumsView", new State());
    }

    public LoadAlbumState getState() {
        return this.state;
    }

    public void setState(LoadAlbumState state) {
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

