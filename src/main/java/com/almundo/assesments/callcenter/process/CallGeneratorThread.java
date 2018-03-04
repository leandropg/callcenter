package com.almundo.assesments.callcenter.process;

import com.almundo.assesments.callcenter.model.Call;
import com.almundo.assesments.callcenter.util.DelayUtil;
import com.almundo.assesments.callcenter.util.RandomUtil;

/**
 * Call Generator Thread
 */
public class CallGeneratorThread extends Thread {
	
	/**
	 * Dispatcher Instance
	 */
	private Dispatcher dispatcher;
	
	/**
	 * Maximum Quantity Calls
	 */
	private int maxQtyCalls;
	
	/**
	 * Call Generator
	 * @param dispatcher Dispatcher Instance
	 * @param maxQtyCalls Maximum Quantity Calls
	 */
	public CallGeneratorThread(Dispatcher dispatcher, int maxQtyCalls) {
		
		this.dispatcher = dispatcher;
		
		// Check Maximum Quantity Calls
		if(maxQtyCalls >= 1) {
		
			this.maxQtyCalls = maxQtyCalls;
			
		} else {
			
			this.maxQtyCalls = 1;
		}
	}

	/**
	 * Run Method
	 */
	@Override
	public void run() {
		
		int callId = 1;
		int duration;
		Call incomingCall;
		
		// Infinite Loop
		while(callId <= maxQtyCalls) {
			
			// Delay 500 Milliseconds
			DelayUtil.delayMilliseconds(500);

			// Obtain Call Duration
			duration = RandomUtil.obtainRandomRangeValue(5, 10);
			
			// Create Incoming Call
			incomingCall = new Call(callId++, duration);
			
			// Put Incoming Call into Dispatcher
			dispatcher.dispatchCall(incomingCall);
		}
	}
}