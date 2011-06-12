
package com.kervinramen.spotfinder.engine.model;

import com.kervinramen.spotfinder.indexer.model.CompareHomeFeed;

import junit.framework.TestCase;

public class HomeFeedCompareTests extends TestCase {

    public void testCompare() {
        CompareHomeFeed compare = new CompareHomeFeed();

        compare.sandboxGraph();

        
        this.assertNotNull(compare.graph);
        
        
    }

}
