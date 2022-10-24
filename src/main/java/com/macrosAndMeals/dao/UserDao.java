package com.macrosAndMeals.dao;

import com.macrosAndMeals.model.User;
import org.eclipse.jetty.http.MetaData;

import java.sql.*;
import java.time.LocalDate;
import java.util.*;

public class UserDao {
    public String insertUser(User u, Connection c) throws SQLException {
        String insertUserQuery = "insert into User values (0,?,?,?,?,?,?);";

        PreparedStatement st = c.prepareStatement(insertUserQuery);
        st.setString(2,u.getUsername());
        st.setString(3,u.getPassword());
        st.setString(4,String.valueOf(u.getWeight()));
        st.setString(5,String.valueOf(u.getHeight()));
        st.setString(6,String.valueOf(u.getGender()));
        st.setString(7,u.getSQLDateOfBirth());

        st.execute();
        return "finished";
    }
    public List<User> selectAllUsers(Connection c) throws SQLException {
        String selectUserQuery = "Select userID,username,password,weight,height,gender,dateOfBirth from User";
        Statement st = c.createStatement();
        ResultSet rs = st.executeQuery(selectUserQuery);
        List<User> users = new ArrayList<>();
        while (rs.next()){
            users.add(new User(rs.getInt("userId"),rs.getString("username"),rs.getString("password"),rs.getDouble("weight"),rs.getDouble("height"),rs.getInt("gender"), LocalDate.parse(rs.getString("dateOfBirth"))));
        }
        return users;
    }
    public User selectUser(int userId, Connection c) throws SQLException {
        String selectUserQuery = "Select userID,username,password,weight,height,gender,dateOfBirth from User where userID = " +userId +";";
        Statement st = c.createStatement();
        ResultSet rs = st.executeQuery(selectUserQuery);
        return new User(rs.getInt("userId"),rs.getString("username"),rs.getString("password"),rs.getDouble("weight"),rs.getDouble("height"),rs.getInt("gender"), LocalDate.parse(rs.getString("dateOfBirth")));
    }
    public String deleteUser(int userId, Connection c) throws SQLException {
        String deleteUserQuery = "delete from User where userID = " +userId +";";
        Statement st = c.createStatement();
        st.execute(deleteUserQuery);
        return "finished";
    }
    public String updateUser(User u, Connection c) throws SQLException {
        String updateUserQuery = "update User set username = '" + u.getUsername()
                + "', password = '" + u.getPassword()
                + "', weight = " + u.getWeight()
                + ", height = " + u.getHeight()
                + ", gender = " + u.getGender()
                + ", dateOfBirth = '" + u.getSQLDateOfBirth() + "';";
        c.createStatement().execute(updateUserQuery);
        return "finished";
    }
}
