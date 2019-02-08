package com.rent.entity;

import java.util.Date;

/**
 * PrhFree entity. @author MyEclipse Persistence Tools
 */

public class PrhFree implements java.io.Serializable {

	// Fields

	private Integer id;
	private String accnt;
	private Date bdate;
	private Date edate;
	private Double amount;
	private String logid;
	private Date logdate;
	private String prtno;

	// Constructors

	/** default constructor */
	public PrhFree() {
	}

	/** full constructor */
	public PrhFree(String accnt, Date bdate, Date edate, Double amount,
			String logid, Date logdate, String prtno) {
		this.accnt = accnt;
		this.bdate = bdate;
		this.edate = edate;
		this.amount = amount;
		this.logid = logid;
		this.logdate = logdate;
		this.prtno = prtno;
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

	public Double getAmount() {
		return this.amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
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

	public String getPrtno() {
		return this.prtno;
	}

	public void setPrtno(String prtno) {
		this.prtno = prtno;
	}

}