package com.rent.entity;

import java.util.Date;

/**
 * PrhPayment2 entity. @author MyEclipse Persistence Tools
 */

public class PrhPayment2 implements java.io.Serializable {

	// Fields

	private Integer id;
	private String accnt;
	private Integer inumber;
	private String orgid;
	private String prtno;
	private String paycode;
	private String ref;
	private Double credit;
	private String ref1;
	private String ref2;
	private String logid;
	private String logshift;
	private String logname;
	private Date logdate;
	private Date sysdate;
	private String roomno;
	private String oroomno;
	private Double credit1;
	private String tag;
	private Date logupdate;

	// Constructors

	/** default constructor */
	public PrhPayment2() {
	}

	/** minimal constructor */
	public PrhPayment2(String accnt, Integer inumber, String orgid,
			String prtno, String paycode, Double credit, String logid,
			String logname, Date logdate, Date sysdate) {
		this.accnt = accnt;
		this.inumber = inumber;
		this.orgid = orgid;
		this.prtno = prtno;
		this.paycode = paycode;
		this.credit = credit;
		this.logid = logid;
		this.logname = logname;
		this.logdate = logdate;
		this.sysdate = sysdate;
	}

	/** full constructor */
	public PrhPayment2(String accnt, Integer inumber, String orgid,
			String prtno, String paycode, String ref, Double credit,
			String ref1, String ref2, String logid, String logshift,
			String logname, Date logdate, Date sysdate, String roomno,
			String oroomno, Double credit1, String tag, Date logupdate) {
		this.accnt = accnt;
		this.inumber = inumber;
		this.orgid = orgid;
		this.prtno = prtno;
		this.paycode = paycode;
		this.ref = ref;
		this.credit = credit;
		this.ref1 = ref1;
		this.ref2 = ref2;
		this.logid = logid;
		this.logshift = logshift;
		this.logname = logname;
		this.logdate = logdate;
		this.sysdate = sysdate;
		this.roomno = roomno;
		this.oroomno = oroomno;
		this.credit1 = credit1;
		this.tag = tag;
		this.logupdate = logupdate;
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

	public Integer getInumber() {
		return this.inumber;
	}

	public void setInumber(Integer inumber) {
		this.inumber = inumber;
	}

	public String getOrgid() {
		return this.orgid;
	}

	public void setOrgid(String orgid) {
		this.orgid = orgid;
	}

	public String getPrtno() {
		return this.prtno;
	}

	public void setPrtno(String prtno) {
		this.prtno = prtno;
	}

	public String getPaycode() {
		return this.paycode;
	}

	public void setPaycode(String paycode) {
		this.paycode = paycode;
	}

	public String getRef() {
		return this.ref;
	}

	public void setRef(String ref) {
		this.ref = ref;
	}

	public Double getCredit() {
		return this.credit;
	}

	public void setCredit(Double credit) {
		this.credit = credit;
	}

	public String getRef1() {
		return this.ref1;
	}

	public void setRef1(String ref1) {
		this.ref1 = ref1;
	}

	public String getRef2() {
		return this.ref2;
	}

	public void setRef2(String ref2) {
		this.ref2 = ref2;
	}

	public String getLogid() {
		return this.logid;
	}

	public void setLogid(String logid) {
		this.logid = logid;
	}

	public String getLogshift() {
		return this.logshift;
	}

	public void setLogshift(String logshift) {
		this.logshift = logshift;
	}

	public String getLogname() {
		return this.logname;
	}

	public void setLogname(String logname) {
		this.logname = logname;
	}

	public Date getLogdate() {
		return this.logdate;
	}

	public void setLogdate(Date logdate) {
		this.logdate = logdate;
	}

	public Date getSysdate() {
		return this.sysdate;
	}

	public void setSysdate(Date sysdate) {
		this.sysdate = sysdate;
	}

	public String getRoomno() {
		return this.roomno;
	}

	public void setRoomno(String roomno) {
		this.roomno = roomno;
	}

	public String getOroomno() {
		return this.oroomno;
	}

	public void setOroomno(String oroomno) {
		this.oroomno = oroomno;
	}

	public Double getCredit1() {
		return this.credit1;
	}

	public void setCredit1(Double credit1) {
		this.credit1 = credit1;
	}

	public String getTag() {
		return this.tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	public Date getLogupdate() {
		return this.logupdate;
	}

	public void setLogupdate(Date logupdate) {
		this.logupdate = logupdate;
	}

}