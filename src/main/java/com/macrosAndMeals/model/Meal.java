package com.macrosAndMeals.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.macrosAndMeals.validation.MealValidation;

public class Meal {
    //think about wether you should have a private and public bool
    //probably not tbh
    //add a page to sort by top
    //therefore you need to add a likes field
    //nah nah you have forgot the aim of the program mate xd
    //all user made meals should be private bc there is no way of showing it is accurate macros,
    //if they add the meal they should only add it for themselves surely
    //and maybe have a share link for the meal id so users can add a meal someone else has made that is shared through tiktok or something if they trust the macros are right
    //so the user meals table will be the meals that they add that are only used in the generation algortithm for them
    //people can click a button "add to my meals" that will allow them to use that meal n the generation algorithm
    //so make a user created meal page and allow likes and everything
    //so u need a likes field in the meal table, set likes as -1 if its a default meal
    //then user can sort by likes and like date created and stuff to see certain meals
    //so the best meals are at the top and can be added to the users own allowed meals
    //and the user can generate meals and there will be option like to make sure certain meals are included
    //and if the user is not happy with the generation then they can remove a meal and regenerate a set that doesnt include that meal
    //probably allow them to save their meal sets

    //thw url in a user created meal is a link to the user created meal page,
    //for this all macros are needed as well as ingredient and method, optional photo of final result.
    //so make a user created meal table
    //with date created, ingredient, method, image, dont need macros as they are in the meal table
    //ingredients will be added seperately through a ingredients table so it is easier to format, as will the methods sp u can have seperate steps
    //or allow them to just paste in the link from an external website
    //need to be able to report bad meals
    //if reported should it get taken down until it can be checked? then u will need to limit reports. so idk
    //add a mealset table.
    //each user can have 1 current mealset
    //max 15 meals in a mealset to include snacks
    // so it doesnt need a linking tablejust when retrueving the meals in the set just do
    //get mealid where userId = ... so that all meal id are retrueved then get meals with that meal id


    private MealValidation validation = new MealValidation();
    private int mealId;
    private String name;
    private String url;
    private double calories;
    private double fat;
    private double carbs;
    private double protein;
    private int likes;

    @JsonCreator
    public Meal(@JsonProperty("mealId")int mealId, @JsonProperty("name")String name,@JsonProperty("url") String url,
                @JsonProperty("calories")double calories, @JsonProperty("fat")double fat,
                @JsonProperty("carbs")double carbs, @JsonProperty("protein")double protein,@JsonProperty("likes") int likes) {
        setMealId(mealId);
        setName(name);
        setUrl(url);
        setCalories(calories);
        setFat(fat);
        setCarbs(carbs);
        setProtein(protein);
        setLikes(likes);
    }

    public Meal() {

    }

    public int getMealId() {
        return mealId;
    }

    public void setMealId(int mealId) {
        if (validation.checkMealId(mealId)){
            this.mealId = mealId;
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (validation.checkName(name)){
            this.name = name;
        }
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        if (validation.checkUrl(url)){
            this.url = url;
        }
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

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        if (validation.checkLikes(likes)) {
            this.likes = likes;
        }
    }
}
