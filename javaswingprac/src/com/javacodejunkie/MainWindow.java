package com.javacodejunkie;

import javax.swing.*;

public class MainWindow {

        private JFrame window;

        public MainWindow() {
            window = new JFrame();
            window.setTitle("screen");
            window.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            window.setSize(500, 500);
            window.setLocationRelativeTo(null);
        }

        public void show() {
            window.setVisible(true);
        }


}
