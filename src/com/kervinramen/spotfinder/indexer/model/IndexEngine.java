package com.kervinramen.spotfinder.indexer.model;

import com.google.appengine.repackaged.org.json.JSONArray;
import com.google.appengine.repackaged.org.json.JSONException;
import com.google.appengine.repackaged.org.json.JSONObject;
import com.kervinramen.spotfinder.base.model.FacebookUser;
import com.kervinramen.spotfinder.base.model.FacebookUsers;
import com.restfb.DefaultJsonMapper;
import com.restfb.JsonMapper;
import com.restfb.types.NamedFacebookType;
import com.restfb.types.StatusMessage;
import com.restfb.types.User;

/**
 * This class takes care of indexing basically, it retrieves the list of
 * FacebookUsers from the database and computes the number of links between each
 * one of them
 * 
 * @author Kervin Ramen
 * 
 */
public class IndexEngine {

    public void start() {
        FacebookUsers users = new FacebookUsers();
        users.getAllUsers();

        
        int count = 0;
        for (FacebookUser user : users.users) {
            this.indexFeed( user.getFeedGraph());
            count++;
             
            if (count>3) {
                break;
            }
        }
    }

    private void indexFeed(JSONObject jsonObject) {

        JSONArray feed = new JSONArray();

        try {
            feed = jsonObject.getJSONArray("data");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        for (int i = 0; i < feed.length(); i++) {
            try {
                JSONObject feedData = feed.getJSONObject(i);
                
                this.indexData(feedData.toString(), feedData.getString("type"));

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    private NamedFacebookType indexData(String feedData, String type) {
        NamedFacebookType retValue = null;
        JsonMapper jsonMapper = new DefaultJsonMapper();
        
        if (type.equals("User")) {    
            retValue = jsonMapper.toJavaObject(feedData, User.class);
        } else if (type.equals("Status")) {
            retValue = jsonMapper.toJavaObject(feedData, StatusMessage.class);
        }

        return retValue;
    }

}
