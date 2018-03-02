package com.almundo.assesments.callcenter;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;

import com.almundo.assesments.callcenter.model.Director;
import com.almundo.assesments.callcenter.model.Employee;
import com.almundo.assesments.callcenter.model.Operator;
import com.almundo.assesments.callcenter.model.Supervisor;
import com.almundo.assesments.callcenter.process.CallGenerator;
import com.almundo.assesments.callcenter.process.Dispatcher;

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
  
    	CallGenerator callGenerator;
    	
    	// Load Log Configuration
    	loadLogConfiguration();
    	
    	// Show Message to User
    	LOGGER.log(Level.INFO, "Start Call Center Application");
    	LOGGER.log(Level.INFO, "It's 7:00 am, the employees are arrived");
    	setEmployeesDispatcher();
    	
    	// Init Incoming Call
    	LOGGER.log(Level.INFO, "It's 8:00 am, the Call Center is open");
    	LOGGER.log(Level.INFO, "Waiting Calls...");
    	callGenerator = new CallGenerator();
    	callGenerator.start();
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
    
    /**
     * Set Employees Dispatcher 
     */
    private void setEmployeesDispatcher() {
    	
    	List<Employee> lstOperator;
    	List<Employee> lstSupervisor;
    	List<Employee> lstDirector;

    	// Fill List Operator
    	lstOperator = new ArrayList<>();
    	lstOperator.add(new Operator(1));
    	lstOperator.add(new Operator(2));
    	lstOperator.add(new Operator(3));
    	lstOperator.add(new Operator(4));
    	lstOperator.add(new Operator(5));
    	lstOperator.add(new Operator(6));

    	// Fill List Supervisor
    	lstSupervisor = new ArrayList<>();
    	lstSupervisor.add(new Supervisor(7));
    	lstSupervisor.add(new Supervisor(8));
    	lstSupervisor.add(new Supervisor(9));
    	
    	// Fill List Director
    	lstDirector = new ArrayList<>();
    	lstDirector.add(new Director(10));
    	
    	// Set Employee Lists in the Dispatcher
    	Dispatcher.getInstance().setLstOperator(lstOperator);
    	Dispatcher.getInstance().setLstSupervisor(lstSupervisor);
    	Dispatcher.getInstance().setLstDirector(lstDirector);
    }
}