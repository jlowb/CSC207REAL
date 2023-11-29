package src.entity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.Random;

public class MusicQueue implements Iterable<Integer> {
    private Node head;
    private Node tail;
    private Node current;
    private final Random random;
    private boolean shuffled;
    private int lengthOfDiscography;

    private static class Node {
        int songID;
        Node next;
        Node prev;

        Node(int songID) {
            this.songID = songID;
        }
    }

    public MusicQueue(boolean shuffled, int lengthOfDiscography) {
        this.head = null;
        this.tail = null;
        this.current = null;
        this.random = new Random();
        this.shuffled = shuffled;
        this.lengthOfDiscography = lengthOfDiscography;
    }

    public int getCurrentID() {
        return (current != null) ? current.songID : -1;
    }

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

    public void next() {
        if (isNotEmpty()) {
            if (current.next == null){
                add(getNextSongId());
            }
            current = current.next;
        }
    }

    public void previous() {
        if (isNotEmpty() && current.prev != null) {
            current = current.prev;
        }
    }

    public void clearQueue() {
        current.next = null;
    }

    public void toggleShuffle() {
        shuffled = !shuffled;
        if (shuffled) {
            shuffle();
        }
    }

    private void shuffle() {
        if (isNotEmpty()) {
            Node currentShuffleNode = current;
            ArrayList<Node> nodes = toArray(currentShuffleNode.next);
            Collections.shuffle(nodes);
            rebuildQueue(nodes, currentShuffleNode);
        }
    }

    private ArrayList<Node> toArray(Node startNode) {
        ArrayList<Node> nodes = new ArrayList<Node>();
        Node currentNode = startNode;
        while (currentNode != null) {
            nodes.add(currentNode);
            currentNode = currentNode.next;
        }
        return nodes;
    }

    private void rebuildQueue(ArrayList<Node> nodes, Node startNode) {
        startNode.next = nodes.get(0);
        tail = nodes.get(nodes.size() - 1);
        for (int i = 0; i < nodes.size() - 1; i++) {
            nodes.get(i).next = nodes.get(i + 1);
            nodes.get(i + 1).prev = nodes.get(i);
        }
        nodes.get(nodes.size() - 1).next = null;
    }

    private int getNextSongId() {
        if (shuffled) {
            return getRandomUnplayedSong();
        } else {
            return (current.songID + 1) % lengthOfDiscography;
        }
    }

    private int getRandomUnplayedSong() {
        int nextSongId;
        do {
            nextSongId = random.nextInt(lengthOfDiscography);
        } while (hasSongBeenPlayed(nextSongId));

        return nextSongId;
    }

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

    public boolean isNotEmpty() {
        return head != null;
    }

    @Override
    public Iterator<Integer> iterator() {
        return new MusicQueueIterator(head);
    }

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

    public static void main(String[] args) {
        MusicQueue musicQueue = new MusicQueue(true, 107);

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
}
