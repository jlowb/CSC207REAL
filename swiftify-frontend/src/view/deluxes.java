package view;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class deluxes extends JFrame implements ActionListener, PropertyChangeListener  {
    private JPanel panel1;
    private JButton a1989button;
    private JButton speakbutton;
    private JPanel speakpanel;
    private JPanel a1989panel;
    private JButton button1;
    private JButton button2;
    private JButton button3;

    public deluxes(){
        createUIComponents();
        setContentPane(panel1);
        setTitle("Swiftify - Albums - Deluxe");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(1080, 680);
        setLocationRelativeTo(null);
        setVisible(true);

    }


        public static void main(String[] args) {
        new deluxes();
    }
    private void createUIComponents() {
        // TODO: place custom component creation code here
        speakpanel = new LoadAlbumView1.ImagePanel("swiftify-frontend/src/pngs/speak_now.png");
        setContentPane(speakpanel);

        a1989panel = new LoadAlbumView1.ImagePanel("swiftify-frontend/src/pngs/1989.png");
        setContentPane(a1989panel);
        speakbutton = new JButton("Speak Now");
        a1989button = new JButton("1989");


        speakbutton.addActionListener(this);
        a1989button.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {

    }
}
