package entity;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Song implements Comparable<Song> {
    private final int songID;
    private final String album;
    private final int duration;
    private final int releaseYear;
    private final String s3Key;
    private final String title;

    // Constructor
    public Song(@JsonProperty("SongID") int songID,
                @JsonProperty("album") String album,
                @JsonProperty("duration") int duration,
                @JsonProperty("release_year") int releaseYear,
                @JsonProperty("s3_key") String s3Key,
                @JsonProperty("title") String title) {
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

    @Override
    public int compareTo(Song other) {
        return Integer.compare(this.songID, other.songID);
    }
}


