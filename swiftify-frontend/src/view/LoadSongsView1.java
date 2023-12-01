package view;

import entity.Song;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class LoadSongsView1 extends JFrame {
    private JPanel LoadSongsViewPanel;
    private JProgressBar SongProgressBar;
    private JPanel ControlsPanel;
    private JLabel CurrentSongField;
    private JPanel RightPanel;
    private JScrollPane LeftPanel;
    private JPanel SongListPanel;
    private JSplitPane MainPanel;
    private JButton PreviousSongButton;
    private JButton PlayPauseButton;
    private JButton NextSongButton;
    private JButton ShuffleButton;
    private JPanel SongPanel;
    private JPanel AddToQueuePanel;

    public LoadSongsView1(String albumName) {
        setContentPane(LoadSongsViewPanel);
        adjustUIComponents();
        setTitle("Swiftify Album - " + albumName);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(1080, 680);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public static void main(String[] args) {
        new LoadSongsView1("Test");
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
}
