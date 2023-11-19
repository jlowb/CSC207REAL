package main.java.com.group113.swiftify.view;

<<<<<<< HEAD
import main.java.com.group113.swiftify.interface_adapter.load_album.LoadAlbumViewModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.LinkedList;


public class LoadAlbumView extends JPanel implements ActionListener, PropertyChangeListener {
    private JButton LoadAlbumButton;
    private final LoadAlbumViewModel loadAlbumViewModel;
    private JLabel TaylorSwift_album;
    private JLabel Fearless_album;
    private JLabel Speak_Now_album;
    private JLabel Red_album;
    private JLabel _1989_album;
    private JLabel Reputation_album;
    private JLabel Lover_album;
    private JLabel Folklore_album;
    private JLabel Evermore_album;
    private JLabel Midnights_album;
    private String selectedAlbum;

    public LoadAlbumView(LoadAlbumViewModel loadAlbumViewModel) {
        this.loadAlbumViewModel = loadAlbumViewModel;
        this.loadAlbumViewModel.addPropertyChangeListener(this);

        JLabel title = new JLabel("Taylor Swift Albums");
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel TaylorSwift_album = new JLabel("Taylor Swift");
        JLabel Fearless_album = new JLabel("Fearless");
        JLabel Speak_Now_album = new JLabel("Speak Now");
        JLabel Red_album = new JLabel("Red");
        JLabel _1989_album = new JLabel("1989");
        JLabel Reputation_album = new JLabel("Reputation");
        JLabel Lover_album = new JLabel("Lover");
        JLabel Folklore_album = new JLabel("Folklore");
        JLabel Evermore_album = new JLabel("Evermore");
        JLabel Midnights_album = new JLabel("Midnights");

        TaylorSwift_album.addMouseListener(new AlbumLabelClickListener("Taylor Swift"));
        Fearless_album.addMouseListener(new AlbumLabelClickListener("Fearless"));
        Speak_Now_album.addMouseListener(new AlbumLabelClickListener("Speak Now"));
        Red_album.addMouseListener(new AlbumLabelClickListener("Red"));
        _1989_album.addMouseListener(new AlbumLabelClickListener("1989"));
        Reputation_album.addMouseListener(new AlbumLabelClickListener("Reputation"));
        Lover_album.addMouseListener(new AlbumLabelClickListener("Lover"));
        Folklore_album.addMouseListener(new AlbumLabelClickListener("Folklore"));
        Evermore_album.addMouseListener(new AlbumLabelClickListener("Evermore"));
        Midnights_album.addMouseListener(new AlbumLabelClickListener("Midnights"));

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        this.add(title);
        this.add(TaylorSwift_album);
        this.add(Fearless_album);
        this.add(Speak_Now_album);
        this.add(Red_album);
        this.add(_1989_album);
        this.add(Reputation_album);
        this.add(Lover_album);
        this.add(Folklore_album);
        this.add(Evermore_album);
        this.add(Midnights_album);

    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        String propertyName = evt.getPropertyName();
        if ("selectedAlbum".equals(propertyName)) {
            selectedAlbum = (String) evt.getNewValue();
        }

    }
    private void showAlbumSongs(String albumTitle, LinkedList<String> songs) { // usecase interactor
        JPanel albumSongsPanel = new AlbumSongs(albumTitle, songs);

        this.removeAll();
        this.add(albumSongsPanel);
        this.revalidate();
    }

    public void updateAlbums(LinkedList<String> albums) {
        for (String album : albums) {
            JLabel albumLabel = new JLabel(album);
            albumLabel.addMouseListener(new AlbumLabelClickListener(album));
            this.add(albumLabel);
        }
    }

    private class AlbumSongs extends JPanel {
        public AlbumSongs(String albumTitle, LinkedList<String> songs) {
            setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

            JLabel titleLabel = new JLabel("Songs for Album: " + albumTitle);
            add(titleLabel);

            DefaultListModel<String> listModel = new DefaultListModel<>();
            JList<String> songList = new JList<>(listModel);

            for (String song : songs) {
                listModel.addElement(song);
            }

            // we use logic from the database tho but still dk lol
            add(new JScrollPane(songList));


        }
    }


    private class AlbumLabelClickListener extends MouseAdapter {
        private final String albumTitle;

        public AlbumLabelClickListener(String albumTitle) {
            this.albumTitle = albumTitle;
        }

        @Override
        public void mouseClicked(MouseEvent e) {
            selectedAlbum = albumTitle;
        }
    }
    private LinkedList<String> getSongs(String albumTitle) {
       // use databse here
        LinkedList<String> songs = new LinkedList<>();
        if ("Taylor Swift".equals(albumTitle)) {
            songs.add("Song 1");
            songs.add("Song 2");
        }
        if ("Fearless".equals(albumTitle)) {
            songs.add("Song 1");
        }
        if ("Speak Now".equals(albumTitle)) {
            songs.add("Song 1");
        }
        if ("Red".equals(albumTitle)) {
            songs.add("Song 1");
        }
        if ("1989".equals(albumTitle)) {
            songs.add("Song 1");
        }
        if ("Reputation".equals(albumTitle)) {
            songs.add("Song 1");
        }
        if ("Lover".equals(albumTitle)) {
            songs.add("Song 1");
        }
        if ("Folklore".equals(albumTitle)) {
            songs.add("Song 1");
        }
        if ("Evermore".equals(albumTitle)) {
            songs.add("Song 1");
        }
        if ("Midnights".equals(albumTitle)) {
            songs.add("Song 1");
        }
        return songs;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == LoadAlbumButton) {
            if (selectedAlbum != null) {
                LinkedList<String> songsForAlbum = getSongs(selectedAlbum);
                showAlbumSongs(selectedAlbum, songsForAlbum);
            }
        }
    }

    private void navigateToAlbumSongs(String albumTitle, LinkedList<String> songs) {
        JOptionPane.showMessageDialog(this, "Navigating to the list of songs for: " + albumTitle);
        AlbumSongs albumSongs = new AlbumSongs(albumTitle, songs);

    }
}
=======
public class LoadAlbumView {
}
>>>>>>> main
