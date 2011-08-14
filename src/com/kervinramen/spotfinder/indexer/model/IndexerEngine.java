package com.kervinramen.spotfinder.indexer.model;

import java.util.ArrayList;

public class IndexerEngine {

    public void start() {

        // Gets all relationships from the userindex table
        ArrayList<UserIndex> userIndexes = UserIndex.getUserIndexes();

        for (UserIndex userIndex : userIndexes) {
            // Gets the score for this relationship
            double score = getScore(userIndex);

            // Sets the score
            userIndex.setScore(score);

            // Updates the score in the database
            userIndex.save();
        }
    }

    public double getScore(UserIndex index) {
        // The weight of outgoing relationship is twice of incoming
        double w = 2;

        // Total number of global comments of the user
        long totalcount = UserIndex.getTotal(index.getUserId());

        // score for outgoing comments
        double scoreout = (double) index.getCount() / (double) totalcount;

        // Gets the friends's interaction towards the user
        UserIndex friendIndex = UserIndex.getUserIndex(index.getFriendId());

        // score for incoming comments
        double scorein = 0;
        // if the friend sent comments interacted with the user
        if (friendIndex != null) {
            // The score of incoming cannot be greater that outgoing
            // if so, scorein = scoreout
            if (index.getCount() > friendIndex.getCount()) {
                scorein = scoreout;
            } else {
                double totalofFriend = (double) UserIndex.getTotal(friendIndex.getUserId());
                scorein = (double) friendIndex.getCount() / totalofFriend;
            }
        }

        // returns final score
        return (w * scorein) + scoreout;
    }
}
