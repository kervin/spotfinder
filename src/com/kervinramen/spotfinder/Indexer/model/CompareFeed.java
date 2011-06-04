package com.kervinramen.spotfinder.Indexer.model;

import com.kervinramen.spotfinder.base.model.FacebookUser;

public class CompareFeed implements ICompare {

    @Override
    public int getToCount(FacebookUser user1, FacebookUser user2) {
        // TODO Auto-generated method stub
        return 5;
    }

    @Override
    public int getFromCount(FacebookUser user1, FacebookUser user2) {
        // TODO Auto-generated method stub
        return 5;
    }

    @Override
    public int getSameCount(FacebookUser user1, FacebookUser user2) {
        // TODO Auto-generated method stub
        return 5;
    }

}
