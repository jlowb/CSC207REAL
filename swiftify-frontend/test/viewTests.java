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

            assert mainView != null : "MainView is not null";
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

}

//
//import org.junit.Before;
//        import org.junit.Test;
//        import java.util.NoSuchElementException;
//        import java.util.Iterator;
//
//        import static org.junit.Assert.*;
//
//
//public class WeekTest {
//
//    private Week week;
//    private Iterator<String> days;
//
//    @Before
//    public void init() {
//        week = new Week();
//        days = week.iterator();
//    }
//
//    @Test
//    public void testMainRuns() {
//        Week.main(new String[]{});
//    }
//
//    @Test
//    public void testForEachLoop() {
//        int iterations = 0;
//        for (String s :
//                week) {
//            iterations += 1;
//        }
//        assertEquals(7, iterations);
//    }
//
//    @Test
//    public void getDay() {
//        assertEquals("Sunday", week.getDay(0));
//        assertEquals("Saturday", week.getDay(6));
//    }
//
//    @Test
//    public void testFirst() {
//        assertTrue(days.hasNext());
//        assertEquals("Sunday", days.next());
//    }
//
//    @Test
//    public void testHasNextTwice() {
//        assertTrue(days.hasNext());
//        assertTrue(days.hasNext());
//        assertEquals("Sunday", days.next());
//    }
//
//    @Test
//    public void testNextAndGetDay() {
//        for (int i = 0; i != 7; i++) {
//            assertEquals(week.getDay(i), days.next());
//        }
//    }
//
//    @Test
//    public void testHasNextWhenFalse() {
//        for (int i = 0; i != 7; i++) {
//            days.next();
//        }
//        assertFalse(days.hasNext());
//    }
//
//     TODO: Task 2 Add a test as outlined in the readme
//    @Test
//    public void testNoSuchElementExceptionIndex() {
//        Week week = new Week();
//        Iterator<String> iterator = week.iterator();
//
//        for (int i = 0; i < 7; i++) {
//            iterator.next();
//        }
//
//        try {
//            iterator.next();
//            fail("Expected NoSuchElementException but failed");
//        } catch (NoSuchElementException e) {
//        }
//    }
//
//}
