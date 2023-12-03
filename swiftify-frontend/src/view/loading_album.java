package view;

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
    private JButton button12;
    private JScrollBar scrollBar1;
    private JButton songButton;


    public loading_album(String selectedAlbum) {
        setContentPane(panel1);
        setTitle("Swiftify Album- Red");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(1080, 680);
        setLocationRelativeTo(null);
        setVisible(true);

        ActionListener a = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() instanceof JButton) {
                    LoadAlbumView page = new LoadAlbumView();
                    page.setVisible(true);
                }
                dispose();
            }
        };

    }
    public static void main(String[] args) {
        new loading_album(args[0]);
    }


}


