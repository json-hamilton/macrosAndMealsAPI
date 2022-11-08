package com.macrosAndMeals.model;

public class UserCreatedMeal_Ingredient {
    private int userCreatedMealID;
    private String ingredient;

    public UserCreatedMeal_Ingredient(int userCreatedMealID, String ingredient) {
        this.userCreatedMealID = userCreatedMealID;
        this.ingredient = ingredient;
    }

    public UserCreatedMeal_Ingredient() {
    }

    public int getUserCreatedMealID() {
        return userCreatedMealID;
    }

    public void setUserCreatedMealID(int userCreatedMealID) {
        this.userCreatedMealID = userCreatedMealID;
    }

    public String getIngredient() {
        return ingredient;
    }

    public void setIngredient(String ingredient) {
        this.ingredient = ingredient;
    }
}
