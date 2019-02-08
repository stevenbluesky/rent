package com.rent.entity;

/**
 * SmsKmobile entity. @author MyEclipse Persistence Tools
 */

public class SmsKmobile implements java.io.Serializable {

	// Fields

	private SmsKmobileId id;

	// Constructors

	/** default constructor */
	public SmsKmobile() {
	}

	/** full constructor */
	public SmsKmobile(SmsKmobileId id) {
		this.id = id;
	}

	// Property accessors

	public SmsKmobileId getId() {
		return this.id;
	}

	public void setId(SmsKmobileId id) {
		this.id = id;
	}

}