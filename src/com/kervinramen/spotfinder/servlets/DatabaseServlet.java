package com.kervinramen.spotfinder.servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kervinramen.spotfinder.facebookapp.model.FacebookUsers;

public class DatabaseServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		
		if (req.getPathInfo().compareTo("/user") == 0) {
			FacebookUsers users = new FacebookUsers();
			users.getAllUsers();
			req.setAttribute("users", users);
			req.setAttribute("someValue", "you got the value!");

			RequestDispatcher dispatcher = req
					.getRequestDispatcher("views/database/viewall.jsp");

			try {
				if (dispatcher != null)
					dispatcher.forward(req, resp);
			} catch (Exception ex) {
				resp.getWriter().append(ex.getMessage());
			}

		}
		
		if (req.getPathInfo().compareTo("/user/add") == 0) {
			
		}
		

	}
}
