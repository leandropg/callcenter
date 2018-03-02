package com.almundo.assesments.callcenter.model;

/**
 * Call
 */
public class Call {

	/**
	 * Id
	 */
	private int id;

	/**
	 * Duration
	 */
	private int duration;
	
	/**
	 * Call
	 * @param id Id
	 * @param duration Duration
	 */
	public Call(int id, int duration) {
		
		this.id = id;
		this.duration = duration;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}
}