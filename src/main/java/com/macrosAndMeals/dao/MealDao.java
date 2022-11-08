package com.macrosAndMeals.dao;

import com.macrosAndMeals.model.Meal;
import com.macrosAndMeals.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class MealDao {
    public boolean insertMeal(Meal meal, Connection c) throws SQLException {
        //ok so the problem is do desc meal there is a date created field u need to update dao, validatation and meal class to accomodate this and find anywhere else too.
        String insertMealQuery = "insert into Meal values (0,?,?,?,?,?,?,?,?);";

        PreparedStatement st = c.prepareStatement(insertMealQuery);
        st.setString(1,meal.getName());
        st.setString(2,meal.getUrl());
        st.setString(3,String.valueOf(meal.getCalories()));
        st.setString(4,String.valueOf(meal.getFat()));
        st.setString(5,String.valueOf(meal.getCarbs()));
        st.setString(6,String.valueOf(meal.getProtein()));
        st.setString(7,String.valueOf(meal.getLikes()));
        st.setString(8,String.valueOf(meal.getDateCreated()));
        return st.execute();
    }
    public List<Meal> selectAllMeals(Connection c) throws SQLException {
        List<Meal> meals = new ArrayList<>();
        String selectMealQuery = "select mealID, name, url, calories, fat, carbs, protein, likes, dateCreated from Meal;";
        PreparedStatement st = c.prepareStatement(selectMealQuery);
        ResultSet rs = st.executeQuery();
        while (rs.next()){
            meals.add(new Meal(rs.getInt("mealID"),rs.getString("name"),rs.getString("url"),
            rs.getDouble("calories"),rs.getDouble("fat"),rs.getDouble("carbs"),rs.getDouble("protein"),rs.getInt("likes"), LocalDate.parse(rs.getString("dateCreated"))));
        }
        return meals;
    }
    public Meal selectMeal(int mealId, Connection c) throws SQLException {
        String selectMealQuery = "select mealID, name, url, calories, fat, carbs, protein, likes, dateCreated from Meal where mealID = " + mealId + ";";
        PreparedStatement st = c.prepareStatement(selectMealQuery);
        ResultSet rs = st.executeQuery();
        rs.next();
        return new Meal(rs.getInt("mealID"),rs.getString("name"),rs.getString("url"),
                rs.getDouble("calories"),rs.getDouble("fat"),rs.getDouble("carbs")
                ,rs.getDouble("protein"),rs.getInt("likes"),LocalDate.parse(rs.getString("dateCreated")));

    }
    public boolean deleteMeal(int mealId, Connection c) throws SQLException {
        String deleteMealQuery = "delete from Meal where mealID = " + mealId + ";";
        PreparedStatement st = c.prepareStatement(deleteMealQuery);
        return st.execute();
    }
    public boolean updateMeal(Meal meal, Connection c) throws SQLException {
        String updateUserQuery = "update Meal set name = '" + meal.getName()
                + "', url = '" + meal.getUrl()
                + "', calories = " + meal.getCalories()
                + ", fat = " + meal.getFat()
                + ", carbs = " + meal.getCarbs()
                + ", protein = " + meal.getProtein()
                + ", likes = " + meal.getLikes()
                + ", dateCreated = '" + meal.getDateCreated()
                + "' where mealID =" + meal.getMealId() + ";";
        ;
        return c.createStatement().execute(updateUserQuery);
    }
}
