package com.kervinramen.entities;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import com.google.appengine.api.datastore.Key;



@PersistenceCapable
public class FacebookUser {
	@PrimaryKey
	@Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
	private Key id;
	
	@Persistent
	private String name;
	@Persistent
	private String accessToken;

	public String getName() {
		return this.name;
	}

	public void setName(String value) {
		this.name = value;
	}

	public String getAccessToken() {
		return this.accessToken;
	}

	public void setAccessToken(String value) {
		this.accessToken = value;
	}

	public FacebookUser() {

	}

	public FacebookUser(String name, String accessToken) {
		this.name = name;
		this.accessToken = accessToken;
	}
}
