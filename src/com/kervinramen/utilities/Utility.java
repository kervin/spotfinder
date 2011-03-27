package com.kervinramen.utilities;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.io.Writer;
import java.net.URL;
import java.util.logging.Logger;
import java.io.StringWriter;
import java.io.BufferedReader;
import java.io.InputStreamReader;

import org.mortbay.log.Log;

import sun.util.logging.resources.logging;

import com.google.appengine.repackaged.org.json.JSONException;
import com.google.appengine.repackaged.org.json.JSONObject;

// TODO: make utility singleton and implement logging facilities
public class Utility {

	
	public static String convertStreamToString(InputStream is) throws IOException {
		/*
		 * To convert the InputStream to String we use the Reader.read(char[]
		 * buffer) method. We iterate until the Reader return -1 which means
		 * there's no more data to read. We use the StringWriter class to
		 * produce the string.
		 */
		if (is != null) {
			Writer writer = new StringWriter();

			char[] buffer = new char[1024];
			try {
				Reader reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
				int n;
				while ((n = reader.read(buffer)) != -1) {
					writer.write(buffer, 0, n);
				}
			} finally {
				is.close();
			}
			return writer.toString();
		} else {
			return "";
		}
	}

	public static String BytetoString(ByteArrayInputStream is) {
		int size = is.available();
		char[] theChars = new char[size];
		byte[] bytes = new byte[size];

		is.read(bytes, 0, size);
		for (int i = 0; i < size;)
			theChars[i] = (char) (bytes[i++] & 0xff);

		return new String(theChars);
	}

	
	/**
	 * Converts a String to a JSONObject
	 * @param value
	 * @return
	 */
	public static JSONObject getJSON(String value) {

		JSONObject user = new JSONObject();

		try {
			user = new JSONObject(value);
		} catch (JSONException e) {
			// log error
		}

		return user;
	}

	
	/**
	 * Sends a GET Request
	 * 
	 * @return string response
	 */
	public static String getResponse(String address) {
		String response = new String();
		try {
			URL url = new URL(address);
			Object stream = url.getContent();
			ByteArrayInputStream bais = (ByteArrayInputStream) stream;
			response = BytetoString(bais);

		} catch (Exception ex) {
			response = ex.getMessage();
		}

		return response;
	}

	/**
	 * Gets the response when for plain/text response
	 * 
	 * @param address
	 *            url of the site
	 * @return string
	 */
	public static String getStringResponse(String address) {

		String response = new String();
		try {
			URL url = new URL(address);
			Object stream = url.getContent();
			InputStream bais = (InputStream) stream;
			response = convertStreamToString(bais);


		} catch (Exception ex) {
			response = ex.getMessage();
		}

		return response;
	}

}