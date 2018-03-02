package com.almundo.assesments.callcenter.process;

import com.almundo.assesments.callcenter.model.Call;

import util.DelayUtil;
import util.RandomUtil;

/**
 * Call Generator
 */
public class CallGenerator extends Thread {
	
	/**
	 * Run Method
	 */
	@Override
	public void run() {
		super.run();
		
		int callId = 1;
		int duration;
		Call incomingCall;
		
		// Infinite Loop
		while(true) {
			
			// Delay 500 Milliseconds
			DelayUtil.delayMilliseconds(500);

			// Obtain Call Duration
			duration = RandomUtil.obtainRandomRangeValue(5, 10);
			
			// Create Incoming Call
			incomingCall = new Call(callId++, duration);
			
			// Put Incoming Call into Dispatcher
			Dispatcher.getInstance().dispatchCall(incomingCall);
		}
	}
}