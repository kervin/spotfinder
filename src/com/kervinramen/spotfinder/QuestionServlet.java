package com.kervinramen.spotfinder;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.repackaged.org.json.JSONObject;
import com.kervinramen.DAL.DataAccess;
import com.kervinramen.entities.*;

public class QuestionServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

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
		user.setUsername(userInfo.optString("username"));
		user.setBasicGraph(userInfo);
		
		DataAccess.saveUser(user);
		
		resp.getWriter().println("Hello there, " + user);

	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		doGet(request, response);
	}

}
