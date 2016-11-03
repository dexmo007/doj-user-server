package rest;

import data.User;
import data.UserService;

import javax.inject.Inject;
import javax.resource.spi.AuthenticationMechanism;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

/**
 * admin api to add users
 */
@Path("/admin")
public class AdminApi {

    @Inject
    UserService userService;

    @POST
    @Path("/add")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addUser(User user) {
        userService.add(user);
        return Response.accepted().build();
    }

    @GET
    @Path("/all")
    @Produces(MediaType.APPLICATION_JSON)
    public List<User> getAll() {
        return userService.getAll();
    }

}
