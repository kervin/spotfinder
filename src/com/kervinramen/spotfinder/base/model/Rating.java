package com.kervinramen.spotfinder.base.model;

import java.util.ArrayList;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.FetchOptions;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.Query;
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
    private long userId;
    
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

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public long getUserId() {
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

    public Integer getrate() {
        return rate;
    }
    
    
    public Rating() {
        
    }
    
    public Rating(int rate, long userId, int spotId) {
        this.rate = rate;
        this.userId = userId;
        this.spotId = spotId;
    }
    
    public Rating(Entity result) {
        this.id = (Key) result.getProperty("id");
        this.userId = (Long) (result.getProperty("userId"));
        this.spotId = Integer.valueOf(result.getProperty("spotId").toString());
        this.rate =  Integer.valueOf(result.getProperty("rate").toString());
    }
    
    
    public static Rating getRating(long userId, long spotId) {
        Rating retValue = new Rating();

        // Get the Datastore Service
        DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();

        // The Query interface assembles a query
        Query q = new Query("Rating");
        q.addFilter("userId", Query.FilterOperator.EQUAL, userId);
        q.addFilter("spotId", Query.FilterOperator.EQUAL, spotId);

        datastore.prepare(q);
        List<Entity> entities = datastore.prepare(q).asList(FetchOptions.Builder.withDefaults());

        if (!entities.isEmpty()) {
            for (Entity entity : entities) {
              retValue = new Rating(entity);
              return retValue;
            }
        }

        return null;
           
    }
    
    
    public static ArrayList<Rating> getAllRating() {
        ArrayList<Rating> retValue = new  ArrayList<Rating>();

        // Get the Datastore Service
        DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();

        // The Query interface assembles a query
        Query q = new Query("Rating");

        datastore.prepare(q);
        List<Entity> entities = datastore.prepare(q).asList(FetchOptions.Builder.withDefaults());

        if (!entities.isEmpty()) {
            for (Entity entity : entities) {
              Rating rating = new Rating(entity);
              retValue.add(rating);
            }
        }

        return retValue;
           
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
