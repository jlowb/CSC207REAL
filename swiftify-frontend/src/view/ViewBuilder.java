package view;

import entity.Album;
import entity.Song;
import interface_adapter.ViewModel;
import interface_adapter.load_album.LoadAlbumState;
import interface_adapter.load_songs.LoadSongsState;

import javax.swing.*;

public class ViewBuilder {

    private final ViewModel viewModel;

    public ViewBuilder(ViewModel viewModel) {
        this.viewModel = viewModel;
    }

    public JFrame buildView() {
        if (this.viewModel.getViewName().equalsIgnoreCase("LoadSongsView")) {
            return buildAlbumSongsView();
        }
        if (this.viewModel.getViewName().equalsIgnoreCase("AlbumView")) {
            return buildAlbumView();
        }

        // write switch case for other views later
        return new JFrame();
    }

    private LoadSongsView buildAlbumSongsView() {
        LoadSongsState loadSongsState = (LoadSongsState) this.viewModel.getState();
        LoadSongsView loadSongsView1 = new LoadSongsView(loadSongsState.getAlbumName());
        for (Song song : loadSongsState.getSongs()) {
            loadSongsView1.addSong(song);
        }
        return loadSongsView1;
    }

    private Vieww BuildAlbumView() {
        LoadAlbumState loadAlbumState = (LoadAlbumState) this.viewModel.getState();
        if (loadAlbumState.getAlbumType().equals("All")) {
            LoadAlbumView1 LoadAlbumView1 = new LoadAlbumView1();
            for (Album album : loadAlbumState.getAlbums()) {
                for (int i=1; i < 11; i++ ) {



        }
       // for (Album albumtype : loadAlbumState.getAlbums()) {
       //

      //  }

        return vieww;
    }

    public LoadAlbumView1 buildAlbumView() {
        return new LoadAlbumView1();
    }
}
