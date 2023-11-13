package main.java.com.group113.swiftify.interface_adapter.load_album;

import main.java.com.group113.swiftify.entity.Album;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.LinkedList;
import java.util.List;

public class LoadAlbumViewModel {
    private final PropertyChangeSupport propertyChangeSupport;
    private List<Album> albums;
    private String selectedAlbum;


    public LoadAlbumViewModel() {
        propertyChangeSupport = new PropertyChangeSupport(this);
        albums = new LinkedList<>();
    }

    public List<Album> getAlbums() {
        return albums;
    }

    public String getSelectedAlbum() {
        return selectedAlbum;
    }

    public void setSelectedAlbum(String selectedAlbum) {
        String oldSelectedAlbum = this.selectedAlbum;
        this.selectedAlbum = selectedAlbum;
        propertyChangeSupport.firePropertyChange("selectedAlbum", oldSelectedAlbum, selectedAlbum);
    }


    public void addPropertyChangeListener(PropertyChangeListener listener) {
        propertyChangeSupport.addPropertyChangeListener(listener);
    }

}