package com.macrosAndMeals.dao;

import com.macrosAndMeals.model.*;
import com.macrosAndMeals.service.MealService;

import java.awt.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserCreatedMealDao {
    //meals
    public boolean insertFullUserCreatedMeal(FullUserCreatedMeal um, Connection c) throws SQLException {
        boolean allWorked = true;
        boolean thisWorked;

        System.out.println(thisWorked = insertUserCreatedMealAutoID(um.getUserCreatedMeal(),c));
        if (!thisWorked){
            allWorked = false;
        }

        System.out.println(thisWorked = insertUserCreatedMeal_IngredientAutoID(um.getIngredients(),c));
        if (!thisWorked){
            allWorked = false;
        }

        System.out.println(thisWorked = insertUserCreatedMeal_MethodAutoID(um.getMethods(),c));
        if (!thisWorked){
            allWorked = false;
        }

    return allWorked;
    }
    public boolean insertUserCreatedMealAutoID(UserCreatedMeal um, Connection c) throws SQLException {
        new MealService().insertMeal(um);
        String insertUserCreatedMealQuery = "insert into UserCreatedMeal values (0,?,?);";
        ResultSet rs = c.prepareStatement("select mealID from Meal order by mealID desc limit 1").executeQuery();
        rs.next();
        int mealID = rs.getInt("mealID");

        PreparedStatement st = c.prepareStatement(insertUserCreatedMealQuery);
        st.setString(1,String.valueOf(mealID));
        st.setString(2,String.valueOf(um.getUserID()));

        return st.execute();
    }
    public boolean insertUserCreatedMeal(UserCreatedMeal um, Connection c) throws SQLException {
        String insertUserCreatedMealQuery = "insert into UserCreatedMeal values (0,?,?);";

        PreparedStatement st = c.prepareStatement(insertUserCreatedMealQuery);
        st.setString(1,String.valueOf(um.getMealId()));
        st.setString(2,String.valueOf(um.getUserID()));

        return st.execute();
    }
    public boolean insertUserCreatedMeal_IngredientAutoID(List<UserCreatedMeal_Ingredient> ingredients, Connection c) throws SQLException {
        boolean allWorked = true;
        for (UserCreatedMeal_Ingredient i:ingredients
        ) {
            String insertUserCreatedMeal_IngredientQuery = "insert into UserCreatedMeal_Ingredient values (?,?);";
            ResultSet rs = c.prepareStatement("select userCreatedMealID from UserCreatedMeal order by userCreatedMealID desc limit 1").executeQuery();
            rs.next();
            int mealID = rs.getInt("userCreatedMealID");
            PreparedStatement st = c.prepareStatement(insertUserCreatedMeal_IngredientQuery);
            st.setString(1,String.valueOf(mealID));
            st.setString(2,i.getIngredient());

            boolean result = st.execute();
            if (allWorked){
                allWorked = result;
            }
        }
        return allWorked;
    }
    public boolean insertUserCreatedMeal_MethodAutoID(List<UserCreatedMeal_Method> methods, Connection c) throws SQLException {
        boolean allWorked = true;
        for (UserCreatedMeal_Method m:methods
        ) {
            String insertUserCreatedMeal_MethodQuery = "insert into UserCreatedMeal_Method values (?,?);";

            ResultSet rs = c.prepareStatement("select userCreatedMealID from UserCreatedMeal order by userCreatedMealID desc limit 1").executeQuery();
            rs.next();
            int mealID = rs.getInt("userCreatedMealID");
            PreparedStatement st = c.prepareStatement(insertUserCreatedMeal_MethodQuery);
            st.setString(1,String.valueOf(mealID ));
            st.setString(2,m.getMethod());

            boolean result = st.execute();
            if (allWorked){
                allWorked = result;
            }
        }
        return allWorked;
    }
    public boolean insertUserCreatedMeal_Ingredient(List<UserCreatedMeal_Ingredient> ingredients, Connection c) throws SQLException {
        boolean allWorked = true;
        for (UserCreatedMeal_Ingredient i:ingredients
        ) {
            String insertUserCreatedMeal_IngredientQuery = "insert into UserCreatedMeal_Ingredient values (?,?);";

            PreparedStatement st = c.prepareStatement(insertUserCreatedMeal_IngredientQuery);
            st.setString(1,String.valueOf(i.getUserCreatedMealID()));
            st.setString(2,i.getIngredient());

            boolean result = st.execute();
            if (allWorked){
                allWorked = result;
            }
        }
        return allWorked;
    }
    public boolean insertUserCreatedMeal_Method(List<UserCreatedMeal_Method> methods, Connection c) throws SQLException {
        boolean allWorked = true;
        for (UserCreatedMeal_Method m:methods
        ) {
            String insertUserCreatedMeal_MethodQuery = "insert into UserCreatedMeal_Method values (?,?);";

            PreparedStatement st = c.prepareStatement(insertUserCreatedMeal_MethodQuery);
            st.setString(1,String.valueOf(m.getUserCreatedMealID()));
            st.setString(2,m.getMethod());

            boolean result = st.execute();
            if (allWorked){
                allWorked = result;
            }
        }
        return allWorked;
    }
    public List<FullUserCreatedMeal> selectAllFullUserCreatedMeals(Connection c) throws SQLException {
        List<FullUserCreatedMeal> fullUserCreatedMeals = new ArrayList<>();
        //select all user created meals, for each user created meal get the ingredients, methods, and actual meal and then join together and bam

        for (UserCreatedMeal um:selectAllUserCreatedMeals(c)
             ) {
            fullUserCreatedMeals.add(new FullUserCreatedMeal(um,getIngredientsInMeal(um.getUserCreatedMealID(),c),getMethodsInMeal(um.getUserCreatedMealID(),c)));
        }
        return fullUserCreatedMeals;
    }
public List<UserCreatedMeal_Ingredient> getIngredientsInMeal(int userCreatedMealID, Connection c) throws SQLException {
    List<UserCreatedMeal_Ingredient> userCreatedMeal_ingredients = new ArrayList<>();
    String selectAllUserCreatedMealIngredientsQuery = "select ingredient from UserCreatedMeal_Ingredient where userCreatedMealID = " + userCreatedMealID;
    Statement st = c.createStatement();
    ResultSet rs = st.executeQuery(selectAllUserCreatedMealIngredientsQuery);
    while (rs.next()) {
        userCreatedMeal_ingredients.add(new UserCreatedMeal_Ingredient(userCreatedMealID, rs.getString("ingredient")));
    }
    return userCreatedMeal_ingredients;
}
    public List<UserCreatedMeal_Method> getMethodsInMeal(int userCreatedMealID, Connection c) throws SQLException {
        List<UserCreatedMeal_Method> userCreatedMeal_methods = new ArrayList<>();
        String selectAllUserCreatedMealMethodsQuery = "select method from UserCreatedMeal_Method where userCreatedMealID = " + userCreatedMealID;
        Statement st = c.createStatement();
        ResultSet rs = st.executeQuery(selectAllUserCreatedMealMethodsQuery);
        while (rs.next()){
            userCreatedMeal_methods.add(new UserCreatedMeal_Method(userCreatedMealID,rs.getString("method")));
        }
        return userCreatedMeal_methods;
    }

    public List<UserCreatedMeal> selectAllUserCreatedMeals(Connection c) throws SQLException {
        List<UserCreatedMeal> userCreatedMeals = new ArrayList<>();
        MealDao dao = new MealDao();
        String selectAllUserCreatedMealsQuery = "select userCreatedMealID, mealID, userID from UserCreatedMeal";
        Statement st = c.createStatement();
        ResultSet rs = st.executeQuery(selectAllUserCreatedMealsQuery);
        while (rs.next()){
            Meal m = dao.selectMeal(rs.getInt("mealID"),c);
            userCreatedMeals.add(new UserCreatedMeal(m.getMealId(), m.getName(), m.getUrl(), m.getCalories(), m.getFat(), m.getCarbs(), m.getProtein(), m.getLikes(), m.getDateCreated(), rs.getInt("userCreatedMealID"), rs.getInt("userID")));
        }
        return userCreatedMeals;
    }

    public boolean updateFullUserCreatedMeal(FullUserCreatedMeal um, Connection c) throws SQLException {
        boolean allWorked = true;
        MealDao dao = new MealDao();

        if (!dao.updateMeal(um.getUserCreatedMeal(),c)){
            allWorked = false;
        }
        if (!updateUserCreatedMeal(um.getUserCreatedMeal(),c)){
            allWorked = false;
        }
        if (!updateUserCreatedMeal_Ingredient(um.getIngredients(),c)){
            allWorked = false;
        }
        if (!updateUserCreatedMeal_Method(um.getMethods(),c)){
            allWorked = false;
        }

        return allWorked;
    }
    public boolean updateUserCreatedMeal(UserCreatedMeal um, Connection c) throws SQLException {
        String insertUserCreatedMealQuery = "update UserCreatedMeal set mealID = ?, userID = ? where userCreatedMealID = ?;";

        PreparedStatement st = c.prepareStatement(insertUserCreatedMealQuery);
        st.setString(1,String.valueOf(um.getMealId()));
        st.setString(2,String.valueOf(um.getUserID()));
        st.setString(3,String.valueOf(um.getUserCreatedMealID()));

        return st.execute();
    }
    public boolean updateUserCreatedMeal_Ingredient(List<UserCreatedMeal_Ingredient> ingredients, Connection c) throws SQLException {
            try {
                deleteUserCreatedMeal_Ingredient(ingredients.get(0).getUserCreatedMealID(), c);
                return insertUserCreatedMeal_Ingredient(ingredients, c);
            }
            catch (Exception e){
                System.out.println(e.getMessage());
                return false;
            }
    }
    public boolean updateUserCreatedMeal_Method(List<UserCreatedMeal_Method> methods, Connection c) throws SQLException {
        try {
            deleteUserCreatedMeal_Method(methods.get(0).getUserCreatedMealID(), c);
            return insertUserCreatedMeal_Method(methods, c);
        }
        catch (Exception e){
            System.out.println(e.getMessage());
            return false;
        }
    }
    //add deletes for meal, ingredient, and method seperately.

    public boolean deleteFullUserCreatedMeal(int fullUserCreatedMealID, Connection c) throws SQLException {
        String deleteMealQuery = "delete from Meal where mealID = (select mealID from UserCreatedMeal where userCreatedMealID = " + fullUserCreatedMealID +");";
        PreparedStatement st = c.prepareStatement(deleteMealQuery);
        return st.execute();
    }
    public boolean deleteUserCreatedMeal(int userCreatedMealID, Connection c) throws SQLException {
        String deleteMealQuery = "delete from UserCreatedMeal where userCreatedMealID = "+userCreatedMealID;
        PreparedStatement st = c.prepareStatement(deleteMealQuery);
        return st.execute();
    }
    public boolean deleteUserCreatedMeal_Ingredient(int userCreatedMealID, Connection c) throws SQLException {
        String deleteMealQuery = "delete from UserCreatedMeal_Ingredient where userCreatedMealID = "+userCreatedMealID;
        PreparedStatement st = c.prepareStatement(deleteMealQuery);
        return st.execute();
    }
    public boolean deleteUserCreatedMeal_Method(int userCreatedMealID, Connection c) throws SQLException {
        String deleteMealQuery = "delete from UserCreatedMeal_Method where userCreatedMealID = "+userCreatedMealID;
        PreparedStatement st = c.prepareStatement(deleteMealQuery);
        return st.execute();
    }

}
