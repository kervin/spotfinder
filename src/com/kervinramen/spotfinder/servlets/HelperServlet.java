package com.kervinramen.spotfinder.servlets;

import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import remoteapiexample.RemoteApiExample;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;

import java.io.IOException;

import com.google.appengine.repackaged.org.json.JSONException;
import com.google.appengine.repackaged.org.json.JSONObject;
import com.google.appengine.tools.remoteapi.*;

import com.kervinramen.spotfinder.facebookapp.model.FacebookUser;
import com.kervinramen.spotfinder.helpers.HttpHelper;

public class HelperServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		resp.setContentType("text/plain");

		if (req.getParameter("cmd").compareTo("1") == 0) {
			resp.getWriter().println(
					"access_token=189720214400755|jM8Ea6-5-REGx4jWN9fTtJp06LU");
			resp.getWriter().println(
					"classpath=" + System.getProperty("java.class.path"));
		}

		if (req.getParameter("cmd").compareTo("2") == 0) {
			String tokenUrl = "http://localhost:8888/helper?cmd=3";
			String userInformation = HttpHelper.getStringResponse(tokenUrl);

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
					+ "'id': '108445785852108',"
					+ "'name': 'Belle \u00c9toile, Plaines Wilhems, Mauritius'} }";

			resp.getWriter().println(responseJSON);
		}

		if (req.getParameter("cmd").compareTo("4") == 0) {
			String info = HttpHelper
					.getResponse("https://graph.facebook.com/me?access_token=2227470867|2.ChW5v1sV5Im1i3L6jgwLuQ__.3600.1301940000-614080403|pX4D-3prlazqiCt6Jl8WQhyVbs0");

			resp.getWriter().println(info);
		}

		if (req.getParameter("cmd").compareTo("5") == 0) {
			String info = HttpHelper
					.getResponse("https://graph.facebook.com/me?access_token=2227470867|2.ChW5v1sV5Im1i3L6jgwLuQ__.3600.1301940000-614080403|pX4D-3prlazqiCt6Jl8WQhyVbs0");

			resp.getWriter().println(info);
		}
		
		if (req.getParameter("cmd").compareTo("live-db") == 0) {
			String password = req.getParameter("password");
			RemoteApiOptions options = new RemoteApiOptions().server(
					"myspotfinder.appspot.com", 443).credentials("kervin152",
					password);

			//RemoteApiInstaller installer = new RemoteApiInstaller();
			com.google.appengine.tools.remoteapi.RemoteApiOptions option = new RemoteApiOptions();
//			com.google.appengine.tools.remoteapi.RemoteApiInstaller instal = new RemoteApiInstaller();
			
			RemoteApiInstaller.makeDevAppServerCookie("test", "test");
			
			Throwable throwzs = new Throwable();
			
			com.google.appengine.tools.remoteapi.RemoteApiException ex = new RemoteApiException("test", "test", "test",throwzs);
			
			//installer.install(options);
			
			// resp.sendRedirect("/");

			
			resp.getWriter().println("hre");
		}

		if (req.getParameter("cmd").compareTo("store") == 0) {
			FacebookUser user = new FacebookUser();
			user.setFeedGraph("test");
			user.setUsername("kervin");
			user.save();
		}

	}

	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		resp.setContentType("text/plain");

	}

}
