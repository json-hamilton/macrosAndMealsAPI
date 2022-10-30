package com.macrosAndMeals.service;

import com.macrosAndMeals.core.ConnectionDB;
import com.macrosAndMeals.dao.UserDao;
import com.macrosAndMeals.model.User;

import javax.ws.rs.core.Response;
import java.sql.SQLException;
import java.util.List;

public class UserService {
    public boolean insertUser(User u){
        try {
            UserDao dao = new UserDao();
            return dao.insertUser(u, ConnectionDB.getConnection());
        }
        catch (SQLException e)
        {
            System.out.println("SQL ERROR, User not Inserted: ");
            System.out.println(u.toString());
            System.out.println(e.getMessage());
            return false;
        }
        catch (Exception e)
        {
            System.out.println("NOT A SQL ERROR, User not Inserted: ");
            System.out.println(u.toString());
            System.out.println(e.getMessage());
            return false;
        }
    }
    public List<User> selectAllUsers(){
        try {
            UserDao dao = new UserDao();
            List<User> users = dao.selectAllUsers(ConnectionDB.getConnection());
            if (users.isEmpty()) {
                System.out.println("SQL ran but returned empty user set");
                return null;
            }
            return users;
        }
        catch (SQLException e){
            System.out.println("SQL ERROR, can't select users: ");
            System.out.println(e.getMessage());
            return null;
        }
        catch (Exception e){
            System.out.println("NOT A SQL ERROR, Can't select users: ");
            System.out.println(e.getMessage());
            return null;
        }
    }
    public User selectUser(int userId){
        try{
            UserDao dao = new UserDao();
            User u = dao.selectUser(userId,ConnectionDB.getConnection());
            if(u == null){
                System.out.println("SQL ran but didn't return a user");
                return null;
            }
            return u;
        }
        catch (SQLException e){
            System.out.println("SQL ERROR, can't select user " + userId);
            System.out.println(e.getMessage());
            return null;
        }
        catch (Exception e){
            System.out.println("NOT A SQL ERROR, Can't select user " + userId);
            System.out.println(e.getMessage());
            return null;
        }
    }
    public boolean deleteUser(int userId){
        try{
            UserDao dao = new UserDao();
            return dao.deleteUser(userId,ConnectionDB.getConnection());
        }
        catch (SQLException e){
            System.out.println("SQL ERROR, can't delete user " + userId);
            System.out.println(e.getMessage());
            return false;
        }
        catch (Exception e){
            System.out.println("NOT A SQL ERROR, Can't delete user " + userId);
            System.out.println(e.getMessage());
            return false;
        }
    }
    public boolean updateUser(User u){
        try{
            UserDao dao = new UserDao();
            return dao.updateUser(u,ConnectionDB.getConnection());
        }
        catch (SQLException e){
            System.out.println("SQL ERROR, can't update user " + u.getUserId());
            System.out.println(e.getMessage());
            return false;
        }
        catch (Exception e){
            System.out.println("NOT A SQL ERROR, Can't update user " + u.getUserId());
            System.out.println(e.getMessage());
            return false;
        }
    }
}
