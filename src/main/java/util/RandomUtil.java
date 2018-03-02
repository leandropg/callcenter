package util;

import java.util.Random;

/**
 * Random Util
 */
public class RandomUtil {

	/**
	 * Obtain Random Range Value
	 * @param lowBound Low Bound
	 * @param highBound High Bound
	 */
	public static int obtainRandomRangeValue(int lowBound, int highBound) {
		
		Random random;
		int randomValue;

		// Obtain Random Value in the Range
		random = new Random(); 
		randomValue = random.nextInt(highBound - lowBound) + lowBound;		
		
		// Return Random Value
		return randomValue;
	}
}