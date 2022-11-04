/*
Note: this file is modelled after the AlarmSystem file provided by the course coordinators
 */

package ui;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.*;
import javax.swing.border.Border;

public class WhatsCookinAppUI extends JFrame {
    private static final int WIDTH = 800;
    private static final int HEIGHT = 600;

    private JPanel panel;

    private Color fillColor;

    private WhatsCookinApp wca;

    // EFFECTS: constructor creates interface to display WhatsCookingApp UI
    public WhatsCookinAppUI() {
        /*try {
            wca = new WhatsCookinApp();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }*/

        setDefaultLookAndFeelDecorated(true);
        setSize(WIDTH, HEIGHT);
        setTitle("What's Cookin'?");
//
//        panel = new JPanel();
//        //panel.setLayout(new GridLayout(2, 2));
//        panel.addMouseListener(new DesktopFocusAction());
//
        addButtons();
//        add(panel);

        //pack();
        centreOnScreen();
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        //panel.setVisible(true);
        setVisible(true);
    }

    @Override
    public void paint(Graphics g) {
        //super.paint(g);
        //g.setColor(fillColor);
        //g.fillRect(0, 0, getWidth(), getHeight());
        //g.setColor(Color.BLACK);
    }

    /**
     * Represents action to be taken when user clicks desktop
     * to switch focus. (Needed for key handling.)
     */
    private class DesktopFocusAction extends MouseAdapter {
        @Override
        public void mouseClicked(MouseEvent e) {
            WhatsCookinAppUI.this.requestFocusInWindow();
        }
    }

    /**
     * Helper to centre main application window on desktop
     */
    private void centreOnScreen() {
        int width = Toolkit.getDefaultToolkit().getScreenSize().width;
        int height = Toolkit.getDefaultToolkit().getScreenSize().height;
        setLocation((width - getWidth()) / 2, (height - getHeight()) / 2);
    }

    /**
     * Helper to add control buttons.
     */
    private void addButtons() {
        add(new JButton(new RandomizerAction()));
        add(new JButton(new RecipesAction()));
        add(new JButton(new RestaurantsAction()));
        add(new JButton(new SaveAction()));
        add(new JButton(new LoadAction()));

    }

    private class RandomizerAction extends AbstractAction {

        RandomizerAction() {
            super("Randomizer");
        }

        @Override
        public void actionPerformed(ActionEvent evt) {
            String test = JOptionPane.showInputDialog(null,
                    "test message",
                    "test title",
                    JOptionPane.QUESTION_MESSAGE);
        }
    }

    private class RecipesAction extends AbstractAction {

        RecipesAction() {
            super("Recipes");
        }

        @Override
        public void actionPerformed(ActionEvent evt) {

        }
    }

    private class RestaurantsAction extends AbstractAction {

        RestaurantsAction() {
            super("Restaurants");
        }

        @Override
        public void actionPerformed(ActionEvent evt) {

        }
    }

    private class SaveAction extends AbstractAction {

        SaveAction() {
            super("Save");
        }

        @Override
        public void actionPerformed(ActionEvent evt) {

        }
    }

    private class LoadAction extends AbstractAction {

        LoadAction() {
            super("Load");
        }

        @Override
        public void actionPerformed(ActionEvent evt) {

        }
    }

}
