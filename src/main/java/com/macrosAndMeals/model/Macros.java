package com.macrosAndMeals.model;

import com.macrosAndMeals.validation.MealValidation;

public class Macros {
    MealValidation validation = new MealValidation();
    private double calories;
    private double fat;
    private double carbs;
    private double protein;
    private int numMeals;

    public Macros(double calories, double fat, double carbs, double protein, int numMeals) {
        this.calories = calories;
        this.fat = fat;
        this.carbs = carbs;
        this.protein = protein;
        this.numMeals = numMeals;
    }

    public Macros() {
    }

    public double getCalories() {
        return calories;
    }

    public void setCalories(double calories) {
        if (validation.checkCalories(calories)){
            this.calories = calories;
        }
    }

    public double getFat() {
        return fat;
    }

    public void setFat(double fat) {
        if (validation.checkFat(fat)){
            this.fat = fat;
        }
    }

    public double getCarbs() {
        return carbs;
    }

    public void setCarbs(double carbs) {
        if (validation.checkCarbs(carbs)){
            this.carbs = carbs;
        }
    }

    public double getProtein() {
        return protein;
    }

    public void setProtein(double protein) {
        if (validation.checkProtein(protein)) {
            this.protein = protein;
        }
    }
    public int getNumMeals() {
        return numMeals;
    }

    public void setNumMeals(int numMeals) {
        this.numMeals = numMeals;
    }

    @Override
    public String toString() {
        return "Macros{" +
                "validation=" + validation +
                ", calories=" + calories +
                ", fat=" + fat +
                ", carbs=" + carbs +
                ", protein=" + protein +
                ", numMeals=" + numMeals +
                '}';
    }
}
