package com.almundo.assesments.callcenter.model;

/**
 * Call
 */
public class Call {

	/**
	 * Call Id
	 */
	private String callId;

	/**
	 * Call
	 * @param callId Call Id
	 */
	public Call(String callId) {
		
		this.callId = callId;
	}

	public String getCallId() {
		return callId;
	}

	public void setCallId(String id) {
		this.callId = id;
	}
}