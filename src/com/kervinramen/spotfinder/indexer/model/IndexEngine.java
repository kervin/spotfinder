package com.kervinramen.spotfinder.indexer.model;

import com.google.appengine.repackaged.org.json.JSONArray;
import com.google.appengine.repackaged.org.json.JSONException;
import com.google.appengine.repackaged.org.json.JSONObject;
import com.kervinramen.spotfinder.base.model.FacebookUser;
import com.kervinramen.spotfinder.base.model.FacebookUsers;
import com.restfb.DefaultJsonMapper;
import com.restfb.JsonMapper;
import com.restfb.types.*;

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
            this.indexFeed(user.getFeedGraph());
            count++;

            if (count > 3) {
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

    private void indexData(String feedData, String type) {

        Post post = this.getPost(feedData);

        
        if (post.getComments() != null && post.getComments().getData() != null) {
            for (Comment comment : post.getComments().getData()) {
                this.updateIndex(post.getFrom(), comment.getFrom());

            }
        }

    }

    /**
     * Updates the relationship between these users.
     * 
     * @param to
     *            The user that posted the message
     * @param from
     *            The user that commented on the post
     */
    private void updateIndex(NamedFacebookType to, NamedFacebookType from) {

        UserIndex userIndex = UserIndex.searchUserIndex(to.getId(), from.getId());

        if (userIndex == null) {
            // first interactions between these users.
            userIndex = new UserIndex(to.getId(), from.getId(), to.getName(), from.getName());
            userIndex.save();
        } else {
            userIndex.incrementCount();
            userIndex.save();
        }

    }

    /**
     * Converts a string to a Post Class
     * 
     * @param feedData
     * @return
     */
    private Post getPost(String feedData) {
        Post retValue = null;
        JsonMapper jsonMapper = new DefaultJsonMapper();

        retValue = jsonMapper.toJavaObject(feedData, Post.class);

        return retValue;
    }

    /*
     * private NamedFacebookType indexData(String feedData, String type) {
     * NamedFacebookType retValue = null; JsonMapper jsonMapper = new
     * DefaultJsonMapper();
     * 
     * if (type.equals("user")) { retValue = jsonMapper.toJavaObject(feedData,
     * Post.class); } else if (type.equals("status")) { retValue =
     * jsonMapper.toJavaObject(feedData, Post.class); } else if
     * (type.equals("link")) { retValue = jsonMapper.toJavaObject(feedData,
     * Post.class); }
     * 
     * return retValue; }
     */

}
