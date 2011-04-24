package com.kervinramen.spotfinder.helpers;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.io.Writer;
import java.io.StringWriter;
import java.io.BufferedReader;
import java.io.InputStreamReader;

import com.google.appengine.repackaged.org.json.JSONException;
import com.google.appengine.repackaged.org.json.JSONObject;

// TODO: make utility singleton and implement logging facilities
public class StringHelper {

	
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

	


}