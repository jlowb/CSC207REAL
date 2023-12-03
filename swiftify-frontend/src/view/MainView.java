package view;

import interface_adapter.load_album.LoadAlbumViewModel;

import javax.swing.*;
import java.awt.*;
import java.beans.PropertyChangeListener;

public class MainView {
    private static JPanel panel;
    private static JComboBox<String> comb;

    public MainView(String AlbumType) {
        LoadAlbumViewModel loadAlbumViewModel = new LoadAlbumViewModel();
        loadAlbumViewModel.addPropertyChangeListener((PropertyChangeListener) this);
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
}