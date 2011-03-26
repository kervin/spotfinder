package com.kervinramen.entities;

public class App {

	private String appId = "189720214400755";
	private String appKey = "e14a34fa3bf641ea83d9b118b0b0a604";
	private String appSecret = "1cac42f937ba7b9998e195ef416d0f79";
	private String appUrl = "http://myspotfinder.appspot.com/question";
	
	public String getAppId() {
		return this.appId;
	}
	
	public String getAppSecret() {
		return this.appSecret;
	}
	
	public String getAppUrl() {
		return this.appUrl;
	}
	
	public String getDialogUrl() {
        String dialogUrl = "http://www.facebook.com/dialog/oauth?"
        	+ "client_id="  + this.appId 
        	+ "&redirect_uri=" + this.appUrl;
        
        return dialogUrl;
	}
	
	public App() {
		
	}
}
