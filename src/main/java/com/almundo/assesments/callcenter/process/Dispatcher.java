package com.almundo.assesments.callcenter.process;

import java.util.List;
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
	 * Dispatcher Instance
	 */
	private static Dispatcher instance;
	
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
	
	/**
	 * Dispatcher
	 */
	public Dispatcher() {
		
	}
	
	/**
	 * Dispatch Call
	 * @param incomingCall Incoming Call
	 */
	public void dispatchCall(Call incomingCall) {

		// Try Assign Call to Operator
		if(!assignCallEmployee(incomingCall, lstOperator)) {
			
			// Try Assign Call to Supervisor
			if(!assignCallEmployee(incomingCall, lstSupervisor)) {
				
				// Try Assign Call to Director
				if(!assignCallEmployee(incomingCall, lstDirector)) {
					
					LOGGER.log(Level.INFO, String.format("Ignore Call Id %s", incomingCall.getCallId()));
				}
			}
		}
	}
	
	/**
	 * Assign Call Employee
	 * @param incomingCall Incoming Call
	 * @param lstEmployee
	 * @return Flag Call Assigned
	 */
	private boolean assignCallEmployee(Call incomingCall, List<Employee> lstEmployee) {
		
		boolean isCallAssigned = false;
		
		// Try assign call to an Employee
		for(Employee employee : lstEmployee) {
			
			// Check if Employee not is Busy
			if(!employee.isBusy()) {
				
				// Set Call Assigned
				employee.setCallAssigned(incomingCall);
				
				// Set Flag Busy
				employee.setBusy(true);
								
				// Set Flag Call Assigned
				isCallAssigned = true;
				LOGGER.log(Level.INFO, String.format("Incoming Call Id %s assigned to Employee %d", incomingCall.getCallId(), employee.getCode()));
				break;
			}
		}
		return isCallAssigned;
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