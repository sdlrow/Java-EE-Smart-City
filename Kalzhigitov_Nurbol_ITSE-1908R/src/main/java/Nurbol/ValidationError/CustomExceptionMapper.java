package Nurbol.ValidationError;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class CustomExceptionMapper implements ExceptionMapper<Throwable> {

    @Override
    public Response toResponse(Throwable t) {
        if (t instanceof WebApplicationException) {
            WebApplicationException webEx = (WebApplicationException)t;
            return webEx.getResponse();
        } else {
            return Response.ok("ERROR HAS OCCURED!").build();
        }
    }

}
