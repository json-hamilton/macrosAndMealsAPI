package com.macrosAndMeals.model;

public class UserCreatedMeal_Method {
    private int userCreatedMealID;
    private String method;

    public UserCreatedMeal_Method(int userCreatedMealID, String method) {
        this.userCreatedMealID = userCreatedMealID;
        this.method = method;
    }
    public UserCreatedMeal_Method() {
    }

    public int getUserCreatedMealID() {
        return userCreatedMealID;
    }

    public void setUserCreatedMealID(int userCreatedMealID) {
        this.userCreatedMealID = userCreatedMealID;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }
}
