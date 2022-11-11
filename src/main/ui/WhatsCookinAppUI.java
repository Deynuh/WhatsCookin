/*
Note: this file is modelled after the AlarmSystem file provided by the course coordinators
 */

package ui;

import model.RecipeBook;
import model.RestaurantList;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.FileNotFoundException;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.border.*;

public class WhatsCookinAppUI extends JFrame {
    private static final int WIDTH = 800;
    private static final int HEIGHT = 250;

    private JPanel panelHolder;
    private JPanel mainPanel;
    private JPanel randomizerPanel;
    private JPanel recipesPanel;
    private JPanel restaurantsPanel;
    private CardLayout layout;

    private WhatsCookinApp wca;
    private RecipeBook rb;
    private RestaurantList rl;

    // EFFECTS: constructor creates interface to display WhatsCookingApp UI
    public WhatsCookinAppUI() {
        try {
            wca = new WhatsCookinApp();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        layout = new CardLayout();
        panelHolder = new JPanel();
        panelHolder.setLayout(layout);

        mainPanel = new JPanel();
        mainPanel.setBackground(new Color(238, 233, 207));
        addButtons();

        panelHolder.add(mainPanel, "main");
        add(panelHolder);
        setTitle("What's Cookin'?");

        pack();
        setSize(new Dimension(WIDTH, HEIGHT));
        centreOnScreen();
        setBackground(new Color(238, 233, 207));
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setDefaultLookAndFeelDecorated(true);
        mainPanel.setVisible(true);
        setVisible(true);
        setResizable(false);
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

        for (JButton button : buttons) {
            mainPanel.add(button);
        }
    }

    private void addButtonHoverAction(ArrayList<JButton> buttons) {
        for (JButton button : buttons) {
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
            createRandomizerPanel();
            layout.show(panelHolder, "Randomizer");
        }
    }

    private void createRandomizerPanel() {
        randomizerPanel = new JPanel();
        randomizerPanel.setBackground(new Color(238, 233, 207));

        addRandomizerButtons();

        panelHolder.add(randomizerPanel, "Randomizer");
        mainPanel.setVisible(false);
        randomizerPanel.setVisible(true);
    }

    private void addRandomizerButtons() {
        ArrayList<JButton> buttons = new ArrayList<>();
        JButton b1 = new JButton(new RandRecipeAction());
        JButton b2 = new JButton(new RandRestoAction());
        JButton b3 = new JButton(new RandAnyAction());

        buttons.add(b1);
        buttons.add(b2);
        buttons.add(b3);

        editButtonAppearance(buttons);
        addButtonHoverAction(buttons);

        for (JButton button : buttons) {
            randomizerPanel.add(button);
        }
    }

    private class RandRecipeAction extends AbstractAction {

        RandRecipeAction() {
            super("Choose a recipe");
        }

        @Override
        public void actionPerformed(ActionEvent evt) {
            //wca.getRandom("recipe", rb.getRecipeBook());
            Graphics g = mainPanel.getGraphics();
            randomizerPanel.paint(g);

            if (rb.getRecipeBook().size() == 0) {
                g.drawString("You have no recipes. Please add a recipe first.",375, 200);
            } else {
                g.drawString("You should make: " + rb.randomRecipe().getName(), 375, 200);
            }
        }
    }

    private class RandRestoAction extends AbstractAction {

        RandRestoAction() {
            super("Choose a restaurant");
        }

        @Override
        public void actionPerformed(ActionEvent evt) {

        }
    }

    private class RandAnyAction extends AbstractAction {

        RandAnyAction() {
            super("Choose for me");
        }

        @Override
        public void actionPerformed(ActionEvent evt) {

        }
    }

    private class RecipesAction extends AbstractAction {

        RecipesAction() {
            super("Recipes");
        }

        @Override
        public void actionPerformed(ActionEvent evt) {
            createRecipesPanel();
            layout.show(panelHolder, "Recipes");
        }
    }

    private void createRecipesPanel() {
        recipesPanel = new JPanel();
        recipesPanel.setBackground(new Color(238, 233, 207));

        addRecipesButtons();

        panelHolder.add(recipesPanel, "Recipes");
        mainPanel.setVisible(false);
        recipesPanel.setVisible(true);
    }

    private void addRecipesButtons() {
        ArrayList<JButton> buttons = new ArrayList<>();
        JButton b1 = new JButton(new AddRecipeAction());
        JButton b2 = new JButton(new DeleteRecipeAction());
        JButton b3 = new JButton(new ViewRecipeAction());

        buttons.add(b1);
        buttons.add(b2);
        buttons.add(b3);

        editButtonAppearance(buttons);
        addButtonHoverAction(buttons);

        for (JButton button : buttons) {
            recipesPanel.add(button);
        }
    }

    private class AddRecipeAction extends AbstractAction {

        AddRecipeAction() {
            super("Add");
        }

        @Override
        public void actionPerformed(ActionEvent evt) {

        }
    }

    private class DeleteRecipeAction extends AbstractAction {

        DeleteRecipeAction() {
            super("Delete");
        }

        @Override
        public void actionPerformed(ActionEvent evt) {

        }
    }

    private class ViewRecipeAction extends AbstractAction {

        ViewRecipeAction() {
            super("View Recipes");
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
            createRestaurantsPanel();
            layout.show(panelHolder, "Restaurants");
        }
    }

    private void createRestaurantsPanel() {
        restaurantsPanel = new JPanel();
        restaurantsPanel.setBackground(new Color(238, 233, 207));

        addRestaurantsButtons();

        panelHolder.add(restaurantsPanel, "Restaurants");
        mainPanel.setVisible(false);
        restaurantsPanel.setVisible(true);
    }

    private void addRestaurantsButtons() {
        ArrayList<JButton> buttons = new ArrayList<>();
        JButton b1 = new JButton(new AddRestaurantAction());
        JButton b2 = new JButton(new DeleteRestaurantAction());
        JButton b3 = new JButton(new ViewRestaurantAction());

        buttons.add(b1);
        buttons.add(b2);
        buttons.add(b3);

        editButtonAppearance(buttons);
        addButtonHoverAction(buttons);

        for (JButton button : buttons) {
            restaurantsPanel.add(button);
        }
    }

    private class AddRestaurantAction extends AbstractAction {

        AddRestaurantAction() {
            super("Add");
        }

        @Override
        public void actionPerformed(ActionEvent evt) {

        }
    }

    private class DeleteRestaurantAction extends AbstractAction {

        DeleteRestaurantAction() {
            super("Delete");
        }

        @Override
        public void actionPerformed(ActionEvent evt) {

        }
    }

    private class ViewRestaurantAction extends AbstractAction {

        ViewRestaurantAction() {
            super("View Restaurants");
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
            Graphics g = mainPanel.getGraphics();
            mainPanel.paint(g);
            g.drawString("Saved!",380, 200);
        }
    }

    private class LoadAction extends AbstractAction {

        LoadAction() {
            super("Load");
        }

        @Override
        public void actionPerformed(ActionEvent evt) {
            Graphics g = mainPanel.getGraphics();
            mainPanel.paint(g);
            g.drawString("Loaded!",375, 200);
        }
    }

}
