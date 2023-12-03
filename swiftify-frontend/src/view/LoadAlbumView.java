package view;

import entity.Album;
import interface_adapter.load_album.LoadAlbumState;
import interface_adapter.load_album.LoadAlbumViewModel;
import interface_adapter.load_songs.LoadSongsController;
import use_case.load_songs.LoadSongsInputData;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class LoadAlbumView extends JFrame {

    private JPanel AlbumsPanel;
    private LoadAlbumViewModel loadAlbumViewModel;
    private JButton Album10;
    private JButton Album5;
    private JButton Album4;
    private JButton Album3;
    private JButton Album9;
    private JButton Album8;
    private JButton Album7;
    private JButton Album6;
    private JButton Album2;
    private JButton Album1;
    private JPanel Panel1;
    private JPanel Panel6;
    private JPanel Panel2;
    private JPanel Panel3;
    private JPanel Panel4;
    private JPanel Panel5;
    private JPanel Panel10;
    private JPanel Panel7;
    private JPanel Panel8;
    private JPanel Panel9;
    private JPanel MainPanel;
    private static LoadSongsController loadSongsController;
    private List<JPanel> panelList;

    public LoadAlbumView(LoadSongsController loadSongsController, List<JPanel> panelList) {
        //LoadAlbumViewModel loadAlbumViewModel = new LoadAlbumViewModel();
        //loadAlbumViewModel.addPropertyChangeListener((PropertyChangeListener) this);

        this.loadSongsController = loadSongsController;
        this.panelList = panelList;
        createUIComponents();
        setContentPane(MainPanel);
        setTitle("Swiftify - Albums");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(1080, 680);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    static ActionListener loadSongsActionListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() instanceof JButton) {
                String album = e.getActionCommand();
                String albumName = convertFunc(album);

                LoadSongsInputData loadSongsInputData = new LoadSongsInputData(albumName);
                loadSongsController.execute(loadSongsInputData);
            }
        }

        private String convertFunc(String albumName_Button) {
            if (albumName_Button.endsWith("Button")) {
                String withoutButton = albumName_Button.substring(0, albumName_Button.length() - "Button".length());
                return withoutButton.replaceAll("([a-z])([A-Z])", "$1 $2");
            } else {
                return albumName_Button;
            }
        }
    };

    public static void main(String[] args) {

    }

    public static List<JPanel> loadAlbums(LoadAlbumState loadAlbumState) {
        List<JPanel> panels = new ArrayList<JPanel>();
        for (Album album : loadAlbumState.getAlbums()) {
            JPanel panel = new ImagePanel(getAlbumCover(album.getName()));
            JButton button = new JButton(getAlbumCover(album.getName()));
            button.addActionListener(loadSongsActionListener);
            panels.add(panel);
        }
        return panels;
    }

    private static String getAlbumCover(String albumName) {
        return switch (albumName) {
            case ("Speak Now (Deluxe Edition)") -> "swiftify-frontend/src/pngs/speak_now.png";
            case ("folklore") -> "swiftify-frontend/src/pngs/folklore.png";
            case ("Lover") -> "swiftify-frontend/src/pngs/lover.png";
            case ("Red (Taylor's Version)") -> "swiftify-frontend/src/pngs/red.png";
            case ("Fearless (Taylor's Version)") -> "swiftify-frontend/src/pngs/fearless.png";
            case ("evermore") -> "swiftify-frontend/src/pngs/evermore.png";
            case ("Reputation") -> "swiftify-frontend/src/pngs/reputation.png";
            case ("1989 (Deluxe)") -> "swiftify-frontend/src/pngs/1989.png";
            case ("Midnights") -> "swiftify-frontend/src/pngs/midnights.png";
            default -> "";
        };
    }

    private void createUIComponents() {
        Panel1 = this.panelList.get(0);
        setContentPane(Panel1);
        Panel2 = this.panelList.get(1);
        setContentPane(Panel2);
        Panel3 = this.panelList.get(2);
        setContentPane(Panel3);
        Panel4 = this.panelList.get(3);
        setContentPane(Panel4);
        Panel5 = this.panelList.get(4);
        setContentPane(Panel5);
        Panel6 = this.panelList.get(5);
        setContentPane(Panel6);
        Panel7 = this.panelList.get(6);
        setContentPane(Panel7);
        Panel8 = this.panelList.get(7);
        setContentPane(Panel8);
        Panel9 = this.panelList.get(8);
        setContentPane(Panel9);

        /*
        Panel1 = new ImagePanel("swiftify-frontend/src/pngs/taylor_swift.png");
        setContentPane(Panel1);

        Panel6 = new ImagePanel("swiftify-frontend/src/pngs/fearless.png");
        setContentPane(Panel6);

        Panel2 = new ImagePanel("swiftify-frontend/src/pngs/speak_now.png");
        setContentPane(Panel2);

        Panel3 = new ImagePanel("swiftify-frontend/src/pngs/1989.png");
        setContentPane(Panel3);

        Panel4 = new ImagePanel("swiftify-frontend/src/pngs/lover.png");
        setContentPane(Panel4);

        Panel5 = new ImagePanel("swiftify-frontend/src/pngs/evermore.png");
        setContentPane(Panel5);

        Panel10 = new ImagePanel("swiftify-frontend/src/pngs/midnights.png");
        setContentPane(Panel10);

        Panel7 = new ImagePanel("swiftify-frontend/src/pngs/red.png");
        setContentPane(Panel7);

        Panel9 = new ImagePanel("swiftify-frontend/src/pngs/folklore.png");
        setContentPane(Panel9);

        Panel8 = new ImagePanel("swiftify-frontend/src/pngs/reputation.png");
        setContentPane(Panel8);

         */
    }

    static class ImagePanel extends JPanel {
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

