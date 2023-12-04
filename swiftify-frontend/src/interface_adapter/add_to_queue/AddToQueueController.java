package interface_adapter.add_to_queue;

import javazoom.jl.decoder.JavaLayerException;
import use_case.add_to_queue.AddToQueueInputBoundary;
import use_case.add_to_queue.AddToQueueInputData;

import java.io.IOException;

public class AddToQueueController {
    private final AddToQueueInputBoundary addToQueueInputBoundary;

    public AddToQueueController(AddToQueueInputBoundary addToQueueInputBoundary) {
        this.addToQueueInputBoundary = addToQueueInputBoundary;
    }

    public void execute(AddToQueueInputData addToQueueInputData) throws IOException, InterruptedException, JavaLayerException {
        this.addToQueueInputBoundary.execute(addToQueueInputData);
    }
}
