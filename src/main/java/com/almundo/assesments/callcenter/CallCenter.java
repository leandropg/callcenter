package com.almundo.assesments.callcenter;

import java.util.List;

import com.almundo.assesments.callcenter.model.Employee;
import com.almundo.assesments.callcenter.process.CallGeneratorThread;
import com.almundo.assesments.callcenter.process.Dispatcher;
import com.almundo.assesments.callcenter.process.HangUpCallThread;
import com.almundo.assesments.callcenter.util.DelayUtil;

/**
 * Call Center Application
 */
public class CallCenter {

	/**
	 * Dispatcher
	 */
	private Dispatcher dispatcher;
	
    /**
     * Start Operation
     * @param lstOperator List Operators
     * @param lstSupervisor List Supervisor
     * @param lstDirector List Directors
     * @param maxQtyCalls Maximum Quantity Calls
     */
	public void startOperation(List<Employee> lstOperator, List<Employee> lstSupervisor, List<Employee> lstDirector, int maxQtyCalls) {

		// Create Dispatcher with List Employees
    	dispatcher = new Dispatcher(lstOperator, lstSupervisor, lstDirector);
    	
    	// Start Call Generator Thread
    	new CallGeneratorThread(dispatcher, maxQtyCalls).start();
    	
    	// Start Hang Up Call Thread
    	new HangUpCallThread(dispatcher).start();
    	
    	// Wait Finish Operation
    	do {
    		
    		// Delay 1 Second to Loop
    		DelayUtil.delaySeconds(1);
    		
    	} while(dispatcher.getQtyCallsAttended() < maxQtyCalls);
    }

	/**
	 * Get Dispatcher
	 * @return Dispatcher
	 */
	public Dispatcher getDispatcher() {
		return dispatcher;
	}
}