package com.kervinramen.spotfinder.engine.model;

import com.kervinramen.spotfinder.Indexer.model.CompareInfo;

import junit.framework.TestCase;


public class InfoCompareTests extends TestCase {

    public void testCompare() {
        CompareInfo compare = new CompareInfo();

        int score = compare.calculateKervin();

        
        this.assertEquals(score, 1);
        
        
    }
    
}
