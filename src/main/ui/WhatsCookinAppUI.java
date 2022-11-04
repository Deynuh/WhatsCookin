/*
Note: this file is modelled after the AlarmSystem file provided by the course coordinators
 */

package ui;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.lang.reflect.Array;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.border.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class WhatsCookinAppUI extends JFrame {
    private static final int WIDTH = 800;
    private static final int HEIGHT = 250;

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
        panel = new JPanel();
        //panel.setLayout(new GridLayout(2,3));
        panel.setBackground(new Color(255, 253, 208));
        addButtons();

        add(panel);
        //setSize(WIDTH, HEIGHT);
        setTitle("What's Cookin'?");

        pack();
        setSize(new Dimension(WIDTH, HEIGHT));
        centreOnScreen();
        setBackground(new Color(255, 253, 208));
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setDefaultLookAndFeelDecorated(true);
        panel.setVisible(true);
        setVisible(true);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        //g.setColor(new Color(255, 253, 208)));
        //g.fillRect(0,0,getWidth(), getHeight());
        //g.setColor(fillColor);
        //g.fillRect(0, 0, getWidth(), getHeight());
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
    private void addButtons() { //figure out how to make it look the same full screen
        ArrayList<JButton> buttons = new ArrayList<>();
        JButton b1 = new JButton(new RandomizerAction());
        JButton b2 = new JButton(new RecipesAction());
        JButton b3 = new JButton(new RestaurantsAction());
        JButton b4 = new JButton(new SaveAction());
        JButton b5 = new JButton(new LoadAction());

        buttons.add(b1);
        buttons.add(b2);
        buttons.add(b3);
        buttons.add(b4);
        buttons.add(b5);

        addButtonIcons(buttons);
        editButtonAppearance(buttons);
        addButtonHoverAction(buttons);

        for (int i = 0; i < buttons.size(); i++) {
            panel.add(buttons.get(i));
        }
    }

    private void addButtonHoverAction(ArrayList<JButton> buttons) {
        for (int i = 0; i < buttons.size(); i++) {
            JButton button = buttons.get(i);
            button.addMouseListener(new MouseAdapter() {
                public void mouseEntered(MouseEvent me) {
                    button.setBorder(new SoftBevelBorder(BevelBorder.LOWERED));
                }

                public void mouseExited(MouseEvent me) {
                    button.setBorder(new SoftBevelBorder(BevelBorder.RAISED));
                }
            });
        }
    }

    private void editButtonAppearance(ArrayList<JButton> buttons) {
        for (int i = 0; i < buttons.size(); i++) {
            JButton button = buttons.get(i);
            if (i >= 3) {
                button.setPreferredSize(new Dimension(100, 50));
                button.setFont(new Font("Futura", Font.PLAIN, 11));
            } else {
                button.setPreferredSize(new Dimension(250, 100));
                button.setFont(new Font("Futura", Font.PLAIN, 16));
            }
            button.setOpaque(true);
            button.setBorder(new SoftBevelBorder(BevelBorder.RAISED));
            button.setBorderPainted(true);
            button.setBackground(new Color(232, 211, 185));
            button.setForeground(Color.BLACK);
        }
    }

    private void addButtonIcons(ArrayList<JButton> buttons) {
        // RANDOMIZER BUTTON
        ImageIcon randomImage = new ImageIcon("images/random.png");
        Image newRandomImage = randomImage.getImage().getScaledInstance(20,20, java.awt.Image.SCALE_SMOOTH);
        randomImage = new ImageIcon(newRandomImage);
        buttons.get(0).setIcon(randomImage);

        // RECIPES BUTTON
        ImageIcon recipesImage = new ImageIcon("images/recipe.png");
        Image newRecipesImage = recipesImage.getImage().getScaledInstance(20,20, java.awt.Image.SCALE_SMOOTH);
        recipesImage = new ImageIcon(newRecipesImage);
        buttons.get(1).setIcon(recipesImage);

        // RESTAURANTS BUTTON
        ImageIcon restaurantsImage = new ImageIcon("images/restaurant.png");
        Image newRestaurantsImage = restaurantsImage.getImage().getScaledInstance(20,20, java.awt.Image.SCALE_SMOOTH);
        restaurantsImage = new ImageIcon(newRestaurantsImage);
        buttons.get(2).setIcon(restaurantsImage);

        // SAVE BUTTON
        ImageIcon saveImage = new ImageIcon("images/save.png");
        Image newSaveImage = saveImage.getImage().getScaledInstance(20,20, java.awt.Image.SCALE_SMOOTH);
        saveImage = new ImageIcon(newSaveImage);
        buttons.get(3).setIcon(saveImage);

        // LOAD BUTTON
        ImageIcon loadImage = new ImageIcon("images/load.png");
        Image newLoadImage = loadImage.getImage().getScaledInstance(20,20, java.awt.Image.SCALE_SMOOTH);
        saveImage = new ImageIcon(newLoadImage);
        buttons.get(4).setIcon(saveImage);
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
