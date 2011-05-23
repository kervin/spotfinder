package com.kervinramen.spotfinder.facebookapp.model;

/**
 * This class stores the places that will have ratings on Spotfinder. 
 * Each place will be an instance of this class
 * 
 * This may evolve into a dynamic class in the future where the places 
 * are fetched from a web service.
 * 
 * @author Kervin Ramen
 * 
 */
public class Spot {

	private String name;

	// determined by Google maps, maybe
	private String location;

	public String getName() {
		return this.name;
	}

	public void setName(String value) {
		this.name = value;
	}

	public String getLocation() {
		return this.location;
	}

	public void setLocation(String value) {
		this.location = value;
	}

	public Spot(String name, String location) {
		this.name = name;
		this.location = location;

	}
}
