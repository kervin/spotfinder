package com.kervinramen.spotfinder.base.model;

import java.util.Date;

import javax.jdo.PersistenceManager;
import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import com.google.appengine.api.datastore.Key;
import com.kervinramen.spotfinder.helpers.PMF;

@PersistenceCapable
public class Log {
    
    @SuppressWarnings("unused")
    @PrimaryKey
    @Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
    private Key id;
    
    @SuppressWarnings("unused")
    @Persistent
    private Date time;
    
    @SuppressWarnings("unused")
    @Persistent
    private String message;

    @SuppressWarnings("unused")
    @Persistent
    private String type;

   
    private Log() {
        
    }
    
    /**
     * Creates a verbose log
     */
    public static void v(String message) {
        Log log = new Log();
        
        log.message = message;
        log.time = new Date();
        log.type = "verbose";
        
        Log.save(log);

    }
    
    /**
     * Creates a critical log
     */
    public static void c(String message) {
        Log log = new Log();
        
        log.message = message;
        log.time = new Date();
        log.type = "critical";
        
        Log.save(log);

    }
    
    private static void save(Log log) {
        PersistenceManager pm = PMF.get().getPersistenceManager();

        try {
            pm.makePersistent(log);
        } finally {
            pm.close();
        }
    }
    

}
