package com.rent.entity;

import java.util.Date;

/**
 * PrhBalDef entity. @author MyEclipse Persistence Tools
 */

public class PrhBalDef implements java.io.Serializable {

	// Fields

	private Integer id;
	private String accnt;
	private Date bdate;
	private Double rate0;
	private Double rate;

	// Constructors

	/** default constructor */
	public PrhBalDef() {
	}

	/** full constructor */
	public PrhBalDef(String accnt, Date bdate, Double rate0, Double rate) {
		this.accnt = accnt;
		this.bdate = bdate;
		this.rate0 = rate0;
		this.rate = rate;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getAccnt() {
		return this.accnt;
	}

	public void setAccnt(String accnt) {
		this.accnt = accnt;
	}

	public Date getBdate() {
		return this.bdate;
	}

	public void setBdate(Date bdate) {
		this.bdate = bdate;
	}

	public Double getRate0() {
		return this.rate0;
	}

	public void setRate0(Double rate0) {
		this.rate0 = rate0;
	}

	public Double getRate() {
		return this.rate;
	}

	public void setRate(Double rate) {
		this.rate = rate;
	}

}