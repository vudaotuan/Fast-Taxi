package org.ws.taxi;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

import org.dao.ShortOrderDAO;
import org.model.ShortOrder;

public class TaxiRequestUserLocationModule {
	@GET
	@Path("/taxi/{id}/user")
	@Produces("application/json")
	public String getTaxilocation(@PathParam("id") int id,
			@QueryParam("curLat") int curLat, @QueryParam("curLon") int curLon) {
		// query from redis
		ShortOrderDAO shortOrderDAO = new ShortOrderDAO();
		ShortOrder shortOrder = shortOrderDAO.taxiRequest(id, curLon, curLat);
		
		return shortOrder.toString();
	}
}
