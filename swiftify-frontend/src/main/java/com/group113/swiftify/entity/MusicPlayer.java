package com.group113.swiftify.entity;

public class MusicPlayer {


    // core logic of "music list"
    // music chain is by default an empty stack. the top of the stack is the current song.
    // hence, the next song is done by adding to the top, prev song is done by removing from the top (history).
    // how to determine what to add?
    // if button is pressed, nextsong will be that exact song.
    // if no button is pressed and not shuffled, nextsong will be song with next song id.
    // if no button and shuffled, nextsong gives random id not previously visited.

    // todo:
    // implement MusicChain stack thing as an inner class (pop methods, etc.) -------- Greatman
    //      make sure to include construction of MusicChain inside of MusicPlayer initializer.
    // implement next/prev/etc. logic -------- Greatman
    // play/pause -------- Alex

    // attribute:
    // bool playing
    // bool shuffled
    // musicChain - current playlist being played

    // methods:
    // MusicPlayer -> constructor
    // Song getCurrentSong -> looks at the top of the stack
    // play -> run getCurrentSong, provide Song to PlayerState as input
    // pause -> interact with PlayerState, does not need to provide input
    // nextSong -> see above logic
    // prevSong -> pops stack
    // void toggleShuffle
}
