package com.kervinramen.spotfinder.indexer.model;

import java.util.ArrayList;
import java.util.Date;
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
import com.kervinramen.spotfinder.helpers.Utilities;
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
    private long userId;

    /**
     * The userId of the user that is commenting / liking the post or status
     */
    @Persistent
    private long friendId;

    /**
     * The username of the userthat made the post or status message
     */
    @Persistent
    private String userName;

    /**
     * The username of the user that commented on the message
     */
    @Persistent
    private String friendName;

    /**
     * Number of interactions between the FROM one user and TO the other user
     */
    @Persistent
    private int count;

    /**
     * Last interaction date
     */
    @Persistent
    private Date lastDate;

    /**
     * The score for the relationship strength
     */
    @Persistent
    private double score;

    /**
     * Last date when the interaction count was updated
     */
    @Persistent
    private Date lastModified;

    public Key getId() {
        return id;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public long getFriendId() {
        return friendId;
    }

    public void setUserIdFrom(long friendId) {
        this.friendId = friendId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUsernameTo(String userName) {
        this.userName = userName;
    }

    public String getFriendName() {
        return friendName;
    }

    public void setFriendName(String friendName) {
        this.friendName = friendName;
    }

    public void incrementCount(Date lastDate) {
        this.count += 1;
        if (this.lastDate.before(lastDate)) {
            this.lastDate = lastDate;
        }
    }

    public int getCount() {
        return this.count;
    }

    public void setLastDate(Date lastDate) {
        this.lastDate = lastDate;
    }

    public Date getLastDate() {
        return lastDate;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    public Date getLastModified() {
        return lastModified;
    }

    public void setLastModified(Date lastModified) {
        this.lastModified = lastModified;
    }

    public UserIndex() {

    }

    /**
     * Converts an Entity to a User Index object
     * 
     * @param entity
     */
    public UserIndex(Entity entity) {
        this.id = entity.getKey();

        // stored as long in db
        this.userId = (Long) (entity.getProperty("userId"));
        this.friendId = (Long) (entity.getProperty("friendId"));
        this.userName = (String) (entity.getProperty("userName"));
        this.friendName = (String) (entity.getProperty("friendName"));
        this.count = Utilities.safeLongToInt((Long) entity.getProperty("count"));
        this.score = (Double) (entity.getProperty("score"));
        this.lastDate = (Date) entity.getProperty("lastDate");
        this.lastModified = (Date) entity.getProperty("lastModified");
        
    }

    public UserIndex(String userId, String friendId, String userName, String friendName, Date lastDate) {
        this.userId = Long.valueOf(userId);
        this.friendId = Long.valueOf(friendId);
        this.userName = userName;
        this.friendName = friendName;
        this.lastDate = lastDate;
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

    public static UserIndex getIndex(long userId, long friendId) {

        // Get the Datastore Service
        DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();

        // The Query interface assembles a query
        Query q = new Query("UserIndex");
        q.addFilter("userId", Query.FilterOperator.EQUAL, userId);
        q.addFilter("friendId", Query.FilterOperator.EQUAL, friendId);

        datastore.prepare(q);
        List<Entity> entities = datastore.prepare(q).asList(FetchOptions.Builder.withDefaults());

        if (!entities.isEmpty()) {
            for (Entity entity : entities) {
                return new UserIndex(entity);
            }
        }

        return null;
           
    }
    
    public static ArrayList<UserIndex> getFriendsIndex(int friendId) {
        ArrayList<UserIndex> retValue = new ArrayList<UserIndex>();

        // Get the Datastore Service
        DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();

        // The Query interface assembles a query
        Query q = new Query("UserIndex");
        q.addFilter("friendId", Query.FilterOperator.EQUAL, friendId);

        datastore.prepare(q);
        List<Entity> entities = datastore.prepare(q).asList(FetchOptions.Builder.withDefaults());

        if (!entities.isEmpty()) {
            for (Entity entity : entities) {
                retValue.add(new UserIndex(entity));                
            }
        }

        return retValue;
           
    }

    
    public static int getTotal(long l) {
        int total = 0;

        // Get the Datastore Service
        DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();

        // The Query interface assembles a query
        Query q = new Query("UserIndex");
        q.addFilter("userId", Query.FilterOperator.EQUAL, l);

        datastore.prepare(q);
        
        List<Entity> entities = datastore.prepare(q).asList(FetchOptions.Builder.withDefaults());

        if (!entities.isEmpty()) {
            for (Entity entity : entities) {
                UserIndex index = new UserIndex(entity);
                total += index.getCount();
            }
        }

        return total;
           
    }

    
    public static ArrayList<UserIndex> getUserIndexes() {
        ArrayList<UserIndex> retValue = new ArrayList<UserIndex>();

        // Get the Datastore Service
        DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();

        // The Query interface assembles a query
        Query q = new Query("UserIndex");

        datastore.prepare(q);
        List<Entity> entities = datastore.prepare(q).asList(FetchOptions.Builder.withDefaults());

        if (!entities.isEmpty()) {
            for (Entity entity : entities) {
                retValue.add(new UserIndex(entity));                
            }
        }

        return retValue;
    }

    public static ArrayList<UserIndex> getUserIndexes(long userId) {
        ArrayList<UserIndex> retValue = new ArrayList<UserIndex>();

        // Get the Datastore Service
        DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();

        // The Query interface assembles a query
        Query q = new Query("UserIndex");
        q.addFilter("userId", Query.FilterOperator.EQUAL, userId);

        datastore.prepare(q);
        List<Entity> entities = datastore.prepare(q).asList(FetchOptions.Builder.withDefaults());

        if (!entities.isEmpty()) {
            for (Entity entity : entities) {
                retValue.add(new UserIndex(entity));                
            }
        }

        return retValue;
    }
    
    /**
     * Searches for a particular user
     * 
     * @param l
     *            userId of the user on facebook
     */
    public static UserIndex getUserIndex(long userId) {

        UserIndex retValue = null;

        // Get the Datastore Service
        DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();

        // The Query interface assembles a query
        Query q = new Query("UserIndex");
        q.addFilter("userId", Query.FilterOperator.EQUAL, userId);

        datastore.prepare(q);
        List<Entity> entities = datastore.prepare(q).asList(FetchOptions.Builder.withDefaults());

        if (!entities.isEmpty()) {
            retValue = new UserIndex(entities.get(0));
        }

        return retValue;
    }
    
    /**
     * Searches for on relationship
     * 
     * @param userId userid of the user 
     * @param friendId userid of the friend
     * 
     * @return
     */
    public static UserIndex searchUserIndex(Long userId, Long friendId) {

        UserIndex retValue = null;

        // Get the Datastore Service
        DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();

        // The Query interface assembles a query
        Query q = new Query("UserIndex");

        q.addFilter("userId", Query.FilterOperator.EQUAL, userId);
        q.addFilter("friendId", Query.FilterOperator.EQUAL, friendId);

        datastore.prepare(q);
        List<Entity> entities = datastore.prepare(q).asList(FetchOptions.Builder.withDefaults());

        if (!entities.isEmpty()) {
            retValue = new UserIndex(entities.get(0));
        }

        return retValue;
    }

}
