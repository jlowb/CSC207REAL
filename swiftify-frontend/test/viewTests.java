import app.LoadAlbumsUseCaseFactory;
import app.Main;
import interface_adapter.ViewManagerModel;
import interface_adapter.load_album.LoadAlbumController;
import interface_adapter.load_songs.LoadSongsController;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import view.LoadAlbumView;
import view.MainView;
import view.ViewManager;

import javax.swing.SwingUtilities;
import java.awt.event.ActionEvent;
import java.beans.PropertyChangeEvent;
import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.junit.Assert.*;
import static org.junit.Assert.fail;

public class viewTests {

    @Test
    public void testMainView() {
        SwingUtilities.invokeLater(() -> {
            Main main = new Main();

            MainView mainView = new MainView(LoadAlbumsUseCaseFactory.createLoadAlbumsController(new ViewManagerModel()));
            mainView.setVisible(true);

            assert mainView != null : "MainView isn't null";
            assert mainView.isVisible() : "MainView is visible";

        });
    }

    @Test
    public void testOKButtonWork() {
        SwingUtilities.invokeLater(() -> {
            Main main = new Main();

            MainView mainView = new MainView(LoadAlbumsUseCaseFactory.createLoadAlbumsController(new ViewManagerModel()));

            boolean buttonPressed = false;

            mainView.loadAlbumsActionListener.actionPerformed(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, null));

            buttonPressed = true;

            assertTrue(buttonPressed);
        });
    }

    @Test
    public void testChangePageOnButtonClick() {
        SwingUtilities.invokeLater(() -> {
            Main main = new Main();

            LoadAlbumController loadAlbumController = LoadAlbumsUseCaseFactory.createLoadAlbumsController(new ViewManagerModel());
            MainView mainView = new MainView(loadAlbumController);
            mainView.setVisible(true);

            assertFalse(mainView.getLoadAlbumView().isVisible());
            mainView.pressOkButton();

            assertTrue(mainView.getLoadAlbumView().isVisible());
        });
    }

    @Test
    public void testAlbumChangePage() {
        SwingUtilities.invokeLater(() -> {
            Main main = new Main();
            LoadAlbumController loadAlbumController = LoadAlbumsUseCaseFactory.createLoadAlbumsController(new ViewManagerModel());
            MainView mainView = new MainView(loadAlbumController);
            mainView.pressOkButton();

            mainView.pressAlbumButton("evermore");

            assertNotNull(mainView.getSongView());
            assertTrue(mainView.getSongView().isVisible());

        });
    }

}

