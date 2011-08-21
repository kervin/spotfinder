package com.kervinramen.spotfinder.indexer.model;

import java.util.Date;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.repackaged.org.json.JSONArray;
import com.google.appengine.repackaged.org.json.JSONException;
import com.google.appengine.repackaged.org.json.JSONObject;
import com.kervinramen.spotfinder.base.model.FacebookUser;
import com.kervinramen.spotfinder.base.model.FacebookUsers;
import com.restfb.DefaultJsonMapper;
import com.restfb.JsonMapper;
import com.restfb.types.Comment;
import com.restfb.types.NamedFacebookType;
import com.restfb.types.Post;

/**
 * This class takes care of indexing, it retrieves the list of FacebookUsers
 * from the database reads the feed of the users and computes the number of
 * links between each one of the users
 * 
 * @author Kervin Ramen
 * 
 */
public class Parser {

    /**
     * Starts the indexing process
     */
    public void start() {

        FacebookUsers users = new FacebookUsers();
        users.getAllUsers();

        int count = 0;
        for (FacebookUser user : users.users) {
            this.indexFeed(user.getId(), user.getFeedGraph());

            this.indexFeed(user.getId(), user.getHomeFeedGraph());

            // TODO: remove this
            // for dev purposes
            count++;

            if (count > 3) {
                // break;
            }
        }
    }

    /**
     * Indexes a feed which is an array of data (post, status message)
     * 
     * @param key
     * 
     * @param jsonObject
     */
    private void indexFeed(Key key, JSONObject jsonObject) {

        JSONArray feed = new JSONArray();
        if (jsonObject.has("data")) {
            try {
                feed = jsonObject.getJSONArray("data");
            } catch (JSONException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
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
    }

    /**
     * Indexes data (one post / status message)
     * 
     * @param feedData
     * @param type
     */
    private void indexData(String feedData, String type) {

        Post post = this.getPost(feedData);

        // TODO: Make comments count
        // Make likes count (or the same??)
        if (post.getComments() != null && post.getComments().getData() != null) {
            for (Comment comment : post.getComments().getData()) {
                this.updateIndex(post.getFrom(), comment.getFrom(), comment.getCreatedTime());

            }
        }
        
        if (post.getLikes() != null && post.getLikes().getData() != null) {
            for (NamedFacebookType like : post.getLikes().getData()) {
                 this.updateIndex(post.getFrom(), like, new Date(0));
            }
        }

        // TODO: Use the other feeds...
    }

    /**
     * Updates the relationship between these users.
     * 
     * @param to
     *            The user that posted the message
     * @param from
     *            The user that commented on the post
     */
    private void updateIndex(NamedFacebookType to, NamedFacebookType from, Date commentDate) {

        // if the user is not commenting on his own post
        if (!to.getId().equals(from.getId())) {
            UserIndex userIndex = UserIndex.searchUserIndex(Long.valueOf(to.getId()), Long.valueOf(from.getId()));

            if (userIndex == null) {
                // first interactions between these users.
                userIndex = new UserIndex(to.getId(), from.getId(), to.getName(), from.getName(), commentDate);
                userIndex.save();
            } else {
                userIndex.incrementCount(commentDate);
                userIndex.save();
            }
        }

    }

    /**
     * Converts a feed string to a Post Class
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
