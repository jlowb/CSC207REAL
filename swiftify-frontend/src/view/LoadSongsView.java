package view;

import data_access.SongLoader;
import data_access.URLSongLoader;
import entity.PlayerState;
import entity.Song;
import javazoom.jl.decoder.JavaLayerException;
import use_case.pause_song.PauseSongInputData;
import use_case.pause_song.PauseSongInteractor;
import use_case.play_song.PlaySongInputData;
import use_case.play_song.PlaySongInteractor;
import use_case.play_song.PlaySongOutputBoundary;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class LoadSongsView extends JFrame {
    private JPanel loadSongsPanel;
    private JButton backButton;
    private JProgressBar progressBar;
    private JPanel panel2;
    private JButton previousSongButton;
    private JButton nextSongButton;
    private JButton playPauseButton;
    private JScrollBar scrollBar;
    private JPanel songPanel;
    private JPanel playerPanel;
    private JPanel controlsPanel;
    private JPanel mainPanel;


    public LoadSongsView(String selectedAlbum) {
        setContentPane(loadSongsPanel);
        setTitle("Swiftify Album- Red");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(1080, 680);
        setLocationRelativeTo(null);
        setVisible(true);

        ActionListener a = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() instanceof JButton) {
                    LoadAlbumView1 page = new LoadAlbumView1();
                    page.setVisible(true);
                }
                dispose();
            }
        };

        //
        Song song1 = new Song(100, "S", 11, 11, "sss", "ss");
        //CAll interactor
        PlaySongInputData song = new PlaySongInputData(song1);
        URLSongLoader songLoader = new URLSongLoader();
        String songURL;
        try {
            songURL = songLoader.fetchPresignedURL(song1.getSongID());
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        } catch (InterruptedException ex) {
            throw new RuntimeException(ex);
        }
        PlayerState music;
        try {
            music = new PlayerState(songURL);
        } catch (JavaLayerException ex) {
            throw new RuntimeException(ex);
        }
        try {
            new PlaySongInteractor(song, songLoader, music).execute(song);
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        } catch (InterruptedException ex) {
            throw new RuntimeException(ex);
        } catch (JavaLayerException ex) {
            throw new RuntimeException(ex);
        }
        //

        ActionListener b = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() instanceof JButton) {
                    try {
                        new PlaySongInteractor(song, songLoader, music).execute(song);
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

        this.playPauseButton.addActionListener(b);



        ActionListener c = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() instanceof JButton) {
                    try {
                        new PauseSongInteractor(song, songLoader, music).execute(song);
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    } catch (InterruptedException ex) {
                        throw new RuntimeException(ex);
                    }
                }
            }
        };

        this.nextSongButton.addActionListener(c);

    }




    public void addSong(String songName) {
        songPanel.add(new JButton(songName));
    }

    public static void main(String[] args) {
        new LoadSongsView(args[0]);
    }
}


