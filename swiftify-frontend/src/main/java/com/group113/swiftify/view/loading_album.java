package main.java.com.group113.swiftify.view;

import main.java.com.group113.swiftify.interface_adapter.load_album.LoadAlbumViewModel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class loading_album extends JFrame {
    private JPanel panel1;
    private JButton button1;
    private JProgressBar progressBar1;
    private JPanel panel2;
    private JButton button2;
    private JButton button3;
    private JButton idStateOfGraceButton;
    private JButton idRedButton;
    private JButton idTreacherousButton;
    private JButton idStayStayStayButton;
    private JButton idSadBeautifulTragicButton;
    private JButton idTheLuckyOneButton;
    private JButton idEverythingHasChangedButton;
    private JButton idStarlightButton;
    private JButton button12;
    private JButton idHolyGroundButton;
    private JButton idTheLastTimeButton;
    private JButton idWeAreNeverButton;
    private JButton idIAlmostDoButton;
    private JButton id22Button;
    private JButton idAllTooWellButton;
    private JButton idIKnewYouButton;
    private JButton idBeginAgainButton;
    private JScrollBar scrollBar1;


    public loading_album(String selectedAlbum) {
        setContentPane(panel1);
        setTitle("Swiftify Album- Red");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(1080, 680);
        setLocationRelativeTo(null);
        setVisible(true);
        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                LoadAlbumViewModel loadAlbumViewModel = null;
                LoadAlbumView1 redPage = new LoadAlbumView1();
                redPage.setVisible(true);

                dispose();
            }
        });
    }
    public static void main(String[] args) {
        new loading_album(args[0]);
    }


}


