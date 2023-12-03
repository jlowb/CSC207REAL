package view;

import entity.Song;
import entity.SongButton;
import entity.SongPlaybackButton;
import interface_adapter.pause_song.PauseSongController;
import interface_adapter.play_song.PlaySongController;
import interface_adapter.resume_song.ResumeSongController;

import javax.swing.*;
import java.awt.*;

public class LoadSongsView extends JFrame {
    private JPanel LoadSongsViewPanel;
    private JPanel RightPanel;
    private JScrollPane LeftPanel;
    private JPanel SongListPanel;
    private JSplitPane MainPanel;
    private SongPlaybackButton PreviousSongButton;
    private SongPlaybackButton PlayPauseButton;
    private SongPlaybackButton NextSongButton;
    private JButton ShuffleButton;
    private JButton BackButton;
    private JPanel CoverPanel;
    private JProgressBar SongProgressBar;
    private JPanel BackPanel;
    private JPanel MusicPlayPanel;
    private JPanel ControlPanel;
    private JLabel CurrentSongField;
    private JPanel SongPanel;
    private JPanel AddToQueuePanel;
    private PlaySongController playSongController;
    private PauseSongController pauseSongController;
    private ResumeSongController resumeSongController;

    public LoadSongsView(String albumName) {
        createUIComponents();
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
        SongButton songButton = new SongButton(song.getSongID(), song.getTitle());
        SongPanel.add(songButton);
        AddToQueuePanel.add(new JButton("+"));
    }

    private void createUIComponents() {
        CoverPanel = new LoadAlbumView.ImagePanel("swiftify-frontend/src/pngs/taylor_swift.png");
        setContentPane(CoverPanel);
    }

    private void adjustUIComponents() {
        SongPanel = new JPanel();
        SongPanel.setLayout(new BoxLayout(SongPanel, BoxLayout.Y_AXIS));
        AddToQueuePanel = new JPanel();
        AddToQueuePanel.setLayout(new BoxLayout(AddToQueuePanel, BoxLayout.Y_AXIS));
        SongListPanel.setLayout(new BorderLayout());
        SongListPanel.add(SongPanel, BorderLayout.WEST);
        SongListPanel.add(AddToQueuePanel, BorderLayout.EAST);
        PreviousSongButton = new SongPlaybackButton(null);
        PreviousSongButton.setText("⏮");
        PlayPauseButton = new SongPlaybackButton(null);
        PlayPauseButton.setText("▶");
        NextSongButton = new SongPlaybackButton(null);
        NextSongButton.setText("⏭");
        PreviousSongButton.setPreferredSize(new Dimension(50, 50));
        PlayPauseButton.setPreferredSize(new Dimension(100, 50));
        NextSongButton.setPreferredSize(new Dimension(50, 50));
        ShuffleButton.setPreferredSize(new Dimension(50, 50));
        ControlPanel.add(PreviousSongButton, 0);
        ControlPanel.add(PlayPauseButton, 1);
        ControlPanel.add(NextSongButton, 2);
    }
}
