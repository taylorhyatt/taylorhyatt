package com.aca.guitarshop.model;

public enum StringBrand {
	
	Ernie_Ball, Daddario, Elixir, Martin;


	public static StringBrand convertStringToStringBrand(String value) {
		StringBrand myStringBrand = null;
		for (StringBrand stringBrand : StringBrand.values()) {
			if (stringBrand.toString().equalsIgnoreCase(value)) {
				myStringBrand = stringBrand;
				break;
			}
		}
		
		
		return myStringBrand;
	}
	
	
}
