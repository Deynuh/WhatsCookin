package ui;

// Options class to deal with duplication in WhatsCookingApp class
public class Options {

    public Options() {
    }

    // EFFECTS: displays the menu for String type (which can either be recipe or restaurant)
    public void displayMenu(String type) {
        System.out.println("------- " + type.toUpperCase() + "S -------");
        System.out.println(" 1: Add a " + type + " \n 2: Delete a " + type);
        System.out.println(" 3: View all " + type + "s \n 4: Previous menu");
    }

    //public void menu() do last?

    /*public void deleteMenu(String type) {
        String parameter = (type + ".get" + "R" + type.substring(1) + "List().size()");
        if (restaurantList.getRestaurantList().size() == 0) {
            System.out.println("There is nothing in your restaurant list to delete.");
            System.out.println("Returning to main menu...");
        } else {
            System.out.println("Which restaurant would you like to delete?");
            showRestaurants();
            int input = key.nextInt();
            if (input >= 0 && input <= restaurantList.getRestaurantList().size()) {
                restaurantList.removeRestaurant(input);
            } else {
                System.out.println("That is not a valid number.");
            }

            System.out.println("Would you like to delete another restaurant? (y/n)");
            if (key.next().equals("y")) {
                //deleteRestaurantMenu();
            } else {
                System.out.println("Returning to main menu...");
            }
        }
    } */

    //public void getRandom(String )

}
