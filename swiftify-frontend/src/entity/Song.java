package entity;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * The Song class represents a music track with various attributes.
 * It implements the Comparable interface to allow sorting based on the song ID.
 */
public class Song implements Comparable<Song> {
    private final int songID;
    private final String album;
    private final int duration;
    private final int releaseYear;
    private final String s3Key;
    private final String title;

    /**
     * Constructs a new Song object with specified details.
     *
     * @param songID The unique identifier for the song.
     * @param album The album to which the song belongs.
     * @param duration The duration of the song in seconds.
     * @param releaseYear The year when the song was released.
     * @param s3Key The S3 key where the song file is stored.
     * @param title The title of the song.
     */
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

    /**
     * Returns the song ID.
     *
     * @return The unique identifier of the song.
     */
    public int getSongID() {
        return songID;
    }

    /**
     * Returns the album name of the song.
     *
     * @return The album name.
     */
    public String getAlbum() {
        return album;
    }

    /**
     * Returns the duration of the song.
     *
     * @return The duration in seconds.
     */
    public int getDuration() {
        return duration;
    }

    /**
     * Returns the release year of the song.
     *
     * @return The year of release.
     */
    public int getReleaseYear() {
        return releaseYear;
    }

    /**
     * Returns the S3 key of the song.
     *
     * @return The S3 key where the song file is stored.
     */
    public String getS3Key() {
        return s3Key;
    }

    /**
     * Returns the title of the song.
     *
     * @return The title of the song.
     */
    public String getTitle() {
        return title;
    }

    /**
     * Compares this song with another song based on song ID.
     *
     * @param other The Song object to be compared.
     * @return A negative integer, zero, or a positive integer as this song ID
     *         is less than, equal to, or greater than the specified song's ID.
     */
    @Override
    public int compareTo(Song other) {
        return Integer.compare(this.songID, other.songID);
    }
}
