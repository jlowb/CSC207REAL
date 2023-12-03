package view;

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

        if (this.viewModel.getViewName().equalsIgnoreCase("All")) {
            return buildAll();
        }


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

 //   private JFrame BuildAlbumView() {
       // LoadAlbumState loadAlbumState = (LoadAlbumState) this.viewModel.getState();
       // if (loadAlbumState.getAlbumType().equals("All")) {
          //  LoadAlbumView1 LoadAlbumView1 = new LoadAlbumView1();
         //   for (Album album : loadAlbumState.getAlbums()) {
        //    LoadAlbumView1 page = new LoadAlbumView1();
        //    page.setVisible(true);
     //   LoadAlbumState loadAlbumState = (LoadAlbumState) this.viewModel.getState();
     //   if (loadAlbumState.getAlbumType().equals("All")) {
         //   return buildAll();


       //     }
      //  return buildAll();
     //   }
       // for (Album albumtype : loadAlbumState.getAlbums()) {
       //

      //  }

    private LoadAlbumView buildAlbumView() {
        LoadAlbumState loadAlbumState = (LoadAlbumState) this.viewModel.getState();
        if (loadAlbumState.getAlbumType().equals("All")) {
            return new LoadAlbumView();
        }

        return new LoadAlbumView(); // wont even just show the static screen
    }



    private JFrame buildAll() {
        return new LoadAlbumView();
    }




  //  public LoadAlbumView1 buildAlbumView() {
   //     return new LoadAlbumView1();
  //  }
}
