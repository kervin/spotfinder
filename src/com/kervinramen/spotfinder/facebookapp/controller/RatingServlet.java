package com.kervinramen.spotfinder.facebookapp.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kervinramen.spotfinder.base.model.Rating;

public class RatingServlet extends HttpServlet {

    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        int rate = Integer.parseInt(req.getParameter("rate"));
        int spotId = Integer.parseInt(req.getParameter("spotid"));
        int userId = Integer.parseInt((String) req.getSession().getAttribute("userid"));

        Rating rating = new Rating(rate, userId, spotId);
        rating.save();
        
        resp.setContentType("text/plain");
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        doGet(request, response);
    }

}
