package interface_adapter.load_album;


// import main.java.com.group113.swiftify.entity.Album;

import entity.Album;
import interface_adapter.State;
import interface_adapter.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.LinkedList;
import java.util.List;

public class LoadAlbumViewModel extends ViewModel {
    private final PropertyChangeSupport propertyChangeSupport;
 //   private List<Album> albums;
    private String selectedAlbum;
    private List<Album> albums;


    public LoadAlbumViewModel() {
        super ("AlbumView", new State());
        propertyChangeSupport = new PropertyChangeSupport(this);
        albums = new LinkedList<>();
    }

    public List<Album> getAlbums() {
        return albums;
    }

    private final PropertyChangeSupport support = new PropertyChangeSupport(this);
    public void firePropertyChanged() {
        support.firePropertyChange("state", null, new State());
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        propertyChangeSupport.addPropertyChangeListener(listener);
    }

}

