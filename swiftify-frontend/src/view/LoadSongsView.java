package view;

import data_access.URLSongLoader;
import entity.Song;
import entity.SongButton;
import entity.SongPlaybackButton;
import interface_adapter.SongPlaybackState;
import interface_adapter.ViewManagerModel;
import interface_adapter.play_song.PlaySongController;
import interface_adapter.play_song.PlaySongPresenter;
import interface_adapter.play_song.PlaySongViewModel;
import javazoom.jl.decoder.JavaLayerException;
import use_case.play_song.PlaySongInputBoundary;
import use_case.play_song.PlaySongInputData;
import use_case.play_song.PlaySongInteractor;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URL;

public class LoadSongsView extends JFrame {
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
        SongButton songButton = new SongButton(song.getSongID(), song.getTitle());
        songButton.addActionListener(playSongActionListener);
        SongPanel.add(songButton);
        AddToQueuePanel.add(new JButton("+"));
    }

    ActionListener playSongActionListener = new ActionListener() {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() instanceof SongButton) {
                PlaySongInputData playSongInputData = new PlaySongInputData(((SongButton) e.getSource()).getSongId(), ((SongButton) e.getSource()).getSongName(), LoadSongsView.this);
                PlaySongInputBoundary playSongInputBoundary = new PlaySongInteractor(new URLSongLoader(), new PlaySongPresenter(new PlaySongViewModel(), new ViewManagerModel()));
                PlaySongController playSongController = new PlaySongController(playSongInputBoundary);
                try {
                    playSongController.execute(playSongInputData);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                } catch (InterruptedException ex) {
                    throw new RuntimeException(ex);
                } catch (JavaLayerException ex) {
                    throw new RuntimeException(ex);
                }

            }
        }
    };

    public void changePlaybackState(SongPlaybackState songPlaybackState) {
        PlayPauseButton.setText("||");
        ((SongPlaybackButton) PlayPauseButton).setSongPlaybackState(songPlaybackState);
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
        PlayPauseButton = new SongPlaybackButton(null);
        PlayPauseButton.setPreferredSize(new Dimension(100, 50));
        NextSongButton.setPreferredSize(new Dimension(50, 50));
        ShuffleButton.setPreferredSize(new Dimension(50, 50));
    }
}
