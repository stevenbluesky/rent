package com.rent.entity;

import java.util.Date;

/**
 * PrhRentalMonth entity. @author MyEclipse Persistence Tools
 */

public class PrhRentalMonth implements java.io.Serializable {

	// Fields

	private Integer id;
	private String accnt;
	private String sta;
	private String build;
	private String floor;
	private String roomno;
	private String oroomno;
	private String gname;
	private String cusno;
	private String company;
	private String prcode;
	private Date arr;
	private Date dep;
	private Date deptoo;
	private Date depapply;
	private Date rdate;
	private Integer rate;
	private Integer rate0;
	private Integer paid;
	private Integer ypaid;
	private Integer yrate;
	private Integer arrpaid;
	private Integer prepaid;
	private Integer xrate0;
	private Integer xpaid;
	private Integer paid90;
	private Integer paid91;
	private Integer paid99;
	private String refer;
	private String arearef;
	private String title;
	private Date begdate;
	private Date enddate;
	private String rmode;
	private Integer paid100;
	private Integer paid101;
	private String paidref;

	// Constructors

	/** default constructor */
	public PrhRentalMonth() {
	}

	/** minimal constructor */
	public PrhRentalMonth(String accnt) {
		this.accnt = accnt;
	}

	/** full constructor */
	public PrhRentalMonth(String accnt, String sta, String build, String floor,
			String roomno, String oroomno, String gname, String cusno,
			String company, String prcode, Date arr, Date dep, Date deptoo,
			Date depapply, Date rdate, Integer rate, Integer rate0,
			Integer paid, Integer ypaid, Integer yrate, Integer arrpaid,
			Integer prepaid, Integer xrate0, Integer xpaid, Integer paid90,
			Integer paid91, Integer paid99, String refer, String arearef,
			String title, Date begdate, Date enddate, String rmode,
			Integer paid100, Integer paid101, String paidref) {
		this.accnt = accnt;
		this.sta = sta;
		this.build = build;
		this.floor = floor;
		this.roomno = roomno;
		this.oroomno = oroomno;
		this.gname = gname;
		this.cusno = cusno;
		this.company = company;
		this.prcode = prcode;
		this.arr = arr;
		this.dep = dep;
		this.deptoo = deptoo;
		this.depapply = depapply;
		this.rdate = rdate;
		this.rate = rate;
		this.rate0 = rate0;
		this.paid = paid;
		this.ypaid = ypaid;
		this.yrate = yrate;
		this.arrpaid = arrpaid;
		this.prepaid = prepaid;
		this.xrate0 = xrate0;
		this.xpaid = xpaid;
		this.paid90 = paid90;
		this.paid91 = paid91;
		this.paid99 = paid99;
		this.refer = refer;
		this.arearef = arearef;
		this.title = title;
		this.begdate = begdate;
		this.enddate = enddate;
		this.rmode = rmode;
		this.paid100 = paid100;
		this.paid101 = paid101;
		this.paidref = paidref;
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

	public String getSta() {
		return this.sta;
	}

	public void setSta(String sta) {
		this.sta = sta;
	}

	public String getBuild() {
		return this.build;
	}

	public void setBuild(String build) {
		this.build = build;
	}

	public String getFloor() {
		return this.floor;
	}

	public void setFloor(String floor) {
		this.floor = floor;
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

	public String getGname() {
		return this.gname;
	}

	public void setGname(String gname) {
		this.gname = gname;
	}

	public String getCusno() {
		return this.cusno;
	}

	public void setCusno(String cusno) {
		this.cusno = cusno;
	}

	public String getCompany() {
		return this.company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getPrcode() {
		return this.prcode;
	}

	public void setPrcode(String prcode) {
		this.prcode = prcode;
	}

	public Date getArr() {
		return this.arr;
	}

	public void setArr(Date arr) {
		this.arr = arr;
	}

	public Date getDep() {
		return this.dep;
	}

	public void setDep(Date dep) {
		this.dep = dep;
	}

	public Date getDeptoo() {
		return this.deptoo;
	}

	public void setDeptoo(Date deptoo) {
		this.deptoo = deptoo;
	}

	public Date getDepapply() {
		return this.depapply;
	}

	public void setDepapply(Date depapply) {
		this.depapply = depapply;
	}

	public Date getRdate() {
		return this.rdate;
	}

	public void setRdate(Date rdate) {
		this.rdate = rdate;
	}

	public Integer getRate() {
		return this.rate;
	}

	public void setRate(Integer rate) {
		this.rate = rate;
	}

	public Integer getRate0() {
		return this.rate0;
	}

	public void setRate0(Integer rate0) {
		this.rate0 = rate0;
	}

	public Integer getPaid() {
		return this.paid;
	}

	public void setPaid(Integer paid) {
		this.paid = paid;
	}

	public Integer getYpaid() {
		return this.ypaid;
	}

	public void setYpaid(Integer ypaid) {
		this.ypaid = ypaid;
	}

	public Integer getYrate() {
		return this.yrate;
	}

	public void setYrate(Integer yrate) {
		this.yrate = yrate;
	}

	public Integer getArrpaid() {
		return this.arrpaid;
	}

	public void setArrpaid(Integer arrpaid) {
		this.arrpaid = arrpaid;
	}

	public Integer getPrepaid() {
		return this.prepaid;
	}

	public void setPrepaid(Integer prepaid) {
		this.prepaid = prepaid;
	}

	public Integer getXrate0() {
		return this.xrate0;
	}

	public void setXrate0(Integer xrate0) {
		this.xrate0 = xrate0;
	}

	public Integer getXpaid() {
		return this.xpaid;
	}

	public void setXpaid(Integer xpaid) {
		this.xpaid = xpaid;
	}

	public Integer getPaid90() {
		return this.paid90;
	}

	public void setPaid90(Integer paid90) {
		this.paid90 = paid90;
	}

	public Integer getPaid91() {
		return this.paid91;
	}

	public void setPaid91(Integer paid91) {
		this.paid91 = paid91;
	}

	public Integer getPaid99() {
		return this.paid99;
	}

	public void setPaid99(Integer paid99) {
		this.paid99 = paid99;
	}

	public String getRefer() {
		return this.refer;
	}

	public void setRefer(String refer) {
		this.refer = refer;
	}

	public String getArearef() {
		return this.arearef;
	}

	public void setArearef(String arearef) {
		this.arearef = arearef;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Date getBegdate() {
		return this.begdate;
	}

	public void setBegdate(Date begdate) {
		this.begdate = begdate;
	}

	public Date getEnddate() {
		return this.enddate;
	}

	public void setEnddate(Date enddate) {
		this.enddate = enddate;
	}

	public String getRmode() {
		return this.rmode;
	}

	public void setRmode(String rmode) {
		this.rmode = rmode;
	}

	public Integer getPaid100() {
		return this.paid100;
	}

	public void setPaid100(Integer paid100) {
		this.paid100 = paid100;
	}

	public Integer getPaid101() {
		return this.paid101;
	}

	public void setPaid101(Integer paid101) {
		this.paid101 = paid101;
	}

	public String getPaidref() {
		return this.paidref;
	}

	public void setPaidref(String paidref) {
		this.paidref = paidref;
	}

}