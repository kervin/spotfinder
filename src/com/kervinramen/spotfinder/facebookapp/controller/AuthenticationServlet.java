package com.kervinramen.spotfinder.facebookapp.controller;

import java.io.IOException;
import javax.servlet.http.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kervinramen.spotfinder.facebookapp.model.App;

@SuppressWarnings("serial")
public class AuthenticationServlet extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		resp.setContentType("text/plain");
		resp.getWriter().println("Hello, there");
	}

	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		resp.setContentType("text/plain");

		App app = new App("");
		String authUrl = app.getAuthenticationUrl();

		resp.sendRedirect(authUrl);

	}

}
