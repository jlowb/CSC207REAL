package use_case.resume_song;

import java.io.IOException;

public interface ResumeSongInputBoundary {

    void execute(ResumeSongInputData resumeSongInputData) throws IOException, InterruptedException ;
}
