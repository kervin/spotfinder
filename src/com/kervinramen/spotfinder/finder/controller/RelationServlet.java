package com.kervinramen.spotfinder.finder.controller;

import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kervinramen.spotfinder.facebookapp.model.FacebookUser;
import com.kervinramen.spotfinder.finder.model.HomeFeedCompare;

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
		
		user.runQuery("");
		
		HomeFeedCompare compare = new HomeFeedCompare();
		compare.sandboxGraph();
		
	}

}
