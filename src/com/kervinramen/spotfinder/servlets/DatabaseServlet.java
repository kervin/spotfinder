package com.kervinramen.spotfinder.servlets;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.kervinramen.spotfinder.facebookapp.model.*;

@SuppressWarnings("serial")
public class DatabaseServlet extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {

		RequestDispatcher dispatcher = null;

		if (req.getPathInfo().compareTo("/") == 0) {
			FacebookUsers users = new FacebookUsers();
			users.getAllUsers();

			// Setting the users obj to the jsp
			req.setAttribute("users", users);

			dispatcher = req.getRequestDispatcher("/views/database/viewall.jsp");

		}

		if (req.getPathInfo().compareTo("/add/") == 0) {
			dispatcher = req.getRequestDispatcher("/views/database/add.jsp");
		}

		try {
			if (dispatcher != null)
				dispatcher.forward(req, resp);
		} catch (Exception ex) {
			resp.getWriter().append(ex.getMessage());
		}

	}

	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		if (req.getPathInfo().compareTo("/add/new/") == 0) {
			FacebookUser user = new FacebookUser();
			user.setUserId(req.getParameter("userid"));
			user.setUsername(req.getParameter("username"));
			user.setInfoGraph(req.getParameter("infograph"));
			user.setFeedGraph(req.getParameter("feedgraph"));
			user.setHomeFeedGraph(req.getParameter("homefeedgraph"));
			user.setAccessToken("test");

			user.save();
		}

		resp.getWriter().append("user Saved");
		resp.getWriter().append("<a href='/database/user/'>view all</a>");
	}

}
