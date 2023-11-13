package com.group113.swiftify.entity;

public class Song {
    private final int songID; // Now an integer
    private final String album;
    private final int duration; // in seconds
    private final int releaseYear;
    private final String s3Key;
    private final String title;

    // Constructor
    public Song(int songID, String album, int duration, int releaseYear, String s3Key, String title) {
        this.songID = songID;
        this.album = album;
        this.duration = duration;
        this.releaseYear = releaseYear;
        this.s3Key = s3Key;
        this.title = title;
    }

    public int getSongID() {
        return songID;
    }

    public String getAlbum() {
        return album;
    }

    public int getDuration() {
        return duration;
    }

    public int getReleaseYear() {
        return releaseYear;
    }

    public String getS3Key() {
        return s3Key;
    }

    public String getTitle() {
        return title;
    }
}


