package com.kervinramen.spotfinder.indexer.model;

import com.kervinramen.spotfinder.base.model.FacebookUser;
import com.restfb.DefaultJsonMapper;
import com.restfb.types.User;
import java.util.Calendar;

/**
 * Compares just the attributes of the users
 * 
 * @author Kervin Ramen
 *
 */
public class CompareInfo implements ICompare {

	public CompareInfo() {
		
	}
	
	public User getInfoGraph(String username) {
		FacebookUser facebookUser = new FacebookUser();
		facebookUser.searchUser(username);
		String infoGraph = facebookUser.getInfoGraphString();	
		
		DefaultJsonMapper mapper = new DefaultJsonMapper();
		User user = (User)mapper.toJavaObject(infoGraph, User.class);
		return user;
	}
	
	public int calculateScore(String username1, String username2) {
		
		User user1 = getInfoGraph(username1);
		User user2 = getInfoGraph(username2);
		
		int score = 0; 
		if (user1.getGender() == user2.getGender()) score += 1;
		if (user1.getHometown() == user2.getHometown()) score += 1;
		if (user1.getHometown() == user2.getHometown()) score += 1;
		
		Calendar user1Birth = Calendar.getInstance();
		user1Birth.setTime(user1.getBirthdayAsDate());
		
		Calendar user2Birth = Calendar.getInstance();
        user2Birth.setTime(user2.getBirthdayAsDate());
        
		
		if (user1Birth.get(Calendar.YEAR) == user1Birth.get(Calendar.YEAR)) score += 1;
		
		return score;
		
	}
	
	public int calculateKervin() {
		return calculateScore("kervin.ramen", "kevina.choolhun");
	}

    
    public int getToCount(FacebookUser user1, FacebookUser user2) {
        // TODO Auto-generated method stub
        return 0;
    }

    
    public int getFromCount(FacebookUser user1, FacebookUser user2) {
        // TODO Auto-generated method stub
        return 0;
    }

    
    public int getSameCount(FacebookUser user1, FacebookUser user2) {
        // TODO Auto-generated method stub
        return 0;
    }
	
}
