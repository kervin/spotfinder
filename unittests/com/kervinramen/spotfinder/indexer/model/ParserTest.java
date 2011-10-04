package com.kervinramen.spotfinder.indexer.model;

import java.util.ArrayList;

import com.google.appengine.tools.development.testing.LocalDatastoreServiceTestConfig;
import com.google.appengine.tools.development.testing.LocalServiceTestHelper;
import com.kervinramen.spotfinder.parser.model.Parser;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;



public class ParserTest {

    private final LocalServiceTestHelper helper = new LocalServiceTestHelper(new LocalDatastoreServiceTestConfig());

    @Before
    public void setUp() throws Exception {

        helper.setUp();
    }

    @After
    public void tearDown() {
        helper.tearDown();
    }

    @Test
    public final void testStart() {
        ArrayList<UserIndex> indexes = UserIndex.getUserIndexes();

        for (UserIndex userIndex : indexes) {
            userIndex.setScore(0.0);
            userIndex.save();
        }

        
        Parser parser = new Parser();
        parser.start();
    }
}
