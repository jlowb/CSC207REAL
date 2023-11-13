package main.java.com.group113.swiftify.view;

import main.java.com.group113.swiftify.interface_adapter.load_album.LoadAlbumViewModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class LoadAlbumView1 extends JFrame implements ActionListener, PropertyChangeListener {

    private JPanel panel;
    private LoadAlbumViewModel loadAlbumViewModel;
    private JButton midnightsButton;
    private JButton evermoreButton;
    private JButton loverButton;
    private JButton a1989Button;
    private JButton folkloreButton;
    private JButton reputationButton;
    private JButton redButton;
    private JButton fearlessButton;
    private JButton speakNowButton;
    private JButton taylorSwiftButton;
    private JPanel panel11;
    private JPanel fearlesspanel;
    private JPanel speaknowpanel;
    private JPanel p1989panel;
    private JPanel loverpanel;
    private JPanel evermorepanel;
    private JPanel midnightspanel;
    private JPanel redpanel;
    private JPanel reputationpanel;
    private JPanel folklorepanel;


    public LoadAlbumView1() {
        loadAlbumViewModel = new LoadAlbumViewModel();
        loadAlbumViewModel.addPropertyChangeListener(this);

        createUIComponents();
        setContentPane(panel);
        setTitle("Swiftify - Albums");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(1080, 680);
        setLocationRelativeTo(null);
        setVisible(true);


        midnightsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        evermoreButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        loverButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        a1989Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        folkloreButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        reputationButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        redButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedAlbum = loadAlbumViewModel.getSelectedAlbum();
                loading_album redPage = new loading_album(selectedAlbum);
                redPage.setVisible(true);

                dispose();

            }
        });
        fearlessButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        speakNowButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        taylorSwiftButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
    }


    public static void main(String[] args) {
        new LoadAlbumView1();
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
        panel11 = new ImagePanel("/Users/malaikamalik/Desktop/malik130/CSC207REAL/swiftify-frontend/src/taylor_swift.png");
        setContentPane(panel11);

        fearlesspanel = new ImagePanel("/Users/malaikamalik/Desktop/malik130/CSC207REAL/swiftify-frontend/src/fearless.png");
        setContentPane(fearlesspanel);

        speaknowpanel = new ImagePanel("/Users/malaikamalik/Desktop/malik130/CSC207REAL/swiftify-frontend/src/speak_now.png");
        setContentPane(speaknowpanel);

        p1989panel = new ImagePanel("/Users/malaikamalik/Desktop/malik130/CSC207REAL/swiftify-frontend/src/1989.png");
        setContentPane(p1989panel);

        loverpanel = new ImagePanel("/Users/malaikamalik/Desktop/malik130/CSC207REAL/swiftify-frontend/src/lover.png");
        setContentPane(loverpanel);

        evermorepanel = new ImagePanel("/Users/malaikamalik/Desktop/malik130/CSC207REAL/swiftify-frontend/src/evermore.png");
        setContentPane(evermorepanel);

        midnightspanel = new ImagePanel("/Users/malaikamalik/Desktop/malik130/CSC207REAL/swiftify-frontend/src/midnights.png");
        setContentPane(midnightspanel);

        redpanel = new ImagePanel("/Users/malaikamalik/Desktop/malik130/CSC207REAL/swiftify-frontend/src/red.png");
        setContentPane(redpanel);

        folklorepanel = new ImagePanel("/Users/malaikamalik/Desktop/malik130/CSC207REAL/swiftify-frontend/src/folklore.png");
        setContentPane(folklorepanel);

        reputationpanel = new ImagePanel("/Users/malaikamalik/Desktop/malik130/CSC207REAL/swiftify-frontend/src/reputation.png");
        setContentPane(reputationpanel);



    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {

    }

    private static class ImagePanel extends JPanel {
        private Image backgroundImage;

        public ImagePanel(String imagePath) {
            this.backgroundImage = new ImageIcon(imagePath).getImage();
            setPreferredSize(new Dimension(backgroundImage.getWidth(this), backgroundImage.getHeight(this)));
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);

            double panelWidth = getWidth();
            double panelHeight = getHeight();
            double imageWidth = backgroundImage.getWidth(this);
            double imageHeight = backgroundImage.getHeight(this);

            double scale = Math.min(panelWidth / imageWidth, panelHeight / imageHeight);
            int scaledWidth = (int) (imageWidth * scale);
            int scaledHeight = (int) (imageHeight * scale);

            int x = (int) ((panelWidth - scaledWidth) / 2);
            int y = (int) ((panelHeight - scaledHeight) / 2);

            g.drawImage(backgroundImage, x, y, scaledWidth, scaledHeight, this);
        }
    }



}