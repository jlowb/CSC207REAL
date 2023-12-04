package use_case.add_to_queue;

import view.LoadSongsView;

public class AddToQueueInputData {
    private final Integer songId;
    private final LoadSongsView view;

    public AddToQueueInputData(Integer songId, LoadSongsView view) {
        this.songId = songId;
        this.view = view;
    }

    public Integer getSongId() {
        return this.songId;
    }

    public LoadSongsView getView() {
        return this.view;
    }
}
