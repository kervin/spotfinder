package com.kervinramen.spotfinder.finder.model;

import com.kervinramen.spotfinder.base.model.FacebookUser;
import com.kervinramen.spotfinder.base.model.Spots;

public class Finder {

    private FacebookUser getFacebookUser(String username) 
    {
        FacebookUser user = new FacebookUser();
        user.searchUser(username);
        return new FacebookUser();
    }
    
    public Spots getSpots() 
    { 
        Spots allSpots = new Spots();
        allSpots.getAllSpots();
        
        return allSpots;
    }
    
    public Spots search(FacebookUser user) 
    {
        return getSpots();
    }
    
    public Spots search()
    {
        return search(getFacebookUser("kervin.ramen"));
    }
    
}