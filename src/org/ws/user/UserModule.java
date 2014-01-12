package org.ws.user;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

import org.dao.UserDAO;
import org.model.User;
import org.ws.DefaultFailObject;
import org.ws.DefaultSuccessObject;

@Path("/fast-taxi")
public class UserModule {

	@GET
	@Path("/users/validate")
	@Produces("application/json")
	public Response login(@QueryParam("name") String name, @QueryParam("password") String password) {
		UserDAO userDAO = new UserDAO();
		System.out.println(name);
		System.out.println(password);
		User user = userDAO.getUserByName(name, password);
		if (user != null){
			return Response.status(200).entity(new DefaultSuccessObject(true, user)).build();
		} else {
			return Response
			        .status(Response.Status.NOT_FOUND)
			        .entity(new DefaultFailObject(false, null, "nhập lại giá trị đê em"))
			        .build();
		}
	}
	
	@POST
	@Path("/users/")
	@Produces("application/json")
	public Response createUser(@QueryParam("name") String name, @QueryParam("password") String password) {
		UserDAO userDAO = new UserDAO();
		int rs = userDAO.createUser(name, password);
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
	
	@PUT
	@Path("/users/{id}")
	@Produces("application/json")
	public Response updateUser(User user) {
		UserDAO userDAO = new UserDAO();
		
		int rs = userDAO.updateUser(user);
		
		if (rs == 1){
			return Response.status(200).entity(new DefaultSuccessObject(true, null)).build();
		} else {
			return Response
			        .status(Response.Status.NOT_FOUND)
			        .entity(new DefaultFailObject(false, null, "Lỗi rồi em ơi"))
			        .build();
		}
	}
}
