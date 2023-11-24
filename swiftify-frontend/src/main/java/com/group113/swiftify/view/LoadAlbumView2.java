package main.java.com.group113.swiftify.view;

import main.java.com.group113.swiftify.interface_adapter.load_album.LoadAlbumViewModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class LoadAlbumView2 extends JFrame implements ActionListener, PropertyChangeListener {
    private JPanel panel1;
    private LoadAlbumViewModel loadAlbumViewModel;
    private JButton button1;
    private JSlider slider1;
    private JPanel RedAlbum;
    private JPanel FearlessAlbum;
    private JPanel Evermore;
    private JPanel Folklore;
    private JPanel Lover;
    private JPanel Midnights;
    private JPanel Reputation;
    private JPanel SpeakNow;
    private JPanel TaylorSwift;
    private JPanel a1989;
    private JPanel Fearless;


    public LoadAlbumView2() {
    //    loadAlbumViewModel = new LoadAlbumViewModel();
      //  loadAlbumViewModel.addPropertyChangeListener(this);

        createUIComponents();
        setContentPane(panel1);
        setTitle("Swiftify - Albums");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(1080, 680);
        setLocationRelativeTo(null);
        setVisible(true);
    }
    public static void main(String[] args) {
        new LoadAlbumView2();
    }

    private void createUIComponents() {
        RedAlbum = new LoadAlbumView1.ImagePanel("/Users/malaikamalik/Desktop/malik130/CSC207REAL/swiftify-frontend/src/red.png");
        setContentPane(RedAlbum);
        Fearless = new LoadAlbumView1.ImagePanel("/Users/malaikamalik/Desktop/malik130/CSC207REAL/swiftify-frontend/src/fearless.png");
        setContentPane(Fearless);
        Folklore = new LoadAlbumView1.ImagePanel("/Users/malaikamalik/Desktop/malik130/CSC207REAL/swiftify-frontend/src/folklore.png");
        setContentPane(Folklore);
        Lover = new LoadAlbumView1.ImagePanel("/Users/malaikamalik/Desktop/malik130/CSC207REAL/swiftify-frontend/src/lover.png");
        setContentPane(Lover);
        Midnights = new LoadAlbumView1.ImagePanel("/Users/malaikamalik/Desktop/malik130/CSC207REAL/swiftify-frontend/src/midnights.png");
        setContentPane(Midnights);
        Evermore = new LoadAlbumView1.ImagePanel("/Users/malaikamalik/Desktop/malik130/CSC207REAL/swiftify-frontend/src/evermore.png");
        setContentPane(Evermore);
        SpeakNow = new LoadAlbumView1.ImagePanel("/Users/malaikamalik/Desktop/malik130/CSC207REAL/swiftify-frontend/src/speak_now.png");
        setContentPane(SpeakNow);
        a1989 = new LoadAlbumView1.ImagePanel("/Users/malaikamalik/Desktop/malik130/CSC207REAL/swiftify-frontend/src/1989.png");
        setContentPane(a1989);
        TaylorSwift = new LoadAlbumView1.ImagePanel("/Users/malaikamalik/Desktop/malik130/CSC207REAL/swiftify-frontend/src/taylor_swift.png");
        setContentPane(TaylorSwift);
        Reputation = new LoadAlbumView1.ImagePanel("/Users/malaikamalik/Desktop/malik130/CSC207REAL/swiftify-frontend/src/reputation.png");
        setContentPane(Reputation);


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
