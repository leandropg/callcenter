package com.almundo.assesments.callcenter.util;

import java.util.Random;

/**
 * Random Util
 */
public class RandomUtil {

	/**
	 * Avoid Create Objects
	 */
	private RandomUtil( ) {
		
	}
	
	/**
	 * Obtain Random Range Value
	 * @param lowBound Low Bound
	 * @param highBound High Bound
	 * @return Random Value
	 */
	public static int obtainRandomRangeValue(int lowBound, int highBound) {
		
		Random random;
		int randomValue;

		// Obtain Random Value in the Range
		random = new Random(); 
		randomValue = random.nextInt(highBound - lowBound + 1) + lowBound;		
		
		// Return Random Value
		return randomValue;
	}
}