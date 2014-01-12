package org.ws.order;

import java.util.Collection;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

import org.dao.CarDAO;
import org.dao.OrderDAO;
import org.dao.ShortOrderDAO;
import org.model.Order;
import org.model.ShortOrder;
import org.ws.DefaultFailObject;
import org.ws.DefaultSuccessObject;

@Path("/user-management")
public class OrderModule {

	@GET
	@Path("/users/{id}/order")
	@Produces("application/json")
	public Response getTaxilocation(@PathParam("id") int id,
			@QueryParam("curLat") int curLat, @QueryParam("curLon") int curLon) {
		ShortOrderDAO shortOrderDAO = new ShortOrderDAO();
		ShortOrder shortOrder = shortOrderDAO.taxiRequest(id, curLon, curLat);

		if (shortOrder != null) {
			return Response.status(200)
					.entity(new DefaultSuccessObject(true, shortOrder)).build();
		} else {
			return Response
					.status(200)
					.entity(new DefaultFailObject(false, null,
							"Bạn chưa có order nào")).build();
		}

	}

	@POST
	@Path("/users/{id}/order/")
	@Produces("application/json")
	public Response getUserById(@PathParam("id") int id,
			@QueryParam("price") int price,
			@QueryParam("user_latitude") int user_latitude,
			@QueryParam("user_longitude") int user_longitude) {
		OrderDAO orderDAO = new OrderDAO();
		if (orderDAO.is_exist_order_not_finish(id)) {
			return Response
					.status(200)
					.entity(new DefaultFailObject(false, null,
							"Bạn vẫn có order đang chạy")).build();
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

			return Response
					.status(200)
					.entity(new DefaultFailObject(true, null,
							"Hệ thống đang chạy nhé :)")).build();

			// return "Hệ thống đang chạy nhé :)";
		}

	}

	@GET
	@Path("/taxi/{id}/order")
	@Produces("application/json")
	public Response getUserlocation(@PathParam("id") int id,
			@QueryParam("curLat") int curLat, @QueryParam("curLon") int curLon) {
		// query from redis
		ShortOrderDAO shortOrderDAO = new ShortOrderDAO();
		ShortOrder shortOrder = shortOrderDAO.taxiRequest(id, curLon, curLat);

		if (shortOrder != null) {
			return Response.status(200)
					.entity(new DefaultSuccessObject(true, shortOrder)).build();
		} else {
			return Response
					.status(200)
					.entity(new DefaultFailObject(false, null,
							"Bạn chưa có order nào")).build();
		}
	}
}
