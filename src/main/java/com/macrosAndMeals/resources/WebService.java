package com.macrosAndMeals.resources;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.macrosAndMeals.model.*;
import com.macrosAndMeals.service.MealService;
import com.macrosAndMeals.service.UserCreatedMealService;
import com.macrosAndMeals.service.UserService;
import io.swagger.annotations.Api;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/api")
@Api("Macros and Meals API")
public class WebService {
//TODO posts and gets all work, now test the puts and deletes
    //users
    @GET
    @Path("/user/all")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllUsers() {
        return Response.status(Response.Status.OK)
                .type(MediaType.APPLICATION_JSON)
                .entity(new JSONArray(new UserService().selectAllUsers()).toString())
                .build();
    }
    @GET
    @Path("/user/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUserById(@PathParam("id") int id) {
        return Response.status(Response.Status.OK)
                .type(MediaType.APPLICATION_JSON)
                .entity(new JSONObject(new UserService().selectUser(id)).toString())
                .build();
    }
    @POST
    @Path("/user/add")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addUser(User u){
        UserService userService = new UserService();
        if(userService.insertUser(u)) {
            return Response.status(Response.Status.CREATED)
                    .type(MediaType.APPLICATION_JSON)
                    .entity(u)
                    .build();
        }
        return Response.status(Response.Status.BAD_REQUEST).build();
    }
    @DELETE
    @Path("/user/delete")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteUser(User u){
        UserService userService = new UserService();
        if(userService.deleteUser(u.getUserId())) {
            return Response.status(Response.Status.OK).build();
        }
        return Response.status(Response.Status.BAD_REQUEST).build();
    }
    @PUT
    @Path("/user/update")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateUser(User u){
        UserService userService = new UserService();
        if (userService.updateUser(u)) {
            return Response.status(Response.Status.OK).build();
        }
        return Response.status(Response.Status.BAD_REQUEST).build();
    }

    //meals
    @GET
    @Path("/meal/all")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllMeals() {
        return Response.status(Response.Status.OK)
                .type(MediaType.APPLICATION_JSON)
                .entity(new MealService().selectAllMeals())
                .build();
    }
    @GET
    @Path("/meal/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getMealById(@PathParam("id") int id) {
        return Response.status(Response.Status.OK)
                .type(MediaType.APPLICATION_JSON)
                .entity(new MealService().selectMeal(id))
                .build();
    }
    @POST
    @Path("/meal/add")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addMeal(Meal m){
        MealService mealService = new MealService();
        if(mealService.insertMeal(m)) {
            return Response.status(Response.Status.CREATED)
                    .type(MediaType.APPLICATION_JSON)
                    .entity(m)
                    .build();
        }
        return Response.status(Response.Status.BAD_REQUEST).build();
    }
    @DELETE
    @Path("/meal/delete")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteMeal(Meal m){
        MealService mealService = new MealService();
        if(mealService.deleteMeal(m.getMealId())) {
            return Response.status(Response.Status.OK).build();
        }
        return Response.status(Response.Status.BAD_REQUEST).build();
    }
    @PUT
    @Path("/meal/update")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateMeal(Meal m){
        MealService mealService = new MealService();
        if (mealService.updateMeal(m)) {
            return Response.status(Response.Status.OK).build();
        }
        return Response.status(Response.Status.BAD_REQUEST).build();
    }
    @POST
    @Path("/userCreatedMeal/add/full")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response insertFullUserCreatedMeal(FullUserCreatedMeal um){
        UserCreatedMealService mealService = new UserCreatedMealService();
        if (mealService.insertFullUserCreatedMeal(um)) {
            return Response.status(Response.Status.OK).build();
        }
        return Response.status(Response.Status.BAD_REQUEST).build();
    }
    @POST
    @Path("/userCreatedMeal/add")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response insertUserCreatedMeal(UserCreatedMeal um){
        UserCreatedMealService mealService = new UserCreatedMealService();
        mealService.insertUserCreatedMealAutoID(um);
        return Response.status(Response.Status.OK).build();
    }
    @POST
    @Path("/userCreatedMeal/add/ingredients")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response insertUserCreatedMeal_Ingredients(List<UserCreatedMeal_Ingredient> ingredients){
        UserCreatedMealService mealService = new UserCreatedMealService();
        mealService.insertUserCreatedMeal_Ingredient(ingredients);
        return Response.status(Response.Status.OK).build();
    }
    @POST
    @Path("/userCreatedMeal/add/methods")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response insertUserCreatedMeal_Methods(List<UserCreatedMeal_Method> methods){
        UserCreatedMealService mealService = new UserCreatedMealService();
        mealService.insertUserCreatedMeal_Method(methods);
        return Response.status(Response.Status.OK).build();
    }
    @GET
    @Path("/userCreatedMeal/get/full")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getFullUserCreatedMeals() {
        return Response.status(Response.Status.OK)
                .type(MediaType.APPLICATION_JSON)
                .entity(new UserCreatedMealService().selectAllFullUserCreatedMeals())
                .build();
    }
    @GET
    @Path("/userCreatedMeal/get/ingredients/{ID}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUserCreatedMeal_Ingredients(@PathParam("ID") int userCreatedMealID) {
        return Response.status(Response.Status.OK)
                .type(MediaType.APPLICATION_JSON)
                .entity(new UserCreatedMealService().getIngredientsInMeal(userCreatedMealID))
                .build();
    }
    @GET
    @Path("/userCreatedMeal/get/methods/{ID}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUserCreatedMeal_Methods(@PathParam("ID") int userCreatedMealID) {
        return Response.status(Response.Status.OK)
                .type(MediaType.APPLICATION_JSON)
                .entity(new UserCreatedMealService().getMethodsInMeal(userCreatedMealID))
                .build();
    }
    @GET
    @Path("/userCreatedMeal/get")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUserCreatedMeals() {
        return Response.status(Response.Status.OK)
                .type(MediaType.APPLICATION_JSON)
                .entity(new UserCreatedMealService().selectAllUserCreatedMeals())
                .build();
    }
    @PUT
    @Path("/userCreatedMeal/update/full")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateFullUserCreatedMeal(FullUserCreatedMeal um){
        UserCreatedMealService mealService = new UserCreatedMealService();
        if (mealService.updateFullUserCreatedMeal(um)) {
            return Response.status(Response.Status.OK).build();
        }
        return Response.status(Response.Status.BAD_REQUEST).build();
    }
    @PUT
    @Path("/userCreatedMeal/update")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateUserCreatedMeal(UserCreatedMeal um){
        UserCreatedMealService mealService = new UserCreatedMealService();
        if (mealService.updateUserCreatedMeal(um)) {
            return Response.status(Response.Status.OK).build();
        }
        return Response.status(Response.Status.BAD_REQUEST).build();
    }
    @PUT
    @Path("/userCreatedMeal/update/ingredients")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateUserCreatedMeal_Ingredient(List<UserCreatedMeal_Ingredient> ingredients){
        UserCreatedMealService mealService = new UserCreatedMealService();
        if (mealService.updateUserCreatedMeal_Ingredient(ingredients)) {
            return Response.status(Response.Status.OK).build();
        }
        return Response.status(Response.Status.BAD_REQUEST).build();
    }
    @PUT
    @Path("/userCreatedMeal/update/methods")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateUserCreatedMeal_Method(List<UserCreatedMeal_Method> methods){
        UserCreatedMealService mealService = new UserCreatedMealService();
        if (mealService.updateUserCreatedMeal_Method(methods)) {
            return Response.status(Response.Status.OK).build();
        }
        return Response.status(Response.Status.BAD_REQUEST).build();
    }
    @DELETE
    @Path("/userCreatedMeal/delete/full")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteFullUserCreatedMeal(int userCreatedMealID){
        UserCreatedMealService mealService = new UserCreatedMealService();
        if (mealService.deleteFullUserCreatedMeal(userCreatedMealID)) {
            return Response.status(Response.Status.OK).build();
        }
        return Response.status(Response.Status.BAD_REQUEST).build();
    }
    @DELETE
    @Path("/userCreatedMeal/delete")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteUserCreatedMeal(int userCreatedMealID){
        UserCreatedMealService mealService = new UserCreatedMealService();
        if (mealService.deleteUserCreatedMeal(userCreatedMealID)) {
            return Response.status(Response.Status.OK).build();
        }
        return Response.status(Response.Status.BAD_REQUEST).build();
    }
    @DELETE
    @Path("/userCreatedMeal/delete/ingredients")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteUserCreatedMeal_Ingredient(int userCreatedMealID){
        UserCreatedMealService mealService = new UserCreatedMealService();
        if (mealService.deleteUserCreatedMeal_Ingredient(userCreatedMealID)) {
            return Response.status(Response.Status.OK).build();
        }
        return Response.status(Response.Status.BAD_REQUEST).build();
    }
    @DELETE
    @Path("/userCreatedMeal/delete/methods")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteUserCreatedMeal_Method(int userCreatedMealID){
        UserCreatedMealService mealService = new UserCreatedMealService();
        if (mealService.deleteUserCreatedMeal_Method(userCreatedMealID)) {
            return Response.status(Response.Status.OK).build();
        }
        return Response.status(Response.Status.BAD_REQUEST).build();
    }
}
