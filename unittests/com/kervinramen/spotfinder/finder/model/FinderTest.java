/**
 * 
 */
package com.kervinramen.spotfinder.finder.model;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.kervinramen.spotfinder.base.model.FacebookUser;
import com.kervinramen.spotfinder.indexer.model.CompareInfo;
import com.kervinramen.spotfinder.indexer.model.CompareInfo;
import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import static com.google.appengine.api.datastore.FetchOptions.Builder.withLimit;
import com.google.appengine.api.datastore.Query;
import com.google.appengine.tools.development.testing.LocalDatastoreServiceTestConfig;
import com.google.appengine.tools.development.testing.LocalServiceTestHelper;

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
