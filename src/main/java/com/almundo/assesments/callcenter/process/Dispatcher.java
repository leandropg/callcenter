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
	 * Quantity Calls Attended
	 */
	private int qtyCallsAttended;
	
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
	 * Dispatcher
	 * @param lstOperator List Operators
	 * @param lstSupervisor List Supervisors
	 * @param lstDirector List Directors
	 */
	public Dispatcher(List<Employee> lstOperator, List<Employee> lstSupervisor, List<Employee> lstDirector) {
	
		// Save List Employees
		this.lstOperator = lstOperator;
		this.lstSupervisor = lstSupervisor;
		this.lstDirector = lstDirector;
		
		// Init List Calls
		lstCallsInProgress = new CopyOnWriteArrayList<>();
		lstCallsInHold = new CopyOnWriteArrayList<>();
		
		// Init Counter Calls Attended
		qtyCallsAttended = 0;
	}
	
	/**
	 * Dispatch Call
	 * @param incomingCall Incoming Call
	 */
	public void dispatchCall(Call incomingCall) {

		// Try Assign Call to Employee
		if(!assignCallToEmployee(incomingCall, lstOperator) &&
				!assignCallToEmployee(incomingCall, lstSupervisor) &&
				!assignCallToEmployee(incomingCall, lstDirector)) {

			// Put Call in Hold
			LOGGER.log(Level.INFO, "Put Call {0} in Hold...", incomingCall.getId());
			lstCallsInHold.add(incomingCall);
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
				LOGGER.log(Level.INFO, "Incoming Call {0} assigned to Employee {1}. Call Duration {2} Seconds", new Object[] {incomingCall.getId(), employee.getCode(), incomingCall.getDuration()});
								
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

	public List<Employee> getLstSupervisor() {
		return lstSupervisor;
	}

	public List<Employee> getLstDirector() {
		return lstDirector;
	}

	public int getQtyCallsAttended() {
		return qtyCallsAttended;
	}

	public void setQtyCallsAttended(int qtyCallsAttended) {
		this.qtyCallsAttended = qtyCallsAttended;
	}
}