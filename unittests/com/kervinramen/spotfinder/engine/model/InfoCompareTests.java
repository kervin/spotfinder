package com.kervinramen.spotfinder.engine.model;

import org.junit.After;
import org.junit.Before;

import com.kervinramen.spotfinder.indexer.model.CompareInfo;
import com.google.appengine.tools.development.testing.LocalDatastoreServiceTestConfig;
import com.google.appengine.tools.development.testing.LocalServiceTestHelper;


import junit.framework.TestCase;


public class InfoCompareTests extends TestCase {

    private final LocalServiceTestHelper helper =
        new LocalServiceTestHelper(new LocalDatastoreServiceTestConfig());

    @Before
    public void setUp() {
        helper.setUp();
    }

    @After
    public void tearDown() {
        helper.tearDown();
    }
    
    public void testCompare() {
        CompareInfo compare = new CompareInfo();

        int score = compare.calculateKervin();

        
        this.assertEquals(score, 1);
        
        
    }
    
}
