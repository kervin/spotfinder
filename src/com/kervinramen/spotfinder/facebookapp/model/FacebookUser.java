package com.kervinramen.spotfinder.facebookapp.model;

import javax.jdo.PersistenceManager;
import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import java.util.Date;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.Text;
import com.google.appengine.repackaged.org.json.JSONObject;

import com.kervinramen.spotfinder.helpers.PMF;
import com.kervinramen.spotfinder.helpers.StringHelper;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;

/**
 * This class contains all the user information 
 * that is obtained from Facebook that is stored
 * @author kervin
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
	
	@Persistent
	// This graph stores the user information of the
	// user such as name, id, activities, jobs
	private Text infoGraph;
	
	@Persistent
	// This graph stored the user's feeds, all the status
	// updates of pic tags from the user
	private Text feedGraph;
	
	@Persistent
	// Friends' feed towards the user
	private Text homeFeedGraph;
	
	public void setId(Key id) 
	{
		this.id = id;
	}

	public Key getId() 
	{
		return id;
	}
	
	public String getUsername() 
	{
		return this.username;
	}

	public void setUserId(String userId) 
	{
		this.userId = userId;
	}

	public String getUserId() 
	{
		return userId;
	}

	public void setUsername(String value) 
	{
		this.username = value;
	}

	public String getAccessToken() 
	{
		return this.accessToken;
	}

	public void setAccessToken(String value) 
	{
		this.accessToken = value;
	}

	public void setCreatedOn(Date createdOn) 
	{
		this.createdOn = createdOn;
	}

	public Date getCreatedOn() 
	{
		return createdOn;
	}

	public void setInfoGraph(JSONObject value)
	{
		this.infoGraph = new Text(value.toString());
	}
	
	public JSONObject getInfoGraph() 
	{
		return StringHelper.getJSON(this.infoGraph.toString());
	}
	
	public void setFeedGraph(JSONObject value)
	{
		this.feedGraph = new Text(value.toString());
	}
	
	public void setFeedGraph(String value)
	{
		this.feedGraph = new Text(value);
	}
	
	public JSONObject getFeedGraph() 
	{
		return StringHelper.getJSON(this.feedGraph.toString());
	}
	
	public void setHomeFeedGraph(JSONObject value)
	{
		this.homeFeedGraph = new Text(value.toString());
	}
	
	public JSONObject getHomeFeedGraph() 
	{
		return StringHelper.getJSON(this.homeFeedGraph.toString());
	}
	
	// Constructor
	public FacebookUser() 
	{
		this.createdOn = new Date();
	}
	
	public FacebookUser(String username, String accessToken) 
	{
		this.username = username;
		this.accessToken = accessToken;
		this.createdOn = new Date();
	}
	
	public void save() 
	{
		PersistenceManager pm = PMF.get().getPersistenceManager();

		try {
			pm.makePersistent(this);
		} finally {
			pm.close();
		}

	}
	
	public void searchUser(String username) 
	{
		// Get the Datastore Service
		DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();

		// The Query interface assembles a query
		Query q = new Query("FacebookUser");
		q.addFilter("username", Query.FilterOperator.EQUAL, username);

		// PreparedQuery contains the methods for fetching query results
		// from the datastore
		PreparedQuery pq = datastore.prepare(q);

		for (Entity result : pq.asIterable()) {
		  this.id = (Key) result.getProperty("id");
		  this.userId = (String) result.getProperty("lastName");
		  this.username = (String) result.getProperty("username");
		  this.createdOn = (Date) result.getProperty("createdOn");
		  this.infoGraph = (Text) result.getProperty("infoGraph");
		  this.feedGraph = (Text) result.getProperty("feedGraph");
		  this.homeFeedGraph = (Text) result.getProperty("homeFeedGraph");
		  
		}
	}

	public void runQuery(String string) {

		// Get the Datastore Service
		//DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();

		// The Query interface assembles a query
		//Query q = new Query("FacebookUser");
		
		
		
	}

}