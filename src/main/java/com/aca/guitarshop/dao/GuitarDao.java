package com.aca.guitarshop.dao;

import java.util.List;

import com.aca.guitarshop.model.Brand;
import com.aca.guitarshop.model.Guitar;

public interface GuitarDao {

	public List<Guitar> getGuitars();

	public List<Guitar> getGuitarsByBrand(Brand brand);

	public List<Guitar> getGuitarsByReleaseYear(Integer releaseYear);

	public List<Guitar> getGuitarsById(Integer guitarId);

	public List<Guitar> getGuitarsByBodyStyle(String bodyStyle);

	public List<Guitar> getGuitarsByModel(String model);

	public Guitar createGuitar(Guitar newGuitar);

	public Guitar updateGuitar(Guitar updateGuitar);

	public Guitar deleteGuitar(Integer GuitarId);

	public List<Guitar> getReport(Integer startReleaseYear, Integer endReleaseYear);

}
