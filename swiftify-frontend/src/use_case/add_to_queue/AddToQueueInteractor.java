package use_case.add_to_queue;

import data_access.MusicPlayerFacade;
import javazoom.jl.decoder.JavaLayerException;

import java.io.IOException;

public class AddToQueueInteractor implements AddToQueueInputBoundary {
    private final AddToQueueOutputBoundary addToQueueOutputBoundary;


    public AddToQueueInteractor(AddToQueueOutputBoundary addToQueueOutputBoundary) {
        this.addToQueueOutputBoundary = addToQueueOutputBoundary;
    }

    public void execute(AddToQueueInputData addToQueueInputData) throws IOException, InterruptedException, JavaLayerException {
        MusicPlayerFacade musicPlayer = MusicPlayerFacade.getInstance(addToQueueInputData.getView().getAlbumName());
        musicPlayer.addToQueue(addToQueueInputData.getSongId());

        AddToQueueOutputData addToQueueOutputData = new AddToQueueOutputData(addToQueueInputData.getView());
        this.addToQueueOutputBoundary.prepareQueueView(addToQueueOutputData);
    }
}
