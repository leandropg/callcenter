package com.almundo.assesments.callcenter.process;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.almundo.assesments.callcenter.model.Employee;

/**
 * Free Employee Thread
 */
public class FreeEmployeeThread extends Thread {

	/**
	 * Logger Instance
	 */
	private static final Logger LOGGER = Logger.getLogger(FreeEmployeeThread.class.getName());
	
	/**
	 * Run Method
	 */
	@Override
	public void run() {
		super.run();
		
		// Infinite Loop
		while(true) {
		
			try {
			
				// Sleep Free Employee Thread
				Thread.sleep(5000);
				
				// Free Operators
				freeEmployee(Dispatcher.getInstance().getLstOperator());
				
				// Free Supervisors
				freeEmployee(Dispatcher.getInstance().getLstSupervisor());
				
				// Free Directors
				freeEmployee(Dispatcher.getInstance().getLstDirector());
			
			} catch (InterruptedException e) {
			
				LOGGER.log(Level.SEVERE, "Error Sleep Free Employee Thread", e);
			}
		}
	}
	
	/**
	 * Free Employee
	 * @param lstEmployee
	 */
	private void freeEmployee(List<Employee> lstEmployee) {
		
		// Iterate over all List
		for(Employee employee : lstEmployee) {
			
			LOGGER.log(Level.INFO, String.format("Check Employee Code %s", employee.getCode()));
		}
	}
}