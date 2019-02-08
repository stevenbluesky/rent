package com.rent.entity;

import java.util.Date;

/**
 * PrhRmooo entity. @author MyEclipse Persistence Tools
 */

public class PrhRmooo implements java.io.Serializable {

	// Fields

	private String no;
	private String orgid;
	private String area;
	private String roomno;
	private String vroomno;
	private String sta;
	private String sno;
	private String devno;
	private String accnt;
	private String reason;
	private String remark;
	private String refer;
	private Date bdate;
	private Date edate;
	private String logid;
	private Date logdate;
	private String logid1;
	private Date logdate1;
	private String maintgrp;
	private String maintusr;
	private Double maintfee;

	// Constructors

	/** default constructor */
	public PrhRmooo() {
	}

	/** minimal constructor */
	public PrhRmooo(String orgid, String area, String roomno, String vroomno,
			String sta, Date bdate, Date edate, String logid, Date logdate) {
		this.orgid = orgid;
		this.area = area;
		this.roomno = roomno;
		this.vroomno = vroomno;
		this.sta = sta;
		this.bdate = bdate;
		this.edate = edate;
		this.logid = logid;
		this.logdate = logdate;
	}

	/** full constructor */
	public PrhRmooo(String orgid, String area, String roomno, String vroomno,
			String sta, String sno, String devno, String accnt, String reason,
			String remark, String refer, Date bdate, Date edate, String logid,
			Date logdate, String logid1, Date logdate1, String maintgrp,
			String maintusr, Double maintfee) {
		this.orgid = orgid;
		this.area = area;
		this.roomno = roomno;
		this.vroomno = vroomno;
		this.sta = sta;
		this.sno = sno;
		this.devno = devno;
		this.accnt = accnt;
		this.reason = reason;
		this.remark = remark;
		this.refer = refer;
		this.bdate = bdate;
		this.edate = edate;
		this.logid = logid;
		this.logdate = logdate;
		this.logid1 = logid1;
		this.logdate1 = logdate1;
		this.maintgrp = maintgrp;
		this.maintusr = maintusr;
		this.maintfee = maintfee;
	}

	// Property accessors

	public String getNo() {
		return this.no;
	}

	public void setNo(String no) {
		this.no = no;
	}

	public String getOrgid() {
		return this.orgid;
	}

	public void setOrgid(String orgid) {
		this.orgid = orgid;
	}

	public String getArea() {
		return this.area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getRoomno() {
		return this.roomno;
	}

	public void setRoomno(String roomno) {
		this.roomno = roomno;
	}

	public String getVroomno() {
		return this.vroomno;
	}

	public void setVroomno(String vroomno) {
		this.vroomno = vroomno;
	}

	public String getSta() {
		return this.sta;
	}

	public void setSta(String sta) {
		this.sta = sta;
	}

	public String getSno() {
		return this.sno;
	}

	public void setSno(String sno) {
		this.sno = sno;
	}

	public String getDevno() {
		return this.devno;
	}

	public void setDevno(String devno) {
		this.devno = devno;
	}

	public String getAccnt() {
		return this.accnt;
	}

	public void setAccnt(String accnt) {
		this.accnt = accnt;
	}

	public String getReason() {
		return this.reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getRefer() {
		return this.refer;
	}

	public void setRefer(String refer) {
		this.refer = refer;
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

	public String getLogid1() {
		return this.logid1;
	}

	public void setLogid1(String logid1) {
		this.logid1 = logid1;
	}

	public Date getLogdate1() {
		return this.logdate1;
	}

	public void setLogdate1(Date logdate1) {
		this.logdate1 = logdate1;
	}

	public String getMaintgrp() {
		return this.maintgrp;
	}

	public void setMaintgrp(String maintgrp) {
		this.maintgrp = maintgrp;
	}

	public String getMaintusr() {
		return this.maintusr;
	}

	public void setMaintusr(String maintusr) {
		this.maintusr = maintusr;
	}

	public Double getMaintfee() {
		return this.maintfee;
	}

	public void setMaintfee(Double maintfee) {
		this.maintfee = maintfee;
	}

}