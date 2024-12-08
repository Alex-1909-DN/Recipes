/*
 * File name: RecipeManager.java
 * Author: Quoc Phong Tran, 041134348
 * Course: CST8284 – OOP
 * Lab: Assignment 3
 * Date: December 03, 2024
 * Professor: Moshiur Rahman
 * Purpose: This program manages a collection of recipes, allows for loading recipes from 
 * a text file and saves the shopping list to a specified file. It provides functionality 
 * for reading recipes and their ingredients.
 * Class list: Recipe, RecipeManagerTest, RecipeManager
 */

package assn3;
/**
 * The RecipeManager class is responsible for managing the recipe list, 
 * loading recipe data from a file, and saving shopping lists based on the 
 * ingredients required for each recipe.
 * @author Quoc Phong Tran
 * @version 1.0.0
 * @see java.io.File
 * @see java.io.IOException
 * @see java.io.OutputStreamWriter
 * @see java.io.FileNotFoundException
 * @see java.util.List
 * @see java.io.FileOutputStream
 * @see java.util.ArrayList
 * @see java.util.Scanner
 * @see assn3
 * @since 21.0.3
 */
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Manages the list of recipes and provides functionality to load recipes,
 * generate shopping lists, and save the list to a file.
 */
public class RecipeManager {

    private List<Recipe> recipes = new ArrayList<>();

    /**
     * Loads recipes from the specified text file.
     * 
     * @param filePath The path to the recipe file.
     * @throws IOException If the file cannot be read.
     */
    public void loadRecipes(String filePath) throws IOException {
    	File file = new File(filePath);

        // Check if the file exists
        if (!file.exists()) {
            throw new FileNotFoundException("Recipe list file not found: " + filePath);
        }
        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine().trim();
                if (line.startsWith("Recipe")) {
                    String name = line.substring(line.indexOf(" ")+1).trim();
                    float eggs = 0;
                    float yeast = 0;
                    float flour = 0;
                    float sugar = 0;
                    float butter = 0;

                    for (int i = 0; i < 5; i++) {
                        String[] parts = scanner.nextLine().split(" ");
                        switch (parts[0]) {
                            case "eggs": eggs = Float.parseFloat(parts[1]); 
                            	break;
                            case "yeast": yeast = Float.parseFloat(parts[1]); 
                            	break;
                            case "flour": flour = Float.parseFloat(parts[1]); 
                            	break;
                            case "sugar": sugar = Float.parseFloat(parts[1]); 
                            	break;
                            case "butter": butter = Float.parseFloat(parts[1]); 
                            	break;
                        }
                    }
                    Recipe recipe = new Recipe(name, eggs, yeast, flour, sugar, butter);
                    recipes.add(recipe);
                }
            }
        }
    }


    /**
     * Saves the shopping list to a file.
     * 
     * @param filePath      The file path to save the shopping list.
     * @param shoppingList  The shopping list content.
     * @throws IOException  If the file cannot be written.
     */
    public void saveShoppingList(String filePath, String shoppingList) throws IOException {
    	try (FileOutputStream fos = new FileOutputStream(filePath);
    	         OutputStreamWriter writer = new OutputStreamWriter(fos)) {
    	        writer.write(shoppingList);
        }
    }
    
    /**
     * Retrieves the list of all recipes managed by the RecipeManager.
     * 
     * @return A list of Recipe objects representing all the recipes 
     *         loaded into the RecipeManager.
     */
    public List<Recipe> getRecipes() {
        return recipes;
    }
}