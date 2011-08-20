package com.kervinramen.spotfinder.finder.controller;

import java.io.IOException;
import java.io.StringWriter;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import com.kervinramen.spotfinder.base.model.Spots;
import com.kervinramen.spotfinder.finder.model.Finder;

@SuppressWarnings("serial")
public class FinderServlet extends HttpServlet {

    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        Finder finder = new Finder();
        Spots spots = finder.search();
        
        StringWriter xml = new StringWriter();
        
        JAXBContext context;
        try {
            context = JAXBContext.newInstance(Spots.class);
            Marshaller m = context.createMarshaller();
            m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

            m.marshal(spots, xml);
        } catch (JAXBException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        resp.getWriter().write(xml.toString());


        
    }
}
