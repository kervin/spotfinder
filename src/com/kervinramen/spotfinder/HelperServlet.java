package com.kervinramen.spotfinder;

import java.io.IOException;
import javax.servlet.http.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kervinramen.DAL.DataAccess;
import com.kervinramen.entities.FacebookUser;
import com.kervinramen.utilities.Utility;

public class HelperServlet extends HttpServlet {
	   
	   
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		resp.setContentType("text/plain");
		
		if (req.getParameter("cmd").compareTo("1") == 0) {
			resp.getWriter().println(
					"access_token=189720214400755|jM8Ea6-5-REGx4jWN9fTtJp06LU");
		}

		if (req.getParameter("cmd").compareTo("2") == 0) {
			String tokenUrl = "http://localhost:8888/helper?cmd=1";
		
			String accessToken = Utility.getStringResponse(tokenUrl);
			
			resp.getWriter().println(accessToken);
			
			

		}

	}

	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		resp.setContentType("text/plain");

	}

}
