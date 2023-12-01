package view;

import interface_adapter.load_album.LoadAlbumController;
import interface_adapter.load_album.LoadAlbumViewModel;
import use_case.load_album.DataAccess;
import use_case.load_album.LoadAlbumInteractor;
import use_case.load_album.LoadAlbumsInputBoundary;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeListener;

public class Vieww {
    private static JPanel panel;
    private static JComboBox<String> comb;

    public Vieww() {
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

            String[] choices = {"Deluxe / Deluxe Edition", "Taylor's Version", "All"};
            comb = new JComboBox<>(choices);
            comb.setMaximumSize(comb.getPreferredSize());
            comb.setAlignmentX(Component.CENTER_ALIGNMENT);
            panel.add(comb);

            JButton b = new JButton("OK");
            b.setAlignmentX(Component.CENTER_ALIGNMENT);
            panel.add(b);

            frame.setVisible(true);

            ActionListener actionListener = new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (e.getSource() instanceof JButton) {
                        DataAccess dataAccess = new DataAccess();


                        LoadAlbumInteractor loadAlbumInteractor = dataAccess.LoadAlbumInteractor();

                        LoadAlbumsInputBoundary loadAlbumsInputBoundary = loadAlbumInteractor;
                        LoadAlbumController controller = new LoadAlbumController(loadAlbumsInputBoundary);

                        String AlbumType = (String) comb.getSelectedItem();
                        controller.execute(AlbumType);

                        if ("All".equals(AlbumType)) {
                            LoadAlbumView1 page = new LoadAlbumView1();
                            page.setVisible(true);
                        } else if ("Deluxe / Deluxe Edition".equals(AlbumType)) {
                            deluxes page = new deluxes();
                            page.setVisible(true);
                        } else if ("Taylor's Version".equals(AlbumType)) {
                            taylorver page = new taylorver();
                            page.setVisible(true);
                        }



                    }
                    frame.dispose();
                }
            };

            b.addActionListener(actionListener);
        ;
    }


}