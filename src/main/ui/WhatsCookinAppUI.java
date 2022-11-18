/*
Note: this file is modelled after the AlarmSystem file provided by the course coordinators
 */

package ui;

import model.Recipe;
import model.Restaurant;

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

    private JPanel mainPanel;
    private JPanel randomizerPanel;
    private JFrame viewRecipesFrame;
    private JPanel viewRecipesPanel;
    private JFrame viewRestaurantsFrame;
    private JPanel viewRestaurantsPanel;


    private JList recipes;
    private JList restaurants;

    private WhatsCookinApp wca;

    // EFFECTS: constructor creates interface to display WhatsCookingApp UI
    public WhatsCookinAppUI() {
        try {
            wca = new WhatsCookinApp();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        mainPanel = new JPanel();
        mainPanel.setBackground(new Color(238, 233, 207));
        addButtons();

        add(mainPanel);
        pack();
        setTitle("What's Cookin'?");
        setSize(new Dimension(WIDTH, HEIGHT));
        centreOnScreen(this);
        setBackground(new Color(238, 233, 207));
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setDefaultLookAndFeelDecorated(true);
        mainPanel.setVisible(true);
        setVisible(true);
        setResizable(false);
    }

    // MODIFIES: this
    // EFFECTS:  paints the frame
    @Override
    public void paint(Graphics g) {
        super.paint(g);
        //g.setColor(new Color(255, 253, 208)));
        //g.fillRect(0,0,getWidth(), getHeight());
        //g.setColor(fillColor);
        //g.fillRect(0, 0, getWidth(), getHeight());
    }

     // MODIFIES: this
     // EFFECTS: centres main application window on desktop
    private void centreOnScreen(JFrame frame) {
        int width = Toolkit.getDefaultToolkit().getScreenSize().width;
        int height = Toolkit.getDefaultToolkit().getScreenSize().height;
        frame.setLocation((width - getWidth()) / 2, (height - getHeight()) / 2);
    }

    // MODIFIES: mainPanel, this
    // EFFECTS:  adds buttons to the main panel
    private void addButtons() {
        ArrayList<JButton> buttons = new ArrayList<>();
        JButton b1 = new JButton(new RandomizerAction());
        JButton b2 = new JButton(new ViewRecipesAction());
        JButton b3 = new JButton(new ViewRestaurantAction());
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

    // EFFECTS: adds hover animation to buttons
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

    // EFFECTS: edits button appearances
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

    // EFFECTS: adds image icons to buttons
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

    // EFFECTS: creates randomizer panel and sets it to content pane
    private class RandomizerAction extends AbstractAction {

        RandomizerAction() {
            super("Randomizer");
        }

        @Override
        public void actionPerformed(ActionEvent evt) {
            createRandomizerPanel();
            mainPanel.setVisible(false);
            setContentPane(randomizerPanel);
            randomizerPanel.setVisible(true);
        }
    }

    // EFFECTS: creates a new JPanel for the randomizer
    private void createRandomizerPanel() {
        randomizerPanel = new JPanel();
        randomizerPanel.setBackground(new Color(238, 233, 207));

        addRandomizerButtons();
    }

    // MODIFIES: randomizerPanel
    // EFFECTS: adds buttons to the randomizer panel
    private void addRandomizerButtons() {
        ArrayList<JButton> buttons = new ArrayList<>();
        JButton b1 = new JButton(new RandRecipeAction());
        JButton b2 = new JButton(new RandRestoAction());
        JButton b3 = new JButton(new RandAnyAction());
        JButton b4 = new JButton(new BackButtonAction());

        buttons.add(b1);
        buttons.add(b2);
        buttons.add(b3);
        buttons.add(b4);

        editButtonAppearance(buttons);
        addButtonHoverAction(buttons);

        for (JButton button : buttons) {
            randomizerPanel.add(button);
        }
    }

    // EFFECTS: goes back to main page
    private class BackButtonAction extends AbstractAction {
        BackButtonAction() {
            super("Go back");
        }

        @Override
        public void actionPerformed(ActionEvent evt) {
            setContentPane(mainPanel);
            mainPanel.setVisible(true);
        }
    }

    // EFFECTS: gives a random recipe or asks the user to add a recipe if no recipes
    private class RandRecipeAction extends AbstractAction {

        RandRecipeAction() {
            super("Choose a recipe");
        }

        @Override
        public void actionPerformed(ActionEvent evt) {
            //wca.getRandom("recipe", rb.getRecipeBook());
            Graphics g = mainPanel.getGraphics();
            randomizerPanel.paint(g);

            if (wca.recipeBook().getRecipeBook().size() == 0) {
                g.drawString("You have no recipes. Please add a recipe first.",375, 200);
            } else {
                g.drawString("You should make: " + wca.recipeBook().randomRecipe().getName(), 375, 200);
            }
        }
    }

    // EFFECTS: gives a random restaurant or asks the user to add a restaurant if no restaurants
    private class RandRestoAction extends AbstractAction {

        RandRestoAction() {
            super("Choose a restaurant");
        }

        @Override
        public void actionPerformed(ActionEvent evt) {
            //wca.
        }
    }

    // EFFECTS: gives a random recipe/restaurant or asks the user to add a recipe/restaurant if no recipes/restaurants
    private class RandAnyAction extends AbstractAction {

        RandAnyAction() {
            super("Choose for me");
        }

        @Override
        public void actionPerformed(ActionEvent evt) {

        }
    }

    // MODIFIES: recipeBook
    // EFFECTS: adds a recipe to the recipe book
    private class AddRecipeAction extends AbstractAction {

        AddRecipeAction() {
            super("Add");
        }

        @Override
        public void actionPerformed(ActionEvent evt) {
//            JButton repeatButton = new JButton(new RepeatAction()); //implement
//
//            if (repeatButton.isSelected())
            JTextField recipeName = new JTextField(10);
            JTextArea recipeDescription = new JTextArea(2, 20);
            JTextField recipeDuration = new JTextField(5);
            //recipeIngredients = new JTextField(5); //CREATE A LIST, implement
            //ArrayList<String> listOfIngredients = new ArrayList<>();
            //JList recipeIngredients = new JList(listOfIngredients);


            JPanel addRecipePanel = new JPanel(new FlowLayout());
            addRecipePanel.add(new JLabel("Recipe name "));
            addRecipePanel.add(recipeName);
            addRecipePanel.add(Box.createHorizontalStrut(5)); // a spacer
            addRecipePanel.add(new JLabel("Recipe description "));
            addRecipePanel.add(recipeDescription);
            addRecipePanel.add(Box.createHorizontalStrut(5)); // a spacer
            addRecipePanel.add(new JLabel("Recipe duration (minutes) "));
            addRecipePanel.add(recipeDuration);
            //addRecipePanel.add(repeatButton);

            JOptionPane.showConfirmDialog(null, addRecipePanel,
                    "Adding A Recipe", JOptionPane.OK_CANCEL_OPTION); //how to make just ok?

            String name = recipeName.getText();
            String description = recipeDescription.getText();
            int duration = Integer.parseInt(recipeDuration.getText());


            wca.addRecipe(name, description, duration);

            //viewRecipesPanel.validate();
            //wviewRecipesPanel.repaint();
            //find a way to add it to the JList and update JList
        }
    }

//    private class RepeatAction extends AbstractAction {
//
//        RepeatAction() {
//            super("Add another one");
//        }
//
//        @Override
//        public void actionPerformed(ActionEvent evt) {
//            new AddRecipeAction();
//        }
//    }

    // MODIFIES: recipeBook
    // EFFECTS: removes a recipe from the recipe book
    private class DeleteRecipeAction extends AbstractAction {

        DeleteRecipeAction() {
            super("Delete");
        }

        @Override
        public void actionPerformed(ActionEvent evt) {
            Object[] recipeArray = wca.recipeBook().getRecipeBook().toArray();
            String[] names = new String[recipeArray.length];
            for (int i = 0; i < recipeArray.length; i++) {
                names[i] = ((Recipe) recipeArray[i]).getName();
            }

            JComboBox deleteOptions = new JComboBox<>(names);

            JPanel deleteRecipePanel = new JPanel();
            deleteRecipePanel.add(deleteOptions);

            JOptionPane.showConfirmDialog(null, deleteRecipePanel,
                    "Delete A Recipe", JOptionPane.OK_CANCEL_OPTION);

            //System.out.println(deleteOptions.getSelectedItem());

            String name = deleteOptions.getSelectedItem().toString();

            for (int i = 0; i < wca.recipeBook().getRecipeBook().size(); i++) {
                if (wca.recipeBook().getRecipeBook().get(i).getName().equals(name)) {
                    wca.deleteRecipe(i);
                }
            }
        }
    }

    // EFFECTS: shows the recipe book with add and delete functions
    private class ViewRecipesAction extends AbstractAction {

        ViewRecipesAction() {
            super("Recipes");
        }

        @Override
        public void actionPerformed(ActionEvent evt) {
            Object[] recipeArray = wca.recipeBook().getRecipeBook().toArray();
            String[] names = new String[recipeArray.length];
            for (int i = 0; i < recipeArray.length; i++) {
                names[i] = ((Recipe) recipeArray[i]).getName();
            }
            recipes = new JList(names);

            viewRecipesFrame = new JFrame();
            viewRecipesPanel = new JPanel();

            JButton addOneRecipe = new JButton(new AddRecipeAction());
            JButton deleteOneRecipe = new JButton(new DeleteRecipeAction());

            ArrayList<JButton> buttons = new ArrayList<>();
            buttons.add(addOneRecipe);
            buttons.add(deleteOneRecipe);

            editRecipeButtons(buttons);

            centreOnScreen(viewRecipesFrame);
            viewRecipesPanel.setBackground(new Color(238, 233, 207));

            viewRecipesPanel.add(addOneRecipe);
            viewRecipesPanel.add(deleteOneRecipe); //make better eventually
            viewRecipesPanel.add(recipes);

            viewRecipesFrame.add(viewRecipesPanel);
            viewRecipesFrame.setSize(500,500);
            viewRecipesFrame.setVisible(true);
        }
    }

    // EFFECTS: edits recipe buttons appearance
    private void editRecipeButtons(ArrayList<JButton> buttons) {
        editButtonAppearance(buttons);
        addButtonHoverAction(buttons);
        for (JButton button : buttons) {
            button.setPreferredSize(new Dimension(75, 25));
        }
    }

    // MODIFIES: restaurantList
    // EFFECTS: adds a restaurant to the restaurant list
    private class AddRestaurantAction extends AbstractAction {

        AddRestaurantAction() {
            super("Add");
        }

        @Override
        public void actionPerformed(ActionEvent evt) {
            JTextField restaurantName = new JTextField(10);
            JTextField restaurantDescription = new JTextField(10);

            JPanel addRestaurantPanel = new JPanel();
            addRestaurantPanel.add(new JLabel("Restaurant name "));
            addRestaurantPanel.add(restaurantName);
            addRestaurantPanel.add(Box.createHorizontalStrut(5)); // a spacer
            addRestaurantPanel.add(new JLabel("Restaurant description "));
            addRestaurantPanel.add(restaurantDescription);

            JOptionPane.showConfirmDialog(null, addRestaurantPanel,
                    "Adding A Restaurant", JOptionPane.OK_CANCEL_OPTION); //how to make just ok?

            String name = restaurantName.getText().toString();
            String description = restaurantDescription.getText().toString();

            wca.addRestaurant(name, description);
        }
    }

    // MODIFIES: restaurantList
    // EFFECTS: adds a restaurant to the restaurant list
    private class DeleteRestaurantAction extends AbstractAction {

        DeleteRestaurantAction() {
            super("Delete");
        }

        @Override
        public void actionPerformed(ActionEvent evt) {
            Object[] restaurantArray = wca.restaurantList().getRestaurantList().toArray();
            String[] names = new String[restaurantArray.length];
            for (int i = 0; i < restaurantArray.length; i++) {
                names[i] = ((Restaurant) restaurantArray[i]).getName();
            }

            JComboBox deleteOptions = new JComboBox<>(names);

            JPanel deleteRestaurantPanel = new JPanel();
            deleteRestaurantPanel.add(deleteOptions);

            JOptionPane.showConfirmDialog(null, deleteRestaurantPanel,
                    "Delete A Restaurant", JOptionPane.OK_CANCEL_OPTION);

            //System.out.println(deleteOptions.getSelectedItem());

            String name = deleteOptions.getSelectedItem().toString();

            for (int i = 0; i < wca.restaurantList().getRestaurantList().size(); i++) {
                if (wca.restaurantList().getRestaurantList().get(i).getName().equals(name)) {
                    wca.deleteRestaurant(i);
                }
            }
        }
    }

    // EFFECTS: shows the restaurant list with add and delete functions
    private class ViewRestaurantAction extends AbstractAction {

        ViewRestaurantAction() {
            super("Restaurant");
        }

        @Override
        public void actionPerformed(ActionEvent evt) {
            Object[] restaurantsArray = wca.restaurantList().getRestaurantList().toArray();
            String[] names = new String[restaurantsArray.length];
            for (int i = 0; i < restaurantsArray.length; i++) {
                names[i] = ((Restaurant) restaurantsArray[i]).getName();
            }
            restaurants = new JList(names);

            viewRestaurantsFrame = new JFrame();
            viewRestaurantsPanel = new JPanel();

            JButton addOneRestaurant = new JButton(new AddRestaurantAction());
            JButton deleteOneRestaurant = new JButton(new DeleteRestaurantAction());
            ArrayList<JButton> buttons = new ArrayList<>();
            buttons.add(addOneRestaurant);
            buttons.add(deleteOneRestaurant);

            editRestaurantButtons(buttons);

            centreOnScreen(viewRestaurantsFrame);
            viewRestaurantsPanel.setBackground(new Color(238, 233, 207));

            viewRestaurantsPanel.add(addOneRestaurant);
            viewRestaurantsPanel.add(deleteOneRestaurant); //make better eventually

            viewRestaurantsPanel.add(restaurants);
            viewRestaurantsFrame.add(viewRestaurantsPanel);

            viewRestaurantsFrame.setSize(500,500);
            viewRestaurantsFrame.setVisible(true);

        }
    }

    // EFFECTS: edits restaurant button appearance
    private void editRestaurantButtons(ArrayList<JButton> buttons) {
        editButtonAppearance(buttons);
        addButtonHoverAction(buttons);

        for (JButton button : buttons) {
            button.setPreferredSize(new Dimension(75, 25));
        }
    }

    // EFFECTS: saves recipes to recipe book and restaurants to restaurant list
    private class SaveAction extends AbstractAction {

        SaveAction() {
            super("Save");
        }

        @Override
        public void actionPerformed(ActionEvent evt) {
            wca.save();

            //Displaying "Saved!"
            Graphics g = mainPanel.getGraphics();
            mainPanel.paint(g);
            g.drawString("Saved!",380, 200);
        }
    }

    // EFFECTS: loads recipes from recipe book and restaurants from restaurant list
    private class LoadAction extends AbstractAction {

        LoadAction() {
            super("Load");
        }

        @Override
        public void actionPerformed(ActionEvent evt) {
            wca.load();

            //Displaying "Loaded!"
            Graphics g = mainPanel.getGraphics();
            mainPanel.paint(g);
            g.drawString("Loaded!",375, 200);
        }
    }
}
