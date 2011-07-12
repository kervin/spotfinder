package com.kervinramen.spotfinder.facebookapp.controller;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.repackaged.org.json.JSONObject;
import com.kervinramen.spotfinder.base.model.FacebookUser;
import com.kervinramen.spotfinder.base.model.Spots;
import com.kervinramen.spotfinder.facebookapp.model.App;

@SuppressWarnings("serial")
public class QuestionServlet extends HttpServlet {

    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String testMode = req.getParameter("testmode");
        if (testMode == null) {
            String code = req.getParameter("code");
            App app = new App(code);

            if (code == null || code.isEmpty()) {
                // Redirect in case of error
                resp.sendRedirect(app.getDialogUrl());
            }

            // Queries the graph for information
            JSONObject userInfo = app.getBasicGraph();

            FacebookUser user = new FacebookUser();
            user.setAccessToken(app.getAccessToken());
            user.setUserId(userInfo.optString("id"));
            user.setUsername(userInfo.optString("username"));
            user.setInfoGraph(userInfo);
            user.setFeedGraph(app.getFeedGraph());
            user.setHomeFeedGraph(app.getHomeGraph());

            user.save();

            // save userid in session
            req.getSession().setAttribute("userid", user.getUserId());
            
        } else { 
            req.getSession().setAttribute("userid", "614080403");
        }

        // show questions to the user
        Spots spots = new Spots();
        spots.getAllSpots();

        // Setting the users obj to the jsp
        req.setAttribute("spots", spots.spots);

        RequestDispatcher dispatcher = null;
        dispatcher = req.getRequestDispatcher("/views/facebookapp/question.jsp");
        
        try {
            if (dispatcher != null)
                dispatcher.forward(req, resp);
        } catch (Exception ex) {
            resp.getWriter().append(ex.getMessage());
        }

    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        doGet(request, response);
    }

}
