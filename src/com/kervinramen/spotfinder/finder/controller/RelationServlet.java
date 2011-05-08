package com.kervinramen.spotfinder.finder.controller;

import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kervinramen.spotfinder.facebookapp.model.FacebookUser;

public class RelationServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		
		FacebookUser user = new FacebookUser();
		user.searchUser("kervin.ramen");
				
		resp.getWriter().println(user.getCreatedOn());
		resp.getWriter().println(user.getInfoGraph().toString());
		resp.getWriter().println(user.getFeedGraph().toString());
		resp.getWriter().println(user.getHomeFeedGraph().toString());
		
		user.runQuery("");
		
	}

}
