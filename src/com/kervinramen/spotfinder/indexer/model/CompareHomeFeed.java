package com.kervinramen.spotfinder.indexer.model;

import org.jgrapht.EdgeFactory;
import org.jgrapht.graph.*;

import com.kervinramen.spotfinder.base.model.FacebookUser;
import com.restfb.DefaultJsonMapper;
import com.restfb.types.User;

/**
 * This class calculates the number of comments in bound to a user
 * @author Kervin Ramen
 *
 */
public class CompareHomeFeed implements ICompare {

    public User getHomeFeedGraph(String username) {
        FacebookUser facebookUser = new FacebookUser();
        facebookUser.searchUser(username);
        String homeGeedGraph = facebookUser.getHomeFeedGraphString();
        
        DefaultJsonMapper mapper = new DefaultJsonMapper();
        User user = (User)mapper.toJavaObject(homeGeedGraph, User.class);
        return user;
    }
    
    public SimpleGraph<String, String> graph;
    
    public void sandboxGraph() {
 
        ClassBasedEdgeFactory<String, String> edge = new ClassBasedEdgeFactory<String, String>(null);
        graph = new SimpleGraph<String, String>(edge);
        
    }

    
    public int getToCount(FacebookUser user1, FacebookUser user2) {
        // TODO Auto-generated method stub
        return 0;
    }

    
    public int getFromCount(FacebookUser user1, FacebookUser user2) {
        // TODO Auto-generated method stub
        return 0;
    }

    
    public int getSameCount(FacebookUser user1, FacebookUser user2) {
        // TODO Auto-generated method stub
        return 0;
    }
    
}
