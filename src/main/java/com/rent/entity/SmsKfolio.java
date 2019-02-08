package com.rent.entity;

/**
 * SmsKfolio entity. @author MyEclipse Persistence Tools
 */

public class SmsKfolio implements java.io.Serializable {

	// Fields

	private SmsKfolioId id;

	// Constructors

	/** default constructor */
	public SmsKfolio() {
	}

	/** full constructor */
	public SmsKfolio(SmsKfolioId id) {
		this.id = id;
	}

	// Property accessors

	public SmsKfolioId getId() {
		return this.id;
	}

	public void setId(SmsKfolioId id) {
		this.id = id;
	}

}