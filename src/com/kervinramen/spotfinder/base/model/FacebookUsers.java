package com.kervinramen.spotfinder.base.model;

import java.util.ArrayList;
import java.util.List;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.FetchOptions;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;
import com.kervinramen.spotfinder.indexer.model.UserIndex;

public class FacebookUsers {

	public ArrayList<FacebookUser> users;

	public ArrayList<FacebookUser> getUsers() {
		return this.users;
	}

	public void setUsers(ArrayList<FacebookUser> value) {
		this.users = value;
	}

	public FacebookUsers() {
		this.users = new ArrayList<FacebookUser>();
	}

	
    public static ArrayList<FacebookUser> getFriends(String userId) {
        ArrayList<FacebookUser> retValue = new ArrayList<FacebookUser>();

        // Get the Datastore Service
        DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();

        // The Query interface assembles a query
        Query q = new Query("FacebookUser");
        q.addFilter("userId", Query.FilterOperator.EQUAL, userId);

        datastore.prepare(q);
        List<Entity> entities = datastore.prepare(q).asList(FetchOptions.Builder.withDefaults());

        if (!entities.isEmpty()) {
            for (Entity entity : entities) {
                retValue.add(new FacebookUser(entity));                
            }
        }

        return retValue;
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
