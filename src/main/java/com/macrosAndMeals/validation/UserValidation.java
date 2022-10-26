package com.macrosAndMeals.validation;

import org.decimal4j.util.DoubleRounder;

import java.time.LocalDate;
import java.time.Period;

public class UserValidation {
    public boolean checkUserId(int userId) throws IllegalArgumentException{
        if (userId>=0){
            return true;
        }
        else{
            throw new IllegalArgumentException("User ID cannot be negative");
        }
    }
    public boolean checkUsername(String username) throws IllegalArgumentException{
        if (username.matches("^[A-Za-z]\\w{3,29}$")){
            if (username.length()<=30 && username.length()>=3){
                return true;
            }
            else{
                throw new IllegalArgumentException("Username must be between 3 and 30 characters");
            }
        }
        else {
            throw new IllegalArgumentException("Username can only have letters, digits, and underscores.");
        }
    }
    public boolean checkPassword(String password) throws IllegalArgumentException{
        if (password.matches("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#$%^&-+=()])(?=\\S+$).{8,30}$")){
            return true;
        }
        else {
            throw new IllegalArgumentException("Password does not meet the complexity requirements");
        }
    }
    public boolean checkWeight(double weight) throws IllegalArgumentException{
        if (weight>=0 && weight <999){
            return true;
        }
        else{
            throw new IllegalArgumentException("Weight must be greater than 0 and less than 999");
        }
    }
    public boolean checkHeight(double height) throws IllegalArgumentException{
        if (height>=0 && height <999){
            return true;
        }
        else{
            throw new IllegalArgumentException("Height must be greater than 0 and less than 999");
        }
    }
    public boolean checkGender(int gender) throws IllegalArgumentException{
        if (gender==0 ||gender==1){
            return true;
        }
        else{
            throw new IllegalArgumentException("Gender must be male or female (0 or 1)");
        }
    }
    public boolean checkDateOfBirth(LocalDate dateOfBirth){
        int age = Math.abs(Period.between(LocalDate.now(),dateOfBirth).getYears());
        if (age>=18){
            return true;
        }
        else{
            System.out.println(age);
            throw new IllegalArgumentException("Age is less than 18");
        }
    }
}
