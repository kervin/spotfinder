package com.kervinramen.spotfinder.indexer.controller;

import java.io.IOException;
import java.util.Date;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kervinramen.spotfinder.base.model.Log;
import com.kervinramen.spotfinder.indexer.model.IndexingEngine;

@SuppressWarnings("serial")
public class CronServlet extends HttpServlet {

    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        int count = Integer.valueOf(req.getParameter("count"));
        
        String response = "";
        response = logIndexer(count);
        Log.v(response);
        
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
