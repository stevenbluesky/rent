package com.rent.entity;

import java.util.Date;

/**
 * PrhRmrateAdjust entity. @author MyEclipse Persistence Tools
 */

public class PrhRmrateAdjust implements java.io.Serializable {

	// Fields

	private Integer id;
	private String roomno;
	private String oroomno;
	private String vroomno;
	private String area;
	private String build;
	private String type;
	private Double area0;
	private Double rate0;
	private Double rate;
	private Double nrate0;
	private Double nrate;
	private String sta;
	private Date bdate;
	private String refer;
	private String logid;
	private Date logdate;
	private String done;
	private Double setrate;
	private Double nsetrate;

	// Constructors

	/** default constructor */
	public PrhRmrateAdjust() {
	}

	/** minimal constructor */
	public PrhRmrateAdjust(String roomno, String oroomno, String vroomno,
			String area, String type, Double area0, Double rate0, Double rate,
			Double nrate0, Double nrate, String sta) {
		this.roomno = roomno;
		this.oroomno = oroomno;
		this.vroomno = vroomno;
		this.area = area;
		this.type = type;
		this.area0 = area0;
		this.rate0 = rate0;
		this.rate = rate;
		this.nrate0 = nrate0;
		this.nrate = nrate;
		this.sta = sta;
	}

	/** full constructor */
	public PrhRmrateAdjust(String roomno, String oroomno, String vroomno,
			String area, String build, String type, Double area0, Double rate0,
			Double rate, Double nrate0, Double nrate, String sta, Date bdate,
			String refer, String logid, Date logdate, String done,
			Double setrate, Double nsetrate) {
		this.roomno = roomno;
		this.oroomno = oroomno;
		this.vroomno = vroomno;
		this.area = area;
		this.build = build;
		this.type = type;
		this.area0 = area0;
		this.rate0 = rate0;
		this.rate = rate;
		this.nrate0 = nrate0;
		this.nrate = nrate;
		this.sta = sta;
		this.bdate = bdate;
		this.refer = refer;
		this.logid = logid;
		this.logdate = logdate;
		this.done = done;
		this.setrate = setrate;
		this.nsetrate = nsetrate;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public String getVroomno() {
		return this.vroomno;
	}

	public void setVroomno(String vroomno) {
		this.vroomno = vroomno;
	}

	public String getArea() {
		return this.area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getBuild() {
		return this.build;
	}

	public void setBuild(String build) {
		this.build = build;
	}

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Double getArea0() {
		return this.area0;
	}

	public void setArea0(Double area0) {
		this.area0 = area0;
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

	public Double getNrate0() {
		return this.nrate0;
	}

	public void setNrate0(Double nrate0) {
		this.nrate0 = nrate0;
	}

	public Double getNrate() {
		return this.nrate;
	}

	public void setNrate(Double nrate) {
		this.nrate = nrate;
	}

	public String getSta() {
		return this.sta;
	}

	public void setSta(String sta) {
		this.sta = sta;
	}

	public Date getBdate() {
		return this.bdate;
	}

	public void setBdate(Date bdate) {
		this.bdate = bdate;
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

	public String getDone() {
		return this.done;
	}

	public void setDone(String done) {
		this.done = done;
	}

	public Double getSetrate() {
		return this.setrate;
	}

	public void setSetrate(Double setrate) {
		this.setrate = setrate;
	}

	public Double getNsetrate() {
		return this.nsetrate;
	}

	public void setNsetrate(Double nsetrate) {
		this.nsetrate = nsetrate;
	}

}