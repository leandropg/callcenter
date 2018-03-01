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
	 */
	@Override
	public String format(LogRecord record) {
		
		Calendar calendar;
		SimpleDateFormat simpleDateFormat;
		StringBuilder sb;
		
		// Configure Simple Date Format
		simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd - HH:mm:ss");
		
		// Obtain Calendar Instance
		calendar = Calendar.getInstance();
		
		// Init Buffer
		sb = new StringBuilder();
		sb.append(simpleDateFormat.format(calendar.getTime()));
		sb.append(" [");
		sb.append(record.getLevel().getName());
		sb.append("] > ");
		sb.append(record.getMessage());
		return sb.toString();
	}

	
}