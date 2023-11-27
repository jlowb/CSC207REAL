package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Objects;

public class TaylorVersion extends JFrame implements ActionListener, PropertyChangeListener {
    private JButton FearlessButton;
    private JButton RedButton;
    private JButton BackButton;
    private JPanel FearlessPanel;
    private JPanel RedPanel;

    public TaylorVersion() {
        createUIComponents();

        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(Color.BLACK);

        FearlessPanel.setLayout(new BorderLayout());
        RedPanel.setLayout(new BorderLayout());

        panel.add(FearlessPanel, BorderLayout.WEST);
        panel.add(RedPanel, BorderLayout.EAST);


        FearlessPanel.add(FearlessButton, BorderLayout.SOUTH);
        RedPanel.add(RedButton, BorderLayout.SOUTH);


        setContentPane(panel);
        setTitle("Swiftify - Albums - Taylor's Version");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(1080, 680);
        setLocationRelativeTo(null);
        setVisible(true);



        ActionListener actionListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() instanceof JButton) {
                    if (Objects.equals(e.getActionCommand(), "Fearless (Taylor's Version)")) {
                        System.out.println("fearless");
                    }
                    if (Objects.equals(e.getActionCommand(), "Red (Taylor's Version)")) {
                        System.out.println("red");
                    }
                }
            }
        };

        FearlessButton.addActionListener(actionListener);
        RedButton.addActionListener(actionListener);
    }

    public static void main(String[] args) {
        new TaylorVersion();
    }


    private void createUIComponents() {
        FearlessPanel = new TaylorVersion.ImagePanel("/Users/malaikamalik/Desktop/malik130/CSC207REAL/swiftify-frontend/src/pngs/fearless.png");
        RedPanel = new TaylorVersion.ImagePanel("/Users/malaikamalik/Desktop/malik130/CSC207REAL/swiftify-frontend/src/pngs/red.png");

        // Initialize buttons
        FearlessButton = new JButton("Fearless (Taylor's Version)");
        RedButton = new JButton("Red (Taylor's Version)");
        FearlessPanel.setPreferredSize(new Dimension(530, 340));
        RedPanel.setPreferredSize(new Dimension(530, 340));


        FearlessButton.setPreferredSize(new Dimension(150, 50));
        RedButton.setPreferredSize(new Dimension(150, 50));
    }



    @Override
    public void propertyChange(PropertyChangeEvent evt) {

    }

    @Override
    public void actionPerformed(ActionEvent e) {

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


