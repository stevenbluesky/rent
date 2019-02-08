package com.rent.entity;

import java.util.Date;

/**
 * PrhRmdev entity. @author MyEclipse Persistence Tools
 */

public class PrhRmdev implements java.io.Serializable {

	// Fields

	private Integer id;
	private String roomno;
	private String sta;
	private String code;
	private String devclass;
	private String devname;
	private Integer devnumb;
	private String brand;
	private String model;
	private String standard;
	private String color;
	private Double price;
	private Date buydate;
	private Date mntdate;
	private Double drate;
	private Double nprice;
	private String refer;
	private String logid;
	private String logshift;
	private String logip;
	private Date logdate;
	private Date logupdate;

	// Constructors

	/** default constructor */
	public PrhRmdev() {
	}

	/** minimal constructor */
	public PrhRmdev(String roomno, String sta, Double price, Double drate,
			Double nprice) {
		this.roomno = roomno;
		this.sta = sta;
		this.price = price;
		this.drate = drate;
		this.nprice = nprice;
	}

	/** full constructor */
	public PrhRmdev(String roomno, String sta, String code, String devclass,
			String devname, Integer devnumb, String brand, String model,
			String standard, String color, Double price, Date buydate,
			Date mntdate, Double drate, Double nprice, String refer,
			String logid, String logshift, String logip, Date logdate,
			Date logupdate) {
		this.roomno = roomno;
		this.sta = sta;
		this.code = code;
		this.devclass = devclass;
		this.devname = devname;
		this.devnumb = devnumb;
		this.brand = brand;
		this.model = model;
		this.standard = standard;
		this.color = color;
		this.price = price;
		this.buydate = buydate;
		this.mntdate = mntdate;
		this.drate = drate;
		this.nprice = nprice;
		this.refer = refer;
		this.logid = logid;
		this.logshift = logshift;
		this.logip = logip;
		this.logdate = logdate;
		this.logupdate = logupdate;
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

	public String getSta() {
		return this.sta;
	}

	public void setSta(String sta) {
		this.sta = sta;
	}

	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getDevclass() {
		return this.devclass;
	}

	public void setDevclass(String devclass) {
		this.devclass = devclass;
	}

	public String getDevname() {
		return this.devname;
	}

	public void setDevname(String devname) {
		this.devname = devname;
	}

	public Integer getDevnumb() {
		return this.devnumb;
	}

	public void setDevnumb(Integer devnumb) {
		this.devnumb = devnumb;
	}

	public String getBrand() {
		return this.brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getModel() {
		return this.model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getStandard() {
		return this.standard;
	}

	public void setStandard(String standard) {
		this.standard = standard;
	}

	public String getColor() {
		return this.color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public Double getPrice() {
		return this.price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Date getBuydate() {
		return this.buydate;
	}

	public void setBuydate(Date buydate) {
		this.buydate = buydate;
	}

	public Date getMntdate() {
		return this.mntdate;
	}

	public void setMntdate(Date mntdate) {
		this.mntdate = mntdate;
	}

	public Double getDrate() {
		return this.drate;
	}

	public void setDrate(Double drate) {
		this.drate = drate;
	}

	public Double getNprice() {
		return this.nprice;
	}

	public void setNprice(Double nprice) {
		this.nprice = nprice;
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

	public String getLogshift() {
		return this.logshift;
	}

	public void setLogshift(String logshift) {
		this.logshift = logshift;
	}

	public String getLogip() {
		return this.logip;
	}

	public void setLogip(String logip) {
		this.logip = logip;
	}

	public Date getLogdate() {
		return this.logdate;
	}

	public void setLogdate(Date logdate) {
		this.logdate = logdate;
	}

	public Date getLogupdate() {
		return this.logupdate;
	}

	public void setLogupdate(Date logupdate) {
		this.logupdate = logupdate;
	}

}