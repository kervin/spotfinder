package com.kervinramen.spotfinder;

import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kervinramen.utilities.Utility;

public class KevinaServlet extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		resp.setContentType("text/xml");
		resp.getWriter().println("" +
				"<?xml version='1.0' encoding='Windows-1252'?>" +
				"<data>" +
				"<user>" +
				"<firstname>Kevina</firstname><lastname>Choolhun</lastname>" +
				"</user>" +
				"<user>" +
				"<name>Kervin</name>" +
				"<lastname>Ramen</lastname>" +
				"</user>" +
				"</data>");

	}

}
