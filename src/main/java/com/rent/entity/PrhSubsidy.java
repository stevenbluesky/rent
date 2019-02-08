package com.rent.entity;

import java.util.Date;

/**
 * PrhSubsidy entity. @author MyEclipse Persistence Tools
 */

public class PrhSubsidy implements java.io.Serializable {

	// Fields

	private String bno;
	private String orgid;
	private String bsta;
	private String btype;
	private String bsno;
	private String bname;
	private String bidno;
	private String bphone;
	private Date bdate;
	private Double brate;
	private Integer bmonths;
	private Integer bdays;
	private Date edate;
	private String brefer;
	private String accnt;
	private String oaccnt;
	private String guestno;
	private String roomno;
	private String logid;
	private Date logdate;
	private String auditid;
	private Date auditdate;
	private String backid;
	private Date backdate;

	// Constructors

	/** default constructor */
	public PrhSubsidy() {
	}

	/** full constructor */
	public PrhSubsidy(String orgid, String bsta, String btype, String bsno,
			String bname, String bidno, String bphone, Date bdate,
			Double brate, Integer bmonths, Integer bdays, Date edate,
			String brefer, String accnt, String oaccnt, String guestno,
			String roomno, String logid, Date logdate, String auditid,
			Date auditdate, String backid, Date backdate) {
		this.orgid = orgid;
		this.bsta = bsta;
		this.btype = btype;
		this.bsno = bsno;
		this.bname = bname;
		this.bidno = bidno;
		this.bphone = bphone;
		this.bdate = bdate;
		this.brate = brate;
		this.bmonths = bmonths;
		this.bdays = bdays;
		this.edate = edate;
		this.brefer = brefer;
		this.accnt = accnt;
		this.oaccnt = oaccnt;
		this.guestno = guestno;
		this.roomno = roomno;
		this.logid = logid;
		this.logdate = logdate;
		this.auditid = auditid;
		this.auditdate = auditdate;
		this.backid = backid;
		this.backdate = backdate;
	}

	// Property accessors

	public String getBno() {
		return this.bno;
	}

	public void setBno(String bno) {
		this.bno = bno;
	}

	public String getOrgid() {
		return this.orgid;
	}

	public void setOrgid(String orgid) {
		this.orgid = orgid;
	}

	public String getBsta() {
		return this.bsta;
	}

	public void setBsta(String bsta) {
		this.bsta = bsta;
	}

	public String getBtype() {
		return this.btype;
	}

	public void setBtype(String btype) {
		this.btype = btype;
	}

	public String getBsno() {
		return this.bsno;
	}

	public void setBsno(String bsno) {
		this.bsno = bsno;
	}

	public String getBname() {
		return this.bname;
	}

	public void setBname(String bname) {
		this.bname = bname;
	}

	public String getBidno() {
		return this.bidno;
	}

	public void setBidno(String bidno) {
		this.bidno = bidno;
	}

	public String getBphone() {
		return this.bphone;
	}

	public void setBphone(String bphone) {
		this.bphone = bphone;
	}

	public Date getBdate() {
		return this.bdate;
	}

	public void setBdate(Date bdate) {
		this.bdate = bdate;
	}

	public Double getBrate() {
		return this.brate;
	}

	public void setBrate(Double brate) {
		this.brate = brate;
	}

	public Integer getBmonths() {
		return this.bmonths;
	}

	public void setBmonths(Integer bmonths) {
		this.bmonths = bmonths;
	}

	public Integer getBdays() {
		return this.bdays;
	}

	public void setBdays(Integer bdays) {
		this.bdays = bdays;
	}

	public Date getEdate() {
		return this.edate;
	}

	public void setEdate(Date edate) {
		this.edate = edate;
	}

	public String getBrefer() {
		return this.brefer;
	}

	public void setBrefer(String brefer) {
		this.brefer = brefer;
	}

	public String getAccnt() {
		return this.accnt;
	}

	public void setAccnt(String accnt) {
		this.accnt = accnt;
	}

	public String getOaccnt() {
		return this.oaccnt;
	}

	public void setOaccnt(String oaccnt) {
		this.oaccnt = oaccnt;
	}

	public String getGuestno() {
		return this.guestno;
	}

	public void setGuestno(String guestno) {
		this.guestno = guestno;
	}

	public String getRoomno() {
		return this.roomno;
	}

	public void setRoomno(String roomno) {
		this.roomno = roomno;
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

	public String getAuditid() {
		return this.auditid;
	}

	public void setAuditid(String auditid) {
		this.auditid = auditid;
	}

	public Date getAuditdate() {
		return this.auditdate;
	}

	public void setAuditdate(Date auditdate) {
		this.auditdate = auditdate;
	}

	public String getBackid() {
		return this.backid;
	}

	public void setBackid(String backid) {
		this.backid = backid;
	}

	public Date getBackdate() {
		return this.backdate;
	}

	public void setBackdate(Date backdate) {
		this.backdate = backdate;
	}

}