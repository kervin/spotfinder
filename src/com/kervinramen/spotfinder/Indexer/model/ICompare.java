package com.kervinramen.spotfinder.Indexer.model;

import com.kervinramen.spotfinder.base.model.FacebookUser;

public interface ICompare {


    /**
     * Returns the number of links to the 
     * user1 from user 2
     *
     * @param user1
     * @param user2
     */
    public int getToCount(FacebookUser user1, FacebookUser user2);
    
    
    /**
     * 
     * Returns the number of links to the 
     * user1 from user 2
     * 
     * @param user1
     * @param user2
     */
    public int getFromCount(FacebookUser user1, FacebookUser user2);
    
    
    /**
     * 
     * Returns the number of links that user1 and user2
     * had on the same thread
     * 
     * @param user1
     * @param user2
     */
    public int getSameCount(FacebookUser user1, FacebookUser user2);
}
