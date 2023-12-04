package app;

import data_access.URLSongLoader;
import interface_adapter.ViewManagerModel;
import interface_adapter.next_song.NextSongController;
import interface_adapter.next_song.NextSongPresenter;
import interface_adapter.next_song.NextSongViewModel;
import use_case.next_song.NextSongInputBoundary;
import use_case.next_song.NextSongInteractor;
import use_case.next_song.NextSongOutputBoundary;

public class NextSongUseCaseFactory {

    private NextSongUseCaseFactory() {

    }

    public static NextSongController createNextSongController(ViewManagerModel viewManagerModel, URLSongLoader songLoader) {
        NextSongOutputBoundary nextSongOutputBoundary = new NextSongPresenter(new NextSongViewModel(), viewManagerModel);
        NextSongInputBoundary nextSongInputBoundary = new NextSongInteractor(songLoader, nextSongOutputBoundary);
        return new NextSongController(nextSongInputBoundary);
    }
}
