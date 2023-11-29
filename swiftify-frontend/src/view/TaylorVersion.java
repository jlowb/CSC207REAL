package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Objects;

public class TaylorVersion extends JFrame implements ActionListener, PropertyChangeListener {
    private JButton fearlessButton;
    private JButton redButton;
    private JButton backButton;
    private JPanel fearlessPanel;
    private JPanel redPanel;
    private JPanel bottomPanel;
    private JButton playButton;
    private JButton pauseButton;
    private JButton forwardButton;

    public TaylorVersion() {
        createUIComponents();

        JLabel statusLabel = new JLabel("Playing Now: ");

        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(Color.BLACK);

        fearlessPanel.setLayout(new BorderLayout());
        redPanel.setLayout(new BorderLayout());

        panel.add(fearlessPanel, BorderLayout.WEST);
        panel.add(redPanel, BorderLayout.EAST);

        fearlessPanel.add(fearlessButton, BorderLayout.SOUTH);
        redPanel.add(redButton, BorderLayout.SOUTH);

        // Create the bottom panel with buttons
        bottomPanel = new JPanel(new FlowLayout());
        bottomPanel.add(backButton);
        bottomPanel.add(playButton);
        bottomPanel.add(pauseButton);
        bottomPanel.add(forwardButton);

        panel.add(bottomPanel, BorderLayout.SOUTH);

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

        fearlessButton.addActionListener(actionListener);
        redButton.addActionListener(actionListener);

    }

    public static void main(String[] args) {
        new TaylorVersion();
    }

    private void createUIComponents() {
        fearlessPanel = new ImagePanel("/Users/malaikamalik/Desktop/malik130/CSC207REAL/swiftify-frontend/src/pngs/fearless.png");
        redPanel = new ImagePanel("/Users/malaikamalik/Desktop/malik130/CSC207REAL/swiftify-frontend/src/pngs/red.png");

        fearlessButton = new JButton("Fearless (Taylor's Version)");
        redButton = new JButton("Red (Taylor's Version)");
        playButton = new JButton(">");
        pauseButton = new JButton("||");
        forwardButton = new JButton("<");
        JLabel statusLabel = new JLabel("Playing Now");



        fearlessPanel.setPreferredSize(new Dimension(530, 340));
        redPanel.setPreferredSize(new Dimension(530, 340));

        fearlessButton.setPreferredSize(new Dimension(150, 50));
        redButton.setPreferredSize(new Dimension(150, 50));
        statusLabel.setPreferredSize(new Dimension(300, 20));
        playButton.setPreferredSize(new Dimension(200, 50));
        pauseButton.setPreferredSize(new Dimension(200, 50));
        forwardButton.setPreferredSize(new Dimension(200, 50));
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        // Handle property changes if needed
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // Handle action events if needed
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