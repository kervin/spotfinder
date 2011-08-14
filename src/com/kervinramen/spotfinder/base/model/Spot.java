package com.kervinramen.spotfinder.base.model;

import javax.jdo.PersistenceManager;
import javax.jdo.annotations.Extension;
import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import com.google.appengine.api.datastore.Entity;
import com.kervinramen.spotfinder.helpers.PMF;

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
@PersistenceCapable
public class Spot {

    @PrimaryKey
    @Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
    @Extension(vendorName="datanucleus", key="gae.encoded-pk", value="true")
    private String encodedKey;

    
    /**
     * The long part of the key
     */
    @Persistent
    @Extension(vendorName="datanucleus", key="gae.pk-id", value="true")
    private Long spotId;
    /** 
     * Name of the spot
     */
    @Persistent
	private String name;

	/**
	 * Gmaps location
	 */
    @Persistent
	private String location;

	/**
	 * Some brief description
	 */
	private String description;
	
	/** 
	 * Image of the spot, 
	 * place in /media/
	 */
	@Persistent
	private String image;

    
    public Long getSpotId() {
        return this.spotId;
    }
    
    public void setSpotId(Long value) {
        this.spotId = value;
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

    public Spot() {
    }
    
    /**
     * Saves this object to database
     */
    public void save() {
        PersistenceManager pm = PMF.get().getPersistenceManager();
        
        try {
            pm.makePersistent(this);
        } finally {
            pm.close();
        }
    }


    /**
     * Parses a database entity into a spot
     * 
     * @param result
     */
    public void parseEntity(Entity result) {
        this.spotId = result.getKey().getId();
        this.name = (String) result.getProperty("name");
        this.description = (String) result.getProperty("description");
        this.location = (String) result.getProperty("location");
        this.image = (String) result.getProperty("image");
        
    }

    public void update() {

        
    }
}
