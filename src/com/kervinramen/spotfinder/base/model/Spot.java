package com.kervinramen.spotfinder.base.model;

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

    /**
     * Id of the spot
     */
    private int spotId;
    
    /** 
     * Name of the spot
     */
	private String name;

	/**
	 * Gmaps location
	 */
	private String location;

	/**
	 * Some brief description
	 */
	private String description;
	
	/** 
	 * Image of the spot, 
	 * place in /media/
	 */
	private String image;
	
	
	public void setSpotId(int spotId) {
        this.spotId = spotId;
    }

    public int getSpotId() {
        return spotId;
    }

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

	public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getImage() {
        return image;
    }

    public Spot(String name, String location) {
		this.name = name;
		this.location = location;

	}
}
