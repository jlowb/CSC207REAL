package use_case.add_to_queue;

import entity.PlayerState;
import view.LoadSongsView;

public class AddToQueueOutputData {
    private final LoadSongsView view;

    public AddToQueueOutputData(LoadSongsView view) {
        this.view = view;
    }

    public LoadSongsView getView() {
        return this.view;
    }
}
