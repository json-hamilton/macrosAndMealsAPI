package com.macrosAndMeals.validation;

import java.math.BigDecimal;

public class MealValidation {
    public boolean checkMealId(int mealId){
        if (mealId>=0){
            return true;
        }
        System.out.println("Meal id " + mealId + "can't be less than 0");
        return false;
    }
    public boolean checkName(String name){
        if (name.matches("^[A-Za-z]\\w{3,250}$")){
            return true;
        }
        System.out.println("Name cannot have special characters and must be less than 250 characters, " + name);
        return false;
    }
    public boolean checkUrl(String url){
        if (url == null |url.length() <255){
            return true;
        }
        System.out.println("URL too long");
        return false;
    }
    public boolean checkCalories(double calories){
        BigDecimal cal = new BigDecimal(String.valueOf(calories));
        if (calories<10000 & cal.scale()<=1){
            return true;
        }
        System.out.println("Calories have to be less than 10,000 and can only have 1 decimal place, " + calories);
        return false;
    }
    public boolean checkFat(double fat){
        BigDecimal f = new BigDecimal(String.valueOf(fat));
        if (fat<1000 & f.scale()<=1){
            return true;
        }
        System.out.println("Fat has to be less than 1000g and can only have 1 decimal place, " + fat);
        return false;
    }
    public boolean checkCarbs(double carbs){
        BigDecimal f = new BigDecimal(String.valueOf(carbs));
        if (carbs<1000 & f.scale()<=1){
            return true;
        }
        System.out.println("Carbs have to be less than 1000g and can only have 1 decimal place, " + carbs);
        return false;
    }
    public boolean checkProtein(double protein){
        BigDecimal f = new BigDecimal(String.valueOf(protein));
        if (protein<1000 & f.scale()<=1){
            return true;
        }
        System.out.println("Protein has to be less than 1000g and can only have 1 decimal place, " + protein);
        return false;
    }
    public boolean checkLikes(int likes){
        if (likes>=-1){ //-1 likes is for default meals that shouldn't appear in the search
            return true;
        }
        System.out.println("Cannot have a negative number of likes " + likes);
        return false;
    }
}
