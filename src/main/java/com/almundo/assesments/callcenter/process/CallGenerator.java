package com.almundo.assesments.callcenter.process;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.almundo.assesments.callcenter.model.Call;

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
		
		int intRandom;
		Calendar calendar;
		Call call;
		Random random;
		SimpleDateFormat sdf;
		String callId;
		
		// Init Random Object
		random = new Random();
		intRandom = random.nextInt(11);
		
		// Set Simple Date Format
		sdf = new SimpleDateFormat("HHmmss");
		
		// Infinite Loop
		while(true) {
			
			try {
				
				// Random Sleep
				Thread.sleep(intRandom * 1000);
				
				// Obtain Calendar Instance
				calendar = Calendar.getInstance();
				
				// Obtain Call Id
				callId = sdf.format(calendar.getTime());
				
				// Create New Call
				LOGGER.log(Level.INFO, String.format("Incoming Call. Ring! Ring! Call Id: %s", callId));
				call = new Call(callId);

			} catch (InterruptedException e) {
				
				LOGGER.log(Level.SEVERE, "Error Random Sleep", e);
			}
			
		}
	}
}