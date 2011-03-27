package com.kervinramen.entities;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import java.util.Date;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.Text;
import com.google.appengine.repackaged.org.json.JSONObject;

import com.kervinramen.utilities.Utility;


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
	private String username;
	
	@Persistent
	private String accessToken;

	@Persistent
	private Date createdOn;
	
	@Persistent
	private Text basicGraph;
	
	public void setId(Key id) {
		this.id = id;
	}

	public Key getId() {
		return id;
	}
	
	public String getUsername() {
		return this.username;
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
	
	public void setBasicGraph(JSONObject value){
		this.basicGraph = new Text(value.toString());
	}
	
	public JSONObject getBasicGraph() {
		return Utility.getJSON(this.basicGraph.toString());
	}
	
	// Constructor
	
	public FacebookUser() {
		this.createdOn = new Date();
	}
	
	public FacebookUser(String username, String accessToken) {
		this.username = username;
		this.accessToken = accessToken;
		this.createdOn = new Date();
			   
		   
	}


}
