package com.kervinramen.spotfinder.finder.controller;

import java.io.IOException;
import java.io.StringWriter;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import com.kervinramen.spotfinder.base.model.Spot;
import com.kervinramen.spotfinder.base.model.Spots;
import com.kervinramen.spotfinder.finder.model.Finder;

@SuppressWarnings("serial")
public class FinderServlet extends HttpServlet {

    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        Finder finder = new Finder();
        Spots spots = null;
        
        if (req.getParameter("id") == null || 
                req.getParameter("lat") == null || 
                req.getParameter("lng") == null) {
            //spots = finder.search();

        } else {
            String username = req.getParameter("id");
            String lat = req.getParameter("lat");
            String lng = req.getParameter("lng");
            spots = finder.search(username, lat, lng);
        }

        Spots top10spots = new Spots();
        int count = 1;
        for (Spot spot : spots.spot) {
            top10spots.spot.add(spot);
            count++;

            if (count > 10) {
                break;
            }
        }

        StringWriter xml = new StringWriter();

        JAXBContext context;
        try {
            context = JAXBContext.newInstance(Spots.class);
            Marshaller m = context.createMarshaller();
            m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

            m.marshal(top10spots, xml);
            
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        resp.getWriter().write(xml.toString());

    }
}
