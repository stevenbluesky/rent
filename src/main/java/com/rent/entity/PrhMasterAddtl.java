package com.rent.entity;

import java.util.Date;

/**
 * PrhMasterAddtl entity. @author MyEclipse Persistence Tools
 */

public class PrhMasterAddtl implements java.io.Serializable {

	// Fields

	private Integer id;
	private String accnt;
	private String code;
	private String item;
	private String roomno;
	private String ano;
	private Integer aid;
	private Date bdate;
	private Date edate;
	private Double rate;
	private Double pamount;
	private Double amount;
	private String sta;
	private String logid;
	private Date logdate;
	private String prtno;
	private String plogid;
	private Date plogdate;

	// Constructors

	/** default constructor */
	public PrhMasterAddtl() {
	}

	/** minimal constructor */
	public PrhMasterAddtl(String accnt, String code, Integer aid) {
		this.accnt = accnt;
		this.code = code;
		this.aid = aid;
	}

	/** full constructor */
	public PrhMasterAddtl(String accnt, String code, String item,
			String roomno, String ano, Integer aid, Date bdate, Date edate,
			Double rate, Double pamount, Double amount, String sta,
			String logid, Date logdate, String prtno, String plogid,
			Date plogdate) {
		this.accnt = accnt;
		this.code = code;
		this.item = item;
		this.roomno = roomno;
		this.ano = ano;
		this.aid = aid;
		this.bdate = bdate;
		this.edate = edate;
		this.rate = rate;
		this.pamount = pamount;
		this.amount = amount;
		this.sta = sta;
		this.logid = logid;
		this.logdate = logdate;
		this.prtno = prtno;
		this.plogid = plogid;
		this.plogdate = plogdate;
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

	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getItem() {
		return this.item;
	}

	public void setItem(String item) {
		this.item = item;
	}

	public String getRoomno() {
		return this.roomno;
	}

	public void setRoomno(String roomno) {
		this.roomno = roomno;
	}

	public String getAno() {
		return this.ano;
	}

	public void setAno(String ano) {
		this.ano = ano;
	}

	public Integer getAid() {
		return this.aid;
	}

	public void setAid(Integer aid) {
		this.aid = aid;
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

	public Double getPamount() {
		return this.pamount;
	}

	public void setPamount(Double pamount) {
		this.pamount = pamount;
	}

	public Double getAmount() {
		return this.amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public String getSta() {
		return this.sta;
	}

	public void setSta(String sta) {
		this.sta = sta;
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

	public String getPlogid() {
		return this.plogid;
	}

	public void setPlogid(String plogid) {
		this.plogid = plogid;
	}

	public Date getPlogdate() {
		return this.plogdate;
	}

	public void setPlogdate(Date plogdate) {
		this.plogdate = plogdate;
	}

}