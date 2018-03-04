package com.almundo.assesments.callcenter.log;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.logging.Formatter;
import java.util.logging.LogRecord;

/**
 * Log Formatter
 */
public class LogFormatter extends Formatter {

	/**
	 * Log Format
	 * @param logRecord Log Record
	 */
	@Override
	public String format(LogRecord logRecord) {
		
		Calendar calendar;
		SimpleDateFormat simpleDateFormat;
		String message;
		StringBuilder sb;
		StringBuilder token;
		Object [] parameters;
		
		// Configure Simple Date Format
		simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd - HH:mm:ss");
		
		// Obtain Calendar Instance
		calendar = Calendar.getInstance();
		
		// Init Buffer
		sb = new StringBuilder();
		sb.append(simpleDateFormat.format(calendar.getTime()));
		sb.append(" [");
		sb.append(logRecord.getLevel().getName());
		sb.append("] > ");
		
		// Obtain Message
		message = logRecord.getMessage();
		
		// Get Parameters
		parameters = logRecord.getParameters();
		
		if(parameters != null) {
	
			// Iterate over all Parameters
			for(int i = 0; i < parameters.length; i++) {
				
				// Obtain Token to replace
				token = new StringBuilder();
				token.append("{");
				token.append(i);
				token.append("}");
				
				// Replace Token
				message = message.replace(token, String.valueOf(parameters[i]));
			}
		}
		sb.append(message);
		sb.append("\n");
		return sb.toString();
	}

	
}