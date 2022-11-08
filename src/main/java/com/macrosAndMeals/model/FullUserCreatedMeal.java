package com.macrosAndMeals.model;

import java.util.List;

public class FullUserCreatedMeal {
    private UserCreatedMeal userCreatedMeal;
    private List<UserCreatedMeal_Ingredient> ingredients;
    private List<UserCreatedMeal_Method> methods;

    public FullUserCreatedMeal(UserCreatedMeal userCreatedMeal, List<UserCreatedMeal_Ingredient> ingredients, List<UserCreatedMeal_Method> methods) {
        this.userCreatedMeal = userCreatedMeal;
        this.ingredients = ingredients;
        this.methods = methods;
    }

    public FullUserCreatedMeal() {
    }

    public UserCreatedMeal getUserCreatedMeal() {
        return userCreatedMeal;
    }

    public void setUserCreatedMeal(UserCreatedMeal userCreatedMeal) {
        this.userCreatedMeal = userCreatedMeal;
    }

    public List<UserCreatedMeal_Ingredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<UserCreatedMeal_Ingredient> ingredients) {
        this.ingredients = ingredients;
    }

    public List<UserCreatedMeal_Method> getMethods() {
        return methods;
    }

    public void setMethods(List<UserCreatedMeal_Method> methods) {
        this.methods = methods;
    }
}
