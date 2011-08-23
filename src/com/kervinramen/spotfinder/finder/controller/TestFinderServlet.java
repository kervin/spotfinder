package com.kervinramen.spotfinder.finder.controller;

import java.io.IOException;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Collections;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import com.kervinramen.spotfinder.base.model.FacebookUser;
import com.kervinramen.spotfinder.base.model.Rating;
import com.kervinramen.spotfinder.base.model.Spot;
import com.kervinramen.spotfinder.base.model.Spots;
import com.kervinramen.spotfinder.finder.model.Finder;

@SuppressWarnings("serial")
public class TestFinderServlet extends HttpServlet {

    private FacebookUser getFacebookUser(String username) {
        FacebookUser user = new FacebookUser();
        user.searchUser(username);
        return user;
    }
    
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        String username = "kervin.ramen";
        
        Finder finder = new Finder();
        Spots spots = finder.search(username);
        
        FacebookUser user = getFacebookUser(username);
        
        ArrayList<Spot> top10Spot =  new ArrayList<Spot>();
        
        int count = 1; 
        for (Spot spot : spots.getSpots()) {
            top10Spot.add(spot);
            count++;
            
            if (count >10) {
                break;
            }
        }
        
        Spots allSpots = new Spots();
        allSpots.getAllSpots();
        
        
        ArrayList<Spot> ratedSpots = new ArrayList<Spot>();
        
        for (Spot spot : allSpots.spot) {
            Rating spotRating = Rating.getRating(user.getLongUserId(), spot.getSpotId());
            
            if (spotRating != null) {
                spot.setRank((double)spotRating.getrate());
                ratedSpots.add(spot);
            }
            
            
        }
        
        Collections.sort(ratedSpots);
        
        ArrayList<Spot> top10RatedSpot =  new ArrayList<Spot>();
        count = 1; 
        for (Spot spot : ratedSpots) {
            top10RatedSpot.add(spot);
            count++;
            
            if (count >10) {
                break;
            }
        }
        
        resp.getWriter().write(user.getUsername() + "   ");
        resp.getWriter().write(user.getUserId() + "   ");
        resp.getWriter().write(getRecall(top10Spot, top10RatedSpot).toString());

        
    }
    
    
    private Integer getRecall(ArrayList<Spot> ratedSpots, ArrayList<Spot> rankedSpots) {
        int count = 0;
        
        for (Spot rankedSpot : rankedSpots) {
            for (Spot ratedSpot : ratedSpots) {
                if (rankedSpot.getSpotId().compareTo(ratedSpot.getSpotId()) == 0) {
                       count++;
                }
                
            }
        }
        
        return count;
    }
    
    
    
}
