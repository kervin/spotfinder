package com.kervinramen.spotfinder;

import java.io.IOException;
import javax.servlet.http.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.repackaged.org.json.JSONException;
import com.google.appengine.repackaged.org.json.JSONObject;
import com.kervinramen.utilities.Utility;

public class HelperServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		resp.setContentType("text/plain");

		if (req.getParameter("cmd").compareTo("1") == 0) {
			resp.getWriter().println(
					"access_token=189720214400755|jM8Ea6-5-REGx4jWN9fTtJp06LU");
		}

		if (req.getParameter("cmd").compareTo("2") == 0) {
			String tokenUrl = "http://localhost:8888/helper?cmd=3";
			String userInformation = Utility.getStringResponse(tokenUrl);
			
			JSONObject user = null;

			try {
				user = new JSONObject(userInformation);
				resp.getWriter().println("Hello " + user.get("name"));
			} catch (JSONException e) {
				resp.getWriter().println("Error " + e.getMessage());
			}
			
		}

		if (req.getParameter("cmd").compareTo("3") == 0) {
			String responseJSON = "{  'id': '614080403',   'name': 'Kervin Ramen',"
					+ "'first_name': 'Kervin',"
					+ "'last_name': 'Ramen',"
					+ "'link': 'http://www.facebook.com/kervin.ramen',"
					+ "'username': 'kervin.ramen',"
					+ "'hometown': {"
					+ "  'id': '108445785852108',"
					+ "  'name': 'Belle \u00c9toile, Plaines Wilhems, Mauritius'} }";
			
			resp.getWriter().println(responseJSON);
		}

	}

	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		resp.setContentType("text/plain");

	}

}
