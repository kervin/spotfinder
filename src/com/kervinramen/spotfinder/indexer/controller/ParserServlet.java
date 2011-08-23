package com.kervinramen.spotfinder.indexer.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kervinramen.spotfinder.indexer.model.Parser;
import com.kervinramen.spotfinder.indexer.model.UserIndex;

@SuppressWarnings("serial")
public class ParserServlet extends HttpServlet {

    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        ArrayList<UserIndex> indexes = UserIndex.getUserIndexes();

        for (UserIndex userIndex : indexes) {
            userIndex.setScore(0.0);
            userIndex.setCount(0);
            userIndex.save();
        }

        resp.getWriter().write(logParser(1));
        resp.getWriter().write(logParser(2));
        resp.getWriter().write(logParser(10));

        resp.getWriter().write(logParser(30));
   }

    private String logParser(int count) {

        Date start = new Date();

        for (int i = 0; i < count; i++) {
            callParser();
        }

        Date end = new Date();

        // Calculate the time taken
        long timeTaken = (end.getTime() - start.getTime());
        Double timeTakenInSec = timeTaken / 1000.0;
        
        // Returns the time taken
        String response = "Time taken for 1:" + timeTakenInSec.toString() + "<br />";

        return response;

    }

    private void callParser() {
        Parser parser = new Parser();
        parser.start();
    }
}
