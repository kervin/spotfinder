package com.kervinramen.spotfinder.finder.model;

import junit.framework.TestCase;

public class HomeFeedCompareTests extends TestCase {

    public void testCompare() {
        HomeFeedCompare compare = new HomeFeedCompare();

        compare.sandboxGraph();

        
        this.assertNotNull(compare.graph);
        
        
    }

}
