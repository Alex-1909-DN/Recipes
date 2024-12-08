/*
 * File name: Recipe.java
 * Author: Quoc Phong Tran, 041134348
 * Course: CST8284 – OOP
 * Lab: Assignment 3
 * Date: December 03, 2024
 * Professor: Moshiur Rahman
 * Purpose: This class represents a recipe for a type of bread, including its ingredients and the quantity ordered. 
 * The class provides methods for retrieving and modifying the recipe's properties.
 * Class list: Recipe, RecipeManagerTest, RecipeManager
 */

package assn3;
/**
 * The class represents a recipe for a type of bread.
 * @author Quoc Phong Tran
 * @version 1.0.0
 * @see assn3
 * @since 21.0.3
 */
public class Recipe {
	private String name;       // Name of the recipe (bread)
    private float eggs;        // Amount of eggs required for the recipe
    private float yeast;       // Amount of yeast required for the recipe
    private float flour;       // Amount of flour required for the recipe
    private float sugar;       // Amount of sugar required for the recipe
    private float butter;      // Amount of butter required for the recipe
    private int quantityOrdered; // Quantity of the recipe ordered

    /**
     * Constructor to initialize the recipe with name, eggs, yeast, 
     * flour, sugar, butter, and the initial quantity ordered (default to 0).
     * 
     * @param name The name of the recipe (bread type)
     * @param eggs Amount of eggs required for the recipe
     * @param yeast Amount of yeast required for the recipe
     * @param flour Amount of flour required for the recipe
     * @param sugar Amount of sugar required for the recipe
     * @param butter Amount of butter required for the recipe
     */
    public Recipe(String name, float eggs, float yeast, float flour, float sugar, float butter) {
        this.name = name;
        this.eggs = eggs;
        this.yeast = yeast;
        this.flour = flour;
        this.sugar = sugar;
        this.butter = butter;
        this.quantityOrdered = 0;
    }
    
    /**
     * Gets the name of the recipe.
     * 
     * @return The name of the recipe.
     */
	public String getName() {
		return name;
	}
	
	/**
     * Sets the name of the recipe.
     * 
     * @param name The name of the recipe.
     */
	public void setName(String name) {
		this.name = name;
	}
    /**
     * Gets the number of eggs required for the recipe.
     * 
     * @return The number of eggs.
     */
	public float getEggs() {
		return eggs;
	}
    /**
     * Sets the number of eggs required for the recipe.
     * 
     * @param eggs The number of eggs.
     */
	public void setEggs(float eggs) {
		this.eggs = eggs;
	}
    /**
     * Gets the amount of yeast required for the recipe.
     * 
     * @return The amount of yeast.
     */
	public float getYeast() {
		return yeast;
	}
    /**
     * Sets the amount of yeast required for the recipe.
     * 
     * @param yeast The amount of yeast.
     */
	public void setYeast(float yeast) {
		this.yeast = yeast;
	}
    /**
     * Gets the amount of flour required for the recipe.
     * 
     * @return The amount of flour.
     */
	public float getFlour() {
		return flour;
	}
    /**
     * Sets the amount of flour required for the recipe.
     * 
     * @param flour The amount of flour.
     */
	public void setFlour(float flour) {
		this.flour = flour;
	}
    /**
     * Gets the amount of sugar required for the recipe.
     * 
     * @return The amount of sugar.
     */
	public float getSugar() {
		return sugar;
	}
    /**
     * Sets the amount of sugar required for the recipe.
     * 
     * @param sugar The amount of sugar.
     */
	public void setSugar(float sugar) {
		this.sugar = sugar;
	}
    /**
     * Gets the amount of butter required for the recipe.
     * 
     * @return The amount of butter.
     */
	public float getButter() {
		return butter;
	}
    /**
     * Sets the amount of butter required for the recipe.
     * 
     * @param butter The amount of butter.
     */
	public void setButter(float butter) {
		this.butter = butter;
	}
    /**
     * Gets the quantity of the recipe ordered.
     * 
     * @return The quantity ordered.
     */
	public int getQuantityOrdered() {
		return quantityOrdered;
	}
    /**
     * Sets the quantity of the recipe ordered.
     * 
     * @param quantityOrdered The quantity ordered.
     */
	public void setQuantityOrdered(int quantityOrdered) {
		this.quantityOrdered = quantityOrdered;
	}
    
}