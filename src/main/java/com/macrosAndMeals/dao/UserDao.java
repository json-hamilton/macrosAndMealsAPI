package com.macrosAndMeals.dao;

import com.macrosAndMeals.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class UserDao {
    //TODO make more user sql statements
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
}
