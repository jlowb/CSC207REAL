package view;

import data_access.SongLoader;
import entity.Album;
import entity.Song;
import interface_adapter.load_album.LoadAlbumViewModel;
import interface_adapter.load_songs.LoadSongsController;
import interface_adapter.load_songs.LoadSongsPresenter;
import interface_adapter.load_songs.LoadSongsViewModel;
import use_case.load_songs.LoadSongsInputBoundary;
import use_case.load_songs.LoadSongsInputData;
import use_case.load_songs.LoadSongsInteractor;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.LinkedList;

public class LoadAlbumView1 extends JFrame implements ActionListener, PropertyChangeListener {

    private JPanel panel;
    private LoadAlbumViewModel loadAlbumViewModel;
    private JButton MidnightsButton;
    private JButton EvermoreButton;
    private JButton LoverButton;
    private JButton a1989Button;
    private JButton FolkloreButton;
    private JButton ReputationButton;
    private JButton RedButton;
    private JButton FearlessButton;
    private JButton SpeakNowButton;
    private JButton TaylorSwiftButton;
    private JPanel panel11;
    private JPanel fearlesspanel;
    private JPanel Speaknowpanel;
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


        ActionListener a = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() instanceof JButton) {

                 //   loading_album page = new loading_album(e.getActionCommand());
                  //  page.setVisible(true);
                  //  LoadAlbumController controller = new LoadAlbumController();
                   // controller.execute(e.getActionCommand());
                    String album = e.getActionCommand();
                    String albumName = convertFunc(album);

                    // Create an instance of LoadAlbumsInteractor (or your actual implementation)
                  //  LoadAlbumsInputBoundary loadAlbumsInputBoundary = new LoadAlbumInteractor();

                    // Create an instance of LoadAlbumController, passing the LoadAlbumsInputBoundary
                 //   LoadAlbumController controller = new LoadAlbumController(loadAlbumsInputBoundary);

                    // Call the execute method in the controller, passing the album name
                 //   controller.execute(albumName);

                    // hagen code here
                    LoadSongsInputBoundary loadSongsInputBoundary = new LoadSongsInteractor(
                            new SongLoader(), new LoadSongsPresenter(new LoadSongsViewModel())
                    );
                    LoadSongsInputData inputData = new LoadSongsInputData(
                            new Album(albumName, new ArrayList<Song>())
                    );
                    LoadSongsController controller = new LoadSongsController(loadSongsInputBoundary);
                    controller.execute(inputData);
                    LoadSongsViewModel loadSongsViewModel = controller.loadSongsInputBoundary.getOutputBoundary().getModel();
                    loading_album page = new loading_album("testAlbum");
                    for (Song song : loadSongsViewModel.getState().getSongs()) {
                        page.addSong(song.getTitle());
                    }
                    // loading_album page = new loading_album(e.getActionCommand());
                    page.setVisible(true);

                }
                dispose();
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



        MidnightsButton.addActionListener(a);
        EvermoreButton.addActionListener(a);
        LoverButton.addActionListener(a);
        a1989Button.addActionListener(a);
        FolkloreButton.addActionListener(a);
        ReputationButton.addActionListener(a);
        RedButton.addActionListener(a);
        FearlessButton.addActionListener(a);
        SpeakNowButton.addActionListener(a);
        TaylorSwiftButton.addActionListener(a);

    }

    public JPanel getPanel() {
            return panel;
        }
    public static void main(String[] args) {
        new LoadAlbumView1();
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
        panel11 = new ImagePanel("/Users/malaikamalik/Desktop/malik130/CSC207REAL/swiftify-frontend/src/pngs/taylor_swift.png");
        setContentPane(panel11);

        fearlesspanel = new ImagePanel("/Users/malaikamalik/Desktop/malik130/CSC207REAL/swiftify-frontend/src/pngs/fearless.png");
        setContentPane(fearlesspanel);

        Speaknowpanel = new ImagePanel("/Users/malaikamalik/Desktop/malik130/CSC207REAL/swiftify-frontend/src/pngs/speak_now.png");
        setContentPane(Speaknowpanel);

        p1989panel = new ImagePanel("/Users/malaikamalik/Desktop/malik130/CSC207REAL/swiftify-frontend/src/pngs/1989.png");
        setContentPane(p1989panel);

        loverpanel = new ImagePanel("/Users/malaikamalik/Desktop/malik130/CSC207REAL/swiftify-frontend/src/pngs/lover.png");
        setContentPane(loverpanel);

        evermorepanel = new ImagePanel("/Users/malaikamalik/Desktop/malik130/CSC207REAL/swiftify-frontend/src/pngs/evermore.png");
        setContentPane(evermorepanel);

        midnightspanel = new ImagePanel("/Users/malaikamalik/Desktop/malik130/CSC207REAL/swiftify-frontend/src/pngs/midnights.png");
        setContentPane(midnightspanel);

        redpanel = new ImagePanel("/Users/malaikamalik/Desktop/malik130/CSC207REAL/swiftify-frontend/src/pngs/red.png");
        setContentPane(redpanel);

        folklorepanel = new ImagePanel("/Users/malaikamalik/Desktop/malik130/CSC207REAL/swiftify-frontend/src/pngs/folklore.png");
        setContentPane(folklorepanel);

        reputationpanel = new ImagePanel("/Users/malaikamalik/Desktop/malik130/CSC207REAL/swiftify-frontend/src/pngs/reputation.png");
        setContentPane(reputationpanel);

    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {

    }

    public void updateAlbums(LinkedList<String> albums) {
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

