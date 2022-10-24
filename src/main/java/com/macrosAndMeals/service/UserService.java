package com.macrosAndMeals.service;

import com.macrosAndMeals.core.ConnectionDB;
import com.macrosAndMeals.dao.UserDao;
import com.macrosAndMeals.model.User;

import java.sql.SQLException;

public class UserService {
    //TODO link to dao
    public String insertUser(User u){
        try {
            UserDao dao = new UserDao();
            return dao.insertUser(u, ConnectionDB.getConnection());
        }
        catch (SQLException e)
        {
            System.out.println("User not Inserted: ");
            System.out.println(u.toString());
            System.out.println(e.getMessage());
            return e.getMessage();
        }
        catch (Exception e)
        {
            System.out.println("User not Inserted: ");
            System.out.println(u.toString());
            System.out.println(e.getMessage());
            return e.getMessage();
        }
    }
}
