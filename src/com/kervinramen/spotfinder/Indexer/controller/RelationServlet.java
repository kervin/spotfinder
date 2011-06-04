package com.kervinramen.spotfinder.Indexer.controller;

import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kervinramen.spotfinder.Indexer.model.CompareHomeFeed;
import com.kervinramen.spotfinder.base.model.FacebookUser;

@SuppressWarnings("serial")
public class RelationServlet extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		
		FacebookUser user = new FacebookUser();
		user.searchUser("kervin.ramen");
				
		resp.getWriter().println(user.getCreatedOn());
		resp.getWriter().println(user.getInfoGraph().toString());
		resp.getWriter().println(user.getFeedGraph().toString());
		resp.getWriter().println(user.getHomeFeedGraph().toString());
		
		
	}

}
