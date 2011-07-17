package com.kervinramen.spotfinder.indexer.model;

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
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;
import com.kervinramen.spotfinder.helpers.PMF;

/**
 * This object stores the relationship between users
 * 
 * @author Kervin Ramen
 *
 */
@PersistenceCapable
public class UserIndex {

    // Attributes

    @PrimaryKey
    @Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
    private Key id;

    /**
     * The userid of the user that made the post or status message
     */
    @Persistent
    private int userIdTo;

    /**
     * The userId of the user that is commenting / liking the post or status
     */
    @Persistent
    private int userIdFrom;
    
    /**
     * The username of the userthat made the post or status message
     */
    @Persistent
    private String usernameTo;
    
    /**
     * The username of the user that commented on the message
     */
    @Persistent
    private String usernameFrom;

    /**
     * Number of interactions between the FROM one user and TO the other user
     */
    @Persistent
    private int count;
    
//    /**
//     * Number of actions of user2 to user1
//     */
//    private int toCount;
//    
//    /**
//     * Number of actions towards user2 from user1
//     */
//    private int fromCount;
//    
//    /**
//     * The number of action user1 and user1 did on the same thread
//     */
//    private int sameCount;
    
    public Key getId() {
        return id;
    }


    public int getUserIdTo() {
        return userIdTo;
    }

    public void setUserIdTo(int userIdTo) {
        this.userIdTo = userIdTo;
    }

    public int getUserIdFrom() {
        return userIdFrom;
    }

    public void setUserIdFrom(int userIdFrom) {
        this.userIdFrom = userIdFrom;
    }

    public String getUsernameTo() {
        return usernameTo;
    }

    public void setUsernameTo(String usernameTo) {
        this.usernameTo = usernameTo;
    }

    public String getUsernameFrom() {
        return usernameFrom;
    }

    public void setUsernameFrom(String usernameFrom) {
        this.usernameFrom = usernameFrom;
    }
   
    public void incrementCount() {
        this.count += 1;
    }
    
    public int getCount() {
        return this.count;
    }
    
    
//    public void setToCount(int toCount) {
//        this.toCount = toCount;
//    }
//
//    public int getToCount() {
//        return toCount;
//    }
//
//    public void setFromCount(int fromCount) {
//        this.fromCount = fromCount;
//    }
//
//    public int getFromCount() {
//        return fromCount;
//    }
//
//    public void setSameCount(int sameCount) {
//        this.sameCount = sameCount;
//    }
//
//    public int getSameCount() {
//        return sameCount;
//    }

    public UserIndex() {

    }
    
    public UserIndex(String userIdTo, String userIdFrom, String usernameTo, String usernameFrom) {
        this.userIdTo = Integer.parseInt(userIdTo);
        this.userIdFrom = Integer.parseInt(userIdFrom);
        this.usernameTo = usernameTo;
        this.usernameFrom = usernameFrom;
        this.count = 1;
    }
    
    /**
     * Saves the userindex
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
     * Searches for a particular user
     * 
     * @param userId userId of the user on facebook
     */
    public static UserIndex searchUserIndex(String userIdTo, String userIdFrom) {
        UserIndex userIndex = null;
        
        // Get the Datastore Service
        DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();

        // The Query interface assembles a query
        Query q = new Query("UserIndex");
        q.addFilter("userIdTo", Query.FilterOperator.EQUAL, userIdTo);
        q.addFilter("userIdFrom", Query.FilterOperator.EQUAL, userIdFrom);
        //q.addSort(propertyName)
        
        // PreparedQuery contains the methods for fetching query results
        // from the datastore
        PreparedQuery pq = datastore.prepare(q);

       
        
        for (Entity result : pq.asIterable()) {
           // this.parseEntity(result);
            
            String some;
        }
        List<Entity> greetings = datastore.prepare(q).asList(FetchOptions.Builder.withDefaults());
        
        
        
        return userIndex;
    }

}
