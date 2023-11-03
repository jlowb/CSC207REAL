package com.group113.swiftify.app;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class App {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            createAndShowGUI();
        });
    }

    private static void createAndShowGUI() {
        JFrame frame = new JFrame("Swiftify - Music Player");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 200);

        // Create buttons for play, pause, stop, and skip
        JButton playButton = new JButton("Play");
        JButton pauseButton = new JButton("Pause");
        JButton stopButton = new JButton("Stop");
        JButton skipButton = new JButton("Skip");

        // Create a progress bar
        JProgressBar progressBar = new JProgressBar(0, 100);

        // Create a playlist area (you can use JList or JTextArea)
        JTextArea playlistArea = new JTextArea(5, 30);
        playlistArea.setEditable(false);

        // Add action listeners to the buttons
        playButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Play music here
            }
        });

        pauseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Pause music here
            }
        });

        stopButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Stop music here
            }
        });

        skipButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Skip to the next track in the playlist
            }
        });

        // Create a panel to hold the buttons and progress bar
        JPanel controlPanel = new JPanel();
        controlPanel.add(playButton);
        controlPanel.add(pauseButton);
        controlPanel.add(stopButton);
        controlPanel.add(skipButton);

        // Create a panel to hold the playlist area
        JPanel playlistPanel = new JPanel(new BorderLayout());
        playlistPanel.add(new JLabel("Playlist/Songs:"), BorderLayout.NORTH);
        playlistPanel.add(new JScrollPane(playlistArea), BorderLayout.CENTER);

        // Add the panels to the frame
        frame.getContentPane().add(controlPanel, BorderLayout.NORTH);
        frame.getContentPane().add(progressBar, BorderLayout.CENTER);
        frame.getContentPane().add(playlistPanel, BorderLayout.SOUTH);

        frame.setVisible(true);
    }
}




