package data_access;

import entity.Album;
import entity.Song;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AlbumBuilder {

    public static List<Album> fetchAlbums(List<Song> songs) {
        Map<String, List<Song>> albumMap = new HashMap<>();

        // Group songs by album
        for (Song song : songs) {
            albumMap.computeIfAbsent(song.getAlbum(), k -> new ArrayList<>()).add(song);
        }

        // Create albums from the grouped songs
        List<Album> albums = new ArrayList<>();
        for (Map.Entry<String, List<Song>> entry : albumMap.entrySet()) {
            albums.add(new Album(entry.getKey(), entry.getValue()));
        }

        return albums;
    }
}
