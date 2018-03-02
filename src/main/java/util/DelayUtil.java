package util;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Delay Util
 */
public class DelayUtil {

	/**
	 * Logger Instance
	 */
	private static final Logger LOGGER = Logger.getLogger(DelayUtil.class.getName());
	
	/**
	 * Delay Milliseconds
	 * @param milliseconds Milliseconds
	 */
	public static void delayMilliseconds(int milliseconds) {
		
		try {
			
			// Sleep in Milliseconds
			Thread.sleep(milliseconds);
		
		} catch (InterruptedException e) {
			
			LOGGER.log(Level.SEVERE, "Error Delay Milliseconds", e);
		}
	}
	
	/**
	 * Delay Seconds
	 * @param seconds Seconds
	 */
	public static void delaySeconds(int seconds) {
		
		try {
			
			// Sleep in Seconds
			Thread.sleep(seconds * 1000);
		
		} catch (InterruptedException e) {
			
			LOGGER.log(Level.SEVERE, "Error Delay Seconds", e);
		}
	}
	
	/**
	 * Delay Random Seconds
	 * @param lowLimit Low Bound
	 * @param highLimit High Bound
	 */
	public static void delayRandomSeconds(int lowBound, int highBound) {
		
		int qtyRandomSeconds;

		// Obtain Quantity Random Seconds
		qtyRandomSeconds = RandomUtil.obtainRandomRangeValue(lowBound, highBound);
		
		// Delay Random Seconds
		delaySeconds(qtyRandomSeconds);
	}
}