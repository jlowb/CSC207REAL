package view;

import data_access.URLSongLoader;
import entity.MusicPlayerFacade;
import entity.Song;
import entity.SongButton;
import entity.AddToQueueButton;
import entity.SongPlaybackButton;
import interface_adapter.SongPlaybackState;
import interface_adapter.ViewManagerModel;
import interface_adapter.add_to_queue.AddToQueueController;
import interface_adapter.add_to_queue.AddToQueuePresenter;
import interface_adapter.add_to_queue.AddToQueueViewModel;
import interface_adapter.next_song.NextSongController;
import interface_adapter.next_song.NextSongPresenter;
import interface_adapter.next_song.NextSongViewModel;
import interface_adapter.pause_song.PauseSongController;
import interface_adapter.pause_song.PauseSongPresenter;
import interface_adapter.pause_song.PauseSongViewModel;
import interface_adapter.play_song.PlaySongController;
import interface_adapter.play_song.PlaySongPresenter;
import interface_adapter.play_song.PlaySongViewModel;
import interface_adapter.prev_song.PrevSongController;
import interface_adapter.prev_song.PrevSongPresenter;
import interface_adapter.prev_song.PrevSongViewModel;
import interface_adapter.resume_song.ResumeSongController;
import javazoom.jl.decoder.JavaLayerException;
import use_case.next_song.NextSongInputBoundary;
import use_case.next_song.NextSongInputData;
import use_case.next_song.NextSongInteractor;
import use_case.pause_song.PauseSongInputData;
import use_case.play_song.PlaySongInputData;
import use_case.prev_song.PrevSongInputBoundary;
import use_case.prev_song.PrevSongInputData;
import use_case.prev_song.PrevSongInteractor;
import use_case.resume_song.ResumeSongInputData;
import use_case.add_to_queue.AddToQueueInputBoundary;
import use_case.add_to_queue.AddToQueueInputData;
import use_case.add_to_queue.AddToQueueInteractor;

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
    private JPanel ControlsPanel;
    private JLabel CurrentSongField;
    private JPanel SongPanel;
    private JPanel AddToQueuePanel;
    private PlaySongController playSongController;
    private PauseSongController pauseSongController;
    private ResumeSongController resumeSongController;
    private String albumName;

    public LoadSongsView(String albumName, PlaySongController playSongController, PauseSongController pauseSongController, ResumeSongController resumeSongController) {
        this.albumName = albumName;
        this.playSongController = playSongController;
        this.pauseSongController = pauseSongController;
        this.resumeSongController = resumeSongController;

        createUIComponents();
        setContentPane(LoadSongsViewPanel);
        adjustUIComponents();
        setTitle("Swiftify Album - " + albumName);
        addWindowListener(ghettoBackButton);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setSize(1080, 680);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public String getAlbumName() {
        return this.albumName;
    }

    public static void main(String[] args) {

    }

    public void addSong(Song song) {
        SongButton songButton = new SongButton(song.getSongID(), song.getTitle());
        songButton.addActionListener(playSongActionListener);
        SongPanel.add(songButton);
        AddToQueueButton addToQueueButton = new AddToQueueButton("+", song.getSongID());
        addToQueueButton.addActionListener(addToQueueActionListener);
        AddToQueuePanel.add(addToQueueButton);
    }

    ActionListener playSongActionListener = new ActionListener() {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (PlayPauseButton.getSongPlaybackState() != null) {
                PlayPauseButton.getSongPlaybackState().getMusicPlayer().stop();
            }
            // BELOW IS SHADY
            MusicPlayerFacade musicPlayer = MusicPlayerFacade.getInstance(LoadSongsView.this.getAlbumName());
            int n = ((SongButton) e.getSource()).getSongId();
            musicPlayer.addToQueue(n-1);
            //
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

    ActionListener addToQueueActionListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            MusicPlayerFacade musicPlayerFacade = MusicPlayerFacade.getInstance(LoadSongsView.this.getAlbumName());
            if (!musicPlayerFacade.getQueue().isNotEmpty()) {
                musicPlayerFacade.addToQueue(-1);
            }
            AddToQueueInputData addToQueueInputData = new AddToQueueInputData(((AddToQueueButton) e.getSource()).getSongId() -1, LoadSongsView.this);
            ViewManagerModel viewManagerModel = new ViewManagerModel();
            new ViewManager(viewManagerModel);
            AddToQueueInputBoundary addToQueueInputBoundary = new AddToQueueInteractor(new AddToQueuePresenter(new AddToQueueViewModel(), viewManagerModel));
            AddToQueueController addToQueueController = new AddToQueueController(addToQueueInputBoundary);
            try {
                addToQueueController.execute(addToQueueInputData);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            } catch (InterruptedException ex) {
                throw new RuntimeException(ex);
            } catch (JavaLayerException ex) {
                throw new RuntimeException(ex);
            }
        }
    };

    ActionListener nextSongActionListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            MusicPlayerFacade musicPlayer = MusicPlayerFacade.getInstance(LoadSongsView.this.getAlbumName());
            Song nextSong = musicPlayer.getNextSong();
            if (nextSong != null) {
                if (PlayPauseButton.getSongPlaybackState() != null) {
                    PlayPauseButton.getSongPlaybackState().getMusicPlayer().stop();
                }
                NextSongInputData nextSongInputData = new NextSongInputData(nextSong, LoadSongsView.this);
                ViewManagerModel viewManagerModel = new ViewManagerModel();
                new ViewManager(viewManagerModel);
                NextSongInputBoundary nextSongInputBoundary = new NextSongInteractor(new URLSongLoader(), new NextSongPresenter(new NextSongViewModel(), viewManagerModel));
                NextSongController nextSongController = new NextSongController(nextSongInputBoundary);
                try {
                    nextSongController.execute(nextSongInputData);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                } catch (InterruptedException ex) {
                    throw new RuntimeException(ex);
                } catch (JavaLayerException ex) {
                    throw new RuntimeException(ex);
                }
            } else {
            }
        }
    };

    ActionListener prevSongActionListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            MusicPlayerFacade musicPlayer = MusicPlayerFacade.getInstance(LoadSongsView.this.getAlbumName());
            Song prevSong = musicPlayer.getPrevSong();
            if (prevSong != null) {
                if (PlayPauseButton.getSongPlaybackState() != null) {
                    PlayPauseButton.getSongPlaybackState().getMusicPlayer().stop();
                }
                PrevSongInputData prevSongInputData = new PrevSongInputData(prevSong, LoadSongsView.this);
                ViewManagerModel viewManagerModel = new ViewManagerModel();
                new ViewManager(viewManagerModel);
                PrevSongInputBoundary prevSongInputBoundary = new PrevSongInteractor(new URLSongLoader(), new PrevSongPresenter(new PrevSongViewModel(), viewManagerModel));
                PrevSongController prevSongController = new PrevSongController(prevSongInputBoundary);
                try {
                    prevSongController.execute(prevSongInputData);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                } catch (InterruptedException ex) {
                    throw new RuntimeException(ex);
                } catch (JavaLayerException ex) {
                    throw new RuntimeException(ex);
                }
            } else {
            }
        }
    };

    ActionListener shuffleActionListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            MusicPlayerFacade musicPlayer = MusicPlayerFacade.getInstance(LoadSongsView.this.getAlbumName());
            musicPlayer.toggleShuffle();
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
        NextSongButton.addActionListener(nextSongActionListener);
        PreviousSongButton.addActionListener(prevSongActionListener);
        ShuffleButton.addActionListener(shuffleActionListener);
        ControlsPanel.add(PreviousSongButton, 0);
        ControlsPanel.add(PlayPauseButton, 1);
        ControlsPanel.add(NextSongButton, 2);
    }

    private void createUIComponents() {
        CoverPanel = new LoadAlbumView.ImagePanel("swiftify-frontend/src/pngs/taylor_swift.png");
        setContentPane(CoverPanel);
    }
}
