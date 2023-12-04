package app;

import data_access.URLSongLoader;
import interface_adapter.ViewManagerModel;
import interface_adapter.next_song.NextSongController;
import interface_adapter.next_song.NextSongPresenter;
import interface_adapter.next_song.NextSongViewModel;
import interface_adapter.prev_song.PrevSongController;
import interface_adapter.prev_song.PrevSongPresenter;
import interface_adapter.prev_song.PrevSongViewModel;
import use_case.next_song.NextSongInputBoundary;
import use_case.next_song.NextSongInteractor;
import use_case.next_song.NextSongOutputBoundary;
import use_case.prev_song.PrevSongInputBoundary;
import use_case.prev_song.PrevSongInteractor;
import use_case.prev_song.PrevSongOutputBoundary;

public class PrevSongUseCaseFactory {

    private PrevSongUseCaseFactory() {

    }

    public static PrevSongController createPrevSongController(ViewManagerModel viewManagerModel, URLSongLoader songLoader) {
        PrevSongOutputBoundary prevSongOutputBoundary = new PrevSongPresenter(new PrevSongViewModel(), viewManagerModel);
        PrevSongInputBoundary prevSongInputBoundary = new PrevSongInteractor(songLoader, prevSongOutputBoundary);
        return new PrevSongController(prevSongInputBoundary);
    }
}
