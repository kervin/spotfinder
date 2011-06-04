package com.kervinramen.spotfinder.facebookapp.controller;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.repackaged.org.json.JSONObject;
import com.kervinramen.spotfinder.base.model.FacebookUser;
import com.kervinramen.spotfinder.facebookapp.model.App;

@SuppressWarnings("serial")
public class QuestionServlet extends HttpServlet {


	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {

		resp.setContentType("text/plain");
		resp.getWriter().println("You have successfully been authenticated!!");

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

		resp.getWriter().println("Hello there, " + user.getUsername());

	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		doGet(request, response);
	}

}
