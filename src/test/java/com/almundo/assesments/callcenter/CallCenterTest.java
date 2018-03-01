package com.almundo.assesments.callcenter;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit Test for Call Center
 */
public class CallCenterTest extends TestCase {
    
	/**
     * Create Test Call Center App
     * @param testName Test Name
     */
    public CallCenterTest(String testName) {
        
    	super(testName);
    }

    /**
     * Get Suite
     * @return Suite of Tests being tested
     */
    public static Test suite() {
        
    	return new TestSuite(CallCenterTest.class);
    }

    /**
     * Rigourous Test
     */
    public void testApp() {
        
    	assertTrue( true );
    }
}