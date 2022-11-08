package com.macrosAndMeals.service;

import com.macrosAndMeals.core.ConnectionDB;
import com.macrosAndMeals.dao.MealDao;
import com.macrosAndMeals.dao.UserCreatedMealDao;
import com.macrosAndMeals.model.*;
import com.macrosAndMeals.validation.UserCreatedMealValidation;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserCreatedMealService {
    UserCreatedMealDao dao = new UserCreatedMealDao();
    UserCreatedMealValidation v = new UserCreatedMealValidation();
    public boolean insertFullUserCreatedMeal(FullUserCreatedMeal um){
        try {
            if (v.checkFullUserCreatedMeal(um)) {
                return dao.insertFullUserCreatedMeal(um, ConnectionDB.getConnection());
            }
            System.out.println("Not inserted, FullUserCreatedMeal not validated: " + um);
        }
        catch (SQLException e){
            System.out.println("SQL Error " + e);
        }
        catch (Exception e){
            System.out.println("Other Error " + e);
        }
        return false;
    }
    public boolean insertUserCreatedMealAutoID(UserCreatedMeal um) {
        try {
            if (v.checkUserCreatedMeal(um)) {
                return dao.insertUserCreatedMealAutoID(um, ConnectionDB.getConnection());
            }
            System.out.println("Not inserted, UserCreatedMeal not validated: " + um);
        }
        catch (SQLException e){
            System.out.println("SQL Error " + e);
        }
        catch (Exception e){
            System.out.println("Other Error " + e);
        }
        return false;
    }
    public boolean insertUserCreatedMeal_Ingredient(List<UserCreatedMeal_Ingredient> ingredients) {
        try {
            for (UserCreatedMeal_Ingredient i:ingredients
                 ) {
                if (!v.checkUserCreatedMeal_Ingredient(i)){
                    System.out.println("Not inserted, ingredient not valid: " + i);
                    return false;
                }
            }
            return dao.insertUserCreatedMeal_Ingredient(ingredients, ConnectionDB.getConnection());
        }
        catch (SQLException e){
            System.out.println("SQL Error " + e);
        }
        catch (Exception e){
            System.out.println("Other Error " + e);
        }
        return false;
    }
    public boolean insertUserCreatedMeal_Method(List<UserCreatedMeal_Method> methods) {
        try {
            for (UserCreatedMeal_Method m:methods
            ) {
                if (!v.checkUserCreatedMeal_Method(m)){
                    System.out.println("Not inserted, method not valid: " + m);
                    return false;
                }
            }
            return dao.insertUserCreatedMeal_Method(methods, ConnectionDB.getConnection());
        }
        catch (SQLException e){
            System.out.println("SQL Error " + e);
        }
        catch (Exception e){
            System.out.println("Other Error " + e);
        }
        return false;
    }
    public List<FullUserCreatedMeal> selectAllFullUserCreatedMeals(){
        try {
            return dao.selectAllFullUserCreatedMeals(ConnectionDB.getConnection());
        }
        catch (SQLException e){
            System.out.println("SQL Error " + e);
        }
        catch (Exception e){
            System.out.println("Other Error " + e);
        }
        return null;
    }
    public List<UserCreatedMeal_Ingredient> getIngredientsInMeal(int userCreatedMealID) {
        try {
            return dao.getIngredientsInMeal(userCreatedMealID, ConnectionDB.getConnection());
        }
        catch (SQLException e){
            System.out.println("SQL Error " + e);
        }
        catch (Exception e){
            System.out.println("Other Error " + e);
        }
        return null;
    }
    public List<UserCreatedMeal_Method> getMethodsInMeal(int userCreatedMealID) {
        try {
            return dao.getMethodsInMeal(userCreatedMealID, ConnectionDB.getConnection());
        }
        catch (SQLException e){
            System.out.println("SQL Error " + e);
        }
        catch (Exception e){
            System.out.println("Other Error " + e);
        }
        return null;
    }

    public List<UserCreatedMeal> selectAllUserCreatedMeals(){
        try {
            return dao.selectAllUserCreatedMeals(ConnectionDB.getConnection());
        }
        catch (SQLException e){
            System.out.println("SQL Error " + e);
        }
        catch (Exception e){
            System.out.println("Other Error " + e);
        }
        return null;
    }

    public boolean updateFullUserCreatedMeal(FullUserCreatedMeal um) {
        try {
            if(v.checkFullUserCreatedMeal(um)) {
                return dao.updateFullUserCreatedMeal(um, ConnectionDB.getConnection());
            }
            System.out.println("not updated, fullusercreatedmeal not valid: "+um);
        }
        catch (SQLException e){
            System.out.println("SQL Error " + e);
        }
        catch (Exception e){
            System.out.println("Other Error " + e);
        }
        return false;
    }
    public boolean updateUserCreatedMeal(UserCreatedMeal um) {
        try {
            if (v.checkUserCreatedMeal(um)) {
                return dao.updateUserCreatedMeal(um, ConnectionDB.getConnection());
            }
            System.out.println("Not updated, usercreatedmeal not valid: "+ um);
        }
        catch (SQLException e){
            System.out.println("SQL Error " + e);
        }
        catch (Exception e){
            System.out.println("Other Error " + e);
        }
        return false;
    }
    public boolean updateUserCreatedMeal_Ingredient(List<UserCreatedMeal_Ingredient> ingredients) {
        try {
            for (UserCreatedMeal_Ingredient i:ingredients
            ) {
                if (!v.checkUserCreatedMeal_Ingredient(i)){
                    System.out.println("Not updated, ingredient not valid: " + i);
                    return false;
                }
            }
            return dao.updateUserCreatedMeal_Ingredient(ingredients, ConnectionDB.getConnection());
        }
        catch (SQLException e){
            System.out.println("SQL Error " + e);
        }
        catch (Exception e){
            System.out.println("Other Error " + e);
        }
        return false;
    }
    public boolean updateUserCreatedMeal_Method(List<UserCreatedMeal_Method> methods) {
        try {
            for (UserCreatedMeal_Method m:methods
            ) {
                if (!v.checkUserCreatedMeal_Method(m)){
                    System.out.println("Not updated, method not valid: " + m);
                    return false;
                }
            }
            return dao.updateUserCreatedMeal_Method(methods, ConnectionDB.getConnection());
        }
        catch (SQLException e){
            System.out.println("SQL Error " + e);
        }
        catch (Exception e){
            System.out.println("Other Error " + e);
        }
        return false;
    }
    //add deletes for meal, ingredient, and method seperately.

    public boolean deleteFullUserCreatedMeal(int fullUserCreatedMealID) {
        try {
            return dao.deleteFullUserCreatedMeal(fullUserCreatedMealID, ConnectionDB.getConnection());
        }
        catch (SQLException e){
            System.out.println("SQL Error " + e);
        }
        catch (Exception e){
            System.out.println("Other Error " + e);
        }
        return false;
    }
    public boolean deleteUserCreatedMeal(int userCreatedMealID) {
        try {
            return dao.deleteUserCreatedMeal(userCreatedMealID, ConnectionDB.getConnection());
        }
        catch (SQLException e){
            System.out.println("SQL Error " + e);
        }
        catch (Exception e){
            System.out.println("Other Error " + e);
        }
        return false;
    }
    public boolean deleteUserCreatedMeal_Ingredient(int userCreatedMealID) {
        try {
            return dao.deleteUserCreatedMeal_Ingredient(userCreatedMealID, ConnectionDB.getConnection());
        }
        catch (SQLException e){
            System.out.println("SQL Error " + e);
        }
        catch (Exception e){
            System.out.println("Other Error " + e);
        }
        return false;
    }
    public boolean deleteUserCreatedMeal_Method(int userCreatedMealID) {
        try {
            return dao.deleteUserCreatedMeal_Method(userCreatedMealID, ConnectionDB.getConnection());
        }
        catch (SQLException e){
            System.out.println("SQL Error " + e);
        }
        catch (Exception e){
            System.out.println("Other Error " + e);
        }
        return false;
    }
}
