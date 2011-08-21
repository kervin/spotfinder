package com.kervinramen.spotfinder.helpers;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringWriter;
import java.io.Writer;
import java.util.Date;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import com.google.appengine.repackaged.org.json.JSONException;
import com.google.appengine.repackaged.org.json.JSONObject;

// TODO: make utility singleton and implement logging facilities
public class Utilities {

    public static String convertStreamToString(InputStream is) throws IOException {
        /*
         * To convert the InputStream to String we use the Reader.read(char[]
         * buffer) method. We iterate until the Reader return -1 which means
         * there's no more data to read. We use the StringWriter class to
         * produce the string.
         */
        if (is != null) {
            Writer writer = new StringWriter();

            char[] buffer = new char[1024];
            try {
                Reader reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
                int n;
                while ((n = reader.read(buffer)) != -1) {
                    writer.write(buffer, 0, n);
                }
            } finally {
                is.close();
            }
            return writer.toString();
        } else {
            return "";
        }
    }

    public static String BytetoString(ByteArrayInputStream is) {
        int size = is.available();
        char[] theChars = new char[size];
        byte[] bytes = new byte[size];

        is.read(bytes, 0, size);
        for (int i = 0; i < size;)
            theChars[i] = (char) (bytes[i++] & 0xff);

        return new String(theChars);
    }

    /**
     * Converts a String to a JSONObject
     * 
     * @param value
     * @return
     */
    public static JSONObject getJSON(String value) {

        JSONObject user = new JSONObject();

        try {
            user = new JSONObject(value);
        } catch (JSONException e) {
            // log error
        }

        return user;
    }

    public static int safeLongToInt(long l) {
        if (l < Integer.MIN_VALUE || l > Integer.MAX_VALUE) {
            throw new IllegalArgumentException(l + " cannot be cast to int without changing its value.");
        }
        return (int) l;
    }


    /**
     * Returns the number of days from the date to now
     * @param lastDate
     * @return
     */
    public static Double getDaysElapsed(Date lastDate) {
        long MILLSECS_PER_DAY = 24 * 60 * 60 * 1000;

        // today
        Date today = new Date();

        return (double) ((today.getTime() - lastDate.getTime()) / MILLSECS_PER_DAY);
    }
    
    /**
     * Implementation of Haversine formula
     * 
     * @param lat1
     *            Latitude of point 1
     * @param lng1
     *            Longitude of point 1
     * @param lat2
     *            Latitude of point 2
     * @param lng2
     *            Longitude of point 2
     * 
     * @return distance between the two points
     */
    public static double distFrom(double lat1, double lng1, double lat2, double lng2) {
        // Earth radius in km
        double earthRadius = 6371.0;
        
        double dLat = Math.toRadians(lat2 - lat1);
        double dLng = Math.toRadians(lng2 - lng1);

        double a = Math.sin(dLat / 2) * Math.sin(dLat / 2) + Math.cos(Math.toRadians(lat1))
                * Math.cos(Math.toRadians(lat2)) * Math.sin(dLng / 2) * Math.sin(dLng / 2);

        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        double dist = earthRadius * c;

        return dist;
    }
    
    public static void runQuery() {
        PersistenceManager pm = PMF.get().getPersistenceManager();

        Query query = pm.newQuery("select from FacebookUser");

        query.execute();
    }

}