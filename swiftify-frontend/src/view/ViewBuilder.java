package view;

import javax.swing.*;

public class ViewBuilder {

    private String viewName;

    public ViewBuilder(String viewName) {
        this.viewName = viewName;
    }

    public JFrame buildView() {
        JFrame view = new JFrame();
        if (this.viewName.equalsIgnoreCase("LoadSongsView")) {
            view = buildView2("test");
        }
        return view;
    }

    private LoadAlbumView1 buildView1() {
        return new LoadAlbumView1();
    }

    private LoadSongsView buildView2(String albumName) {
        return new LoadSongsView(albumName);
    }
}
