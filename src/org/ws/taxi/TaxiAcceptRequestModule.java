package org.ws.taxi;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

import org.dao.OrderDAO;
import org.dao.ShortOrderDAO;
import org.model.Order;
import org.model.ShortOrder;

public class TaxiAcceptRequestModule {
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
