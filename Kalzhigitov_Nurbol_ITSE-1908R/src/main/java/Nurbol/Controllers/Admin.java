package Nurbol.Controllers;

import Nurbol.Entities.Building;
import Nurbol.Entities.ERole;
import Nurbol.Entities.User;
import Nurbol.Logging.Logging;
import Nurbol.Services.UserService;
import Nurbol.Message.Message;
import Nurbol.Services.BuildingService;

import javax.annotation.security.RolesAllowed;
import javax.ejb.EJB;
import javax.jms.JMSException;
import javax.ws.rs.*;
import javax.ws.rs.core.*;
import javax.ws.rs.ext.ExceptionMapper;
import java.util.List;


@Path("/admin")
@RolesAllowed({"ADMIN"})
public class Admin implements ExceptionMapper {
    @EJB
    BuildingService buildingService;

    @EJB
    UserService userService;

    @EJB
    private Message message;

    @Context
    UriInfo uriInfo;
    @Context
    Request request;
    @Context
    HttpHeaders httpHeaders;
    //1
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Logging
    public Response admin() {
        return Response.ok().entity("Welcome Admin").build();
    }

    @DELETE
    @Path("/deleteBuilding/{Id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Logging
    public Response deleteBuilding(
            @DefaultValue("0")
            @PathParam("Id") int Id){
        String build = buildingService.getBuildingById(Id).getName();
        buildingService.deleteBuildingById(Id);
        return Response.ok().entity("Building " + build + " was removed").build();
    }

    @DELETE
    @Path("/deleteUser/{Id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Logging
    public Response deleteUser(
            @DefaultValue("0")
            @PathParam("Id") int id){
        String userLogin = userService.getUserById((long) id).getLogin();
        userService.deleteUserById(id);
        return Response.ok().entity("User " + userLogin + " was removed").build();
    }

    @POST
    @Path("/sendMessage")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.TEXT_PLAIN)
    @Logging
    public Response sendMessage(String text) throws JMSException {

        message.sendMessage(text);
        return  Response.ok()
                .entity("Message " + text + " was sent to all user")
                .build();
    }

    @GET
    @Path("/receiveAllMessage")
    @Produces("application/json")
    @Logging
    public Response getAllMessage() throws JMSException {
        List<String> receiveAllMessage = message.receiveAll();
        return  Response.ok()
                .entity("Message " + receiveAllMessage + " from admin")
                .build();
    }



    @GET
    @Path("/getAllBuildings")
    @Produces(MediaType.APPLICATION_JSON)
    @Logging
    public Response getAllBuildings() {
        List<Building> buildings = buildingService.getAllBuildings();
        return Response.ok().entity(buildings).build();
    }




    @PUT
    @Path("/updatePassowrd/{id}")
    @Produces(MediaType.TEXT_PLAIN)
    @Logging
    public Response updateUserPassword(@DefaultValue("0")
                                           @PathParam("id") int id, String newPassword){
        User user = userService.getUserById((long) id);
        if (user.getRole().getName() == ERole.Admin){
            return Response.serverError().entity("You can not change admin's role").build();
        } else {
            userService.updatePasswordById(id, newPassword);
            return Response.ok().entity("Password has been changed").build();
        }
    }


    @PUT
    @Path("/updatePasswordByLogin/{login}")
    @Produces(MediaType.TEXT_PLAIN)
    @Logging
    public Response updateUserPasswordByLogin(@PathParam("login") String login, String newPassword){
        User user = userService.getUserByLogin(login);
        if (user.getRole().getName() == ERole.Admin){
            return Response.serverError().entity("You can not change admin's role").build();
        } else {
            userService.updatePasswordByLogin(login,newPassword);
            return Response.ok().entity("Password has been changed").build();
        }

    }



    @Override
    public Response toResponse(Throwable throwable) {
        return Response.status(500)
                .entity("ERROR! " + "ERROR HAS OCCURRED!")
                .build();
    }


}
