package com.kervinramen.spotfinder.base.model;

import javax.jdo.PersistenceManager;
import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import java.util.Date;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.Text;
import com.google.appengine.repackaged.org.json.JSONObject;
import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;

import com.kervinramen.spotfinder.helpers.PMF;
import com.kervinramen.spotfinder.helpers.StringHelper;

/**
 * This class contains all the user information that is obtained from Facebook
 * that is stored
 * 
 * @author Kervin Ramen
 * 
 */
@PersistenceCapable
public class FacebookUser {

    // Attributes

    @PrimaryKey
    @Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
    private Key id;

    @Persistent
    private String userId;

    @Persistent
    private String username;

    @Persistent
    private String accessToken;

    @Persistent
    private Date createdOn;

    /**
     * Stores the user information, name, id, activities..
     */
    @Persistent
    public Text infoGraph;

    /**
     * Stores user's feeds (status,tags updates from the user)
     */
    @Persistent
    private Text feedGraph;

    /**
     * Stores friends feeds towards the user
     */
    @Persistent
    private Text homeFeedGraph;

    /**
     * Whether this user has been indexed.
     */
    @Persistent
    private Boolean isIndexed;

    /**
     * The time this user has last been indexed
     */
    @Persistent
    private Date lastIndexed;

    public void setId(Key id) {
        this.id = id;
    }

    public Key getId() {
        return id;
    }

    public String getUsername() {

        return this.username;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUsername(String value) {
        this.username = value;
    }

    public String getAccessToken() {
        return this.accessToken;
    }

    public void setAccessToken(String value) {
        this.accessToken = value;
    }

    public void setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
    }

    public Date getCreatedOn() {
        return createdOn;
    }

    public void setInfoGraph(JSONObject value) {
        this.infoGraph = new Text(value.toString());
    }

    public void setInfoGraph(String value) {
        this.infoGraph = new Text(value);
    }

    public JSONObject getInfoGraph() {
        return StringHelper.getJSON(this.infoGraph.toString());
    }

    public String getInfoGraphString() {
        if (this.infoGraph != null)
            return this.infoGraph.getValue();
        else
            return "";
    }

    public void setFeedGraph(JSONObject value) {
        this.feedGraph = new Text(value.toString());
    }

    public void setFeedGraph(String value) {
        this.feedGraph = new Text(value);
    }

    public JSONObject getFeedGraph() {
        return StringHelper.getJSON(this.feedGraph.getValue());
    }

    public String getFeedGraphString() {
        return this.feedGraph.getValue();
    }

    public void setHomeFeedGraph(JSONObject value) {
        this.homeFeedGraph = new Text(value.toString());
    }

    public void setHomeFeedGraph(String value) {
        if (value == null) {
            this.homeFeedGraph = null;
        } else {
            this.homeFeedGraph = new Text(value);
        }
    }

    public JSONObject getHomeFeedGraph() {
        return StringHelper.getJSON(this.homeFeedGraph.toString());
    }

    public String getHomeFeedGraphString() {
        if (this.homeFeedGraph != null)
            return this.homeFeedGraph.getValue();
        else
            return "";
    }

    public void setIndexed(Boolean indexed) {
        this.isIndexed = indexed;
        this.lastIndexed = new Date();
    }

    public Boolean getIndexed() {
        return this.isIndexed;
    }

    public Date getLastIndexed() {
        return this.lastIndexed;
    }

    /**
     * Constructor, sets the createdon to currect date
     */
    public FacebookUser() {
        this.createdOn = new Date();
    }

    /**
     * Constructor
     * 
     * @param username
     * @param accessToken
     */
    public FacebookUser(String username, String accessToken) {
        this.username = username;
        this.accessToken = accessToken;
        this.createdOn = new Date();
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
     * Searches for a particular user
     * 
     * @param username
     *            username of the user on facebook
     */
    public void searchUser(String username) {
        // Get the Datastore Service
        DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();

        // The Query interface assembles a query
        Query q = new Query("FacebookUser");
        q.addFilter("username", Query.FilterOperator.EQUAL, username);

        // PreparedQuery contains the methods for fetching query results
        // from the datastore
        PreparedQuery pq = datastore.prepare(q);

        for (Entity result : pq.asIterable()) {
            this.parseEntity(result);
        }
    }

    /**
     * Assigns an Entity to the current object
     * 
     * @param result
     */
    public void parseEntity(Entity result) {

        this.id = (Key) result.getProperty("id");
        this.userId = (String) result.getProperty("userId");
        this.username = (String) result.getProperty("username");
        this.createdOn = (Date) result.getProperty("createdOn");
        this.infoGraph = (Text) result.getProperty("infoGraph");
        this.feedGraph = (Text) result.getProperty("feedGraph");
        this.homeFeedGraph = (Text) result.getProperty("homeFeedGraph");

    }

}
