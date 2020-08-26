package com.aca.guitarshop.service;

import java.util.List;

import com.aca.guitarshop.dao.GuitarDao;
import com.aca.guitarshop.dao.GuitarDaoImpl;
import com.aca.guitarshop.model.Brand;
import com.aca.guitarshop.model.Guitar;
import com.aca.guitarshop.model.PublishMessage;
import com.aca.guitarshop.model.RequestError;
import com.aca.guitarshop.model.SubscribeMessage;
import com.aca.guitarshop.service.AwsSnsPublic;
import com.aca.guitarshop.service.AwsSnsSubscription;

import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.Response;
// providing the logic
public class GuitarService {

	private GuitarDao guitarDao = new GuitarDaoImpl();

	public List<Guitar> getReport(Integer startReleaseYear, Integer endReleaseYear) {
//	still got to validate this, the code is in report RESTful API with Jersey
		return guitarDao.getReport(startReleaseYear, endReleaseYear);
	}

	public Guitar deleteGuitar(Integer guitarId) {
		return guitarDao.deleteGuitar(guitarId);
	}

	public Guitar updateGuitar(Guitar updateGuitar) {
		validateReleaseYear(updateGuitar.getReleaseYear());
		return guitarDao.updateGuitar(updateGuitar);
	}

	public Guitar createGuitar(Guitar newGuitar) {
		validateReleaseYear(newGuitar.getReleaseYear());

		Guitar guitars = guitarDao.createGuitar(newGuitar);
		String message = "A new Guitar model '" + guitars.getModel() + "' is now available";
		AwsSnsPublic.publishMessage("New Guitar Alert", message);

		return guitars;
	}

	public List<Guitar> getGuitar() {
		return guitarDao.getGuitars();
	}

	public List<Guitar> getGuitarByBrand(Brand brand) {
		return guitarDao.getGuitarsByBrand(brand);
	}

	public List<Guitar> getGuitarById(Integer guitarId) {
		validateGuitarId(guitarId);
		List<Guitar> myGuitar = guitarDao.getGuitarsById(guitarId);
		if (myGuitar.size() == 0) {
			makeError(3, "Invalid value for guitar id. Value '" + guitarId + "'does not exist");
		}
		return myGuitar;
	}

	public List<Guitar> getGuitarsByReleaseYear(Integer releaseYear) {
		validateReleaseYear(releaseYear);
		return guitarDao.getGuitarsByReleaseYear(releaseYear);
	}

	public List<Guitar> getGuitarsByModel(String model) {
		validateModel(model);
		return guitarDao.getGuitarsByModel(model);
	}

	private void validateModel(String model) { // needs to be validated

	}

	public List<Guitar> getGuitarsByBodyStyle(String bodyStyle) {
		validateBodyStyle(bodyStyle);
		return guitarDao.getGuitarsByBodyStyle(bodyStyle);
	}

	private void validateBodyStyle(String bodyStyle) { // needs to be validateddddddd

	}

	private void validateGuitarId(Integer guitarId) {
		if (guitarId < 9 || guitarId > 45) {
			makeError(2, "Invalid value for guitar id. Value mus be > 9 and < 45.");
		}
	}

	private void validateReleaseYear(Integer releaseYear) {
		if (releaseYear < 1935 || releaseYear > 2025) {
			makeError(1, "Invalid value for release year. Value must be > 1935 and < 2025");
		}
	}

	private void makeError(Integer id, String message) {
		RequestError error = new RequestError(id, message);
		Response response = Response.status(400).entity(error).build();
		throw new WebApplicationException(response);
	}

	public PublishMessage publishMessage(PublishMessage publishMessage) {
		AwsSnsPublic.publishMessage(publishMessage.getSubject(), publishMessage.getMessage());
		return publishMessage;
	}

	public SubscribeMessage subscribe(SubscribeMessage subscribeMessage) {

		if (subscribeMessage.getIsEmail()) {
			AwsSnsSubscription.subscribeEmail(subscribeMessage.getEndpoint());
		} else {
			AwsSnsSubscription.subscribePhoneNumber(subscribeMessage.getEndpoint());
		}

		return subscribeMessage;
	}

}
