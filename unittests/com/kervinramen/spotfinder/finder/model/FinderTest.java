/**
 * 
 */
package com.kervinramen.spotfinder.finder.model;

import static org.junit.Assert.assertEquals;

import java.util.Calendar;
import java.util.Date;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.google.appengine.tools.development.testing.LocalDatastoreServiceTestConfig;
import com.google.appengine.tools.development.testing.LocalServiceTestHelper;

/**
 * @author Kervin Ramen
 * 
 */
public class FinderTest {

    private Finder finder;
    private Date now;
    private Calendar c;

    private final LocalServiceTestHelper helper = new LocalServiceTestHelper(new LocalDatastoreServiceTestConfig());

    @Before
    public void setUp() {
        helper.setUp();
        finder = new Finder();
        now = new Date();
        c = Calendar.getInstance();

        c.setTime(now);
    }

    @After
    public void tearDown() {
        helper.tearDown();
    }


    /**
     * Test method for
     * {@link com.kervinramen.spotfinder.finder.model.Finder#search()}.
     */
    @Test
    public final void testFinder() {

        finder.search();
        
        assertEquals("without decay", (double) 1, 2, 0);

    }
    
    
    /**
     * Test method for
     * {@link com.kervinramen.spotfinder.finder.model.Finder#search()}.
     */
    @Test
    public final void testGetDecayedScore() {

        double score = finder.getDecayedScore((double) 3, now);
        assertEquals("without decay", (double) 1, score, 0);

    }

    @Test
    public final void testGetDecayedScore10() {
        c.add(Calendar.DATE, -10);

        double score = finder.getDecayedScore((double) 3, c.getTime());
        assertEquals("With 10 days decay", (double) 3, score, 0);

    }
    @Test
    public final void testGetDecayedScore20() {
        c.add(Calendar.DATE, -20);
        
        double score = finder.getDecayedScore((double) 3, c.getTime());
        assertEquals("With 30 days decay", (double) 3, score, 0);
        
    }
    @Test
    public final void testGetDecayedScore30() {
        c.add(Calendar.DATE, -30);
        
        double score = finder.getDecayedScore((double) 3, c.getTime());
        assertEquals("With 30 days decay", (double) 3, score, 0);
        
    }
    @Test
    public final void testGetDecayedScore40() {
        c.add(Calendar.DATE, -40);
        
        double score = finder.getDecayedScore((double) 3, c.getTime());
        assertEquals("With 30 days decay", (double) 3, score, 0);
        
    }

}
