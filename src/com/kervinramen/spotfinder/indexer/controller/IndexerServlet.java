package com.kervinramen.spotfinder.indexer.controller;

import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kervinramen.spotfinder.indexer.model.IndexingEngine;

@SuppressWarnings("serial")
public class IndexerServlet extends HttpServlet {

    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        IndexingEngine engine = new IndexingEngine();
        engine.start();

    }

}
