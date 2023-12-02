package view;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class taylorver extends JFrame implements ActionListener, PropertyChangeListener  {
    private JPanel panel1;
    private JButton fearlessbutton;
    private JButton redbutton;
    private JPanel redpanel;
    private JPanel fearlesspanel;
    private JButton button1;
    private JButton button2;
    private JButton button3;
    private JButton button4;

    public taylorver() {
        setContentPane(panel1);
        setTitle("Swiftify - Albums - Taylor's Version");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(1080, 680);
        setLocationRelativeTo(null);
        setVisible(true);

    }
    public static void main(String[] args) {
        new taylorver();
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {

    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
        redpanel = new LoadAlbumView1.ImagePanel("swiftify-frontend/src/pngs/red.png");
        setContentPane(redpanel);

        fearlesspanel = new LoadAlbumView1.ImagePanel("swiftify-frontend/src/pngs/fearless.png");
        setContentPane(fearlesspanel);

        fearlessbutton = new JButton("Fearless (Taylor's Version)");
        redbutton = new JButton("Red (Taylor's Version)");
        fearlessbutton.addActionListener(this);
        redbutton.addActionListener(this);
    }
}
