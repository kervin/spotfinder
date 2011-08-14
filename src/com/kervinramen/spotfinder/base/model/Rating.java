package com.kervinramen.spotfinder.base.model;

import javax.jdo.PersistenceManager;
import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import com.google.appengine.api.datastore.Key;
import com.kervinramen.spotfinder.helpers.PMF;

/**
 * Rating class stores the rating of the spots by the users
 * 
 * @author Kervin Ramen
 *
 */
@PersistenceCapable
public class Rating {
    
    @SuppressWarnings("unused")
    @PrimaryKey
    @Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
    private Key id;
    
    /**
     * UserId of the Facebook User
     */
    @Persistent
    private int userId;
    
    /**
     * SpotId of the spot
     */
    @Persistent
    private int spotId;
    
    /**
     * Rating of the user for this spot
     */
    @Persistent
    private int rate;

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getUserId() {
        return userId;
    }

    public void setSpotId(int spotId) {
        this.spotId = spotId;
    }

    public int getSpotId() {
        return spotId;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }

    public int getrate() {
        return rate;
    }
    
    
    public Rating() {
        
    }
    
    public Rating(int rate, int userId, int spotId) {
        this.rate = rate;
        this.userId = userId;
        this.spotId = spotId;
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
}
