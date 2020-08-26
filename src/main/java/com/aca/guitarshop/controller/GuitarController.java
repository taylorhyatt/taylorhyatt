package com.aca.guitarshop.controller;
import java.util.List;

import com.aca.guitarshop.service.GuitarService;
import com.aca.guitarshop.model.Brand;
import com.aca.guitarshop.model.Guitar;
import com.aca.guitarshop.model.PublishMessage;
import com.aca.guitarshop.model.SubscribeMessage;
//add model and bodyStyle
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;

@Path("/guitars")				//developing the paths
@Produces(MediaType.APPLICATION_JSON)
public class GuitarController {

	private GuitarService service = new GuitarService();

	public GuitarController() {
		System.out.println("Guitar controller constuctor called...");
	}

	@GET
	public List<Guitar> getGuitars() {
		return service.getGuitar();
	}

	@GET
	@Path("/brand/{brandValue}")
	public List<Guitar> getGuitarsByBrand(@PathParam("brandValue") Brand brand) {
		return service.getGuitarByBrand(brand);
	}

	@GET
	@Path("/bodyStyle/{bodyStyleValue}")
	public List<Guitar> getGuitarsByBodyStyle(@PathParam("bodyStyleValue") String bodyStyle) {
		return service.getGuitarsByBodyStyle(bodyStyle);
	}

	@GET
	@Path("/model/{modelValue}")
	public List<Guitar> getGuitarsByModel(@PathParam("modelValue") String model) {
		return service.getGuitarsByModel(model);
	}

	@GET
	@Path("/releaseyear/{releaseYearValue}")
	public List<Guitar> getGuitarByReleaseYear(@PathParam("releaseYearValue") Integer releaseYear) {
		return service.getGuitarsByReleaseYear(releaseYear);
	}

	@GET
	@Path("{guitarIdValue}")
	public List<Guitar> getGuitarsById(@PathParam("guitarIdValue") Integer guitarId) {
		return service.getGuitarById(guitarId);
	}

	@POST
	@Path("/message")
	@Consumes(MediaType.APPLICATION_JSON)
	public PublishMessage publishMessge(PublishMessage publishMessage) {

		return service.publishMessage(publishMessage);

	}

	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	public Guitar updateGuitars(Guitar updateGuitar) {
		return service.updateGuitar(updateGuitar);

	}

	@DELETE
	@Path("/{guitarIdValue}")
	public Guitar deleteGuitar(@PathParam("guitarIdValue") Integer guitarId) {
		return service.deleteGuitar(guitarId);
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Guitar createGuitar(Guitar newGuitar) {
		return service.createGuitar(newGuitar);

	}

	@GET
	@Path("/report")
	public List<Guitar> getReport(@QueryParam("startReleaseYear") Integer startReleaseYear,
			@QueryParam("endReleaseYear") Integer endReleaseYear) {
		return service.getReport(startReleaseYear, endReleaseYear);

	}

	@POST
	@Path("/subscribe")
	@Consumes(MediaType.APPLICATION_JSON)
	public SubscribeMessage subscribe(SubscribeMessage subscribeMessage) {

		return service.subscribe(subscribeMessage);

	}

}
