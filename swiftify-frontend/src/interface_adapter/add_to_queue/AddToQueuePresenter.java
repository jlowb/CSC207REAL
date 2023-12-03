package interface_adapter.add_to_queue;

import interface_adapter.ViewManagerModel;
import use_case.add_to_queue.AddToQueueOutputBoundary;
import use_case.add_to_queue.AddToQueueOutputData;

public class AddToQueuePresenter implements AddToQueueOutputBoundary {
    private final AddToQueueViewModel addToQueueViewModel;

    private final ViewManagerModel viewManagerModel;

    public AddToQueuePresenter(AddToQueueViewModel addToQueueViewModel, ViewManagerModel viewManagerModel) {
        this.addToQueueViewModel = addToQueueViewModel;
        this.viewManagerModel = viewManagerModel;
    }

    public void prepareQueueView(AddToQueueOutputData addToQueueOutputData) {
        this.addToQueueViewModel.firePropertyChanged();
        viewManagerModel.setActiveView(addToQueueViewModel.getViewName());
        viewManagerModel.setViewModel(this.addToQueueViewModel);
        viewManagerModel.firePropertyChanged();
    }

    public AddToQueueViewModel getModel() {
        return this.addToQueueViewModel;
    }
}
