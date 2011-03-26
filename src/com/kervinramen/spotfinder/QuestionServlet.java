package com.kervinramen.spotfinder;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import java.io.IOException;
import javax.servlet.http.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.repackaged.org.json.JSONException;
import com.google.appengine.repackaged.org.json.JSONObject;
import com.kervinramen.entities.*;
import com.kervinramen.DAL.*;
import com.kervinramen.utilities.*;

public class QuestionServlet extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {

		resp.setContentType("text/plain");
		resp.getWriter().println("You have successfully been authenticated!!");

		App app = new App();

		String code = req.getParameter("code");

		if (code == null || code.isEmpty()) {
			// Redirect in case of error
			resp.sendRedirect(app.getDialogUrl());
		}

		String tokenUrl = "https://graph.facebook.com/oauth/access_token?"
				+ "client_id=" + app.getAppId() + "&redirect_uri="
				+ app.getAppUrl() + "&client_secret=" + app.getAppSecret()
				+ "&type=" + "client_cred" + "&code=" + code;

		FacebookUser facebookUser = new FacebookUser("tokenurl", tokenUrl);
		DataAccess.saveUser(facebookUser);

		String accessToken = Utility.getResponse(tokenUrl);

		String graphUrl = "https://graph.facebook.com/me?" + accessToken;

		String userInformation = Utility.getResponse(graphUrl);

		JSONObject user = null;

		try {
			user = new JSONObject(userInformation);
			resp.getWriter().println("Hello " + user.get("name"));
		} catch (JSONException e) {
			resp.getWriter().println("Error " + e.getMessage());
		}

	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		doGet(request, response);
	}

}
