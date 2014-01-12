package org.ws.car;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

import org.dao.CarDAO;
import org.dao.OrderDAO;
import org.dao.ShortOrderDAO;
import org.dao.UserDAO;
import org.model.Car;
import org.model.Order;
import org.model.ShortOrder;
import org.model.User;
import org.ws.DefaultFailObject;
import org.ws.DefaultSuccessObject;

public class CarModule {
	@GET
	@Path("/cars/validate")
	@Produces("application/json")
	public Response login(@QueryParam("name") String name, @QueryParam("password") String password) {
		CarDAO carDAO = new CarDAO();
		System.out.println(name);
		System.out.println(password);
		Car car = carDAO.getCarByName(name, password);
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
	@Path("/cars/")
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
	@Path("/cars/{id}")
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
	
	
	@GET
	@Path("/taxi/{id}/accept")
	@Produces("application/json")
	public String acceptOrder(@PathParam("car_id") int car_id,
			@PathParam("user_id") int user_id,
			@QueryParam("curLat") int curLat, @QueryParam("curLon") int curLon) {

		OrderDAO orderDAO = new OrderDAO();
		Order order = orderDAO.get_order(user_id, 0);
		ShortOrder shortOrder = null;
		if (order != null) {
			order.setStatus(1);
			orderDAO.update_order_taxi(order);

			shortOrder = new ShortOrder(order.getUser_id(), car_id,
					curLat, curLon, order.getCar_latitude(),
					order.getCar_longitude());

			ShortOrderDAO shortOrderDAO = new ShortOrderDAO();
			shortOrderDAO.insertShortOrder(shortOrder);
		}

		return shortOrder.toString();
	}
}
