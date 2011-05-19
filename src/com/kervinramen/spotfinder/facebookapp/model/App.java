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
	private String appId = "220847734607402";
	private String appSecret = "151ca6ef6213be7a5608fb229ae82d4f";
	private String appUrl = "http://apps.facebook.com/spotfinder/question";
	private String accessToken;
	private String code;

	/**
	 * App needs a code to work.
	 * 
	 * @param code
	 *            Posted by Facebook
	 */
	public App(String code) {
		this.code = code;
	}

	// Methods

	/**
	 * This url is when the user is wrongly authenticated and thus redirected to
	 * login
	 * 
	 * @return
	 */
	public String getDialogUrl() {
		String dialogUrl = "http://www.facebook.com/dialog/oauth?" + "client_id=" + this.appId + "&redirect_uri="
				+ this.appUrl;

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
	private String queryAccessToken() {
		String tokenUrl = "https://graph.facebook.com/oauth/access_token?" + "client_id=" + this.appId
				+ "&redirect_uri=" + this.appUrl + "&client_secret=" + this.appSecret + "&code=" + this.code;

		this.accessToken = HttpHelper.getStringResponse(tokenUrl);
		return this.accessToken;
	}

	/**
	 * This url is the url fore requesting permissions
	 * 
	 * @return
	 */
	public String getAuthenticationUrl() {
		String url = "https://www.facebook.com/dialog/oauth?" + "client_id=" + this.appId + "&redirect_uri="
				+ this.appUrl + "&scope=user_photos,user_activities,read_stream";

		return url;

	}

	public String getAccessToken() {
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
		String accessToken = this.queryAccessToken();

		String graphUrl = "https://graph.facebook.com/me?" + accessToken;
		String userInformation = HttpHelper.getStringResponse(graphUrl);

		return StringHelper.getJSON(userInformation);
	}

	public JSONObject getFeedGraph() {
		// Gets the access Token
		String accessToken = this.queryAccessToken();

		String graphUrl = "https://graph.facebook.com/me/feed?" + accessToken;
		String userInformation = HttpHelper.getStringResponse(graphUrl);

		return StringHelper.getJSON(userInformation);
	}

	public JSONObject getHomeGraph() {
		// Gets the access Token
		String accessToken = this.queryAccessToken();

		String graphUrl = "https://graph.facebook.com/me/home?" + accessToken;
		String userInformation = HttpHelper.getStringResponse(graphUrl);

		return StringHelper.getJSON(userInformation);
	}

}
