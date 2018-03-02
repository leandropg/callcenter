package com.almundo.assesments.callcenter.process;

import java.util.logging.Level;
import java.util.logging.Logger;

import com.almundo.assesments.callcenter.model.Call;

import util.DelayUtil;
import util.RandomUtil;

/**
 * Call Generator
 */
public class CallGenerator extends Thread {

	/**
	 * Logger Instance
	 */
	private static final Logger LOGGER = Logger.getLogger(CallGenerator.class.getName());
	
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
			LOGGER.log(Level.INFO, String.format("New Incoming Call... Ring! Ring! Id: %d Duration: %d Seconds", incomingCall.getId(), incomingCall.getDuration()));
			
			// Put Incoming Call into Dispatcher
			Dispatcher.getInstance().dispatchCall(incomingCall);
		}
	}
}