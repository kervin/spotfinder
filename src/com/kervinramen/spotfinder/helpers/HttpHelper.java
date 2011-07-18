package com.kervinramen.spotfinder.helpers;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.net.URL;

public class HttpHelper {

	/**
	 * Sends a GET Request
	 * 
	 * @return string response
	 */
	public static String getResponse(String address) {
		String response = new String();
		
		try {
			//String encodedurl = URLEncoder.encode(address,"UTF-8");
			
			URL url = new URL(address);
			Object stream = url.getContent();
			ByteArrayInputStream bais = (ByteArrayInputStream) stream;
			response = Utilities.BytetoString(bais);

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
			response = Utilities.convertStreamToString(bais);


		} catch (Exception ex) {
			response = ex.getMessage();
		}

		return response;
	}
	
}
