package com.kervinramen.spotfinder;

import javax.servlet.http.HttpServlet;
import java.io.IOException;
import javax.servlet.http.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kervinramen.entities.FacebookUser;
import com.kervinramen.DAL.*;

public class QuestionServlet extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		resp.setContentType("text/plain");
		resp.getWriter().println("You have successfully been authenticated!!");
		
		FacebookUser user = new FacebookUser("Kervin", "token");
		DataAccess.saveUser(user);
		
	}

}
