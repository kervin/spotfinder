package com.kervinramen.spotfinder.Indexer.model;

import javax.jdo.PersistenceManager;
import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import com.google.appengine.api.datastore.Key;
import com.kervinramen.spotfinder.helpers.PMF;

/**
 * This object stores the relationship between users
 * 
 * @author Kervin Ramen
 *
 */
public class UserIndex {

    // Attributes

    @PrimaryKey
    @Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
    private Key id;

    /**
     * First user. This user's relationship is compare with user2
     */
    private int userId1;

    /**
     * The relationship strength with user1
     */
    private int userId2;

    /**
     * Number of actions of user2 to user1
     */
    private int toCount;
    
    /**
     * Number of actions towards user2 from user1
     */
    private int fromCount;
    
    /**
     * The number of action user1 and user1 did on the same thread
     */
    private int sameCount;
    
    public Key getId() {
        return id;
    }

    public void setUserId1(int userId1) {
        this.userId1 = userId1;
    }

    public int getUserId1() {
        return userId1;
    }

    public void setUserId2(int userId2) {
        this.userId2 = userId2;
    }

    public int getUserId2() {
        return userId2;
    }
    
    public void setToCount(int toCount) {
        this.toCount = toCount;
    }

    public int getToCount() {
        return toCount;
    }

    public void setFromCount(int fromCount) {
        this.fromCount = fromCount;
    }

    public int getFromCount() {
        return fromCount;
    }

    public void setSameCount(int sameCount) {
        this.sameCount = sameCount;
    }

    public int getSameCount() {
        return sameCount;
    }

    public UserIndex() {

    }
    
    /**
     * Saves the userindex
     */
    public void save() {

        PersistenceManager pm = PMF.get().getPersistenceManager();

        try {
            pm.makePersistent(this);
        } finally {
            pm.close();
        }
    }

}
