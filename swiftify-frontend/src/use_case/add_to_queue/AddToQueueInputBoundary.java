package use_case.add_to_queue;

import javazoom.jl.decoder.JavaLayerException;

import java.io.IOException;

public interface AddToQueueInputBoundary {

    void execute(AddToQueueInputData songInputData) throws IOException, InterruptedException, JavaLayerException;
}
