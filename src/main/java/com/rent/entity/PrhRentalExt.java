package com.rent.entity;

import java.util.Date;

/**
 * PrhRentalExt entity. @author MyEclipse Persistence Tools
 */

public class PrhRentalExt implements java.io.Serializable {

	// Fields

	private Integer id;
	private String prtno;
	private String yno;
	private String ylogid;
	private Date ylogdate;
	private Integer yptimes;
	private String zno;
	private String zlogid;
	private Date zlogdate;
	private Integer zptimes;
	private String roomno;
	private String areacode;

	// Constructors

	/** default constructor */
	public PrhRentalExt() {
	}

	/** minimal constructor */
	public PrhRentalExt(String prtno) {
		this.prtno = prtno;
	}

	/** full constructor */
	public PrhRentalExt(String prtno, String yno, String ylogid, Date ylogdate,
			Integer yptimes, String zno, String zlogid, Date zlogdate,
			Integer zptimes, String roomno, String areacode) {
		this.prtno = prtno;
		this.yno = yno;
		this.ylogid = ylogid;
		this.ylogdate = ylogdate;
		this.yptimes = yptimes;
		this.zno = zno;
		this.zlogid = zlogid;
		this.zlogdate = zlogdate;
		this.zptimes = zptimes;
		this.roomno = roomno;
		this.areacode = areacode;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getPrtno() {
		return this.prtno;
	}

	public void setPrtno(String prtno) {
		this.prtno = prtno;
	}

	public String getYno() {
		return this.yno;
	}

	public void setYno(String yno) {
		this.yno = yno;
	}

	public String getYlogid() {
		return this.ylogid;
	}

	public void setYlogid(String ylogid) {
		this.ylogid = ylogid;
	}

	public Date getYlogdate() {
		return this.ylogdate;
	}

	public void setYlogdate(Date ylogdate) {
		this.ylogdate = ylogdate;
	}

	public Integer getYptimes() {
		return this.yptimes;
	}

	public void setYptimes(Integer yptimes) {
		this.yptimes = yptimes;
	}

	public String getZno() {
		return this.zno;
	}

	public void setZno(String zno) {
		this.zno = zno;
	}

	public String getZlogid() {
		return this.zlogid;
	}

	public void setZlogid(String zlogid) {
		this.zlogid = zlogid;
	}

	public Date getZlogdate() {
		return this.zlogdate;
	}

	public void setZlogdate(Date zlogdate) {
		this.zlogdate = zlogdate;
	}

	public Integer getZptimes() {
		return this.zptimes;
	}

	public void setZptimes(Integer zptimes) {
		this.zptimes = zptimes;
	}

	public String getRoomno() {
		return this.roomno;
	}

	public void setRoomno(String roomno) {
		this.roomno = roomno;
	}

	public String getAreacode() {
		return this.areacode;
	}

	public void setAreacode(String areacode) {
		this.areacode = areacode;
	}

}