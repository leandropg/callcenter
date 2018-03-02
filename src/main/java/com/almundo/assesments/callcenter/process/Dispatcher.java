package com.almundo.assesments.callcenter.process;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.almundo.assesments.callcenter.model.Call;
import com.almundo.assesments.callcenter.model.Employee;

/**
 * Dispatcher
 */
public class Dispatcher {

	/**
	 * Logger Instance
	 */
	private static final Logger LOGGER = Logger.getLogger(Dispatcher.class.getName());
	
	/**
	 * Constant Maximum Simultaneous Calls
	 */
	public static final int MAXIMUM_SIMULTANEOUS_CALLS = 10;
	
	/**
	 * Dispatcher Instance
	 */
	private static Dispatcher instance;
	
	/**
	 * List Calls in Progress
	 */
	private List<Call> lstCallsInProgress;

	/**
	 * List Calls in Hold
	 */
	private List<Call> lstCallsInHold;
	
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
	 * Get Dispatcher Instance
	 * @return Dispatcher Instance
	 */
	public static Dispatcher getInstance() {
		
		if(instance == null) {
			
			instance = new Dispatcher();
		}
		return instance;
	}
	
	public Dispatcher() {
		
		// Init List Calls
		lstCallsInProgress = new CopyOnWriteArrayList<>();
		lstCallsInHold = new CopyOnWriteArrayList<>();
	}
	
	/**
	 * Dispatch Call
	 * @param incomingCall Incoming Call
	 */
	public void dispatchCall(Call incomingCall) {

		// Try Assign Call to Operator
		if(!assignCallToEmployee(incomingCall, lstOperator)) {
			
			// Try Assign Call to Supervisor
			if(!assignCallToEmployee(incomingCall, lstSupervisor)) {
				
				// Try Assign Call to Director
				if(!assignCallToEmployee(incomingCall, lstDirector)) {
					
					// Put Call in Hold
					LOGGER.log(Level.INFO, String.format("Put Call Id %s in Hold...", incomingCall.getId()));
					lstCallsInHold.add(incomingCall);
				}
			}
		}
	}
	
	/**
	 * Assign Call to Employee
	 * @param incomingCall Incoming Call
	 * @param lstEmployee
	 * @return Flag Call Assigned
	 */
	private boolean assignCallToEmployee(Call incomingCall, List<Employee> lstEmployee) {
		
		boolean isCallAssigned = false;
		
		// Try assign call to an Employee
		for(Employee employee : lstEmployee) {
			
			// Check if Employee is not Busy and Maximum Call Simultaneous
			if(!employee.isBusy() && lstCallsInProgress.size() < MAXIMUM_SIMULTANEOUS_CALLS) {
			
				// Add Call to List Calls in Progress
				lstCallsInProgress.add(incomingCall);
				
				// Assign Call to Employee
				employee.setCallAssigned(incomingCall);
				employee.setBusy(true);
				LOGGER.log(Level.INFO, String.format("Incoming Call %d assigned to Employee %s. Call Duration %d Seconds", incomingCall.getId(), employee.getCode(), incomingCall.getDuration()));
								
				// Set Flag Call Assigned
				isCallAssigned = true;
				break;	
			}
		}
		return isCallAssigned;
	}

	public List<Call> getLstCallsInProgress() {
		return lstCallsInProgress;
	}

	public List<Call> getLstCallsInHold() {
		return lstCallsInHold;
	}

	public List<Employee> getLstOperator() {
		return lstOperator;
	}

	public void setLstOperator(List<Employee> lstOperator) {
		this.lstOperator = lstOperator;
	}

	public List<Employee> getLstSupervisor() {
		return lstSupervisor;
	}

	public void setLstSupervisor(List<Employee> lstSupervisor) {
		this.lstSupervisor = lstSupervisor;
	}

	public List<Employee> getLstDirector() {
		return lstDirector;
	}

	public void setLstDirector(List<Employee> lstDirector) {
		this.lstDirector = lstDirector;
	}
}