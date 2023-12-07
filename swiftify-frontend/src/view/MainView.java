package view;

import interface_adapter.load_albums.LoadAlbumsController;
import use_case.load_albums.LoadAlbumsInputData;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainView extends JFrame {
    private static JPanel selectionPanel;
    private static JLabel label;
    private static JComboBox<String> comb;
    private LoadAlbumsController loadAlbumController;

    public MainView(LoadAlbumsController loadAlbumController) {
        this.loadAlbumController = loadAlbumController;
        JFrame frame = new JFrame("Swiftify");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1080, 680);
        frame.getContentPane().setBackground(new Color(223, 225, 229));

        selectionPanel = new JPanel();
        selectionPanel.setLayout(new BoxLayout(selectionPanel, BoxLayout.Y_AXIS));
        selectionPanel.setBackground(new Color(223, 225, 229));

        frame.add(selectionPanel);

        label = new JLabel("Select Artist");
        label.setAlignmentX(Component.CENTER_ALIGNMENT);
        selectionPanel.add(label);

        String[] choices = {"Taylor Swift"};
        comb = new JComboBox<>(choices);
        comb.setMaximumSize(comb.getPreferredSize());
        comb.setAlignmentX(Component.CENTER_ALIGNMENT);
        selectionPanel.add(comb);

        JButton selectionConfirmButton = new JButton("OK");
        selectionConfirmButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        selectionConfirmButton.addActionListener(loadAlbumsActionListener);
        selectionPanel.add(selectionConfirmButton);

        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Swiftify");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1080, 680);
        frame.getContentPane().setBackground(new Color(99, 93, 133));

        selectionPanel = new JPanel();
        selectionPanel.setLayout(new BoxLayout(selectionPanel, BoxLayout.Y_AXIS));
        selectionPanel.setBackground(new Color(99, 93, 133));

        frame.add(selectionPanel);

        JLabel label = new JLabel("Select album type");
        label.setAlignmentX(Component.CENTER_ALIGNMENT);
        selectionPanel.add(label);

        String[] choices = {"Group 113's favourites", "All"};
        comb = new JComboBox<>(choices);
        comb.setMaximumSize(comb.getPreferredSize());
        comb.setAlignmentX(Component.CENTER_ALIGNMENT);
        selectionPanel.add(comb);

        JButton b = new JButton("OK");
        b.setAlignmentX(Component.CENTER_ALIGNMENT);
        selectionPanel.add(b);

        frame.setVisible(true);
    }

    ActionListener loadAlbumsActionListener = new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            e.setSource(comb);
            LoadAlbumsInputData loadAlbumsInputData = new LoadAlbumsInputData(((JComboBox) e.getSource()).getSelectedItem().toString());
            MainView.this.loadAlbumController.execute(loadAlbumsInputData);
        }
    };
}