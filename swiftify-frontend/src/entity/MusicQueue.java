package entity;

import data_access.MusicLibrary;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.Random;

/**
 * Represents a music queue that can be used to manage and control a list of songs.
 */

public class MusicQueue implements Iterable<Integer> {
    private Node head;
    private Node tail;
    private Node current;
    private final Random random;
    private boolean shuffled;
    private int discographyLength;
    private String albumName;

    /**
     * Represents a node within the music queue.
     */

    private static class Node {
        int songID;
        Node next;
        Node prev;

        Node(int songID) {
            this.songID = songID;
        }
    }

    /**
     * Constructs a new MusicQueue with the specified configuration.
     *
     * @param shuffled          Whether the queue should be initially shuffled.
     * @param discographyLength The total length of the music discography.
     */

    public MusicQueue(boolean shuffled, int discographyLength, String albumName) {
        this.head = null;
        this.tail = null;
        this.current = null;
        this.random = new Random();
        this.shuffled = shuffled;
        this.discographyLength = discographyLength;
        this.albumName = albumName;
    }

    /**
     * Gets the albumName of the MusicQueue object.
     *
     * @return Return the String albumName of the given MusicQueue.
     */

    public String getAlbumName() {
        return this.albumName;
    }

    /**
     * Gets the discographyLength of the MusicQueue object.
     *
     * @return Return the int discographyLength of the given MusicQueue.
     */

    public int getDiscographyLength() {
        return this.discographyLength;
    }

    /**
     * Gets the ID of the currently playing song.
     *
     * @return The ID of the currently playing song, or -1 if no song is playing.
     */

    public int getCurrentID() {
        return (current != null) ? current.songID : -1;
    }

    /**
     * Adds a new song to the queue.
     *
     * @param songID The ID of the song to add.
     */

    public void add(int songID) {
        Node newNode = new Node(songID);
        if (head == null) {
            head = newNode;
            tail = newNode;
            current = newNode;
        } else {
            tail.next = newNode;
            newNode.prev = tail;
            tail = newNode;
        }
    }

    /**
     * Moves to the next song in the queue.
     */

    public void next() {
        if (isNotEmpty()) {
            if (current.next == null){
                add(getNextSongId());
            }
            current = current.next;
        }
    }

    /**
     * Moves to the previous song in the queue.
     */
    public void previous() {
        if (isNotEmpty() && current.prev != null) {
            current = current.prev;
        }
    }

    /**
     * Clears the entire queue.
     */
    public void clearQueue() {
        current.next = null;
    }

    /**
     * Toggles shuffle mode on/off.
     */

    public void toggleShuffle() {
        shuffled = !shuffled;
        if (shuffled) {
            shuffle();
        }
    }

    /**
     * Shuffles the songs in the queue.
     */

    private void shuffle() {
        if (isNotEmpty()) {
            Node currentShuffleNode = current;
            ArrayList<Node> nodes = toArray(currentShuffleNode.next);
            Collections.shuffle(nodes);
            rebuildQueue(nodes, currentShuffleNode);
        }
    }

    /**
     * Converts the queue to an ArrayList of nodes starting from the specified node.
     *
     * @param startNode The starting node.
     * @return An ArrayList containing nodes.
     */

    private ArrayList<Node> toArray(Node startNode) {
        ArrayList<Node> nodes = new ArrayList<Node>();
        Node currentNode = startNode;
        while (currentNode != null) {
            nodes.add(currentNode);
            currentNode = currentNode.next;
        }
        return nodes;
    }

    /**
     * Rebuilds the queue based on the shuffled order.
     *
     * @param nodes         The ArrayList of nodes representing the shuffled order.
     * @param startNode     The node from which the shuffle started.
     */

    private void rebuildQueue(ArrayList<Node> nodes, Node startNode) {
        startNode.next = nodes.get(0);
        tail = nodes.get(nodes.size() - 1);
        for (int i = 0; i < nodes.size() - 1; i++) {
            nodes.get(i).next = nodes.get(i + 1);
            nodes.get(i + 1).prev = nodes.get(i);
        }
        nodes.get(nodes.size() - 1).next = null;
    }

    /**
     * Gets the ID of the next song to play, considering shuffle mode.
     *
     * @return The ID of the next song to play.
     */

    private int getNextSongId() {
        if (shuffled) {
            return getRandomUnplayedSong();
        } else {
            return (current.songID + 1) % discographyLength;
        }
    }

    /**
     * Gets a random unplayed song ID.
     *
     * @return A random unplayed song ID.
     */

    private int getRandomUnplayedSong() {
        int nextSongId;
        do {
            nextSongId = random.nextInt(discographyLength);
        } while (hasSongBeenPlayed(nextSongId) || !MusicLibrary.getInstance().getSongs().get(nextSongId).getAlbum().equals(this.albumName));

        return nextSongId;
    }

    /**
     * Checks if a song with the given ID has been played before the current song.
     *
     * @param songId The ID of the song to check.
     * @return True if the song has been played, false otherwise.
     */

    private boolean hasSongBeenPlayed(int songId) {
        for (int id : this){
            if (id == songId) {
                return true;
            }
            if (id == current.songID){
                break;
            }
        }

        return false;
    }

    /**
     * Checks if the queue is not empty.
     *
     * @return True if the queue is not empty, false otherwise.
     */

    public boolean isNotEmpty() {
        return head != null;
    }

    @Override
    public Iterator<Integer> iterator() {
        return new MusicQueueIterator(head);
    }

    /**
     * An iterator for iterating through the song IDs in the queue.
     */

    private class MusicQueueIterator implements Iterator<Integer> {
        private Node currentIteratorNode;

        public MusicQueueIterator(Node startNode) {
            this.currentIteratorNode = startNode;
        }

        @Override
        public boolean hasNext() {
            return currentIteratorNode != null;
        }

        @Override
        public Integer next() {
            if (!hasNext()) {
                throw new IllegalStateException("No more elements in the queue.");
            }

            int songID = currentIteratorNode.songID;
            currentIteratorNode = currentIteratorNode.next;
            return songID;
        }
    }

    /**
     * Displays the current state of the queue.
     */

    public void displayQueue() {
        if (!isNotEmpty()) {
            System.out.println("The queue is empty.");
            return;
        }

        System.out.println("Current Music Queue:");
        Node currentQueueNode = head;
        while (currentQueueNode != null) {
            System.out.println("- " + currentQueueNode.songID);
            currentQueueNode = currentQueueNode.next;
        }
    }

    public boolean shuffled() {
        return shuffled;
    }

    /*
    public static void main(String[] args) {
        MusicQueue musicQueue = new MusicQueue(true, 107, "");

        // Add some songs to the queue
        for (int i = 1; i <= 5; i++) {
            musicQueue.add(i);
        }

        System.out.println("Original Queue:");
        musicQueue.displayQueue();

        // Shuffle the queue
        System.out.println("\nShuffling the Queue:");
        musicQueue.shuffle();
        musicQueue.displayQueue();

        // Warning ugly test
        musicQueue.next();
        System.out.println(musicQueue.getCurrentID());

        musicQueue.next();
        System.out.println(musicQueue.getCurrentID());

        musicQueue.next();
        System.out.println(musicQueue.getCurrentID());

        musicQueue.next();
        System.out.println(musicQueue.getCurrentID());

        musicQueue.next();
        System.out.println(musicQueue.getCurrentID());

        musicQueue.next();
        System.out.println(musicQueue.getCurrentID());

        musicQueue.next();
        System.out.println(musicQueue.getCurrentID());

        musicQueue.next();
        System.out.println(musicQueue.getCurrentID());

        musicQueue.toggleShuffle();

        musicQueue.next();
        System.out.println(musicQueue.getCurrentID());

        musicQueue.next();
        System.out.println(musicQueue.getCurrentID());

        musicQueue.next();
        System.out.println(musicQueue.getCurrentID());

        musicQueue.next();
        System.out.println(musicQueue.getCurrentID());

        musicQueue.previous();
        System.out.println(musicQueue.getCurrentID());

        musicQueue.previous();
        System.out.println(musicQueue.getCurrentID());

        musicQueue.previous();
        System.out.println(musicQueue.getCurrentID());

        musicQueue.previous();
        System.out.println(musicQueue.getCurrentID());

        musicQueue.previous();
        System.out.println(musicQueue.getCurrentID());

        musicQueue.previous();
        System.out.println(musicQueue.getCurrentID());

        musicQueue.previous();
        System.out.println(musicQueue.getCurrentID());

        musicQueue.previous();
        System.out.println(musicQueue.getCurrentID());

        musicQueue.previous();
        System.out.println(musicQueue.getCurrentID());

        musicQueue.previous();
        System.out.println(musicQueue.getCurrentID());
    }

     */
}
