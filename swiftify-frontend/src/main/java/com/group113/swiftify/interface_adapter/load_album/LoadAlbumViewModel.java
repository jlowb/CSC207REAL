package main.java.com.group113.swiftify.interface_adapter.load_album;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.LinkedList;

public class LoadAlbumViewModel {
    private final PropertyChangeSupport propertyChangeSupport;
    private LinkedList<String> albums;
    private String selectedAlbum;


    public LoadAlbumViewModel() {
        propertyChangeSupport = new PropertyChangeSupport(this);
        albums = new LinkedList<>();
    }

    public LinkedList<String> getAlbums() {
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

    public void loadAlbumDetails(String albumTitle) {
        if ("Taylor Swift".equals(albumTitle)) {
            LinkedList<String> songs = new LinkedList<>();
            songs.add("Song 1");

            setSelectedAlbum(albumTitle);
            albums.clear();
            albums.add(albumTitle);
        }
        if ("Fearless".equals(albumTitle)) {
            LinkedList<String> songs = new LinkedList<>();
            songs.add("Song 1");

            setSelectedAlbum(albumTitle);
            albums.clear();
            albums.add(albumTitle);
        }
        if ("Speak Now".equals(albumTitle)) {
            LinkedList<String> songs = new LinkedList<>();
            songs.add("Song 1");

            setSelectedAlbum(albumTitle);
            albums.clear();
            albums.add(albumTitle);
        }
        if ("Red".equals(albumTitle)) {
            LinkedList<String> songs = new LinkedList<>();
            songs.add("Song 1");

            setSelectedAlbum(albumTitle);
            albums.clear();
            albums.add(albumTitle);
        }
        if ("1989".equals(albumTitle)) {
            LinkedList<String> songs = new LinkedList<>();
            songs.add("Song 1");

            setSelectedAlbum(albumTitle);
            albums.clear();
            albums.add(albumTitle);
        }
        if ("Reputation".equals(albumTitle)) {
            LinkedList<String> songs = new LinkedList<>();
            songs.add("Song 1");

            setSelectedAlbum(albumTitle);
            albums.clear();
            albums.add(albumTitle);
        }
        if ("Lover".equals(albumTitle)) {
            LinkedList<String> songs = new LinkedList<>();
            songs.add("Song 1");

            setSelectedAlbum(albumTitle);
            albums.clear();
            albums.add(albumTitle);
        }
        if ("Folklore".equals(albumTitle)) {
            LinkedList<String> songs = new LinkedList<>();
            songs.add("Song 1");

            setSelectedAlbum(albumTitle);
            albums.clear();
            albums.add(albumTitle);
        }
        if ("Evermore".equals(albumTitle)) {
            LinkedList<String> songs = new LinkedList<>();
            songs.add("Song 1");

            setSelectedAlbum(albumTitle);
            albums.clear();
            albums.add(albumTitle);
        }
        if ("Midnights".equals(albumTitle)) {
            LinkedList<String> songs = new LinkedList<>();
            songs.add("Song 1");

            setSelectedAlbum(albumTitle);
            albums.clear();
            albums.add(albumTitle);
        }

        propertyChangeSupport.firePropertyChange("selectedAlbum", null, selectedAlbum);
        propertyChangeSupport.firePropertyChange("albums", null, albums);
    }
}
