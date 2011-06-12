package com.kervinramen.spotfinder.indexer.model;

import com.kervinramen.spotfinder.base.model.FacebookUser;
import com.kervinramen.spotfinder.base.model.FacebookUsers;

/**
 * This class takes care of indexing basically, it retrieves
 * the list of FacebookUsers from the database and computes
 * the number of links between each one of them
 * 
 * @author Kervin Ramen
 *
 */
public class IndexEngine {
    
    public void start() {
        FacebookUsers users = new FacebookUsers();
        users.getAllUsers();
        
        for (FacebookUser user1 : users.users) {
            for (FacebookUser user2 : users.users) {
                
            }
        }
        
    }

}
