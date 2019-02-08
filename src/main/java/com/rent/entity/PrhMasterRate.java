package com.rent.entity;

import java.util.Date;

/**
 * PrhMasterRate entity. @author MyEclipse Persistence Tools
 */

public class PrhMasterRate implements java.io.Serializable {

	// Fields

	private Integer id;
	private String accnt;
	private Date bdate;
	private Date edate;
	private Double rate;
	private String refer;
	private String logid;
	private Date logdate;

	// Constructors

	/** default constructor */
	public PrhMasterRate() {
	}

	/** minimal constructor */
	public PrhMasterRate(String accnt, Date bdate, Date edate, Double rate) {
		this.accnt = accnt;
		this.bdate = bdate;
		this.edate = edate;
		this.rate = rate;
	}

	/** full constructor */
	public PrhMasterRate(String accnt, Date bdate, Date edate, Double rate,
			String refer, String logid, Date logdate) {
		this.accnt = accnt;
		this.bdate = bdate;
		this.edate = edate;
		this.rate = rate;
		this.refer = refer;
		this.logid = logid;
		this.logdate = logdate;
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

	public Date getEdate() {
		return this.edate;
	}

	public void setEdate(Date edate) {
		this.edate = edate;
	}

	public Double getRate() {
		return this.rate;
	}

	public void setRate(Double rate) {
		this.rate = rate;
	}

	public String getRefer() {
		return this.refer;
	}

	public void setRefer(String refer) {
		this.refer = refer;
	}

	public String getLogid() {
		return this.logid;
	}

	public void setLogid(String logid) {
		this.logid = logid;
	}

	public Date getLogdate() {
		return this.logdate;
	}

	public void setLogdate(Date logdate) {
		this.logdate = logdate;
	}

}