package com.almundo.assesments.callcenter.process;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.almundo.assesments.callcenter.model.Call;
import com.almundo.assesments.callcenter.model.Employee;

import util.DelayUtil;

/**
 * Hang Up Call Thread
 */
public class HangUpCallThread extends Thread {

	/**
	 * Logger Instance
	 */
	private static final Logger LOGGER = Logger.getLogger(HangUpCallThread.class.getName());

	/**
	 * Dispatcher Instance
	 */
	private Dispatcher dispatcher;
	
	/**
	 * Hang Up Call Thread
	 * @param dispatcher Dispatcher Instance
	 */
	public HangUpCallThread(Dispatcher dispatcher) {
		
		this.dispatcher = dispatcher;
	}
	
	/**
	 * Run Method
	 */
	@Override
	public void run() {
		
		// Infinite Loop
		while(true) {
		
			// Delay 1 Second
			DelayUtil.delaySeconds(1);

			// Hang Up Operators Calls
			hangUpCall(dispatcher.getLstOperator());
			
			// Hang Up Supervisors Calls
			hangUpCall(dispatcher.getLstSupervisor());
			
			// Hang Up Directors Calls
			hangUpCall(dispatcher.getLstDirector());
		}
	}
	
	/**
	 * Hang Up Call
	 * @param lstEmployee List Employee
	 */
	private void hangUpCall(List<Employee> lstEmployee) {
		
		Call callAssigned;
		Call callInHold;
		
		// Iterate over all Employee List
		for(Employee employee : lstEmployee) {
			
			// Check if Employee is busy
			if(employee.isBusy()) {
			
				// Obtain Call Assigned
				callAssigned = employee.getCallAssigned();
				
				// Check if Duration is greater than zero
				if(callAssigned.getDuration() > 0) {
					
					// Decrement Call Duration
					callAssigned.setDuration(callAssigned.getDuration() - 1);
				
				} else {
					
					// Hang Up Call
					LOGGER.log(Level.INFO, String.format("Hang Up Call %d. Free Employee %s", callAssigned.getId(), employee.getCode()));
					dispatcher.getLstCallsInProgress().remove(callAssigned);
					employee.setCallAssigned(null);
					
					// Add Call Attended Counter
					dispatcher.setQtyCallsAttended(dispatcher.getQtyCallsAttended() + 1);
					
					// Check if exist Calls in Hold
					if(!dispatcher.getLstCallsInHold().isEmpty() &&
							dispatcher.getLstCallsInProgress().size() < Dispatcher.MAXIMUM_SIMULTANEOUS_CALLS) {
				
						// Obtain a Call in Hold
						callInHold = dispatcher.getLstCallsInHold().remove(0);
						
						// Add New Call to List Calls in Progress
						dispatcher.getLstCallsInProgress().add(callInHold);
						
						// Set Call Assigned
						employee.setCallAssigned(callInHold);
						LOGGER.log(Level.INFO, String.format("Call in Hold %d assigned to Employee %s. Call Duration %d Seconds", callInHold.getId(), employee.getCode(), callInHold.getDuration()));
					
					} else {
					
						// Free Employee
						employee.setBusy(false);

						// Add Call Attended Counter
						dispatcher.setQtyCallsAttended(dispatcher.getQtyCallsAttended() + 1);
					}
				}
			}
		}
	}
}