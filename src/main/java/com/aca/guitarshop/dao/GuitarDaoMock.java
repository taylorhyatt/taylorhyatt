package com.aca.guitarshop.dao;

import java.util.ArrayList;
import java.util.List;

import com.aca.guitarshop.model.Guitar;
import com.aca.guitarshop.dao.GuitarDaoMock;
import com.aca.guitarshop.model.Brand;

public class GuitarDaoMock implements GuitarDao {

	private static List<Guitar> guitars = new ArrayList<Guitar>();

	private static Integer guitarNumber = 0;

	private static Integer getNextGuitarNumber() {
		guitarNumber++;
		return guitarNumber;
	}

	static {
		Guitar ibanez = new Guitar();
		ibanez.setId(getNextGuitarNumber());
		ibanez.setBrand(Brand.Ibanez);
		ibanez.setModel("IB450");
		ibanez.setBodyStyle("Les Paul");
		ibanez.setReleaseYear(1995);

		Guitar gibson = new Guitar();
		gibson.setId(getNextGuitarNumber());
		gibson.setBrand(Brand.Gibson);
		gibson.setModel("GR225");
		gibson.setBodyStyle("SG");
		gibson.setReleaseYear(2001);

		Guitar epiphone = new Guitar();
		epiphone.setId(getNextGuitarNumber());
		epiphone.setBrand(Brand.Epiphone);
		epiphone.setModel("EP100");
		epiphone.setBodyStyle("Archtop");
		epiphone.setReleaseYear(1999);

		Guitar fender = new Guitar();
		fender.setId(getNextGuitarNumber());
		fender.setBrand(Brand.Fender);
		fender.setModel("STF35");
		fender.setBodyStyle("Stratocaster");
		fender.setReleaseYear(1986);

		guitars.add(ibanez);
		guitars.add(gibson);
		guitars.add(epiphone);
		guitars.add(fender);

	}

	@Override
	public List<Guitar> getGuitars() {
		List<Guitar> myGuitars = new ArrayList<Guitar>();
		myGuitars.addAll(guitars);
		return myGuitars;

	}

	@Override
	public List<Guitar> getGuitarsByBrand(Brand brand) {
		List<Guitar> myGuitars = new ArrayList<Guitar>();
		for (Guitar guitar : GuitarDaoMock.guitars) {
			if (guitar.getBrand().equals(brand)) {
				myGuitars.add(guitar);
			}
		}
		return myGuitars;
	}

	@Override
	public List<Guitar> getGuitarsByReleaseYear(Integer releaseYear) {
		List<Guitar> myGuitars = new ArrayList<Guitar>();
		for (Guitar guitar : GuitarDaoMock.guitars) {
			if (guitar.getReleaseYear().intValue() == releaseYear.intValue()) {
				myGuitars.add(guitar);
			}
		}
		return myGuitars;

	}

	@Override
	public List<Guitar> getGuitarsById(Integer guitarId) {
		List<Guitar> myGuitar = new ArrayList<Guitar>();
		for (Guitar guitar : GuitarDaoMock.guitars) {
			if (guitar.getId().intValue() == guitarId.intValue()) {
				myGuitar.add(guitar);
				break;
			}
		}
		return myGuitar;
	}

	@Override
	public List<Guitar> getGuitarsByBodyStyle(String bodyStyle) {
		List<Guitar> myGuitar = new ArrayList<Guitar>();
		for (Guitar guitar : GuitarDaoMock.guitars) {
			if (guitar.getBodyStyle().toString() == bodyStyle.toString()) {
				myGuitar.add(guitar);
				break;
			}
		}

		return myGuitar;
	}

	@Override
	public Guitar createGuitar(Guitar newGuitar) {
		newGuitar.setId(getNextGuitarNumber());
		guitars.add(newGuitar);
		return newGuitar;
	}

	@Override
	public Guitar updateGuitar(Guitar updateGuitar) {

		// find the guitar to update by id
		for (Guitar guitar : guitars) {
			if (guitar.getId() == updateGuitar.getId()) {
				// replace it
				guitar.setBrand(updateGuitar.getBrand());
				guitar.setModel(updateGuitar.getModel());
				guitar.setReleaseYear(updateGuitar.getReleaseYear());
				break;
			}

		}

		return updateGuitar;
	}

	@Override
	public Guitar deleteGuitar(Integer guitarId) {
		Guitar foundGuitar = null;

		for (Guitar guitar : guitars) {
			if (guitar.getId() == guitarId) {
				guitars.remove(guitar);
				break;
			}
		}

		return foundGuitar;
	}

	@Override
	public List<Guitar> getReport(Integer startReleaseYear, Integer endReleaseYear) {
		List<Guitar> myGuitars = new ArrayList<Guitar>();

		for (Guitar guitar : guitars) {
			if (guitar.getReleaseYear() >= startReleaseYear) {
				if (guitar.getReleaseYear() <= endReleaseYear) {
					myGuitars.add(guitar);
				}
			}
		}

		return myGuitars;
	}

	@Override
	public List<Guitar> getGuitarsByModel(String model) {
		List<Guitar> myGuitars = new ArrayList<Guitar>();
		for (Guitar guitar : GuitarDaoMock.guitars) {
			if (guitar.getModel().toString() == model.toString()) {
				myGuitars.add(guitar);
				break;
			}
		}

		return myGuitars;
	}

}
