package org.ws.user;

import java.util.Collection;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

import org.dao.CarDAO;
import org.dao.OrderDAO;
import org.model.Car;
import org.model.Order;

@Path("/user-management")
public class UserRequestModule {

	@GET
	@Path("/users/{id}")
	@Produces("application/json")
	public String getUserById(@PathParam("id") int id,
			@QueryParam("price") int price,
			@QueryParam("user_latitude") int user_latitude,
			@QueryParam("user_longitude") int user_longitude) {
		OrderDAO orderDAO = new OrderDAO();
		if (orderDAO.is_exist_order_not_finish(id)) {
			return "please delete other order";
		} else {
			Order order = new Order();
			order.setUser_id(id);
			order.setUser_latitude(user_latitude);
			order.setUser_longitude(user_longitude);
			orderDAO.insert_request_order(order);

			CarDAO carDAO = new CarDAO();
			Collection<Integer> collections = carDAO.getCollectionCar(price,
					user_latitude, user_longitude);
			
			// TODO: Send user's request to taxi is gotton from db

			return "Hệ thống đang chạy nhé :)";
		}

	}
}
