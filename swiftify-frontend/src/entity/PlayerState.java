package src.entity;

public class PlayerState {
    // core logic of "song being played".
    // play/pause button pressed -> clean architecture stuff -> usecase executes corresponding method in MusicPlayer
    // MusicPlayer executes play/pause method, which calls this.
    // ...

    // current Song currentSong
    // current int currentTime

    // play (process command issued by MusicPlayer)
        // if play command is issued, should unfreeze current time and resume song playing. if no resume
        // feature exists within java sound, we can do this by passing currentTime.
    // pause (process command issued by MusicPlayer)
        // if pause command is issued, should somehow freeze currentTime and freeze song playing.



}
