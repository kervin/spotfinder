package com.kervinramen.spotfinder.finder.model;

import com.kervinramen.spotfinder.facebookapp.model.FacebookUser;
import com.restfb.DefaultJsonMapper;
import com.restfb.types.User;

public class InfoCompare {

	public InfoCompare() {
		
	}
	
	public User getInfoGraph(String username) {
		FacebookUser facebookUser = new FacebookUser();
		facebookUser.searchUser(username);
		String infoGraph = facebookUser.getInfoGraphString();	
		
		DefaultJsonMapper mapper = new DefaultJsonMapper();
		User user = (User)mapper.toJavaObject(infoGraph, User.class);
		return user;
	}
	
	public void calculateScore(String username1, String username2) {
		
		User user1 = getInfoGraph(username1);
		User user2 = getInfoGraph(username2);
		
		int score = 0; 
		if (user1.getGender() == user2.getGender()) score += 1;
		if (user1.getHometown() == user2.getHometown()) score += 1;
		if (user1.getHometown() == user2.getHometown()) score += 1;
		
	}
	
	public void calculateKervin() {
		calculateScore("kervin.ramen", "kevina.choolhun");
	}
	
}
