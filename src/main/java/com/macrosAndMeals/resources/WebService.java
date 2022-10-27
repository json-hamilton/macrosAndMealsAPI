package com.macrosAndMeals.resources;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.macrosAndMeals.model.User;
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
    //this works in the chrome but not on swagger
    //ok so even the print doesnt work on swagger. so now this is a problem with swagger not me i dont think. w
    @GET
    @Path("/user/all")
    @Produces(MediaType.APPLICATION_JSON)
    public String getAllUsers() {
        List<User> users = new UserService().selectAllUsers();
        JSONArray ja = new JSONArray(users);
        return ja.toString();
    }
//@GET
//@Path("/user/all")
//@Produces(MediaType.APPLICATION_JSON)
//public List<User> getAllUsers() {
//    UserService u = new UserService();
//    return u.selectAllUsers();
//
//}

    @GET
    @Path("/print")
    @Produces(MediaType.APPLICATION_JSON)
    public String sendMsg() {
        return "Hello from a RESTful Web service: " ;
    }
}
