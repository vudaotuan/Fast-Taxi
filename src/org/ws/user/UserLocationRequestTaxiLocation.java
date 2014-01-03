package org.ws.user;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

import org.dao.ShortOrderDAO;
import org.model.ShortOrder;

@Path("/user-management")
public class UserLocationRequestTaxiLocation {

	@GET
	@Path("/users/taxi/{id}")
	@Produces("application/json")
	public String getTaxilocation(@PathParam("id") int id,
			@QueryParam("curLat") int curLat, @QueryParam("curLon") int curLon) {
		ShortOrderDAO shortOrderDAO = new ShortOrderDAO();
		ShortOrder shortOrder = shortOrderDAO.taxiRequest(id, curLon, curLat);

		return shortOrder.toString();
	}
}
