package com.macrosAndMeals.service;

import com.macrosAndMeals.core.ConnectionDB;
import com.macrosAndMeals.dao.MealDao;
import com.macrosAndMeals.model.Meal;

import java.sql.SQLException;
import java.util.List;

public class MealService {
    public boolean insertMeal(Meal meal){
        try{
            MealDao dao = new MealDao();
            return dao.insertMeal(meal, ConnectionDB.getConnection());
        }
        catch (SQLException e)
        {
            System.out.println("SQL ERROR, Meal not Inserted: ");
            System.out.println(meal.toString());
            System.out.println(e.getMessage());
            return false;
        }
        catch (Exception e)
        {
            System.out.println("NOT A SQL ERROR, Meal not Inserted: ");
            System.out.println(meal.toString());
            System.out.println(e.getMessage());
            return false;
        }
    }
    public List<Meal> selectAllMeals(){
        try{
            MealDao dao = new MealDao();
            return dao.selectAllMeals(ConnectionDB.getConnection());
        }
        catch (SQLException e)
        {
            System.out.println("SQL ERROR, can't select meals: ");
            System.out.println(e.getMessage());
            return null;
        }
        catch (Exception e)
        {
            System.out.println("NOT A SQL ERROR, can't select meals: ");
            System.out.println(e.getMessage());
            return null;
        }
    }
    public Meal selectMeal(int mealId){
        try{
            MealDao dao = new MealDao();
            return dao.selectMeal(mealId,ConnectionDB.getConnection());
        }
        catch (SQLException e)
        {
            System.out.println("SQL ERROR, can't select meal: ");
            System.out.println(e.getMessage());
            return null;
        }
        catch (Exception e)
        {
            System.out.println("NOT A SQL ERROR, can't select meal: ");
            System.out.println(e.getMessage());
            return null;
        }
    }
    public boolean deleteMeal(int mealId){
        try{
            MealDao dao = new MealDao();
            return dao.deleteMeal(mealId, ConnectionDB.getConnection());
        }
        catch (SQLException e)
        {
            System.out.println("SQL ERROR, Meal not deleted: ");
            System.out.println(e.getMessage());
            return false;
        }
        catch (Exception e)
        {
            System.out.println("NOT A SQL ERROR, Meal not deleted: ");
            System.out.println(e.getMessage());
            return false;
        }
    }
    public boolean updateMeal(Meal meal){
        try{
            MealDao dao = new MealDao();
            return dao.updateMeal(meal, ConnectionDB.getConnection());
        }
        catch (SQLException e)
        {
            System.out.println("SQL ERROR, Meal not updated: ");
            System.out.println(e.getMessage());
            return false;
        }
        catch (Exception e)
        {
            System.out.println("NOT A SQL ERROR, Meal not updated: ");
            System.out.println(e.getMessage());
            return false;
        }
    }
}
