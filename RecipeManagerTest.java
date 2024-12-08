/*
 * File name: RecipeManagerTest.java
 * Author: Quoc Phong Tran, 041134348
 * Course: CST8284 – OOP
 * Lab: Assignment 3
 * Date: December 03, 2024
 * Professor: Moshiur Rahman
 * Purpose: This file contains the main driver class for the Bread Recipe Manager application. 
 * It allows users to load recipes from a file, view available recipes, order specific quantities 
 * of bread, generate and save a shopping list with ingredients for ordered bread.
 * Class list: Recipe, RecipeManagerTest, RecipeManager
 */

package assn3;
/**
 * This class allows the user to interact with the RecipeManager to load recipes,
 * display available recipes, place orders, and generate a shopping list based on the orders.
 * @author Quoc Phong Tran
 * @version 1.0.0
 * @see java.io.File
 * @see java.io.IOException
 * @see java.util.ArrayList
 * @see java.util.InputMismatchException
 * @see java.util.List
 * @see java.util.Scanner
 * @see assn3
 * @since 21.0.3
 */
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

/**
 * Driver class for the Bread Recipe Manager application.
 */
public class RecipeManagerTest {
	// Initialize the RecipeManager object to manage recipes
    private static RecipeManager manager = new RecipeManager();
    
    // List to track orders
    private static List<Integer> quantities = new ArrayList<>();
    	// List to store recipes
    private static List<Recipe> recipeList = new ArrayList<>();
    
    
    public static void main(String[] args) {
        try {
            // Load recipes from file
            manager.loadRecipes("recipelist.txt");

            Scanner scanner = new Scanner(System.in);
            boolean exit = false;
            System.out.println("Welcome to Quoc Phong Tran's Recipe Manager.");
            // Loop to display the menu and handle user choices.
            do {
            	displayMenu();
                try {
                    System.out.print("Please enter your choice: ");
                    int choice = scanner.nextInt();
                    
                    switch (choice) {
                        case 0: // Reprint the menu
                        	
                            break;

                        case 1: // Show available recipes
                            displayRecipes();
                            break;

                        case 2: // Order bread
                        	int recipeNumber = 0;
                            int quantity = 0;

                            // Loop for "Which bread would you like?"
                            do {
                                try {
                                    System.out.print("Which bread would you like? ");
                                    if (!scanner.hasNextInt()) {
                                        String invalidInput = scanner.next(); // Read invalid input as a string
                                        System.out.println("\nInvalid input: '" + invalidInput + "'. Please enter an integer number.");
                                        continue; // Restart the loop
                                    }
                                    recipeNumber = scanner.nextInt(); // Collect recipe number

                                    // Validate bread choice
                                    if (recipeNumber < 1 || recipeNumber > manager.getRecipes().size()) {
                                        throw new IllegalArgumentException("\nInvalid choice. Please choose a number between 1 and " + manager.getRecipes().size() + ".");
                                    }

                                    // Break out of the loop if valid
                                    break;
                                } catch (IllegalArgumentException e) {
                                    System.out.println(e.getMessage());
                                }
                            } while (true); // Keep looping until a valid recipeNumber is entered

                            // Loop for "How much of this bread would you like?"
                            do {
                                try {
                                    System.out.print("How much of this bread would you like? ");
                                    if (!scanner.hasNextInt()) {
                                        String invalidInput = scanner.next(); // Read invalid input as a string
                                        System.out.println("\nInvalid input: '" + invalidInput + "'. Please enter an integer number.");
                                        continue; // Restart the loop
                                    }
                                    quantity = scanner.nextInt(); // Collect quantity
                                    
                                    // Break out of the loop if valid
                                    break;
                                } catch (Exception e) {
                                    System.out.println("An unexpected error occurred: " + e.getMessage());
                                }
                            } while (true); // Keep looping until a valid quantity is entered

                            // Process the order after valid inputs are provided
                            orderBread(recipeNumber, quantity);
                            
                            break;

                        case 3: // Print and optionally save shopping list
                            printShoppingList(scanner);
                            break;

                        case 4: // Quit Program
                            System.out.println("Thank you for using Quoc Phong Tran's Recipe Manager. Goodbye!");
                            exit = true;
                            break;

                        default:
                            System.out.println("\nInvalid choice. Please select a valid option from the menu.");
                    }
                } catch (InputMismatchException e) {
                    System.out.println("\nInvalid input. Please enter a number.");
                    scanner.next(); // Clear the invalid input
                }
            } while (!exit);
            scanner.close();
        } catch (IOException e) {
            System.out.println("Error: Unable to load recipes. " + e.getMessage());
        }
    }

    /**
     * Displays the main menu options.
     */
    private static void displayMenu() {
        
        System.out.println("Please select one of the following options:");
        System.out.println("1. Show available recipes.");
        System.out.println("2. Create Shopping List.");
        System.out.println("3. Print Shopping List.");
        System.out.println("4. Quit Program.");
        System.out.println("0. to reprint this menu.");
    }

    /**
     * Displays all available recipes.
     */
    private static void displayRecipes() {
        System.out.println("\nAvailable Recipes:");
        int i = 1;
        for (Recipe recipe : manager.getRecipes()) {
            System.out.println(i++ + ". " + recipe.getName());
        }
    }

    /**
     * Orders a specific quantity of bread for the given recipe number.
     *
     * @param breadNumber The index of the recipe in the list (1-based index).
     * @param quantity     The quantity of bread to order.
     * @return A message indicating the result of the operation.
     */
    
    public static String orderBread(int breadNumber, int quantity) {
        if (breadNumber > 0 && breadNumber <= manager.getRecipes().size()) {
            Recipe selectedRecipe = manager.getRecipes().get(breadNumber - 1);

            // Check if this recipe has already been ordered
            if (recipeList.contains(selectedRecipe)) {
                // Update the quantity for the existing order
                int index = recipeList.indexOf(selectedRecipe);
                int newQuantity = quantities.get(index) + quantity;

                // Ensure the total quantity does not drop below zero
                if (newQuantity < 0) {
                    return "Invalid operation: Total quantity for " + selectedRecipe.getName() + " cannot be negative.";
                }

                // Update the quantity
                quantities.set(index, newQuantity);
            } else {
                // If it's a new order, ensure the quantity is positive
                if (quantity <= 0) {
                    return "Invalid operation: Initial order for " + selectedRecipe.getName() + " must be positive.";
                }

                // Add the new recipe and quantity
                recipeList.add(selectedRecipe);
                quantities.add(quantity);
            }

            return "Order updated successfully for " + quantity + " " + selectedRecipe.getName() + "(s).";
        } else {
            return "Invalid input. Please ensure the recipe number is valid.";
        }
    }
   
    /**
     * Generates, displays, and optionally saves the shopping list.
     *
     * @param scanner The scanner object for reading user input.
     */
    private static void printShoppingList(Scanner scanner) {
        String shoppingList = createShoppingList();

        if (shoppingList.trim().isEmpty()) {
            System.out.println("No items in the shopping list. Please create an order first.");
        } else {
            System.out.println("\nYour Shopping List:\n" + shoppingList);

            System.out.println("Do you want to save this list? (yes/no): ");
            String saveChoice = scanner.next().trim();
            
            //check user's input
            if (saveChoice.equalsIgnoreCase("yes") || saveChoice.equalsIgnoreCase("y")) {
                try {
                	File file = new File("shoppinglist.txt");
                	if (!file.exists()) {
                        try {
                        	file.createNewFile();
                        } catch (IOException e) {
                        	System.out.println("An error occurred while creating the file: " + e.getMessage());
                        }
                    }
                    manager.saveShoppingList(file.getAbsolutePath(), shoppingList);
                    System.out.println("Shopping list successfully saved");
                } catch (IOException e) {
                    System.out.println("Error saving the shopping list: " + e.getMessage());
                }
            } else {
                System.out.println("The shopping list was not saved.");
            }
        }
    }
    
    /**
     * Generates a shopping list based on the ordered quantities of recipes.
     * 
     * @return A string representation of the shopping list.
     */
    private static String createShoppingList() {
        float totalEggs = 0;
        float totalYeast = 0;
        float totalFlour = 0;
        float totalSugar = 0;
        float totalButter = 0;

        // StringBuilder to construct the shopping list
        StringBuilder recipeShoppingList = new StringBuilder();

        // Iterate through orderList and accumulate the quantities of bread and ingredients
        for (int i=0; i<recipeList.size(); i++) {
            Recipe recipe = recipeList.get(i);
            int quantityOrdered = quantities.get(i);

            if (quantityOrdered > 0) {
                String breadName = recipe.getName();
                
                // Accumulate ingredient totals
                totalEggs += recipe.getEggs() * quantityOrdered;
                totalYeast += recipe.getYeast() * quantityOrdered;
                totalFlour += recipe.getFlour() * quantityOrdered;
                totalSugar += recipe.getSugar() * quantityOrdered;
                totalButter += recipe.getButter() * quantityOrdered;

                // Add the bread order to the shopping list
                recipeShoppingList.append(quantityOrdered)
                          .append(" ")
                          .append(breadName)
                          .append(" loaf/loaves.\n");
            }
        }

        // Append the total ingredients to the shopping list
        if (totalEggs > 0) recipeShoppingList.append(totalEggs).append(" egg(s)\n");
        if (totalYeast > 0) recipeShoppingList.append(totalYeast).append(" grams of yeast\n");
        if (totalFlour > 0) recipeShoppingList.append(totalFlour).append(" grams of flour\n");
        if (totalSugar > 0) recipeShoppingList.append(totalSugar).append(" grams of sugar\n");
        if (totalButter > 0) recipeShoppingList.append(totalButter).append(" grams of butter\n");

        return recipeShoppingList.toString();
    }
}