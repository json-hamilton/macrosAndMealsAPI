package com.macrosAndMeals.model;

import javax.swing.text.html.ImageView;
import java.awt.*;
import java.time.LocalDate;

public class UserCreatedMeal extends Meal {
    private int userCreatedMealID;
    private int userID;

    public UserCreatedMeal(int mealId, String name, String url, double calories, double fat, double carbs, double protein, int likes, LocalDate dateCreated, int userCreatedMealID, int userID) {
        super(mealId, name, url, calories, fat, carbs, protein, likes, dateCreated);
        this.userCreatedMealID = userCreatedMealID;
        this.userID = userID;
    }

    public UserCreatedMeal() {
    }

    public int getUserCreatedMealID() {
        return userCreatedMealID;
    }

    public void setUserCreatedMealID(int userCreatedMealID) {
        this.userCreatedMealID = userCreatedMealID;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

}
