package com.macrosAndMeals.service;

import com.macrosAndMeals.core.ConnectionDB;
import com.macrosAndMeals.dao.MealDao;
import com.macrosAndMeals.model.Macros;
import com.macrosAndMeals.model.Meal;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.SQLException;
import java.util.ArrayList;
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
    public List<Meal> generateMeals(Macros macros){
        try{
            MealDao dao = new MealDao();
            List<Meal> meals = dao.selectAllMeals(ConnectionDB.getConnection());
            List<Meal> generatedMeals = new ArrayList<>();
            Macros totalMacros = new Macros(0,0,0,0,0);
            Macros macrosThisMeal = new Macros(0,0,0,0,0);
            double highestPercentageMatch = 100;
            int indexOfHighest = 0;
            for (int j = macros.getNumMeals(); j>0;j--){
                if (j == macros.getNumMeals()){
                    macrosThisMeal.setCalories(new BigDecimal(String.valueOf((macros.getCalories()/macros.getNumMeals()))).setScale(1, RoundingMode.HALF_UP).doubleValue());
                    macrosThisMeal.setFat(new BigDecimal(String.valueOf((macros.getFat()/macros.getNumMeals()))).setScale(1, RoundingMode.HALF_UP).doubleValue());
                    macrosThisMeal.setCarbs(new BigDecimal(String.valueOf((macros.getCarbs()/macros.getNumMeals()))).setScale(1, RoundingMode.HALF_UP).doubleValue());
                    macrosThisMeal.setProtein(new BigDecimal(String.valueOf((macros.getProtein()/macros.getNumMeals()))).setScale(1, RoundingMode.HALF_UP).doubleValue());
                }
                for (int i = 0; i<meals.size(); i++){
                    double percentageMatch = 0;
                    percentageMatch += Math.abs(macrosThisMeal.getCalories() - meals.get(i).getCalories())/macrosThisMeal.getCalories();
                    percentageMatch += Math.abs(macrosThisMeal.getFat() - meals.get(i).getFat())/macrosThisMeal.getFat();
                    percentageMatch += Math.abs(macrosThisMeal.getCarbs() - meals.get(i).getCarbs())/macrosThisMeal.getCarbs();
                    percentageMatch += Math.abs(macrosThisMeal.getProtein() - meals.get(i).getProtein())/macrosThisMeal.getProtein();
                    percentageMatch /= 4;
                    if (i != 0){
                        if(Math.abs(percentageMatch)<Math.abs(highestPercentageMatch)){
                            highestPercentageMatch = percentageMatch;
                            indexOfHighest = i;
                        }
                    }
                    else {
                        highestPercentageMatch = percentageMatch;
                        indexOfHighest = i;
                    }
                }
                totalMacros.setCalories(totalMacros.getCalories() + meals.get(indexOfHighest).getCalories());
                totalMacros.setFat(totalMacros.getFat() + meals.get(indexOfHighest).getFat());
                totalMacros.setCarbs(totalMacros.getCarbs() + meals.get(indexOfHighest).getCarbs());
                totalMacros.setProtein(totalMacros.getProtein() + meals.get(indexOfHighest).getProtein());

                if (j!=1) {
                    macrosThisMeal.setCalories(new BigDecimal(String.valueOf(((macros.getCalories() - totalMacros.getCalories()) / (j - 1)))).setScale(1, RoundingMode.HALF_UP).doubleValue()); //i have a feeling this j-1 thing may be off, check it when debug
                    macrosThisMeal.setFat(new BigDecimal(String.valueOf(((macros.getFat() - totalMacros.getFat()) / (j - 1)))).setScale(1, RoundingMode.HALF_UP).doubleValue());
                    macrosThisMeal.setCarbs(new BigDecimal(String.valueOf(((macros.getCarbs() - totalMacros.getCarbs()) / (j - 1)))).setScale(1, RoundingMode.HALF_UP).doubleValue());
                    macrosThisMeal.setProtein(new BigDecimal(String.valueOf(((macros.getProtein() - totalMacros.getProtein()) / (j - 1)))).setScale(1, RoundingMode.HALF_UP).doubleValue());
                }
                generatedMeals.add(meals.get(indexOfHighest));
                meals.remove(indexOfHighest);
            }
            System.out.println(totalMacros);
            return generatedMeals;
        }
        catch (SQLException e)
        {
            System.out.println("SQL ERROR, could not fetch meals: ");
            System.out.println(e.getMessage());
            return null;
        }
        catch (Exception e)
        {
            System.out.println("Error, meals not generated: ");
            System.out.println(e.getMessage());
            return null;
        }
    }
}
