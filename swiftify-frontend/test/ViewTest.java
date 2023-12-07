import app.LoadAlbumsUseCaseFactory;
import app.LoadSongsUseCaseFactory;
import app.Main;
import app.SongPlaybackUseCaseFactory;
import data_access.URLSongLoader;
import interface_adapter.ViewManagerModel;
import interface_adapter.add_to_queue.AddToQueueController;
import interface_adapter.load_albums.LoadAlbumsController;
import interface_adapter.load_albums.LoadAlbumsState;
import interface_adapter.load_songs.LoadSongsController;
import interface_adapter.load_songs.LoadSongsState;
import interface_adapter.next_song.NextSongController;
import interface_adapter.pause_song.PauseSongController;
import interface_adapter.play_song.PlaySongController;
import interface_adapter.prev_song.PrevSongController;
import interface_adapter.resume_song.ResumeSongController;
import org.junit.Test;
import view.*;

import javax.swing.*;

import static org.junit.Assert.*;

public class ViewTest {

    @Test
    public void testInit() {

        ViewManagerModel viewManagerModel = new ViewManagerModel();
        ViewManager viewManager = new ViewManager(viewManagerModel);

        MainView mainView = new MainView(LoadAlbumsUseCaseFactory.createLoadAlbumsController(viewManagerModel));

        assertNotNull(viewManager);
        assertNotNull(mainView);
        assertEquals(viewManagerModel, viewManager.getViewManagerModel());

    }

    @Test
    public void testMainView() {
        SwingUtilities.invokeLater(() -> {
            Main main = new Main();

            MainView mainView = new MainView(LoadAlbumsUseCaseFactory.createLoadAlbumsController(new ViewManagerModel()));
            mainView.setVisible(true);

            assert mainView != null : "MainView isn't null";
            assert mainView.isVisible() : "MainView is visible";

        });
    }

    @Test
    public void testMainMethod() {
        ViewManagerModel viewManagerModel = new ViewManagerModel();

        Main.main(null);

        assertNotNull(viewManagerModel);

    }

    @Test
    public void testViewManagerInit() {
        ViewManagerModel viewManagerModel = new ViewManagerModel();
        ViewManager viewManager = new ViewManager(viewManagerModel);
        assertNotNull(viewManager);
    }

    @Test
    public void testChangePageOnButtonClick() {
        SwingUtilities.invokeLater(() -> {
            Main main = new Main();

            LoadAlbumsController loadAlbumController = LoadAlbumsUseCaseFactory.createLoadAlbumsController(new ViewManagerModel());
            MainView mainView = new MainView(loadAlbumController);
            mainView.setVisible(true);

            assertFalse(mainView.getLoadAlbumView().isVisible());
            mainView.pressOkButton();

            assertTrue(mainView.getLoadAlbumView().isVisible());
        });
    }

    @Test
    public void testAlbumChangePage() {
        SwingUtilities.invokeLater(() -> {
            Main main = new Main();
            LoadAlbumsController loadAlbumController = LoadAlbumsUseCaseFactory.createLoadAlbumsController(new ViewManagerModel());
            MainView mainView = new MainView(loadAlbumController);
            mainView.pressOkButton();

            mainView.pressAlbumButton("evermore");

            assertNotNull(mainView.getSongView());
            assertTrue(mainView.getSongView().isVisible());

        });
    }

    ViewBuilder viewBuilder = new ViewBuilder(new ViewManagerModel()) {

        @Override
        public JFrame buildView(String viewName) {
            if (viewName.equalsIgnoreCase("LoadSongsView")) {
                return buildAlbumSongsView();
            }
            if (viewName.equalsIgnoreCase("LoadAlbumsView")) {
                return buildAlbumsView();
            }
            if (viewName.equalsIgnoreCase("PlaySongView")) {
                return buildPlayingView();
            }
            if (viewName.equalsIgnoreCase("PauseSongView")) {
                return buildPausedView();
            }
            if (viewName.equalsIgnoreCase("ResumeSongView")) {
                return buildResumedView();
            }
            if (viewName.equalsIgnoreCase("AddToQueueView")) {
                return buildAddToQueueView();
            }

            // write switch case for other views later
            return new JFrame();
        }

        @Override
        public LoadSongsView buildAlbumSongsView() {
            LoadSongsState loadSongsState = new LoadSongsState("TestBuildSongsView", null);
            URLSongLoader songLoader = new URLSongLoader();
            PlaySongController playSongController = SongPlaybackUseCaseFactory.createPlaySongController(this.viewManagerModel, songLoader);
            PauseSongController pauseSongController = SongPlaybackUseCaseFactory.createPauseSongController(this.viewManagerModel, null);
            ResumeSongController resumeSongController = SongPlaybackUseCaseFactory.createResumeSongController(this.viewManagerModel, null);
            NextSongController nextSongController = SongPlaybackUseCaseFactory.createNextSongController(this.viewManagerModel, songLoader);
            PrevSongController prevSongController = SongPlaybackUseCaseFactory.createPrevSongController(this.viewManagerModel, songLoader);
            AddToQueueController addToQueueController = SongPlaybackUseCaseFactory.createAddToQueueController(this.viewManagerModel);
            LoadSongsView loadSongsView1 = new LoadSongsView(loadSongsState.getAlbumName(), playSongController, pauseSongController, resumeSongController, nextSongController, prevSongController, addToQueueController, this.viewManagerModel);
            return loadSongsView1;
        }

        @Override
        public LoadAlbumsView buildAlbumsView() {
            LoadAlbumsState loadAlbumState = new LoadAlbumsState("TestBuildAlbumsView", null);
            LoadSongsController loadSongsController = LoadSongsUseCaseFactory.createLoadSongsController(this.viewManagerModel);
            return new LoadAlbumsView(loadSongsController);
        }

        @Override
        public LoadSongsView buildPlayingView() {
            LoadSongsState loadSongsState = new LoadSongsState("TestBuildPlayingView", null);
            URLSongLoader songLoader = new URLSongLoader();
            PlaySongController playSongController = SongPlaybackUseCaseFactory.createPlaySongController(this.viewManagerModel, songLoader);
            PauseSongController pauseSongController = SongPlaybackUseCaseFactory.createPauseSongController(this.viewManagerModel, null);
            ResumeSongController resumeSongController = SongPlaybackUseCaseFactory.createResumeSongController(this.viewManagerModel, null);
            NextSongController nextSongController = SongPlaybackUseCaseFactory.createNextSongController(this.viewManagerModel, songLoader);
            PrevSongController prevSongController = SongPlaybackUseCaseFactory.createPrevSongController(this.viewManagerModel, songLoader);
            AddToQueueController addToQueueController = SongPlaybackUseCaseFactory.createAddToQueueController(this.viewManagerModel);
            LoadSongsView loadSongsView1 = new LoadSongsView(loadSongsState.getAlbumName(), playSongController, pauseSongController, resumeSongController, nextSongController, prevSongController, addToQueueController, this.viewManagerModel);
            return loadSongsView1;
        }

        @Override
        public LoadSongsView buildPausedView() {
            LoadSongsState loadSongsState = new LoadSongsState("TestBuildPausedView", null);
            URLSongLoader songLoader = new URLSongLoader();
            PlaySongController playSongController = SongPlaybackUseCaseFactory.createPlaySongController(this.viewManagerModel, songLoader);
            PauseSongController pauseSongController = SongPlaybackUseCaseFactory.createPauseSongController(this.viewManagerModel, null);
            ResumeSongController resumeSongController = SongPlaybackUseCaseFactory.createResumeSongController(this.viewManagerModel, null);
            NextSongController nextSongController = SongPlaybackUseCaseFactory.createNextSongController(this.viewManagerModel, songLoader);
            PrevSongController prevSongController = SongPlaybackUseCaseFactory.createPrevSongController(this.viewManagerModel, songLoader);
            AddToQueueController addToQueueController = SongPlaybackUseCaseFactory.createAddToQueueController(this.viewManagerModel);
            LoadSongsView loadSongsView1 = new LoadSongsView(loadSongsState.getAlbumName(), playSongController, pauseSongController, resumeSongController, nextSongController, prevSongController, addToQueueController, this.viewManagerModel);
            return loadSongsView1;
        }

        @Override
        public LoadSongsView buildResumedView() {
            LoadSongsState loadSongsState = new LoadSongsState("TestBuildResumedView", null);
            URLSongLoader songLoader = new URLSongLoader();
            PlaySongController playSongController = SongPlaybackUseCaseFactory.createPlaySongController(this.viewManagerModel, songLoader);
            PauseSongController pauseSongController = SongPlaybackUseCaseFactory.createPauseSongController(this.viewManagerModel, null);
            ResumeSongController resumeSongController = SongPlaybackUseCaseFactory.createResumeSongController(this.viewManagerModel, null);
            NextSongController nextSongController = SongPlaybackUseCaseFactory.createNextSongController(this.viewManagerModel, songLoader);
            PrevSongController prevSongController = SongPlaybackUseCaseFactory.createPrevSongController(this.viewManagerModel, songLoader);
            AddToQueueController addToQueueController = SongPlaybackUseCaseFactory.createAddToQueueController(this.viewManagerModel);
            LoadSongsView loadSongsView1 = new LoadSongsView(loadSongsState.getAlbumName(), playSongController, pauseSongController, resumeSongController, nextSongController, prevSongController, addToQueueController, this.viewManagerModel);
            return loadSongsView1;
        }

        @Override
        public LoadSongsView buildAddToQueueView() {
            LoadSongsState loadSongsState = new LoadSongsState("TestBuildAddToQueueView", null);
            URLSongLoader songLoader = new URLSongLoader();
            PlaySongController playSongController = SongPlaybackUseCaseFactory.createPlaySongController(this.viewManagerModel, songLoader);
            PauseSongController pauseSongController = SongPlaybackUseCaseFactory.createPauseSongController(this.viewManagerModel, null);
            ResumeSongController resumeSongController = SongPlaybackUseCaseFactory.createResumeSongController(this.viewManagerModel, null);
            NextSongController nextSongController = SongPlaybackUseCaseFactory.createNextSongController(this.viewManagerModel, songLoader);
            PrevSongController prevSongController = SongPlaybackUseCaseFactory.createPrevSongController(this.viewManagerModel, songLoader);
            AddToQueueController addToQueueController = SongPlaybackUseCaseFactory.createAddToQueueController(this.viewManagerModel);
            LoadSongsView loadSongsView1 = new LoadSongsView(loadSongsState.getAlbumName(), playSongController, pauseSongController, resumeSongController, nextSongController, prevSongController, addToQueueController, this.viewManagerModel);
            return loadSongsView1;
        }
    };

    @Test
    public void testBuildView() {
        LoadAlbumsView view = (LoadAlbumsView) viewBuilder.buildView("LoadAlbumsView");
        assertEquals(view.getTitle(), "Swiftify - Albums");
    }
    @Test
    public void testBuildAlbumsView() {
        LoadAlbumsView view = viewBuilder.buildAlbumsView();
        assertEquals(view.getTitle(), "Swiftify - Albums");
    }

    @Test
    public void testBuildSongsView() {
        LoadSongsView view = viewBuilder.buildAlbumSongsView();
        assertEquals(view.getTitle(), "Swiftify Album - TestBuildSongsView");
    }

    @Test
    public void testBuildPlayingView() {
        LoadSongsView view = viewBuilder.buildPlayingView();
        assertEquals(view.getTitle(), "Swiftify Album - TestBuildPlayingView");
    }

    @Test
    public void testBuildPausedView() {
        LoadSongsView view = viewBuilder.buildPausedView();
        assertEquals(view.getTitle(), "Swiftify Album - TestBuildPausedView");
    }

    @Test
    public void testBuildResumedView() {
        LoadSongsView view = viewBuilder.buildResumedView();
        assertEquals(view.getTitle(), "Swiftify Album - TestBuildResumedView");
    }

    @Test
    public void testBuildAddToQueueView() {
        LoadSongsView view = viewBuilder.buildAddToQueueView();
        assertEquals(view.getTitle(), "Swiftify Album - TestBuildAddToQueueView");
    }
}
