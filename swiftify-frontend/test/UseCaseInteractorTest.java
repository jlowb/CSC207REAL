import app.LoadAlbumsUseCaseFactory;
import app.LoadSongsUseCaseFactory;
import app.SongPlaybackUseCaseFactory;
import data_access.URLSongLoader;
import entity.PlayerState;
import interface_adapter.SongPlaybackState;
import interface_adapter.ViewManagerModel;
import interface_adapter.load_albums.LoadAlbumsController;
import interface_adapter.load_songs.LoadSongsController;
import interface_adapter.pause_song.PauseSongController;
import interface_adapter.play_song.PlaySongController;
import interface_adapter.resume_song.ResumeSongController;
import javazoom.jl.decoder.JavaLayerException;
import org.junit.Test;
import use_case.load_albums.LoadAlbumsInputData;
import use_case.load_albums.LoadAlbumsOutputBoundary;
import use_case.load_albums.LoadAlbumsOutputData;
import use_case.load_songs.LoadSongsInputData;
import use_case.load_songs.LoadSongsOutputBoundary;
import use_case.load_songs.LoadSongsOutputData;
import use_case.pause_song.PauseSongInputData;
import use_case.pause_song.PauseSongOutputBoundary;
import use_case.pause_song.PauseSongOutputData;
import use_case.play_song.PlaySongInputData;
import use_case.play_song.PlaySongOutputBoundary;
import use_case.play_song.PlaySongOutputData;
import use_case.resume_song.ResumeSongInputData;
import use_case.resume_song.ResumeSongOutputBoundary;
import use_case.resume_song.ResumeSongOutputData;

import java.io.IOException;

import static org.junit.Assert.*;

public class UseCaseInteractorTest {

    private final String url = "https://csc207swiftify.s3.amazonaws.com/120.mp3?X-Amz-Security-Token=IQoJb3JpZ2luX2VjEHUaCXVzLWVhc3QtMSJHMEUCIQDNA4T5OUWVjFF0%2Bwr7r7PDIZ5PjtUoHftDud%2BeSVrUBAIgXUvwMibTqOA%2FchfhG%2FGsnoKyBg9uW%2Bea3sMvyEKzIcoq%2FQIIzv%2F%2F%2F%2F%2F%2F%2F%2F%2F%2FARAAGgw4NzM4NDY2MTcxNzkiDJf36wsoF0Mq7%2BBJMSrRAo%2Fkw4rBt5clqfPqKqYRrlkWYmK10LCs3JUjAZkMdUBtxNslRYUsr%2B0zZmAF6uYgsVQiFjHuKd3zsSHi%2Bgk1i9SIZT5QHHtMATysf2%2BACe8ED3jGjdiT0T6OzMb89Tn0ncb9NxJXzMxI%2BsFNM0AsE1hJEwk7jYU4son6c8cjy0Vc83%2Fb6fEuB%2Fzx6cyYrC%2FWmnqti94rRAavgdPwyw20YIEwf5TPjuEqw9EsbQZK1jh6jNbdGxqpHC88Cy3j8ffY8OyBsr1Dg7cXsb%2Bvf8SuzvslGILrspM1lLzIDf0eMVZ556SpWq0jlnVb6%2BBOGxg7QlES6oxU6h9T6671sIR%2FWgRpipIbj8WGBu7AWqIPjdTCaPwIEkIK4GHusJYmGH33pZtGv5ZLy6AmYB64Em8veD5QdUgF22s7b7urz%2FyQvlWISWyje9EJLjWG%2BunHXNknC30wpoCbqwY6ngG285F%2BqL0bGJw8u86Acx4NUto%2BEHAsp68n4YdQj9Tl1kRfU2bEIcOkiN08TJIZmHZhh3EAr4rh%2B0AmCbAC8QoRuJr2K1wAqlcKx1lB8hjTMgCsi5Hv06jeAxcUtU0l4Beu2d0Z6WKN3ktIrAhm6ivgXXylhBgeZv50T6zCz4y4gX%2F81obWK2Y9WL27hOjnWD0%2BsaNAZkp91s%2FbNelrBg%3D%3D&X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Date=20231129T043958Z&X-Amz-SignedHeaders=host&X-Amz-Expires=3600&X-Amz-Credential=ASIA4W5KRGRNUV5SJPNI%2F20231129%2Fus-east-1%2Fs3%2Faws4_request&X-Amz-Signature=f49fc8cc981cffbf3be7c559dc6738e701c5ea036f606141ca651a37de61e3f3";

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
}
