package com.macrosAndMeals.resources;

import com.macrosAndMeals.model.User;
import com.macrosAndMeals.service.UserService;
import io.swagger.annotations.Api;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/api")
@Api("Macros and Meals API")
public class WebService {
    //error with this is it isnt gettinf database connection, look at the console when typimg in http://localhost:8080/api/user/all
    //also it shouldnt crash the program it should handle r=error surely
    @GET
    @Path("/user/all")
    @Produces(MediaType.APPLICATION_JSON)
    public Object getAllUsers(){
        System.out.println(new UserService().selectAllUsers());
        return new UserService().selectAllUsers().get(0);
    }

    @GET
    @Path("/print")
    @Produces(MediaType.APPLICATION_JSON)
    public String sendMsg() {
        return "Hello from a RESTful Web service: " ;
    }
}
