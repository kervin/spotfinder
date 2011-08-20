package com.kervinramen.spotfinder.finder.model;

import java.awt.geom.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Logger;

import com.google.appengine.repackaged.org.joda.time.DateTime;
import com.kervinramen.spotfinder.base.model.*;

public class Finder {

    // Maximum distance of interest spots in km
    private final double maxDistance = 5;

    // Earth radius in km
    private final double earthRadius = 6371.0;

    private FacebookUser getFacebookUser(String username) {
        FacebookUser user = new FacebookUser();
        user.searchUser(username);
        return new FacebookUser();
    }

    private ArrayList<Spot> getSpots() {
        Spots allSpots = new Spots();
        allSpots.getAllSpots();

        return allSpots.spot;
    }

    /*
     * public Spots search(FacebookUser user) { return getSpots(); }
     */

    public Spots search() {
        return search(getFacebookUser("kervin.ramen"), "20", "-57");
    }

    public Spots search(FacebookUser userId, String userLat, String userLng) {
        ArrayList<Spot> nearbySpots = getNearbySpots(Double.valueOf(userLat), Double.valueOf(userLng));

        Spots spots = new Spots();
        spots.setSpots(nearbySpots);

        return spots;
    }

    private ArrayList<Spot> getRatedSpots(String userId, ArrayList<Spot> nearbySpots) {

        ArrayList<FacebookUser> friends = FacebookUsers.getFriends(userId);

        for (Spot spot : nearbySpots) {

        }

        return null;
    }

    /**
     * Implementation of Haversine formula
     * 
     * @param lat1
     *            Latitude of point 1
     * @param lng1
     *            Longitude of point 1
     * @param lat2
     *            Latitude of point 2
     * @param lng2
     *            Longitude of point 2
     * 
     * @return distance between the two points
     */
    public double distFrom(double lat1, double lng1, double lat2, double lng2) {

        double dLat = Math.toRadians(lat2 - lat1);
        double dLng = Math.toRadians(lng2 - lng1);

        double a = Math.sin(dLat / 2) * Math.sin(dLat / 2) + Math.cos(Math.toRadians(lat1))
                * Math.cos(Math.toRadians(lat2)) * Math.sin(dLng / 2) * Math.sin(dLng / 2);

        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        double dist = earthRadius * c;

        return dist;
    }

    public ArrayList<Spot> getNearbySpots(Double userLat, Double userLng) {
        ArrayList<Spot> nearbySpots = new ArrayList<Spot>();

        ArrayList<Spot> allSpots = this.getSpots();

        for (Spot spot : allSpots) {

            String[] spotLocation = spot.getLocation().split(",");

            try {
                Double spotLat = Double.valueOf(spotLocation[0]);
                Double spotLng = Double.valueOf(spotLocation[1]);

                if (distFrom(userLat, userLng, spotLat, spotLng) < maxDistance) {
                    nearbySpots.add(spot);
                }
            } catch (Exception e) {
                Logger log = Logger.getLogger("Finder");
                log.severe(e.getStackTrace().toString());
                
            }
        }

        return allSpots;

    }

    public Double getDaysElapsed(Date lastDate) {
        long MILLSECS_PER_DAY = 24 * 60 * 60 * 1000;

        // today
        Date today = new Date();

        return (double) ((today.getTime() - lastDate.getTime()) / MILLSECS_PER_DAY);
    }

    public Double getDecayedScore(Double score, Date lastDate) {
        Double daysElapsed = getDaysElapsed(lastDate);

        Double halflife = (double) 10;
        Double decayConstant = Math.log(2) / halflife;
        Double decayedScore = score * Math.exp(-decayConstant * daysElapsed);

        return decayedScore;
    }
}