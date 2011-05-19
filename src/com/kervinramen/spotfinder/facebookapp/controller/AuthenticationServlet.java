package com.kervinramen.spotfinder.facebookapp.controller;

import java.io.IOException;
import javax.servlet.http.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kervinramen.spotfinder.facebookapp.model.App;

@SuppressWarnings("serial")
public class AuthenticationServlet extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		App app = new App("");
		String authUrl = app.getAuthenticationUrl();
		resp.getWriter().println("<script>top.location.href='" + authUrl + "'</script>");
	}

	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		resp.setContentType("text/html");

		App app = new App("");
		String authUrl = app.getAuthenticationUrl();
		req.setAttribute("authUrl", authUrl);
//		dispatcher = req.getRequestDispatcher("/views/facebookapp/authenticate.jsp");
		
		resp.getWriter().println("<script>top.location.href='" + authUrl + "'</script>");
		//resp.sendRedirect(authUrl);

	}

}
