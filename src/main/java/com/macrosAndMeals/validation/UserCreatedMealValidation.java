package com.macrosAndMeals.validation;

import com.macrosAndMeals.model.FullUserCreatedMeal;
import com.macrosAndMeals.model.UserCreatedMeal;
import com.macrosAndMeals.model.UserCreatedMeal_Ingredient;
import com.macrosAndMeals.model.UserCreatedMeal_Method;

public class UserCreatedMealValidation {
    public boolean checkUserCreatedMeal(UserCreatedMeal um){
        if (new MealValidation().checkMeal(um)){
            if (um.getUserCreatedMealID() >=0){
                if (um.getUserID()>0){
                    return true;
                }
                else {
                    throw new IllegalArgumentException("User ID cannot be less than 1. " + um.getUserID());
                }
            }
            else{
                throw new IllegalArgumentException("UserCreatedMealID cannot be negative: "+um.getUserCreatedMealID());
            }
        }
        return false;
    }
    public boolean checkUserCreatedMeal_Ingredient(UserCreatedMeal_Ingredient um) {
        if (um.getIngredient().matches("[\\w,.!? ]+")&& um.getIngredient().length()<100) {
            if (um.getUserCreatedMealID() >= 0) {
                return true;
            } else {
                throw new IllegalArgumentException("UserCreatedMealID cannot be negative: " + um.getUserCreatedMealID());
            }
        } else {
            throw new IllegalArgumentException("Ingredient can be letters or numbers, has a minimum of 5 characters and a maximum of 99: " + um.getIngredient());
        }
    }
    public boolean checkUserCreatedMeal_Method(UserCreatedMeal_Method um){
        if (um.getMethod().matches("[\\w,.!? ]+") && um.getMethod().length()<1000) {
            if (um.getUserCreatedMealID() >= 0) {
                return true;
            } else {
                throw new IllegalArgumentException("UserCreatedMealID cannot be negative: " + um.getUserCreatedMealID());
            }
        } else {
            throw new IllegalArgumentException("Method can be (,.!?), letters or numbers, has a minimum of 5 characters and a maximum of 999: " + um.getMethod());
        }
    }
    public boolean checkFullUserCreatedMeal(FullUserCreatedMeal um){
        boolean check;
        check = checkUserCreatedMeal(um.getUserCreatedMeal());
        if (check){
            for (UserCreatedMeal_Ingredient i:um.getIngredients()
                 ) {
                if (!checkUserCreatedMeal_Ingredient(i)){
                    return false;
                }
            }
            for (UserCreatedMeal_Method m:um.getMethods()
            ) {
                if (!checkUserCreatedMeal_Method(m)){
                    return false;
                }
            }
        }
        return check;
    }
}
