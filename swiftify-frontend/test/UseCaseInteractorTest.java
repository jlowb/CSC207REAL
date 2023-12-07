import app.LoadAlbumsUseCaseFactory;
import app.LoadSongsUseCaseFactory;
import app.SongPlaybackUseCaseFactory;
import data_access.MusicPlayerFacade;
import data_access.URLSongLoader;
import entity.PlayerState;
import entity.Song;
import interface_adapter.SongPlaybackState;
import interface_adapter.ViewManagerModel;
import interface_adapter.add_to_queue.AddToQueueController;
import interface_adapter.load_albums.LoadAlbumsController;
import interface_adapter.load_songs.LoadSongsController;
import interface_adapter.load_songs.LoadSongsState;
import interface_adapter.next_song.NextSongController;
import interface_adapter.pause_song.PauseSongController;
import interface_adapter.play_song.PlaySongController;
import interface_adapter.prev_song.PrevSongController;
import interface_adapter.resume_song.ResumeSongController;
import javazoom.jl.decoder.JavaLayerException;
import org.junit.Test;
import use_case.add_to_queue.*;
import use_case.load_albums.LoadAlbumsInputData;
import use_case.load_albums.LoadAlbumsOutputBoundary;
import use_case.load_albums.LoadAlbumsOutputData;
import use_case.load_songs.LoadSongsInputData;
import use_case.load_songs.LoadSongsOutputBoundary;
import use_case.load_songs.LoadSongsOutputData;
import use_case.next_song.NextSongInputBoundary;
import use_case.next_song.NextSongInputData;
import use_case.next_song.NextSongOutputBoundary;
import use_case.next_song.NextSongOutputData;
import use_case.pause_song.PauseSongInputData;
import use_case.pause_song.PauseSongOutputBoundary;
import use_case.pause_song.PauseSongOutputData;
import use_case.play_song.PlaySongInputData;
import use_case.play_song.PlaySongOutputBoundary;
import use_case.play_song.PlaySongOutputData;
import use_case.prev_song.PrevSongInputData;
import use_case.prev_song.PrevSongOutputBoundary;
import use_case.prev_song.PrevSongOutputData;
import use_case.resume_song.ResumeSongInputData;
import use_case.resume_song.ResumeSongOutputBoundary;
import use_case.resume_song.ResumeSongOutputData;
import view.LoadSongsView;
import view.ViewBuilder;

import java.io.IOException;

import static org.junit.Assert.*;

public class UseCaseInteractorTest {

    private final String url = "https://samplelib.com/lib/preview/mp3/sample-15s.mp3";

    private LoadSongsView buildAlbumSongsView() {
        ViewManagerModel viewManagerModel = new ViewManagerModel();
        LoadSongsState loadSongsState = new LoadSongsState("Test", null);
        URLSongLoader songLoader = new URLSongLoader();
        PlaySongController playSongController = SongPlaybackUseCaseFactory.createPlaySongController(viewManagerModel, songLoader);
        PauseSongController pauseSongController = SongPlaybackUseCaseFactory.createPauseSongController(viewManagerModel, null);
        ResumeSongController resumeSongController = SongPlaybackUseCaseFactory.createResumeSongController(viewManagerModel, null);
        NextSongController nextSongController = SongPlaybackUseCaseFactory.createNextSongController(viewManagerModel, songLoader);
        PrevSongController prevSongController = SongPlaybackUseCaseFactory.createPrevSongController(viewManagerModel, songLoader);
        AddToQueueController addToQueueController = SongPlaybackUseCaseFactory.createAddToQueueController(viewManagerModel);
        LoadSongsView loadSongsView1 = new LoadSongsView(loadSongsState.getAlbumName(), playSongController, pauseSongController, resumeSongController, nextSongController, prevSongController, addToQueueController, viewManagerModel);
        return loadSongsView1;
    }

    @Test
    public void loadAlbumsInteractorTest() {
        LoadAlbumsOutputBoundary loadAlbumsOutputBoundary = new LoadAlbumsOutputBoundary() {
            @Override
            public void loadAlbums(LoadAlbumsOutputData loadAlbumsOutputData) {
                assertEquals(loadAlbumsOutputData.getSelection(), "Test");
                assertTrue(loadAlbumsOutputData.getAlbums().isEmpty());
            }
        };

        LoadAlbumsInputData loadAlbumsInputData = new LoadAlbumsInputData("Test");
        LoadAlbumsController loadAlbumsController = LoadAlbumsUseCaseFactory.createLoadAlbumsController(new ViewManagerModel());
        loadAlbumsController.execute(loadAlbumsInputData);
    }

    @Test
    public void loadSongsInteractorTest() {
        LoadSongsOutputBoundary loadSongsOutputBoundary = new LoadSongsOutputBoundary() {
            @Override
            public void loadSongs(LoadSongsOutputData loadSongsOutputData) {
                assertEquals(loadSongsOutputData.getAlbumName(), "Test");
                assertTrue(loadSongsOutputData.getSongs().isEmpty());
            }
        };

        LoadSongsInputData loadSongsInputData = new LoadSongsInputData("Test");
        LoadSongsController loadSongsController = LoadSongsUseCaseFactory.createLoadSongsController(new ViewManagerModel());
        loadSongsController.execute(loadSongsInputData);
    }

    @Test
    public void playSongInteractorTest() throws IOException, InterruptedException, JavaLayerException {
        PlaySongOutputBoundary playSongOutputBoundary = new PlaySongOutputBoundary() {
            @Override
            public void preparePlayingView(PlaySongOutputData playSongOutputData) {
                assertEquals(playSongOutputData.getSong(), "Test");
                assertNull(playSongOutputData.getMusicPlayer());
                assertNull(playSongOutputData.getView());
            }
        };

        PlaySongInputData playSongInputData = new PlaySongInputData(1, "Test", null);
        PlaySongController playSongController = SongPlaybackUseCaseFactory.createPlaySongController(new ViewManagerModel(), new URLSongLoader());
        playSongController.execute(playSongInputData);
    }

    @Test
    public void pauseSongInteractorTest() throws IOException, InterruptedException, JavaLayerException {
        PauseSongOutputBoundary pauseSongOutputBoundary = new PauseSongOutputBoundary() {
            @Override
            public void preparePausedView(PauseSongOutputData pauseSongOutputData) {
                assertNull(pauseSongOutputData.getLoadSongsView());
                assertEquals(pauseSongOutputData.getState().getSongName(), "Test");
            }
        };

        SongPlaybackState songPlaybackState = new SongPlaybackState(null, "Test", new PlayerState(url), false);
        PauseSongInputData pauseSongInputData = new PauseSongInputData(songPlaybackState, null);
        PauseSongController pauseSongController = SongPlaybackUseCaseFactory.createPauseSongController(new ViewManagerModel(), songPlaybackState);
        pauseSongController.execute(pauseSongInputData);
    }

    @Test
    public void resumeSongInteractorTest() throws IOException, InterruptedException, JavaLayerException {
        ResumeSongOutputBoundary resumeSongOutputBoundary = new ResumeSongOutputBoundary() {
            @Override
            public void prepareResumedView(ResumeSongOutputData resumeSongOutputData) {
                assertNull(resumeSongOutputData.getLoadSongsView());
                assertEquals(resumeSongOutputData.getState().getSongName(), "Test");
            }
        };

        SongPlaybackState songPlaybackState = new SongPlaybackState(null, "Test", new PlayerState(url), false);
        ResumeSongInputData resumeSongInputData = new ResumeSongInputData(songPlaybackState, null);
        ResumeSongController resumeSongController = SongPlaybackUseCaseFactory.createResumeSongController(new ViewManagerModel(), songPlaybackState);
        resumeSongController.execute(resumeSongInputData);
    }

    @Test
    public void nextSongInteractorTest() throws IOException, InterruptedException, JavaLayerException {
        NextSongOutputBoundary nextSongOutputBoundary = new NextSongOutputBoundary() {
            @Override
            public void prepareNextView(NextSongOutputData nextSongOutputData) {
                assertEquals(nextSongOutputData.getSong(), "Test");
                assertNull(nextSongOutputData.getView());
            }
        };

        NextSongInputData nextSongInputData = new NextSongInputData(new Song(1, "Test", 1, 1, "Test", "Test"), buildAlbumSongsView());
        NextSongController nextSongController = SongPlaybackUseCaseFactory.createNextSongController(new ViewManagerModel(), new URLSongLoader());
        nextSongController.execute(nextSongInputData);
    }

    @Test
    public void prevSongInteractorTest() throws IOException, InterruptedException, JavaLayerException {
        PrevSongOutputBoundary prevSongOutputBoundary = new PrevSongOutputBoundary() {
            @Override
            public void preparePrevView(PrevSongOutputData prevSongOutputData) {
                assertEquals(prevSongOutputData.getSong(), "Test");
                assertNull(prevSongOutputData.getView());
            }
        };

        PrevSongInputData prevSongInputData = new PrevSongInputData(new Song(1, "Test", 1, 1, "Test", "Test"), buildAlbumSongsView());
        PrevSongController prevSongController = SongPlaybackUseCaseFactory.createPrevSongController(new ViewManagerModel(), new URLSongLoader());
        prevSongController.execute(prevSongInputData);
    }

    @Test
    public void addToQueueInteractorTest() throws IOException, InterruptedException, JavaLayerException {
        AddToQueueOutputBoundary addToQueueOutputBoundary = new AddToQueueOutputBoundary() {
            @Override
            public void prepareQueueView(AddToQueueOutputData addToQueueOutputData) {
                assertNull(addToQueueOutputData.getView());
            }
        };

        AddToQueueInputData addToQueueInputData = new AddToQueueInputData(1, buildAlbumSongsView());
        AddToQueueController addToQueueController = SongPlaybackUseCaseFactory.createAddToQueueController(new ViewManagerModel());
        addToQueueController.execute(addToQueueInputData);
    }
}
