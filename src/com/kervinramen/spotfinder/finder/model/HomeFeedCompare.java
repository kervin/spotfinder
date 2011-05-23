package com.kervinramen.spotfinder.finder.model;

import org.jgrapht.EdgeFactory;
import org.jgrapht.graph.*;

/**
 * This class calculates the number of comments in bound to a user
 * @author Kervin Ramen
 *
 */
public class HomeFeedCompare {

    public SimpleGraph<String, String> graph;
    
    public void sandboxGraph() {
 
        ClassBasedEdgeFactory<String, String> edge = new ClassBasedEdgeFactory<String, String>(null);
        graph = new SimpleGraph<String, String>(edge);
        
    }
    
}
