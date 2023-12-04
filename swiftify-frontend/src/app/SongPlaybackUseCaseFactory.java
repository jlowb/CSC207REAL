package app;

import data_access.URLSongLoader;
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
import interface_adapter.resume_song.ResumeSongPresenter;
import interface_adapter.resume_song.ResumeSongViewModel;
import use_case.add_to_queue.AddToQueueInputBoundary;
import use_case.add_to_queue.AddToQueueInteractor;
import use_case.add_to_queue.AddToQueueOutputBoundary;
import use_case.next_song.NextSongInputBoundary;
import use_case.next_song.NextSongInteractor;
import use_case.next_song.NextSongOutputBoundary;
import use_case.pause_song.PauseSongInputBoundary;
import use_case.pause_song.PauseSongInteractor;
import use_case.pause_song.PauseSongOutputBoundary;
import use_case.play_song.PlaySongInputBoundary;
import use_case.play_song.PlaySongInteractor;
import use_case.play_song.PlaySongOutputBoundary;
import use_case.prev_song.PrevSongInputBoundary;
import use_case.prev_song.PrevSongInteractor;
import use_case.prev_song.PrevSongOutputBoundary;
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


    public static NextSongController createNextSongController(ViewManagerModel viewManagerModel, URLSongLoader songLoader) {
        NextSongOutputBoundary nextSongOutputBoundary = new NextSongPresenter(new NextSongViewModel(), viewManagerModel);
        NextSongInputBoundary nextSongInputBoundary = new NextSongInteractor(songLoader, nextSongOutputBoundary);
        return new NextSongController(nextSongInputBoundary);
    }


    public static PrevSongController createPrevSongController(ViewManagerModel viewManagerModel, URLSongLoader songLoader) {
        PrevSongOutputBoundary prevSongOutputBoundary = new PrevSongPresenter(new PrevSongViewModel(), viewManagerModel);
        PrevSongInputBoundary prevSongInputBoundary = new PrevSongInteractor(songLoader, prevSongOutputBoundary);
        return new PrevSongController(prevSongInputBoundary);
    }

    public static AddToQueueController createAddToQueueController(ViewManagerModel viewManagerModel) {
        AddToQueueOutputBoundary addToQueueOutputBoundary = new AddToQueuePresenter(new AddToQueueViewModel(), viewManagerModel);
        AddToQueueInputBoundary addToQueueInputBoundary = new AddToQueueInteractor(addToQueueOutputBoundary);
        return new AddToQueueController(addToQueueInputBoundary);
    }
}
