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

    @GET
    @Path("/user/all")
    @Produces(MediaType.APPLICATION_JSON)
    public String getAllUsers() {
        List<User> users = new UserService().selectAllUsers();
        JSONArray ja = new JSONArray(users);
        return ja.toString();
    }
    @GET
    @Path("/user/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public String getUserById(@PathParam("id") int id) {
        User u = new UserService().selectUser(id);
        JSONObject jo = new JSONObject(u);
        return jo.toString();
    }
    @POST
    @Path("/user/add")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addUser(User u){
        UserService userService = new UserService();
        userService.insertUser(u);
        return Response.status(Response.Status.CREATED).entity(u).build();
    }
    @POST
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
    @POST
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
}
