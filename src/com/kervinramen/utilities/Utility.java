package com.kervinramen.utilities;

import java.io.ByteArrayInputStream;
import java.net.URL;

public class Utility {
	
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
	 * @param address url of the site
	 * @return string
	 */
	public static String getStringResponse(String address) {
		
		String response = new String();
		try {
			URL url = new URL(address);
			response = url.getContent().toString();
			
		} catch (Exception ex) {
			response = ex.getMessage();
		}

		return response;
	}

}