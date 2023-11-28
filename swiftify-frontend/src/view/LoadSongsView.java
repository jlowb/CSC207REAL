package view;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoadSongsView extends JFrame {
    private JPanel loadSongsPanel;
    private JButton backButton;
    private JProgressBar progressBar;
    private JPanel panel2;
    private JButton previousSongButton;
    private JButton nextSongButton;
    private JButton playPauseButton;
    private JScrollBar scrollBar;
    private JPanel songPanel;
    private JPanel playerPanel;
    private JPanel controlsPanel;
    private JPanel mainPanel;


    public LoadSongsView(String selectedAlbum) {
        setContentPane(loadSongsPanel);
        setTitle("Swiftify Album- Red");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(1080, 680);
        setLocationRelativeTo(null);
        setVisible(true);

        ActionListener a = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() instanceof JButton) {
                    LoadAlbumView1 page = new LoadAlbumView1();
                    page.setVisible(true);
                }
                dispose();
            }
        };
    }

    public void addSong(String songName) {
        songPanel.add(new JButton(songName));
    }

    public static void main(String[] args) {
        new LoadSongsView(args[0]);
    }
}


