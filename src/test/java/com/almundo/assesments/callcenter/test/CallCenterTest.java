package com.almundo.assesments.callcenter.test;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.almundo.assesments.callcenter.CallCenter;
import com.almundo.assesments.callcenter.model.Director;
import com.almundo.assesments.callcenter.model.Employee;
import com.almundo.assesments.callcenter.model.Operator;
import com.almundo.assesments.callcenter.model.Supervisor;
import com.almundo.assesments.callcenter.util.DelayUtil;
import com.almundo.assesments.callcenter.util.LogUtil;

/**
 * Unit Test for Call Center
 */
public class CallCenterTest {
    
	/**
	 * Logger Instance
	 */
	private static final Logger LOGGER = Logger.getLogger(CallCenter.class.getName());
	
	/**
	 * Constant Maximum Calls Simulate
	 */
	public static final int MAXIMUM_CALLS_SIMULATE = 50;
	
	/**
	 * Call Center Instance
	 */
	private CallCenter callCenter;
	
	/**
	 * List Operators
	 */
	private List<Employee> lstOperator;

	/**
	 * List Supervisors
	 */
	private List<Employee> lstSupervisor;
	
	/**
	 * List Directors
	 */
	private List<Employee> lstDirector;
	
	/**
	 * Before Each
	 */
	@BeforeEach
	public void init( ) {
		
		// Load Log Configuration
		LogUtil.getInstance().loadLogConfiguration();
		
    	// Create List Operator
    	lstOperator = new CopyOnWriteArrayList<>();
    	lstOperator.add(new Operator("OP-1"));
    	lstOperator.add(new Operator("OP-2"));
    	lstOperator.add(new Operator("OP-3"));
    	lstOperator.add(new Operator("OP-4"));
    	lstOperator.add(new Operator("OP-5"));
    	lstOperator.add(new Operator("OP-6"));
    	lstOperator.add(new Operator("OP-7"));
    	LOGGER.log(Level.INFO, "There are {0} Operators", lstOperator.size());

    	// Create List Supervisor
    	lstSupervisor = new CopyOnWriteArrayList<>();
    	lstSupervisor.add(new Supervisor("SUP-1"));
    	lstSupervisor.add(new Supervisor("SUP-2"));
    	LOGGER.log(Level.INFO, "There are {0} Supervisors", lstSupervisor.size());
    	
    	// Create List Director
    	lstDirector = new CopyOnWriteArrayList<>();
    	lstDirector.add(new Director("DIR-1"));
    	LOGGER.log(Level.INFO, "There are {0} Directors", lstDirector.size());
		
		// Init Call Center Instance
		callCenter = new CallCenter();
	}
	
    /**
     * Test
     */
	@DisplayName("Test Call Center")
	@Test
    public void testApp() {
        
    	// Configure Call Center Employees
    	LOGGER.log(Level.INFO, "It's 7:00 am... The call center employees are arrived...");
    	DelayUtil.delaySeconds(3);
    	
    	// Start Operation
    	LOGGER.log(Level.INFO, "It's 8:00 am... The operation is starting...");
    	LOGGER.log(Level.INFO, "Waiting Incoming Calls...");
    	DelayUtil.delaySeconds(2);
    	callCenter.startOperation(lstOperator, lstSupervisor, lstDirector, MAXIMUM_CALLS_SIMULATE);
    	LOGGER.log(Level.INFO, "It's 5:00 pm... The operation has finished...");
    	
    	// Check if Random Value is in the range
    	Assertions.assertTrue(callCenter.getDispatcher().getQtyCallsAttended() == MAXIMUM_CALLS_SIMULATE);
    }
}