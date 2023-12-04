package test;

import app.LoadAlbumsUseCaseFactory;
import app.LoadSongsUseCaseFactory;
import app.SongPlaybackUseCaseFactory;
import data_access.URLSongLoader;
import interface_adapter.SongPlaybackState;
import interface_adapter.ViewManagerModel;
import interface_adapter.add_to_queue.AddToQueueController;
import interface_adapter.load_albums.LoadAlbumsController;
import interface_adapter.load_songs.LoadSongsController;
import interface_adapter.next_song.NextSongController;
import interface_adapter.pause_song.PauseSongController;
import interface_adapter.play_song.PlaySongController;
import interface_adapter.prev_song.PrevSongController;
import interface_adapter.resume_song.ResumeSongController;
import org.junit.Test;
import view.ViewManager;

import static org.junit.Assert.*;

public class UseCaseFactoryTest {

    @Test
    public void testCreatePlaySongController() {
        ViewManagerModel viewManagerModel = new ViewManagerModel();
        new ViewManager(viewManagerModel);
        URLSongLoader songLoader = new URLSongLoader();
        PlaySongController playSongController = SongPlaybackUseCaseFactory.createPlaySongController(viewManagerModel, songLoader);
        assertNotNull(playSongController);
    }
    @Test
    public void testCreatePauseSongController() {
        ViewManagerModel viewManagerModel = new ViewManagerModel();
        new ViewManager(viewManagerModel);
        SongPlaybackState songPlaybackState = new SongPlaybackState(null, "test", null, false);
        PauseSongController pauseSongController = SongPlaybackUseCaseFactory.createPauseSongController(viewManagerModel, songPlaybackState);
        assertNotNull(pauseSongController);
    }

    @Test
    public void testCreateResumeSongController() {
        ViewManagerModel viewManagerModel = new ViewManagerModel();
        new ViewManager(viewManagerModel);
        SongPlaybackState songPlaybackState = new SongPlaybackState(null, "test", null, false);
        ResumeSongController resumeSongController = SongPlaybackUseCaseFactory.createResumeSongController(viewManagerModel, songPlaybackState);
        assertNotNull(resumeSongController);
    }

    @Test
    public void testCreateNextSongController() {
        ViewManagerModel viewManagerModel = new ViewManagerModel();
        new ViewManager(viewManagerModel);
        URLSongLoader songLoader = new URLSongLoader();
        NextSongController nextSongController = SongPlaybackUseCaseFactory.createNextSongController(viewManagerModel, songLoader);
        assertNotNull(nextSongController);
    }

    @Test
    public void testCreatePrevSongController() {
        ViewManagerModel viewManagerModel = new ViewManagerModel();
        new ViewManager(viewManagerModel);
        URLSongLoader songLoader = new URLSongLoader();
        PrevSongController prevSongController = SongPlaybackUseCaseFactory.createPrevSongController(viewManagerModel, songLoader);
        assertNotNull(prevSongController);
    }

    @Test
    public void testCreateAddToQueueController() {
        ViewManagerModel viewManagerModel = new ViewManagerModel();
        new ViewManager(viewManagerModel);
        AddToQueueController addToQueueController = SongPlaybackUseCaseFactory.createAddToQueueController(viewManagerModel);
        assertNotNull(addToQueueController);
    }

    @Test
    public void testCreateLoadAlbumsController() {
        ViewManagerModel viewManagerModel = new ViewManagerModel();
        new ViewManager(viewManagerModel);
        LoadAlbumsController loadAlbumsController = LoadAlbumsUseCaseFactory.createLoadAlbumsController(viewManagerModel);
        assertNotNull(loadAlbumsController);
    }

    @Test
    public void testCreateLoadSongsController() {
        ViewManagerModel viewManagerModel = new ViewManagerModel();
        new ViewManager(viewManagerModel);
        LoadSongsController loadSongsController = LoadSongsUseCaseFactory.createLoadSongsController(viewManagerModel);
        assertNotNull(loadSongsController);
    }
}
