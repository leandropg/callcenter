package com.almundo.assesments.callcenter;

import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;

/**
 * Call Center Application
 */
public class CallCenter {

	/**
	 * Logger Instance
	 */
	private static final Logger LOGGER = Logger.getLogger(CallCenter.class.getName());
	
	/**
	 * Entry Point to Application
	 * @param args Console Arguments Application
	 */
    public static void main( String[] args ) {
        
    	// Start Call Center Application
    	new CallCenter().startApp();
    }
    
    /**
     * Start Application
     */
    public void startApp() {
    	    	
    	// Load Log Configuration
    	loadLogConfiguration();
    	
    	// Show Message to User
    	LOGGER.log(Level.INFO, "Start Call Center Application");
    }
    
    /**
     * Load Log Configuration
     */
    private void loadLogConfiguration() {
    	
    	final LogManager logManager;
    	 
    	// Obtain Log Manager
    	logManager = LogManager.getLogManager();
    	
    	// Load logging.properties configuration
    	try (final InputStream inputStream = getClass().getResourceAsStream("/logging.properties")) {
        
    		// Read Log Configuration
    		logManager.readConfiguration(inputStream);
        
    	} catch (SecurityException | IOException e) {

    		LOGGER.log(Level.SEVERE, "Error Load Log Configuration", e);
		}
    }
}