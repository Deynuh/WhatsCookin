/*
NOTE: this code was modelled after a user's answer in
https://stackoverflow.com/questions/11933089/swing-splash-screen-with-progress-bar
 */

package ui;

import java.awt.*;
import java.awt.event.ActionListener;
import javax.swing.*;

// Splash screen for the application
public class SplashScreen extends JWindow {

    private static JProgressBar progressBar = new JProgressBar();
    private static int count = 1;
    private static int TIMER_PAUSE = 25;
    private static int PROGBAR_MAX = 40;
    private static Timer progressBarTimer;

    ActionListener al = new ActionListener() {

        @Override
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            progressBar.setValue(count);
            System.out.println(count);
            if (PROGBAR_MAX == count) {
                progressBarTimer.stop();//stop the timer
                dispose();
            }
            count++;//increase counter

        }
    };


    public SplashScreen() {
        createSplash();
    }

    // MODIFIES: this
    // EFFECTS: creates the splash screen
    private void createSplash() {
        Container container = getContentPane();

        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        JLabel label = new JLabel("Loading...");
        label.setFont(new Font("Futura", Font.BOLD, 14));

        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(getLogo(), gbc);
        progressBar.setOpaque(false);
        container.setBackground(new Color(232, 211, 185));
        panel.setBackground(new Color(232, 211, 185));

        gbc.gridx = 0;
        gbc.gridy = 2;
        panel.add(label, gbc);

        progressBar.setStringPainted(true);
        progressBar.setMaximum(PROGBAR_MAX);
        container.add(panel);
        container.add(progressBar, BorderLayout.SOUTH);

        pack();
        setLocationRelativeTo(null);
        setVisible(true);

        startProgressBar();
    }

    // EFFECTS: creates and adds logo image
    private JLabel getLogo() {
        ImageIcon logo = new ImageIcon("images/logo.png");
        Image newLogo = logo.getImage().getScaledInstance(300,250, Image.SCALE_SMOOTH);
        logo = new ImageIcon(newLogo);
        return new JLabel(logo);
    }

    // EFFECTS: starts the progress bar
    private void startProgressBar() {
        progressBarTimer = new Timer(TIMER_PAUSE, al);
        progressBarTimer.start();
    }

    // EFFECTS: helps the splash screen run
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {

            @Override
            public void run() {
                //splashScreen = new SplashScreen();
            }
        });
    }
}