package app;

import interface_adapter.ViewManagerModel;
import view.MainView;
import view.ViewManager;
/**
 * The main class for the Swiftify application.
 */

public class Main {
    /**
     * The entry point of the application.
     *
     */

    public static void main(String[] args) {
        // Create a ViewManagerModel to manage views
        ViewManagerModel viewManagerModel = new ViewManagerModel();
        // Create a ViewManager to respond to changes in the ViewManagerModel
        new ViewManager(viewManagerModel);

        // Create the main view and associate it with a LoadAlbumsController
        MainView mainView = new MainView(LoadAlbumsUseCaseFactory.createLoadAlbumsController(viewManagerModel));
    }
}