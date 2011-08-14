/**
 * 
 */
package com.kervinramen.spotfinder.finder.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.google.appengine.tools.development.testing.LocalDatastoreServiceTestConfig;
import com.google.appengine.tools.development.testing.LocalServiceTestHelper;
import com.kervinramen.spotfinder.base.model.FacebookUser;

/**
 * @author Kervin Ramen
 *
 */
public class FinderTest {
    
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
    
    
    public FacebookUser getSearcher() {
        FacebookUser searcher = new FacebookUser();
        searcher.searchUser("kervin.ramen");

        assertEquals(123, searcher.getUserId());
        
        return searcher;
    }


    /**
     * Test method for {@link com.kervinramen.spotfinder.finder.model.Finder#search(com.kervinramen.spotfinder.base.model.FacebookUser)}.
     */
    @Test
    public final void testSearchFacebookUser() {
        
        Finder finder = new Finder();
        finder.search(getSearcher());
        
    }

    /**
     * Test method for {@link com.kervinramen.spotfinder.finder.model.Finder#search()}.
     */
    @Test
    public final void testSearch() {
        fail("Not yet implemented"); // TODO
    }

}
