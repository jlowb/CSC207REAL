package app;

import interface_adapter.ViewManagerModel;
import interface_adapter.add_to_queue.AddToQueueController;
import interface_adapter.add_to_queue.AddToQueuePresenter;
import interface_adapter.add_to_queue.AddToQueueViewModel;
import use_case.add_to_queue.AddToQueueInputBoundary;
import use_case.add_to_queue.AddToQueueInteractor;
import use_case.add_to_queue.AddToQueueOutputBoundary;

import javax.swing.text.View;

public class AddToQueueUseCaseFactory {

    private AddToQueueUseCaseFactory() {

    }

    public static AddToQueueController createAddToQueueController(ViewManagerModel viewManagerModel) {
        AddToQueueOutputBoundary addToQueueOutputBoundary = new AddToQueuePresenter(new AddToQueueViewModel(), viewManagerModel);
        AddToQueueInputBoundary addToQueueInputBoundary = new AddToQueueInteractor(addToQueueOutputBoundary);
        return new AddToQueueController(addToQueueInputBoundary);
    }
}
