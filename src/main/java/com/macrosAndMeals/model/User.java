package com.macrosAndMeals.model;

import com.macrosAndMeals.validation.UserValidation;
import org.decimal4j.util.DoubleRounder;
import org.joda.time.DateTime;

import java.text.SimpleDateFormat;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;
import java.util.Date;

public class User {
    private final UserValidation validation = new UserValidation();
    private int userId;
    private String username;
    private String password;
    private double weight;
    private double height;
    private int gender;
    private LocalDate dateOfBirth;

    public User(int userId, String username, String password, double weight, double height, int gender, LocalDate dateOfBirth) {
        setUserId(userId);
        setUsername(username);
        setPassword(password);
        setWeight(weight);
        setHeight(height);
        setGender(gender);
        setDateOfBirth(dateOfBirth);
    }

    public User() {
        userId =0;
        username = "";
        password = "";
        weight = 0;
        height = 0;
        gender = 0;
        dateOfBirth = LocalDate.now();
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId)throws IllegalArgumentException {
        if (validation.checkUserId(userId)){
            this.userId = userId;
        }
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) throws IllegalArgumentException{
        if(validation.checkUsername(username)){
            this.username = username;
        }
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password)throws IllegalArgumentException {
        if (validation.checkPassword(password)){
            this.password = password;
        }
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) throws IllegalArgumentException{
        if(validation.checkWeight(weight)){
            this.weight = DoubleRounder.round(weight,1);
        }
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) throws IllegalArgumentException {
        if (validation.checkHeight(height)){
            this.height = DoubleRounder.round(height,1);
        }
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) throws IllegalArgumentException {
        if (validation.checkGender(gender)){
            this.gender = gender;
        }
    }

    //sql version
    public String getSQLDateOfBirth() {
        return new SimpleDateFormat("yyyy-MM-dd").format(dateOfBirth);
    }

    //normal date version
    public LocalDate getJavaDateOfBirth(){
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) throws IllegalArgumentException{
        if (validation.checkDateOfBirth(dateOfBirth)){
            this.dateOfBirth = dateOfBirth;
        }
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", weight=" + weight +
                ", height=" + height +
                ", gender=" + gender +
                ", dateOfBirth=" + dateOfBirth +
                '}';
    }
}
