package com.almundo.assesments.callcenter.process;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.almundo.assesments.callcenter.model.Call;
import com.almundo.assesments.callcenter.model.Employee;

import util.DelayUtil;

/**
 * HangUp Call Thread
 */
public class HangUpCallThread extends Thread {

	/**
	 * Logger Instance
	 */
	private static final Logger LOGGER = Logger.getLogger(HangUpCallThread.class.getName());
	
	/**
	 * Run Method
	 */
	@Override
	public void run() {
		super.run();
		
		// Infinite Loop
		while(true) {
		
			// Delay 1 Second
			DelayUtil.delaySeconds(1);

			// Hang Up Operators Calls
			hangUpCall(Dispatcher.getInstance().getLstOperator());
			
			// Hang Up Supervisors Calls
			hangUpCall(Dispatcher.getInstance().getLstSupervisor());
			
			// Hang Up Directors Calls
			hangUpCall(Dispatcher.getInstance().getLstDirector());
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
					Dispatcher.getInstance().getLstCallsInProgress().remove(callAssigned);
					employee.setCallAssigned(null);
					
					// Check if exist Calls in Hold
					if(!Dispatcher.getInstance().getLstCallsInHold().isEmpty() &&
							Dispatcher.getInstance().getLstCallsInProgress().size() < Dispatcher.MAXIMUM_SIMULTANEOUS_CALLS) {
				
						// Obtain a Call in Hold
						callInHold = Dispatcher.getInstance().getLstCallsInHold().remove(0);
						
						// Add New Call to List Calls in Progress
						Dispatcher.getInstance().getLstCallsInProgress().add(callInHold);
						
						// Set Call Assigned
						employee.setCallAssigned(callInHold);
						LOGGER.log(Level.INFO, String.format("Call in Hold %d assigned to Employee %s. Call Duration %d Seconds", callInHold.getId(), employee.getCode(), callInHold.getDuration()));
					
					} else {
					
						// Free Employee
						employee.setBusy(false);
					}
				}
			}
		}
	}
}