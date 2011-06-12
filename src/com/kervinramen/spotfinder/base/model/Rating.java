package com.kervinramen.spotfinder.base.model;

/**
 * Rating class stores the rating of the spots by the users
 * 
 * @author Kervin Ramen
 *
 */
public class Rating {

    /**
     * UserId of the Facebook User
     */
    private int userId;
    
    /**
     * SpotId of the spot
     */
    private int spotId;
    
    /**
     * Rating of the user for this spot
     */
    private int rating;

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getUserId() {
        return userId;
    }

    public void setSpotId(int spotId) {
        this.spotId = spotId;
    }

    public int getSpotId() {
        return spotId;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public int getRating() {
        return rating;
    }
    
    
    public Rating() {
        
    }
}
