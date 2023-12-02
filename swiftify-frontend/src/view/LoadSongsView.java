package view;

import entity.Song;

import javax.swing.*;
import java.awt.*;

public class LoadSongsView extends JFrame {
    private JPanel LoadSongsViewPanel;
    private JLabel CurrentSongField;
    private JPanel RightPanel;
    private JScrollPane LeftPanel;
    private JPanel SongListPanel;
    private JSplitPane MainPanel;
    private JButton PreviousSongButton;
    private JButton PlayPauseButton;
    private JButton NextSongButton;
    private JButton ShuffleButton;
    private JButton button1;
    private JPanel panel2;
    private JProgressBar SongProgressBar;
    private JPanel SongPanel;
    private JPanel AddToQueuePanel;

    public LoadSongsView(String albumName) {
        setContentPane(LoadSongsViewPanel);
        adjustUIComponents();
        setTitle("Swiftify Album - " + albumName);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(1080, 680);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public static void main(String[] args) {
        new LoadSongsView("Test");
    }

    public void addSong(Song song) {
        SongPanel.add(new JButton(song.getTitle()));
        AddToQueuePanel.add(new JButton("+"));
    }

    private void adjustUIComponents() {
        SongPanel = new JPanel();
        SongPanel.setLayout(new BoxLayout(SongPanel, BoxLayout.Y_AXIS));
        AddToQueuePanel = new JPanel();
        AddToQueuePanel.setLayout(new BoxLayout(AddToQueuePanel, BoxLayout.Y_AXIS));
        SongListPanel.setLayout(new BorderLayout());
        SongListPanel.add(SongPanel, BorderLayout.WEST);
        SongListPanel.add(AddToQueuePanel, BorderLayout.EAST);
        PreviousSongButton.setPreferredSize(new Dimension(50, 50));
        PlayPauseButton.setPreferredSize(new Dimension(100, 50));
        NextSongButton.setPreferredSize(new Dimension(50, 50));
        ShuffleButton.setPreferredSize(new Dimension(50, 50));



    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
        panel2 = new LoadAlbumView1.ImagePanel("swiftify-frontend/src/pngs/taylor_swift.png");
        setContentPane(panel2);
    }
}
