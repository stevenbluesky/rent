package com.rent.entity;

import java.util.Date;

/**
 * PrhMasterRenew entity. @author MyEclipse Persistence Tools
 */

public class PrhMasterRenew implements java.io.Serializable {

	// Fields

	private Integer id;
	private String accnt;
	private String fileno;
	private Date bdate;
	private Date edate;
	private String roomno;
	private Integer rate;
	private Date arr;
	private String mname;
	private String lname;
	private String rmode;
	private String refer;
	private String logid;
	private Date logdate;
	private String nroomno;
	private Integer nrate;

	// Constructors

	/** default constructor */
	public PrhMasterRenew() {
	}

	/** minimal constructor */
	public PrhMasterRenew(String accnt, Date bdate, Date edate, Integer rate) {
		this.accnt = accnt;
		this.bdate = bdate;
		this.edate = edate;
		this.rate = rate;
	}

	/** full constructor */
	public PrhMasterRenew(String accnt, String fileno, Date bdate, Date edate,
			String roomno, Integer rate, Date arr, String mname, String lname,
			String rmode, String refer, String logid, Date logdate,
			String nroomno, Integer nrate) {
		this.accnt = accnt;
		this.fileno = fileno;
		this.bdate = bdate;
		this.edate = edate;
		this.roomno = roomno;
		this.rate = rate;
		this.arr = arr;
		this.mname = mname;
		this.lname = lname;
		this.rmode = rmode;
		this.refer = refer;
		this.logid = logid;
		this.logdate = logdate;
		this.nroomno = nroomno;
		this.nrate = nrate;
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

	public String getFileno() {
		return this.fileno;
	}

	public void setFileno(String fileno) {
		this.fileno = fileno;
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

	public String getRoomno() {
		return this.roomno;
	}

	public void setRoomno(String roomno) {
		this.roomno = roomno;
	}

	public Integer getRate() {
		return this.rate;
	}

	public void setRate(Integer rate) {
		this.rate = rate;
	}

	public Date getArr() {
		return this.arr;
	}

	public void setArr(Date arr) {
		this.arr = arr;
	}

	public String getMname() {
		return this.mname;
	}

	public void setMname(String mname) {
		this.mname = mname;
	}

	public String getLname() {
		return this.lname;
	}

	public void setLname(String lname) {
		this.lname = lname;
	}

	public String getRmode() {
		return this.rmode;
	}

	public void setRmode(String rmode) {
		this.rmode = rmode;
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

	public String getNroomno() {
		return this.nroomno;
	}

	public void setNroomno(String nroomno) {
		this.nroomno = nroomno;
	}

	public Integer getNrate() {
		return this.nrate;
	}

	public void setNrate(Integer nrate) {
		this.nrate = nrate;
	}

}