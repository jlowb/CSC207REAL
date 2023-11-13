package com.group113.swiftify.entity;

public class Song {
    private int songID; // Now an integer
    private String album;
    private int duration; // in seconds
    private int releaseYear;
    private String s3Key;
    private String title;

    // Constructor
    public Song(int songID, String album, int duration, int releaseYear, String s3Key, String title) {
        this.songID = songID;
        this.album = album;
        this.duration = duration;
        this.releaseYear = releaseYear;
        this.s3Key = s3Key;
        this.title = title;
    }
    // TODO: Methods
}

