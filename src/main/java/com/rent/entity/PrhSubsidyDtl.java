package com.rent.entity;

import java.util.Date;

/**
 * PrhSubsidyDtl entity. @author MyEclipse Persistence Tools
 */

public class PrhSubsidyDtl implements java.io.Serializable {

	// Fields

	private Integer id;
	private String bno;
	private Integer bid;
	private String orgid;
	private Date bdate;
	private Date edate;
	private Double amount;
	private String sta;
	private String osta;
	private String flag;
	private Long uamount;
	private String accnt;
	private String roomno;
	private String prtno;
	private String prtno2;
	private String logid;
	private Date logdate;
	private String ulogid;
	private Date ulogdate;
	private Double payable;
	private String paid;
	private Double pamount;
	private String plogid;
	private Date plogdate;
	private String bno2;
	private Integer bid2;
	private Double pbal;
	private Date pdate;
	private String pbalid;
	private String audit1;
	private String audit2;
	private String auditid1;
	private String auditid2;

	// Constructors

	/** default constructor */
	public PrhSubsidyDtl() {
	}

	/** minimal constructor */
	public PrhSubsidyDtl(String bno, Integer bid) {
		this.bno = bno;
		this.bid = bid;
	}

	/** full constructor */
	public PrhSubsidyDtl(String bno, Integer bid, String orgid, Date bdate,
			Date edate, Double amount, String sta, String osta, String flag,
			Long uamount, String accnt, String roomno, String prtno,
			String prtno2, String logid, Date logdate, String ulogid,
			Date ulogdate, Double payable, String paid, Double pamount,
			String plogid, Date plogdate, String bno2, Integer bid2,
			Double pbal, Date pdate, String pbalid, String audit1,
			String audit2, String auditid1, String auditid2) {
		this.bno = bno;
		this.bid = bid;
		this.orgid = orgid;
		this.bdate = bdate;
		this.edate = edate;
		this.amount = amount;
		this.sta = sta;
		this.osta = osta;
		this.flag = flag;
		this.uamount = uamount;
		this.accnt = accnt;
		this.roomno = roomno;
		this.prtno = prtno;
		this.prtno2 = prtno2;
		this.logid = logid;
		this.logdate = logdate;
		this.ulogid = ulogid;
		this.ulogdate = ulogdate;
		this.payable = payable;
		this.paid = paid;
		this.pamount = pamount;
		this.plogid = plogid;
		this.plogdate = plogdate;
		this.bno2 = bno2;
		this.bid2 = bid2;
		this.pbal = pbal;
		this.pdate = pdate;
		this.pbalid = pbalid;
		this.audit1 = audit1;
		this.audit2 = audit2;
		this.auditid1 = auditid1;
		this.auditid2 = auditid2;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getBno() {
		return this.bno;
	}

	public void setBno(String bno) {
		this.bno = bno;
	}

	public Integer getBid() {
		return this.bid;
	}

	public void setBid(Integer bid) {
		this.bid = bid;
	}

	public String getOrgid() {
		return this.orgid;
	}

	public void setOrgid(String orgid) {
		this.orgid = orgid;
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

	public String getSta() {
		return this.sta;
	}

	public void setSta(String sta) {
		this.sta = sta;
	}

	public String getOsta() {
		return this.osta;
	}

	public void setOsta(String osta) {
		this.osta = osta;
	}

	public String getFlag() {
		return this.flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public Long getUamount() {
		return this.uamount;
	}

	public void setUamount(Long uamount) {
		this.uamount = uamount;
	}

	public String getAccnt() {
		return this.accnt;
	}

	public void setAccnt(String accnt) {
		this.accnt = accnt;
	}

	public String getRoomno() {
		return this.roomno;
	}

	public void setRoomno(String roomno) {
		this.roomno = roomno;
	}

	public String getPrtno() {
		return this.prtno;
	}

	public void setPrtno(String prtno) {
		this.prtno = prtno;
	}

	public String getPrtno2() {
		return this.prtno2;
	}

	public void setPrtno2(String prtno2) {
		this.prtno2 = prtno2;
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

	public String getUlogid() {
		return this.ulogid;
	}

	public void setUlogid(String ulogid) {
		this.ulogid = ulogid;
	}

	public Date getUlogdate() {
		return this.ulogdate;
	}

	public void setUlogdate(Date ulogdate) {
		this.ulogdate = ulogdate;
	}

	public Double getPayable() {
		return this.payable;
	}

	public void setPayable(Double payable) {
		this.payable = payable;
	}

	public String getPaid() {
		return this.paid;
	}

	public void setPaid(String paid) {
		this.paid = paid;
	}

	public Double getPamount() {
		return this.pamount;
	}

	public void setPamount(Double pamount) {
		this.pamount = pamount;
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

	public String getBno2() {
		return this.bno2;
	}

	public void setBno2(String bno2) {
		this.bno2 = bno2;
	}

	public Integer getBid2() {
		return this.bid2;
	}

	public void setBid2(Integer bid2) {
		this.bid2 = bid2;
	}

	public Double getPbal() {
		return this.pbal;
	}

	public void setPbal(Double pbal) {
		this.pbal = pbal;
	}

	public Date getPdate() {
		return this.pdate;
	}

	public void setPdate(Date pdate) {
		this.pdate = pdate;
	}

	public String getPbalid() {
		return this.pbalid;
	}

	public void setPbalid(String pbalid) {
		this.pbalid = pbalid;
	}

	public String getAudit1() {
		return this.audit1;
	}

	public void setAudit1(String audit1) {
		this.audit1 = audit1;
	}

	public String getAudit2() {
		return this.audit2;
	}

	public void setAudit2(String audit2) {
		this.audit2 = audit2;
	}

	public String getAuditid1() {
		return this.auditid1;
	}

	public void setAuditid1(String auditid1) {
		this.auditid1 = auditid1;
	}

	public String getAuditid2() {
		return this.auditid2;
	}

	public void setAuditid2(String auditid2) {
		this.auditid2 = auditid2;
	}

}