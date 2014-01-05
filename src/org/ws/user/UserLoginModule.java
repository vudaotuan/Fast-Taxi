package org.ws.user;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

import org.dao.UserDAO;
import org.model.User;

@Path("/user-management")
public class UserLoginModule {

	@GET
	@Path("/users/login")
	@Produces("application/json")
	public Response login(@QueryParam("name") String name, @QueryParam("password") String password) {
		UserDAO userDAO = new UserDAO();
		System.out.println(name);
		System.out.println(password);
		User user = userDAO.getUserByName(name, password);
		if (user != null){
			return Response.status(200).entity(user).build();
		} else {
			return Response
			        .status(Response.Status.NOT_FOUND)
			        .entity("không có giá trị")
			        .build();
		}
	}
	
	@POST
	@Path("/users/login")
	@Produces("application/json")
	public Response createUser(@QueryParam("name") String name, @QueryParam("password") String password) {
		UserDAO userDAO = new UserDAO();
		System.out.println(name);
		System.out.println(password);
		int rs = userDAO.createUser(name, password);
		System.out.println(rs);
		if (rs == 1){
			User user = userDAO.getUserByName(name, password);
			if (user != null){
				return Response.status(200).entity(user).build();
			} else {
				return Response
				        .status(Response.Status.NOT_FOUND)
				        .entity("không có giá trị")
				        .build();
			}
		} else {
			return Response
			        .status(Response.Status.NOT_FOUND)
			        .entity("không có giá trị")
			        .build();
		}
		
	}
}
