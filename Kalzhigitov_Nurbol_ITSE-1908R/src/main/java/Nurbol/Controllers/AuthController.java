package Nurbol.Controllers;


import Nurbol.Entities.User;
import Nurbol.Services.UserService;
import Nurbol.Services.UserdetailService;
import Nurbol.Logging.Logging;
import Nurbol.Security.JWTService;

import javax.annotation.security.PermitAll;
import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.*;
import javax.ws.rs.ext.ExceptionMapper;

@Path("/auth")

public class AuthController implements ExceptionMapper {

    @EJB
    JWTService jwtService;

    @EJB
    UserdetailService userdetailService;

    @EJB
    UserService userService;

    @Context
    UriInfo uriInfo;
    @Context
    Request request;
    @Context
    HttpHeaders httpHeaders;


    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @PermitAll
    @Logging
    @Path("/getJWT")
    public Response JWTAuthorization(User authenticationData) {
        User user  = userService.authenticate(authenticationData.getLogin(), authenticationData.getPassword());
        if(user == null) {
            return Response.status(500)
                    .entity("Wrong Data was entered" + "!")
                    .build();
        }
        else{
            String JWT_User = jwtService.generateJWTToken(user);
            return Response.ok().entity(JWT_User).build();
        }

    }


    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.TEXT_PLAIN)
    @Path("/checkJWT")
    @PermitAll
    @Logging
    public Response JWTCheck(String JWT) {

        String JWT_check = jwtService.valid(JWT);
        if(!(JWT_check == "not valid")) {
            String[] parts = JWT_check.split(",");
            String username = parts[0].split(":")[1].substring(1, parts[0].split(":")[1].length() - 1);
            String password = parts[1].split(":")[1].substring(1, parts[0].split(":")[1].length() - 1);
            System.out.println(username);
            System.out.println(password);
            return Response.ok().entity(username + " " + password).build();
        }
        else{
            return Response.status(500)
                    .entity("Wrong Token" + "!")
                    .build();
        }
    }


    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/register")
    @PermitAll
    @Logging
    public Response register(User user) {
        User user1 = userService.createNewUser(user.getLogin(), user.getPassword());
        if(user1 == null) {
            return Response.status(500)
                    .entity("Such user already exist!")
                    .build();
        }
        User JWT = userService.authenticate(user1.getLogin(), user1.getPassword());
        if(JWT == null) {
            return Response.status(500)
                    .entity("Wrong Data was entered" + "!")
                    .build();
        }
        else{
            String JWT_User = jwtService.generateJWTToken(user);
            return Response.ok().entity("User has been successfully created!\nJWT: "+ JWT_User).build();
        }



    }


    @Override
    public Response toResponse(Throwable throwable) {
        return Response.status(500)
                .entity("ERROR! " + "ERROR HAS OCCURRED!")
                .build();
    }

}