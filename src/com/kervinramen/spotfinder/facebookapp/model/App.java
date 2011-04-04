package com.kervinramen.spotfinder.facebookapp.model;

import com.google.appengine.repackaged.org.json.JSONObject;
import com.kervinramen.spotfinder.helpers.HttpHelper;
import com.kervinramen.spotfinder.helpers.StringHelper;

/**
 * This class caters for interaction with facebook It contains all the
 * information from the dashboard of facebook It also queries facebook for
 * information
 * 
 * @author kervin
 * 
 */
public class App {

	// Attributes
	private String appId = "189720214400755";
	private String appKey = "e14a34fa3bf641ea83d9b118b0b0a604";
	private String appSecret = "1cac42f937ba7b9998e195ef416d0f79";
	private String appUrl = "http://apps.facebook.com/places-kervin/question";
	private String accessToken;
	private String code;
	
	public String getAppId() {
		return this.appId;
	}

	public String getAppSecret() {
		return this.appSecret;
	}

	public String getAppUrl() {
		return this.appUrl;
	}

	public String getAppKey() {
		return this.appKey;
	}

	/**
	 * App needs a code to work.
	 * @param code Posted by Facebook
	 */
	public App(String code) {
		this.code = code;
	}
	

	// Methods 
	
	/**
	 * Returns the authentication dialog
	 * 
	 * @return
	 */
	public String getDialogUrl() {
		String dialogUrl = "http://www.facebook.com/dialog/oauth?"
				+ "client_id=" + this.appId + "&redirect_uri=" + this.appUrl;

		return dialogUrl;
	}

	/**
	 * Returns the access token for querying the graph It is the access token
	 * that gives permission for any query
	 * 
	 * @param code
	 *            Posted by Facebook on our application for use
	 * @return
	 */
	private String getAccessToken() {
		String tokenUrl = "https://graph.facebook.com/oauth/access_token?"
				+ "client_id=" + this.getAppId() + "&redirect_uri="
				+ this.getAppUrl() + "&client_secret=" + this.getAppSecret()
				+ "&code=" + this.code;
		this.accessToken = HttpHelper.getStringResponse(tokenUrl);
		return this.accessToken;
	}

	/**
	 * The basic graph containing the user information
	 * 
	 * @param accessToken
	 *            permission token
	 * @return
	 */
	public JSONObject getBasicGraph() {
		// Gets the access Token
		String accessToken = this.getAccessToken();

		String graphUrl = "https://graph.facebook.com/me?" + accessToken;
		String userInformation = HttpHelper.getStringResponse(graphUrl);

		return StringHelper.getJSON(userInformation);
	}

}
