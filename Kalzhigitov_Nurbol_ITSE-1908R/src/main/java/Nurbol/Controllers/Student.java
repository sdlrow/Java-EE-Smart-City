package Nurbol.Controllers;

import Nurbol.Entities.Building;
import Nurbol.Logging.Logging;
import Nurbol.Services.BuildingService;
import Nurbol.Services.CategoryService;

import javax.annotation.security.RolesAllowed;
import javax.ejb.EJB;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.*;
import javax.ws.rs.ext.ExceptionMapper;
import java.util.List;

@Path("/student")
@RolesAllowed({"User", "Moderator", "Admin"})
public class Student implements ExceptionMapper {

    @EJB
    BuildingService buildingService;

    @EJB
    CategoryService categoryService;

    @Context
    UriInfo uriInfo;
    @Context
    Request request;
    @Context
    HttpHeaders httpHeaders;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @RolesAllowed({"Admin", "User", "Moderator"})
    @Logging
    public Response welcome() {
        return Response.ok().entity("Welcome in student module!").build();
    }

    @GET
    @Path("/getAllBuildings")
    @Produces(MediaType.APPLICATION_JSON)
    @RolesAllowed({"Admin", "User", "Moderator"})
    @Logging
    public Response getAllBuildings() {
        List<Building> buildings = buildingService.getAllBuildingsByCategoryName("Student");
        return Response.ok().entity(buildings).build();
    }

    @GET
    @Path("/getCategoryInfo")
    @Produces(MediaType.APPLICATION_JSON)
    @RolesAllowed({"Admin", "User", "Moderator"})
    @Logging
    public Response getCategory() {
        return Response.ok().entity(categoryService.getCategoryByName("Student").getDescription()).build();
    }

    @Override
    public Response toResponse(Throwable throwable) {
        return Response.status(500)
                .entity("ERROR! " + "ERROR HAS OCCURRED!")
                .build();
    }
}
