package com.aca.guitarshop.model;

public enum Brand {

Gibson, Ibanez, Epiphone, Fender;
	
	public static Brand convertStringToBrand(String value) {
		Brand myBrand = null;
		for (Brand brand : Brand.values()) {
			if (brand.toString().equalsIgnoreCase(value)) {
				myBrand = brand;
				break;
			}
		}
		
		
		return myBrand;
	}

}