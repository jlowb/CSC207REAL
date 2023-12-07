package view;

import interface_adapter.load_album.LoadAlbumController;
import interface_adapter.load_album.LoadAlbumViewModel;
import interface_adapter.load_songs.LoadSongsController;
import use_case.load_album.LoadAlbumInteractor;
import use_case.load_album.LoadAlbumsInputData;
import use_case.load_songs.LoadSongsInputData;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeListener;
import java.util.concurrent.Executor;


public class MainView extends JFrame {
    private static JPanel panel;
    private static JComboBox<String> comb;
    private LoadAlbumController loadAlbumController;
    private LoadAlbumView loadAlbumView;
    LoadSongsView loadSongsView;


    public MainView(LoadAlbumController loadAlbumController) {
        //LoadAlbumViewModel loadAlbumViewModel = new LoadAlbumViewModel();
        //loadAlbumViewModel.addPropertyChangeListener((PropertyChangeListener) this);
        JFrame frame = new JFrame("Swiftify");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1080, 680);
        frame.getContentPane().setBackground(new Color(99, 93, 133));
        LoadSongsController loadSongsController;


        panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(new Color(99, 93, 133));

        frame.add(panel);

        JLabel label = new JLabel("Select Artist");
        label.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(label);

        String[] choices = {"Taylor Swift"};
        comb = new JComboBox<>(choices);
        comb.setMaximumSize(comb.getPreferredSize());
        comb.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(comb);

        JButton b = new JButton("OK");
        b.setAlignmentX(Component.CENTER_ALIGNMENT);
        b.addActionListener(loadAlbumsActionListener);
        panel.add(b);

        frame.setVisible(true);
    }
    public LoadAlbumView getLoadAlbumView() {
        return loadAlbumView;
    }
    public void pressOkButton() {
        LoadAlbumsInputData loadAlbumsInputData = new LoadAlbumsInputData("All");
        loadAlbumController.execute(loadAlbumsInputData);
        loadAlbumView.setVisible(true);
    }

    public LoadSongsView getSongView() {
        return loadSongsView;
    }
    public void pressAlbumButton(String albumName) {
        LoadSongsInputData loadSongsInputData = new LoadSongsInputData(albumName);
        LoadSongsController loadSongsController = null;
        loadSongsController.execute(loadSongsInputData);
        loadSongsView.setVisible(true);
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Swiftify");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1080, 680);
        frame.getContentPane().setBackground(new Color(99, 93, 133));

        panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(new Color(99, 93, 133));

        frame.add(panel);

        JLabel label = new JLabel("Select album type");
        label.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(label);

        String[] choices = {"Group 113's favourites", "All"};
        comb = new JComboBox<>(choices);
        comb.setMaximumSize(comb.getPreferredSize());
        comb.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(comb);

        JButton b = new JButton("OK");
        b.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(b);

        frame.setVisible(true);
    }

    public ActionListener loadAlbumsActionListener = new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            LoadAlbumsInputData loadAlbumsInputData = new LoadAlbumsInputData("All");
            MainView.this.loadAlbumController.execute(loadAlbumsInputData);
        }
    };
}