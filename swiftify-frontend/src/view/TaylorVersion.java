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
    private JPanel fearlessPanel;
    private JPanel redPanel;

    public TaylorVersion() {
        createUIComponents();
        setupUI();

        ActionListener actionListener = e -> {
            if (e.getSource() instanceof JButton) {
                handleButtonClick(e.getActionCommand());
            }
        };

        fearlessButton.addActionListener(actionListener);
        redButton.addActionListener(actionListener);
    }

    private void handleButtonClick(String actionCommand) {
        if (Objects.equals(actionCommand, "Fearless (Taylor's Version)")) {
            System.out.println("Fearless button clicked");
        } else if (Objects.equals(actionCommand, "Red (Taylor's Version)")) {
            System.out.println("Red button clicked");
        }
    }

    private void setupUI() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(Color.BLACK);

        fearlessPanel.setLayout(new BorderLayout());
        redPanel.setLayout(new BorderLayout());

        panel.add(fearlessPanel, BorderLayout.WEST);
        panel.add(redPanel, BorderLayout.EAST);

        fearlessPanel.add(fearlessButton, BorderLayout.SOUTH);
        redPanel.add(redButton, BorderLayout.SOUTH);

        setContentPane(panel);
        setTitle("Swiftify - Albums - Taylor's Version");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(1080, 680);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public static void main(String[] args) {
        new TaylorVersion();
    }

    private void createUIComponents() {
        fearlessPanel = createImagePanel("/Users/malaikamalik/Desktop/malik130/CSC207REAL/swiftify-frontend/src/pngs/fearless.png");
        redPanel = createImagePanel("/Users/malaikamalik/Desktop/malik130/CSC207REAL/swiftify-frontend/src/pngs/red.png");

        // Initialize buttons
        fearlessButton = new JButton("Fearless (Taylor's Version)");
        redButton = new JButton("Red (Taylor's Version)");

        fearlessPanel.setPreferredSize(new Dimension(530, 340));
        redPanel.setPreferredSize(new Dimension(530, 340));

        fearlessButton.setPreferredSize(new Dimension(150, 50));
        redButton.setPreferredSize(new Dimension(150, 50));
    }

    private ImagePanel createImagePanel(String imagePath) {
        return new ImagePanel(imagePath);
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        // Handle property changes
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // Handle action events
    }

    static class ImagePanel extends JPanel {
        private final Image backgroundImage;

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
