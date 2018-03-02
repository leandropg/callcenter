package com.almundo.assesments.callcenter;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;

import com.almundo.assesments.callcenter.model.Director;
import com.almundo.assesments.callcenter.model.Employee;
import com.almundo.assesments.callcenter.model.Operator;
import com.almundo.assesments.callcenter.model.Supervisor;
import com.almundo.assesments.callcenter.process.CallGenerator;
import com.almundo.assesments.callcenter.process.Dispatcher;
import com.almundo.assesments.callcenter.process.HangUpCallThread;

import util.DelayUtil;

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
    	HangUpCallThread hangUpCallThread;
    	
    	// Load Log Configuration
    	loadLogConfiguration();
    	
    	// Configure Call Center Employees
    	LOGGER.log(Level.INFO, "Start Call Center Application!!!");
    	LOGGER.log(Level.INFO, "It's 7:00 am... The call center employees are arrived...");
    	configureCallCenterEmployees();
    	DelayUtil.delaySeconds(3);
    	
    	// Start Call Generator
    	LOGGER.log(Level.INFO, "It's 8:00 am... The operation is starting...");
    	LOGGER.log(Level.INFO, "Waiting Incoming Calls...");
    	DelayUtil.delaySeconds(2);
    	callGenerator = new CallGenerator();
    	callGenerator.start();
    	
    	// Start Hang Up Call Thread
    	hangUpCallThread = new HangUpCallThread();
    	hangUpCallThread.start();
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
     * Configure Call Center Employees 
     */
    private void configureCallCenterEmployees() {
    	
    	List<Employee> lstOperator;
    	List<Employee> lstSupervisor;
    	List<Employee> lstDirector;

    	// Fill List Operator
    	lstOperator = new CopyOnWriteArrayList<>();
    	lstOperator.add(new Operator("OP-1"));
    	lstOperator.add(new Operator("OP-2"));
    	lstOperator.add(new Operator("OP-3"));
    	lstOperator.add(new Operator("OP-4"));
    	lstOperator.add(new Operator("OP-5"));
    	lstOperator.add(new Operator("OP-6"));
    	lstOperator.add(new Operator("OP-7"));
    	LOGGER.log(Level.INFO, String.format("There are %d Operators", lstOperator.size()));

    	// Fill List Supervisor
    	lstSupervisor = new CopyOnWriteArrayList<>();
    	lstSupervisor.add(new Supervisor("SUP-1"));
    	lstSupervisor.add(new Supervisor("SUP-2"));
    	LOGGER.log(Level.INFO, String.format("There are %d Supervisors", lstSupervisor.size()));
    	
    	// Fill List Director
    	lstDirector = new CopyOnWriteArrayList<>();
    	lstDirector.add(new Director("DIR-1"));
    	LOGGER.log(Level.INFO, String.format("There are %d Directors", lstDirector.size()));
    	
    	// Set Employee Lists in the Dispatcher
    	Dispatcher.getInstance().setLstOperator(lstOperator);
    	Dispatcher.getInstance().setLstSupervisor(lstSupervisor);
    	Dispatcher.getInstance().setLstDirector(lstDirector);
    }
}