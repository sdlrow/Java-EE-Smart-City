package Nurbol;

import javax.annotation.security.RolesAllowed;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/hello-world")
@RolesAllowed({"User", "Moderator", "Admin"})
public class HelloResource {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @RolesAllowed("Admin")
    public Response hello() {
        return Response.ok().entity("TEST").build();
    }
}



