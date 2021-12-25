package Nurbol.Security;

import Nurbol.Entities.User;
import Nurbol.Services.UserService;

import javax.annotation.security.DenyAll;
import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.ResourceInfo;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@Provider
//@JWTAuthed
//@Priority(Priorities.AUTHENTICATION)
public class JWTAuthedFilter implements ContainerRequestFilter {
    @Context
    private ResourceInfo resourceInfo;

    @Inject
    UserService userService;

    @Inject
    private JWTService jwtService;

    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {
        String token = requestContext.getHeaderString(HttpHeaders.AUTHORIZATION);
        Method method = resourceInfo.getResourceMethod();
        //Access allowed for all
        System.out.println("JSJSJSJSJSJ");
        if (!method.isAnnotationPresent(PermitAll.class)) {
            //Access denied for all
            if (method.isAnnotationPresent(DenyAll.class)) {
                requestContext.abortWith(Response.status(Response.Status.FORBIDDEN)
                        .entity("Access blocked for all users !!").build());
                return;
            }
            String payload = "";
            try {
                token = token.split(" ")[1];
                payload = jwtService.valid(token);
            } catch (Exception e) {
                requestContext
                        .abortWith(Response.status(Response.Status.UNAUTHORIZED)
                                .build());
            }
//            String [] parameters = payload.split("\\|");
//
//            for (String param : parameters) {
//                System.out.println(payload);;
//            }
            //
            String username = "";
            String password = "";
            try {
            String[] parts = payload.split(",");


            System.out.println();
            System.out.println();
            username = parts[0].split(":")[1].substring(1, parts[0].split(":")[1].length()-1);
            password = parts[1].split(":")[1].substring(1,parts[0].split(":")[1].length()-1);

            //
            } catch (Exception e) {
                requestContext
                        .abortWith(Response.status(Response.Status.UNAUTHORIZED)
                                .build());
            }
            if (method.isAnnotationPresent(RolesAllowed.class)) {
                RolesAllowed rolesAnnotation = method.getAnnotation(RolesAllowed.class);
                Set<String> rolesSet = new HashSet<String>(Arrays.asList(rolesAnnotation.value()));

                //Is user valid?
                if (!isUserAllowed(username, password, rolesSet)) {
                    requestContext.abortWith(Response.status(Response.Status.UNAUTHORIZED)
                            .entity("You cannot access this resource").build());
                    return;
                }
            }

        }
    }


    private boolean isUserAllowed(final String username, final String password, final Set<String> rolesSet) {
        boolean isAllowed = false;

        //Step 1. Fetch password from database and match with password in argument
        //If both match then get the defined role for user from database and continue; else return isAllowed [false]
        //Access the database and do this part yourself
        //String userRole = userMgr.getUserRole(username);
        User user = userService.authenticate(username, password);

        if (!(user == null)) {
            String userRole = user.getRole().getName().toString();
            System.out.println(userRole);
            System.out.println(rolesSet);
            //Step 2. Verify user role
            if (rolesSet.contains(userRole)) {

                isAllowed = true;
                System.out.println(isAllowed);
            }
        }
        return isAllowed;
    }

}
