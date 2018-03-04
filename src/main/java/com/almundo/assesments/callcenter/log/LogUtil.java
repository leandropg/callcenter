package com.almundo.assesments.callcenter.log;

import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;

/**
 * Log Util
 */
public class LogUtil {

	/**
	 * Logger Instance
	 */
	private static final Logger LOGGER = Logger.getLogger(LogUtil.class.getName());
	
	/**
	 * Log Util Instance
	 */
	private static LogUtil instance;
	
	/**
	 * Get Instance Log Util
	 * @return Instance Log Util
	 */
	public static LogUtil getInstance() {
		
		if(instance == null) {
			
			instance = new LogUtil();
		}
		return instance;
	}
	
    /**
     * Load Log Configuration
     */
    public void loadLogConfiguration() {
    	
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