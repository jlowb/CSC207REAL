import app.LoadAlbumsUseCaseFactory;
import app.Main;
import interface_adapter.ViewManagerModel;
import interface_adapter.load_album.LoadAlbumController;
import org.junit.Test;
import view.MainView;
import view.ViewManager;

import javax.swing.*;

import static org.junit.Assert.*;

public class ViewTest {

    @Test
    public void testInit() {

        ViewManagerModel viewManagerModel = new ViewManagerModel();
        ViewManager viewManager = new ViewManager(viewManagerModel);

        MainView mainView = new MainView(LoadAlbumsUseCaseFactory.createLoadAlbumsController(viewManagerModel));

        assertNotNull(viewManager);
        assertNotNull(mainView);
        assertEquals(viewManagerModel, viewManager.getViewManagerModel());

    }

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
    public void testMainMethod() {
        ViewManagerModel viewManagerModel = new ViewManagerModel();

        Main.main(null);

        assertNotNull(viewManagerModel);

    }

    @Test
    public void testViewManagerInit() {
        ViewManagerModel viewManagerModel = new ViewManagerModel();
        ViewManager viewManager = new ViewManager(viewManagerModel);
        assertNotNull(viewManager);
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
