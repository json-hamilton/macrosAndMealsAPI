package com.macrosAndMeals.dao;

import com.macrosAndMeals.model.User;

import java.sql.*;
import java.time.LocalDate;
import java.util.*;

public class UserDao {
    public boolean insertUser(User u, Connection c) throws SQLException {
        String insertUserQuery = "insert into User values (0,?,?,?,?,?,?);";

        PreparedStatement st = c.prepareStatement(insertUserQuery);
        st.setString(1,u.getUsername());
        st.setString(2,u.getPassword());
        st.setString(3,String.valueOf(u.getWeight()));
        st.setString(4,String.valueOf(u.getHeight()));
        st.setString(5,String.valueOf(u.getGender()));
        st.setString(6,u.getDateOfBirth().toString());
        return st.execute();
    }
    public List<User> selectAllUsers(Connection c) throws SQLException {
        String selectUserQuery = "Select userID,username,password,weight,height,gender,dateOfBirth from User";
        Statement st = c.createStatement();
        ResultSet rs = st.executeQuery(selectUserQuery);
        List<User> users = new ArrayList<>();
        while (rs.next()) {

            LocalDate dob = rs.getDate("dateOfBirth").toLocalDate();
            users.add(new User(rs.getInt("userId"), rs.getString("username"), rs.getString("password"), rs.getDouble("weight"), rs.getDouble("height"), rs.getInt("gender"), dob));
        }
        return users;
    }
    public User selectUser(int userId, Connection c) throws SQLException {
        String selectUserQuery = "Select userID,username,password,weight,height,gender,dateOfBirth from User where userID = " + userId + ";";
        Statement st = c.createStatement();
        ResultSet rs = st.executeQuery(selectUserQuery);
        rs.next();
        LocalDate dob = rs.getDate("dateOfBirth").toLocalDate();
        return new User(rs.getInt("userId"), rs.getString("username"), rs.getString("password"), rs.getDouble("weight"), rs.getDouble("height"), rs.getInt("gender"), dob);
    }
    public boolean deleteUser(int userId, Connection c) throws SQLException {
        String deleteUserQuery = "delete from User where userID = " +userId +";";
        Statement st = c.createStatement();
        return st.execute(deleteUserQuery);
    }
    public boolean updateUser(User u, Connection c) throws SQLException {
        String updateUserQuery = "update User set username = '" + u.getUsername()
                + "', password = '" + u.getPassword()
                + "', weight = " + u.getWeight()
                + ", height = " + u.getHeight()
                + ", gender = " + u.getGender()
                + ", dateOfBirth = '" + u.getDateOfBirth()
                + "' where userId =" + u.getUserId() + ";";
        ;
        return c.createStatement().execute(updateUserQuery);
    }
}
