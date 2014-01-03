package org.ws.user;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

import org.model.User;

@Path("/user-management")
public class UserLoginModule {

	@GET
	@Path("/users/login")
	@Produces("application/json")
	public Response getTaxilocation(@QueryParam("name") String name, @QueryParam("password") String password) {
		
		User user = null;
		return Response.status(200).entity(user).build();
	}
}
