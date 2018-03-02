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

			// Free Operators
			hangUpCall(Dispatcher.getInstance().getLstOperator());
			
			// Free Supervisors
			hangUpCall(Dispatcher.getInstance().getLstSupervisor());
			
			// Free Directors
			hangUpCall(Dispatcher.getInstance().getLstDirector());
		}
	}
	
	/**
	 * Hang Up Call
	 * @param lstEmployee List Employee
	 */
	private void hangUpCall(List<Employee> lstEmployee) {
		
		Call callAssigned;
		
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
					
					LOGGER.log(Level.INFO, String.format("Hang Up Call Id: %d. Free Employee: %s", callAssigned.getId(), employee.getCode()));
					
					// Hang Up Call
					employee.setCallAssigned(null);
					
					// Free Employee
					employee.setBusy(false);
				}
			}
		}
	}
}