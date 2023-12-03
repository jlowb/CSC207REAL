package app;

import data_access.URLSongLoader;
import interface_adapter.SongPlaybackState;
import interface_adapter.ViewManagerModel;
import interface_adapter.pause_song.PauseSongController;
import interface_adapter.pause_song.PauseSongPresenter;
import interface_adapter.pause_song.PauseSongViewModel;
import interface_adapter.play_song.PlaySongController;
import interface_adapter.play_song.PlaySongPresenter;
import interface_adapter.play_song.PlaySongViewModel;
import interface_adapter.resume_song.ResumeSongController;
import interface_adapter.resume_song.ResumeSongPresenter;
import interface_adapter.resume_song.ResumeSongViewModel;
import use_case.pause_song.PauseSongInputBoundary;
import use_case.pause_song.PauseSongInteractor;
import use_case.pause_song.PauseSongOutputBoundary;
import use_case.play_song.PlaySongInputBoundary;
import use_case.play_song.PlaySongInteractor;
import use_case.play_song.PlaySongOutputBoundary;
import use_case.resume_song.ResumeSongInputBoundary;
import use_case.resume_song.ResumeSongInteractor;
import use_case.resume_song.ResumeSongOutputBoundary;

public class SongPlaybackUseCaseFactory {

    private SongPlaybackUseCaseFactory() {

    }

    public static PlaySongController createPlaySongController(ViewManagerModel viewManagerModel, URLSongLoader songLoader) {
        PlaySongOutputBoundary playSongOutputBoundary = new PlaySongPresenter(new PlaySongViewModel(), viewManagerModel);
        PlaySongInputBoundary playSongInputBoundary = new PlaySongInteractor(songLoader, playSongOutputBoundary);
        return new PlaySongController(playSongInputBoundary);
    }

    public static PauseSongController createPauseSongController(ViewManagerModel viewManagerModel, SongPlaybackState songPlaybackState) {
        PauseSongOutputBoundary pauseSongOutputBoundary = new PauseSongPresenter(new PauseSongViewModel(songPlaybackState), viewManagerModel);
        PauseSongInputBoundary pauseSongInputBoundary = new PauseSongInteractor(pauseSongOutputBoundary);
        return new PauseSongController(pauseSongInputBoundary);
    }

    public static ResumeSongController createResumeSongController(ViewManagerModel viewManagerModel, SongPlaybackState songPlaybackState) {
        ResumeSongOutputBoundary resumeSongOutputBoundary = new ResumeSongPresenter(new ResumeSongViewModel(songPlaybackState), viewManagerModel);
        ResumeSongInputBoundary resumeSongInputBoundary = new ResumeSongInteractor(resumeSongOutputBoundary);
        return new ResumeSongController(resumeSongInputBoundary);
    }
}
