package view;

import interface_adapter.load_album.LoadAlbumController;
import interface_adapter.load_album.LoadAlbumViewModel;
import use_case.load_album.LoadAlbumInteractor;
import use_case.load_album.LoadAlbumsInputData;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeListener;

public class MainView extends JFrame {
    private static JPanel panel;
    private static JComboBox<String> comb;
    private LoadAlbumController loadAlbumController;

    public MainView(LoadAlbumController loadAlbumController) {
        //LoadAlbumViewModel loadAlbumViewModel = new LoadAlbumViewModel();
        //loadAlbumViewModel.addPropertyChangeListener((PropertyChangeListener) this);
        this.loadAlbumController = loadAlbumController;
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
        b.addActionListener(loadAlbumsActionListener);
        panel.add(b);

        frame.setVisible(true);
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

    ActionListener loadAlbumsActionListener = new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            LoadAlbumsInputData loadAlbumsInputData = new LoadAlbumsInputData("All");
            MainView.this.loadAlbumController.execute(loadAlbumsInputData);
        }
    };
}