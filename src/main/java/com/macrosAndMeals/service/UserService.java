package com.macrosAndMeals.service;

import com.macrosAndMeals.core.ConnectionDB;
import com.macrosAndMeals.dao.UserDao;
import com.macrosAndMeals.model.User;

import javax.ws.rs.core.Response;
import java.sql.SQLException;
import java.util.List;

public class UserService {
    public String insertUser(User u){
        try {
            UserDao dao = new UserDao();
            return dao.insertUser(u, ConnectionDB.getConnection());
        }
        catch (SQLException e)
        {
            System.out.println("SQL ERROR, User not Inserted: ");
            System.out.println(u.toString());
            System.out.println(e.getMessage());
            return e.getMessage();
        }
        catch (Exception e)
        {
            System.out.println("NOT A SQL ERROR, User not Inserted: ");
            System.out.println(u.toString());
            System.out.println(e.getMessage());
            return e.getMessage();
        }
    }
//    public List<User> selectAllUsers(){
//        try {
//            UserDao dao = new UserDao();
//            List<User> users = dao.selectAllUsers(ConnectionDB.getConnection());
//            if (users.isEmpty()) {
//                System.out.println("SQL ran but returned empty user set");
//                return null;
//            }
//            return users;
//        }
//        catch (SQLException e){
//            System.out.println("SQL ERROR, can't select users: ");
//            System.out.println(e.getMessage());
//            return null;
//        }
//        catch (Exception e){
//            System.out.println("NOT A SQL ERROR, Can't select users: ");
//            System.out.println(e.getMessage());
//            return null;
//        }
//    }
    public Response selectAllUsers(){
        try {
            UserDao dao = new UserDao();
            List<User> users = dao.selectAllUsers(ConnectionDB.getConnection());
            System.out.println(users);
            if (users.isEmpty()) {
                System.out.println("SQL ran but returned empty user set");
                return Response.status(Response.Status.NO_CONTENT).build();
            }
            return Response
                    .status(Response.Status.OK)
                    .entity(users)
                    .build();
        }
        catch (SQLException e){
            System.out.println("SQL ERROR, can't select users: ");
            System.out.println(e.getMessage());
            return Response.serverError().build();
        }
        catch (Exception e){
            System.out.println("NOT A SQL ERROR, Can't select users: ");
            System.out.println(e.getMessage());
            return Response.status(Response.Status.fromStatusCode(444)).build();
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
    public String deleteUser(int userId){
        try{
            UserDao dao = new UserDao();
            return dao.deleteUser(userId,ConnectionDB.getConnection());
        }
        catch (SQLException e){
            System.out.println("SQL ERROR, can't delete user " + userId);
            System.out.println(e.getMessage());
            return null;
        }
        catch (Exception e){
            System.out.println("NOT A SQL ERROR, Can't delete user " + userId);
            System.out.println(e.getMessage());
            return null;
        }
    }
    public String updateUser(User u){
        try{
            UserDao dao = new UserDao();
            return dao.updateUser(u,ConnectionDB.getConnection());
        }
        catch (SQLException e){
            System.out.println("SQL ERROR, can't update user " + u.getUserId());
            System.out.println(e.getMessage());
            return null;
        }
        catch (Exception e){
            System.out.println("NOT A SQL ERROR, Can't update user " + u.getUserId());
            System.out.println(e.getMessage());
            return null;
        }
    }
}
