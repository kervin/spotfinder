package com.kervinramen.spotfinder.facebookapp.model;

import java.util.ArrayList;
import java.util.Date;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;
import com.google.appengine.api.datastore.Text;

public class FacebookUsers {

	public ArrayList<FacebookUser> users;

	public ArrayList<FacebookUser> getUsers() {
		return this.users;
	}

	public void setUsers(ArrayList<FacebookUser> value) {
		this.users = value;
	}

	public FacebookUsers() {

	}

	public void getAllUsers() {
		// Get the Datastore Service
		DatastoreService datastore = DatastoreServiceFactory
				.getDatastoreService();

		Query q = new Query("FacebookUser");

		// fetching results from the datastore
		PreparedQuery pq = datastore.prepare(q);

		for (Entity result : pq.asIterable()) {
			FacebookUser user = new FacebookUser();
			user.parseEntity(result);

			this.users.add(user);

		}
		
	}

}
