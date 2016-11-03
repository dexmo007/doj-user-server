package rest;

import data.User;
import data.UserService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * user validate api
 */
@Path("/users")
public class UserValidationApi {

    @Inject
    private UserService userService;

    @POST
    @Path("/{id}/validate")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response validate(@PathParam("id") String id,
                             String pw) {
        User user = userService.get(id);
        if (user == null)
            return Authorization.error("no such user");
        if (!user.getPw().equals(pw))
            return Authorization.error("wrong password");
        return Response.accepted(user).build();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response get(@PathParam("id") String id,
                        @HeaderParam("Authorization") String auth) {
        User user = userService.get(id);
        if (!Authorization.isUserAuthenticated(userService, auth))
            return Authorization.error("authorization failed");
        return Response.accepted(user).build();
    }


}
