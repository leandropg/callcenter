package com.almundo.assesments.callcenter.util.test;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.almundo.assesments.callcenter.util.RandomUtil;

/**
 * Random Util Test
 */
public class RandomUtilTest {

	/**
     * Test
     */
	@Test
    public void testRangeRandom() {
		
		int randomValue;
		int minValue = 5;
		int maxValue = 10;
		
		// Test 50 times
		for(int i = 0; i < 50; i++) {
		
			// Obtain Random Value
			randomValue = RandomUtil.obtainRandomRangeValue(minValue, maxValue);
			
			// Check if Random Value is in the range
			Assertions.assertTrue(minValue <= randomValue && randomValue <= maxValue);
		}	
	}
}