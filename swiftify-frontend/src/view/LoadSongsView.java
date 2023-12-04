package view;

import entity.MusicPlayerFacade;
import entity.AddToQueueButton;
import entity.SongPlaybackButton;

import data_access.URLSongLoader;
import entity.Song;
import entity.SongButton;
import entity.SongPlaybackButton;
import interface_adapter.SongPlaybackState;
import interface_adapter.ViewManagerModel;
import interface_adapter.pause_song.PauseSongController;
import interface_adapter.pause_song.PauseSongPresenter;
import interface_adapter.pause_song.PauseSongViewModel;
import interface_adapter.play_song.PlaySongController;
import interface_adapter.resume_song.ResumeSongController;
import interface_adapter.resume_song.ResumeSongPresenter;
import interface_adapter.resume_song.ResumeSongViewModel;
import javazoom.jl.decoder.JavaLayerException;
import use_case.pause_song.PauseSongInputBoundary;
import use_case.pause_song.PauseSongInputData;
import use_case.pause_song.PauseSongInteractor;
import use_case.play_song.PlaySongInputBoundary;
import use_case.play_song.PlaySongInputData;
import use_case.play_song.PlaySongInteractor;
import use_case.resume_song.ResumeSongInputBoundary;
import use_case.resume_song.ResumeSongInputData;
import use_case.resume_song.ResumeSongInteractor;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;

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

    public LoadSongsView(String albumName, PlaySongController playSongController, PauseSongController pauseSongController, ResumeSongController resumeSongController) {
        this.playSongController = playSongController;
        this.pauseSongController = pauseSongController;
        this.resumeSongController = resumeSongController;

        createUIComponents();
        setContentPane(LoadSongsViewPanel);
        adjustUIComponents();
        setTitle("Swiftify Album - " + albumName);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setSize(1080, 680);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public static void main(String[] args) {

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
            if (PlayPauseButton.getSongPlaybackState() != null) {
                PlayPauseButton.getSongPlaybackState().getMusicPlayer().stop();
            }

            PlaySongInputData playSongInputData = new PlaySongInputData(((SongButton) e.getSource()).getSongId(), ((SongButton) e.getSource()).getSongName(), LoadSongsView.this);
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
    };

    ActionListener pauseOrResumeSongActionListener = new ActionListener() {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (((SongPlaybackButton) e.getSource()).getSongPlaybackState().getPlaying()) {
                PauseSongInputData pauseSongInputData = new PauseSongInputData(((SongPlaybackButton) e.getSource()).getSongPlaybackState(), LoadSongsView.this);
                try {
                    pauseSongController.execute(pauseSongInputData);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                } catch (InterruptedException ex) {
                    throw new RuntimeException(ex);
                } catch (JavaLayerException ex) {
                    throw new RuntimeException(ex);
                }
            }
            else {

                ResumeSongInputData resumeSongInputData = new ResumeSongInputData(((SongPlaybackButton) e.getSource()).getSongPlaybackState(), LoadSongsView.this);
                try {
                    resumeSongController.execute(resumeSongInputData);
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

    WindowListener ghettoBackButton = new WindowAdapter() {

        @Override
        public void windowClosing(WindowEvent e) {
            if (PlayPauseButton.getSongPlaybackState() != null) {
                PlayPauseButton.getSongPlaybackState().getMusicPlayer().stop();
            }
        }
    };

    public void setPlayingView(SongPlaybackState songPlaybackState) {
        PlayPauseButton.setText("▐▐ ");
        PlayPauseButton.setSongPlaybackState(songPlaybackState);
        PreviousSongButton.setSongPlaybackState(songPlaybackState);
        NextSongButton.setSongPlaybackState(songPlaybackState);
        CurrentSongField.setText("Currently Playing: " + songPlaybackState.getSongName());
    }

    public void setPausedView(SongPlaybackState songPlaybackState) {
        PlayPauseButton.setText("▶");
        CurrentSongField.setText("Currently Paused: " + songPlaybackState.getSongName());
    }

    public void setResumedView(SongPlaybackState songPlaybackState) {
        PlayPauseButton.setText("▐▐ ");
        CurrentSongField.setText("Currently Playing: " + songPlaybackState.getSongName());
    }

    private void createUIComponents() {
        CoverPanel = new LoadAlbumView.ImagePanel("swiftify-frontend/src/pngs/swifty.png");
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
        PlayPauseButton.addActionListener(pauseOrResumeSongActionListener);
        PreviousSongButton.setPreferredSize(new Dimension(50, 50));
        PlayPauseButton.setPreferredSize(new Dimension(100, 50));
        NextSongButton.setPreferredSize(new Dimension(50, 50));
        ShuffleButton.setPreferredSize(new Dimension(50, 50));
        ControlPanel.add(PreviousSongButton, 0);
        ControlPanel.add(PlayPauseButton, 1);
        ControlPanel.add(NextSongButton, 2);
    }
}
