package com.kervinramen.spotfinder.facebookapp.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kervinramen.spotfinder.base.model.FacebookUser;
import com.kervinramen.spotfinder.base.model.Spot;
import com.kervinramen.spotfinder.base.model.Spots;

public class SpotServlet extends HttpServlet {

    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        RequestDispatcher dispatcher = null;
        
        if (req.getPathInfo().compareTo("/add/") == 0) {
            dispatcher = req.getRequestDispatcher("/views/facebookapp/spot/add.jsp"); 
        }
        
        if(req.getPathInfo().compareTo("/") == 0) {
            String spotId =(String)req.getParameter("spotid");
            
            if (spotId == null) {
                Spots spots = new Spots();
                spots.getAllSpots();
                
                // Setting the users obj to the jsp
                req.setAttribute("spots", spots.spots);
                
                dispatcher = req.getRequestDispatcher("/views/facebookapp/spot/viewall.jsp");
            } else {
                Spot spot = Spots.getSpotById(Long.parseLong(spotId));
                // Setting the users obj to the jsp
                req.setAttribute("spot", spot);
                
                dispatcher = req.getRequestDispatcher("/views/facebookapp/spot/edit.jsp");
            }
            
        }
        
        try {
            if (dispatcher != null)
                dispatcher.forward(req, resp);
        } catch (Exception ex) {
            resp.getWriter().append(ex.getMessage());
        }

    }

    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        
        if (req.getPathInfo().compareTo("/add/new/") == 0) {
            Spot spot = new Spot();
            //spot.setSpotId(Integer.parseInt(req.getParameter("spotid")));
            spot.setName(req.getParameter("name"));
            spot.setDescription(req.getParameter("description"));
            spot.setLocation(req.getParameter("location"));
            spot.setImage(req.getParameter("image"));

            spot.save();
        }

        resp.getWriter().append("user Saved");
    }

}
