package com.almundo.assesments.callcenter.model;

/**
 * Employee
 */
public class Employee {

	/**
	 * Code
	 */
	private int code;
	
	/**
	 * Flag Busy
	 */
	private boolean isBusy;
	
	/**
	 * Call Assigned
	 */
	private Call callAssigned;
	
	/**
	 * Create Employee with Code
	 * @param code Employee Code 
	 */
	public Employee(int code) {
		this.code = code;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public boolean isBusy() {
		return isBusy;
	}

	public void setBusy(boolean isBusy) {
		this.isBusy = isBusy;
	}

	public Call getCallAssigned() {
		return callAssigned;
	}

	public void setCallAssigned(Call callAssigned) {
		this.callAssigned = callAssigned;
	}
}