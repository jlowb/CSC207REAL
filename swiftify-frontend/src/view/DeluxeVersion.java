package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class DeluxeVersion extends JFrame implements ActionListener, PropertyChangeListener {
    private JButton the1989Button;
    private JButton speakNowButton;
    private JPanel the1989Panel;
    private JPanel speakNowPanel;

    public DeluxeVersion() {
        createUIComponents();

        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(Color.BLACK);

        speakNowPanel.setLayout(new BorderLayout());
        the1989Panel.setLayout(new BorderLayout());

        panel.add(speakNowPanel, BorderLayout.WEST);
        panel.add(the1989Panel, BorderLayout.EAST);


        speakNowPanel.add(speakNowButton, BorderLayout.SOUTH);
        the1989Panel.add(the1989Button, BorderLayout.SOUTH);


        setContentPane(panel);
        setTitle("Swiftify - Albums - Deluxe");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(1080, 680);
        setLocationRelativeTo(null);
        setVisible(true);


        ActionListener actionListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() instanceof JButton) {
                    System.out.println("Button clicked");
                }
            }
        };

        speakNowButton.addActionListener(actionListener);
        the1989Button.addActionListener(actionListener);
    }

    public static void main(String[] args) {
        new DeluxeVersion();
    }

    private void createUIComponents() {
        speakNowPanel = new ImagePanel("/Users/malaikamalik/Desktop/malik130/CSC207REAL/swiftify-frontend/src/pngs/speak_now.png");
        the1989Panel = new ImagePanel("/Users/malaikamalik/Desktop/malik130/CSC207REAL/swiftify-frontend/src/pngs/1989.png");

        // Initialize buttons
        speakNowButton = new JButton("Speak Now (Deluxe Edition)");
        the1989Button = new JButton("1989 (Deluxe)");
        speakNowPanel.setPreferredSize(new Dimension(530, 340));
        the1989Panel.setPreferredSize(new Dimension(530, 340));



        speakNowButton.setPreferredSize(new Dimension(150, 50));
        the1989Button.setPreferredSize(new Dimension(150, 50));
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {

    }

    static class ImagePanel extends JPanel {
        private Image backgroundImage;

        public ImagePanel(String imagePath) {
            this.backgroundImage = new ImageIcon(imagePath).getImage();
            setPreferredSize(new Dimension(backgroundImage.getWidth(this), backgroundImage.getHeight(this)));


            setBackground(new Color(99, 93, 133));
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);

            double panelWidth = getWidth();
            double panelHeight = getHeight();
            double imageWidth = backgroundImage.getWidth(this);
            double imageHeight = backgroundImage.getHeight(this);

            double scale = Math.min(panelWidth / imageWidth, panelHeight / imageHeight);
            int scaledWidth = (int) (imageWidth * scale);
            int scaledHeight = (int) (imageHeight * scale);

            int x = (int) ((panelWidth - scaledWidth) / 2);
            int y = (int) ((panelHeight - scaledHeight) / 2);

            g.drawImage(backgroundImage, x, y, scaledWidth, scaledHeight, this);
        }
    }
}