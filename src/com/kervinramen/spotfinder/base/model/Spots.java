package com.kervinramen.spotfinder.base.model;

import java.util.ArrayList;

import javax.jdo.PersistenceManager;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.EntityNotFoundException;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;
import com.kervinramen.spotfinder.helpers.PMF;

public class Spots {

    public ArrayList<Spot> spots;

    public ArrayList<Spot> getSpots() {
        return this.spots;
    }

    public void setSpots(ArrayList<Spot> value) {
        this.spots = value;
    }

    public Spots() {
        this.spots = new ArrayList<Spot>();
    }
    
    public void getAllSpots() {
        // Get the Datastore Service
        DatastoreService datastore = DatastoreServiceFactory
                .getDatastoreService();

        Query q = new Query("Spot");

        // fetching results from the datastore
        PreparedQuery pq = datastore.prepare(q);

        for (Entity result : pq.asIterable()) {
            Spot spot = new Spot();
            spot.parseEntity(result);
            
            this.spots.add(spot);
        }
        
    }
    
    
    /**
     * Searches for a particular spot
     * 
     * @param spotId
     * @return
     */
    public static Spot getSpotById(Long spotId) {
        PersistenceManager pm = PMF.get().getPersistenceManager();
        Spot spot = pm.getObjectById(Spot.class, spotId);
        return spot;
        
        /*
          Query query = entityManager.createQuery(
            "SELECT m FROM MyEntity m WHERE id = :id");
                query.setParameter("id", yourId);

            MyEntity yourEntity = (MyEntity) query.getSingleResult();
          
         */
       /*
        Spot spot = new Spot();
        // Get the Datastore Service
        DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();

        // The Query interface assembles a query
        Query q = new Query("Spot");
        q.addFilter("spotId", Query.FilterOperator.EQUAL, spotId);

        // PreparedQuery contains the methods for fetching query results
        // from the datastore
        PreparedQuery pq = datastore.prepare(q);

        for (Entity result : pq.asIterable()) {
           
            spot.parseEntity(result);
        }
        */
        
       
    }
}
