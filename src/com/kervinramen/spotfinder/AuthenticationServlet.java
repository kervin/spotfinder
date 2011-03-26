package com.kervinramen.spotfinder;

import java.io.IOException;
import javax.servlet.http.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AuthenticationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		resp.setContentType("text/plain");
		resp.getWriter().println("Hello, there");
	}

	
	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		resp.setContentType("text/plain");
		
		
		resp.sendRedirect("https://www.facebook.com/dialog/oauth?"
				+ "client_id=189720214400755"
				+ "&redirect_uri=http://apps.facebook.com/places-kervin/question"
				+ "&scope=user_photos,user_activities");
		
	}

}
