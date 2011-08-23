package com.kervinramen.spotfinder.indexer.controller;

import java.io.IOException;
import java.util.Date;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kervinramen.spotfinder.indexer.model.IndexingEngine;
import com.kervinramen.spotfinder.indexer.model.Parser;

@SuppressWarnings("serial")
public class IndexerServlet extends HttpServlet {

    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        String response = "";

        response += logIndexer(1);
        response += logIndexer(2);
        response += logIndexer(10);
        response += logIndexer(30);
        // logIndexer(100);
        
        resp.getWriter().write(response);
        
    }

    private String logIndexer(Integer count) {

        Date start = new Date();

        for (Integer i = 0; i < count; i++) {
            callIndexer();
        }

        Date end = new Date();

        // Calculate the time taken
        long timeTaken = (end.getTime() - start.getTime());
        Double timeTakenInSec = timeTaken / 1000.0;

        // Returns the time taken
        String response = "Time taken for" + count.toString() + " times: " + timeTakenInSec.toString() + "\n";

        return response;

    }

    private void callIndexer() {
        IndexingEngine engine = new IndexingEngine();
        engine.start();
    }

}
