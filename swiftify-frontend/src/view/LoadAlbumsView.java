package view;

import entity.Album;
import entity.SongPlaybackButton;
import interface_adapter.load_albums.LoadAlbumsState;
import interface_adapter.load_albums.LoadAlbumsViewModel;
import interface_adapter.load_songs.LoadSongsController;
import use_case.load_songs.LoadSongsInputData;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
/**
 * Represents the view for loading albums in the Swiftify application.
 */
public class LoadAlbumsView extends JFrame {

    private LoadAlbumsViewModel loadAlbumViewModel;
    private JPanel MainPanel;
    private JPanel AlbumPanel1;
    private JPanel AlbumPanel2;
    private JButton ShuffleButton;
    private JPanel ControlsPanel;
    private SongPlaybackButton PreviousSongButton;
    private SongPlaybackButton PlayPauseButton;
    private SongPlaybackButton NextSongButton;

    private static LoadSongsController loadSongsController;
    /**
     * Constructs a new LoadAlbumsView with the specified LoadSongsController.
     *
     * @param loadSongsController The controller responsible for loading songs.
     */


    public LoadAlbumsView(LoadSongsController loadSongsController) {
        this.loadSongsController = loadSongsController;

        setContentPane(MainPanel);
        adjustUIComponents();
        setTitle("Swiftify - Albums");
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setSize(1080, 680);
        setLocationRelativeTo(null);
        setVisible(true);
    }
    /**
     * ActionListener for handling album loading when a button is clicked.
     */

    ActionListener loadSongsActionListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() instanceof JButton) {
                String album = e.getActionCommand();
                String albumName = convertFunc(album);

                LoadSongsInputData loadSongsInputData = new LoadSongsInputData(albumName);
                loadSongsController.execute(loadSongsInputData);
                LoadAlbumsView.this.setVisible(false);
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
    /**
     * Loads album panels based on the provided LoadAlbumsState.
     *
     * @param loadAlbumState The state containing information about loaded albums.
     */

    public void loadAlbumPanels(LoadAlbumsState loadAlbumState) {
        List<JPanel> panels = new ArrayList<JPanel>();
        int i = 1;
        for (Album album : loadAlbumState.getAlbums()) {
            JPanel panel = new ImagePanel(getAlbumCover(album.getName()));
            panel.setLayout(new BorderLayout());
            panel.setBackground(new Color(223, 225, 229));
            JButton button = new JButton(album.getName());
            button.addActionListener(loadSongsActionListener);
            panel.add(button, BorderLayout.SOUTH);
            if (i % 2 != 0) {
                AlbumPanel1.add(panel);
            }
            else {
                AlbumPanel2.add(panel);
            }
            i++;
        }
    }

    /*
    public List<JButton> loadAlbumButtons(LoadAlbumsState loadAlbumState) {
        List<JButton> buttons = new ArrayList<JButton>();
        for (Album album : loadAlbumState.getAlbums()) {
            JButton button = new JButton(album.getName());
            button.addActionListener(loadSongsActionListener);
            buttons.add(button);
        }
        return buttons;
    }

     */
    /**
     * Retrieves the album cover image path based on the album name.
     *
     * @param albumName The name of the album.
     * @return The path to the album cover image.
     */

    public static String getAlbumCover(String albumName) {
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

    private void adjustUIComponents() {
        AlbumPanel1.setLayout(new GridLayout(5, 1));
        AlbumPanel2.setLayout(new GridLayout(5, 1));
    }
    /**
     * Represents a JPanel with a background image.
     */

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

