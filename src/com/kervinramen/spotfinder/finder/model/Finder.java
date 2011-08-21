package com.kervinramen.spotfinder.finder.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Logger;

import com.kervinramen.spotfinder.base.model.FacebookUser;
import com.kervinramen.spotfinder.base.model.Rating;
import com.kervinramen.spotfinder.base.model.Spot;
import com.kervinramen.spotfinder.base.model.Spots;
import com.kervinramen.spotfinder.helpers.Utilities;
import com.kervinramen.spotfinder.indexer.model.UserIndex;

public class Finder {

    // Maximum distance of interest spots in km
    private final double maxDistance = 70;

    private ArrayList<Spot> getSpots() {
        Spots allSpots = new Spots();
        allSpots.getAllSpots();

        return allSpots.spot;
    }

    /*
     * public Spots search(FacebookUser user) { return getSpots(); }
     */

    public Spots search() {
        return search("kervin.ramen", "-20", "57");
    }

    /**
     * Returns a context-aware results
     * 
     * @param userId
     * @param userLat
     * @param userLng
     * @return
     */
    public Spots search(String username, String userLat, String userLng) {
        ArrayList<Spot> nearbySpots = getNearBySpots(Double.valueOf(userLat), Double.valueOf(userLng));
        ArrayList<Spot> rankedSpots = getRankedSpots(username, nearbySpots);

        Spots spots = new Spots();
        spots.setSpots(rankedSpots);

        return spots;
    }

    private FacebookUser getFacebookUser(String username) {
        FacebookUser user = new FacebookUser();
        user.searchUser(username);
        return user;
    }

    /**
     * Gets the updates score
     * 
     * @param username
     *            The Facebook username
     * @param nearbySpots
     *            The Spots that are near to the user
     * @return
     */
    private ArrayList<Spot> getRankedSpots(String username, ArrayList<Spot> nearbySpots) {

        ArrayList<Spot> rankedSpots = new ArrayList<Spot>();

        // Get the Facebook info for the username passed
        FacebookUser user = getFacebookUser(username);

        // Get all the indexes for the user. All the friends that she had
        // interaction with
        ArrayList<UserIndex> indexes = UserIndex.getUserIndexes(user.getLongUserId());

        // For each spot
        for (Spot spot : nearbySpots) {
            Double totalRating = 0.0;
            int count = 0;

            // For each friend
            for (UserIndex index : indexes) {

                // Get the ratin of the friend for this spot
                Rating rating = Rating.getRating(index.getFriendId(), spot.getSpotId());

                if (rating != null) {
                    // Caculate the totalRank
                    totalRating = (rating.getrate() * index.getScore());
                }
                count++;
            }

            // Calcuate the mean rank
            Double rank = totalRating / count;

            // Set the rank for the spot
            spot.setRank(rank);

            rankedSpots.add(spot);
        }

        return rankedSpots;
    }

    /**
     * Gets spots near the user
     * 
     * @param userLat
     * @param userLng
     * @return
     */
    private ArrayList<Spot> getNearBySpots(Double userLat, Double userLng) {
        ArrayList<Spot> nearbySpots = new ArrayList<Spot>();

        ArrayList<Spot> allSpots = this.getSpots();

        for (Spot spot : allSpots) {

            String[] spotLocation = spot.getLocation().split(",");

            try {
                Double spotLat = Double.valueOf(spotLocation[0]);
                Double spotLng = Double.valueOf(spotLocation[1]);

                if (Utilities.distFrom(userLat, userLng, spotLat, spotLng) < maxDistance) {
                    nearbySpots.add(spot);
                }
            } catch (Exception e) {
                Logger log = Logger.getLogger("Finder");
                log.severe(e.getStackTrace().toString());

            }
        }

        return nearbySpots;

    }

    /**
     * Returns a decayed score
     * 
     * @param score
     * @param lastDate
     * @return
     */
    public Double getDecayedScore(Double score, Date lastDate) {
        Double daysElapsed = Utilities.getDaysElapsed(lastDate);

        Double halflife = (double) 10;
        Double decayConstant = Math.log(2) / halflife;
        Double decayedScore = score * Math.exp(-decayConstant * daysElapsed);

        return decayedScore;
    }
}