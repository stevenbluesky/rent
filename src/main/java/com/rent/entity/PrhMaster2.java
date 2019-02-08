package com.rent.entity;

import java.math.BigDecimal;
import java.util.Date;

/**
 * PrhMaster2 entity. @author MyEclipse Persistence Tools
 */

public class PrhMaster2 implements java.io.Serializable {

	// Fields

	private Integer id;
	private String accnt;
	private String orgid;
	private String sta;
	private String name;
	private String sex;
	private String idcls;
	private String idno;
	private String city;
	private String street;
	private String zip;
	private String mobile;
	private String phone;
	private String email;
	private String company;
	private String coaddr;
	private String cophone;
	private String payname;
	private String payidno;
	private String bank;
	private String bankref;
	private String bankno;
	private String class_;
	private String src;
	private String fileno;
	private Date bdate;
	private Date edate;
	private String type;
	private String roomno;
	private String oroomno;
	private String vroomno;
	private Double rate;
	private Double setrate;
	private String reason;
	private Date arr;
	private Date dep;
	private Date arr1;
	private Date dep1;
	private Double deposit;
	private String rentcode;
	private Date rentdate;
	private String remark;
	private Double payment;
	private Double expend;
	private String crt;
	private Date crttime;
	private String con;
	private Date contime;
	private String co;
	private Date cotime;
	private String refer1;
	private String refer2;
	private String refer3;
	private String refer4;
	private String logid;
	private String logname;
	private Date logdate;
	private String logip;
	private BigDecimal logmark;
	private Date logupdate;

	// Constructors

	/** default constructor */
	public PrhMaster2() {
	}

	/** minimal constructor */
	public PrhMaster2(String accnt, String orgid, String sta, String name,
			String class_, String src) {
		this.accnt = accnt;
		this.orgid = orgid;
		this.sta = sta;
		this.name = name;
		this.class_ = class_;
		this.src = src;
	}

	/** full constructor */
	public PrhMaster2(String accnt, String orgid, String sta, String name,
			String sex, String idcls, String idno, String city, String street,
			String zip, String mobile, String phone, String email,
			String company, String coaddr, String cophone, String payname,
			String payidno, String bank, String bankref, String bankno,
			String class_, String src, String fileno, Date bdate, Date edate,
			String type, String roomno, String oroomno, String vroomno,
			Double rate, Double setrate, String reason, Date arr, Date dep,
			Date arr1, Date dep1, Double deposit, String rentcode,
			Date rentdate, String remark, Double payment, Double expend,
			String crt, Date crttime, String con, Date contime, String co,
			Date cotime, String refer1, String refer2, String refer3,
			String refer4, String logid, String logname, Date logdate,
			String logip, BigDecimal logmark, Date logupdate) {
		this.accnt = accnt;
		this.orgid = orgid;
		this.sta = sta;
		this.name = name;
		this.sex = sex;
		this.idcls = idcls;
		this.idno = idno;
		this.city = city;
		this.street = street;
		this.zip = zip;
		this.mobile = mobile;
		this.phone = phone;
		this.email = email;
		this.company = company;
		this.coaddr = coaddr;
		this.cophone = cophone;
		this.payname = payname;
		this.payidno = payidno;
		this.bank = bank;
		this.bankref = bankref;
		this.bankno = bankno;
		this.class_ = class_;
		this.src = src;
		this.fileno = fileno;
		this.bdate = bdate;
		this.edate = edate;
		this.type = type;
		this.roomno = roomno;
		this.oroomno = oroomno;
		this.vroomno = vroomno;
		this.rate = rate;
		this.setrate = setrate;
		this.reason = reason;
		this.arr = arr;
		this.dep = dep;
		this.arr1 = arr1;
		this.dep1 = dep1;
		this.deposit = deposit;
		this.rentcode = rentcode;
		this.rentdate = rentdate;
		this.remark = remark;
		this.payment = payment;
		this.expend = expend;
		this.crt = crt;
		this.crttime = crttime;
		this.con = con;
		this.contime = contime;
		this.co = co;
		this.cotime = cotime;
		this.refer1 = refer1;
		this.refer2 = refer2;
		this.refer3 = refer3;
		this.refer4 = refer4;
		this.logid = logid;
		this.logname = logname;
		this.logdate = logdate;
		this.logip = logip;
		this.logmark = logmark;
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

	public String getOrgid() {
		return this.orgid;
	}

	public void setOrgid(String orgid) {
		this.orgid = orgid;
	}

	public String getSta() {
		return this.sta;
	}

	public void setSta(String sta) {
		this.sta = sta;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSex() {
		return this.sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getIdcls() {
		return this.idcls;
	}

	public void setIdcls(String idcls) {
		this.idcls = idcls;
	}

	public String getIdno() {
		return this.idno;
	}

	public void setIdno(String idno) {
		this.idno = idno;
	}

	public String getCity() {
		return this.city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getStreet() {
		return this.street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getZip() {
		return this.zip;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}

	public String getMobile() {
		return this.mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getPhone() {
		return this.phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCompany() {
		return this.company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getCoaddr() {
		return this.coaddr;
	}

	public void setCoaddr(String coaddr) {
		this.coaddr = coaddr;
	}

	public String getCophone() {
		return this.cophone;
	}

	public void setCophone(String cophone) {
		this.cophone = cophone;
	}

	public String getPayname() {
		return this.payname;
	}

	public void setPayname(String payname) {
		this.payname = payname;
	}

	public String getPayidno() {
		return this.payidno;
	}

	public void setPayidno(String payidno) {
		this.payidno = payidno;
	}

	public String getBank() {
		return this.bank;
	}

	public void setBank(String bank) {
		this.bank = bank;
	}

	public String getBankref() {
		return this.bankref;
	}

	public void setBankref(String bankref) {
		this.bankref = bankref;
	}

	public String getBankno() {
		return this.bankno;
	}

	public void setBankno(String bankno) {
		this.bankno = bankno;
	}

	public String getClass_() {
		return this.class_;
	}

	public void setClass_(String class_) {
		this.class_ = class_;
	}

	public String getSrc() {
		return this.src;
	}

	public void setSrc(String src) {
		this.src = src;
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

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
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

	public Double getRate() {
		return this.rate;
	}

	public void setRate(Double rate) {
		this.rate = rate;
	}

	public Double getSetrate() {
		return this.setrate;
	}

	public void setSetrate(Double setrate) {
		this.setrate = setrate;
	}

	public String getReason() {
		return this.reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
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

	public Date getArr1() {
		return this.arr1;
	}

	public void setArr1(Date arr1) {
		this.arr1 = arr1;
	}

	public Date getDep1() {
		return this.dep1;
	}

	public void setDep1(Date dep1) {
		this.dep1 = dep1;
	}

	public Double getDeposit() {
		return this.deposit;
	}

	public void setDeposit(Double deposit) {
		this.deposit = deposit;
	}

	public String getRentcode() {
		return this.rentcode;
	}

	public void setRentcode(String rentcode) {
		this.rentcode = rentcode;
	}

	public Date getRentdate() {
		return this.rentdate;
	}

	public void setRentdate(Date rentdate) {
		this.rentdate = rentdate;
	}

	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Double getPayment() {
		return this.payment;
	}

	public void setPayment(Double payment) {
		this.payment = payment;
	}

	public Double getExpend() {
		return this.expend;
	}

	public void setExpend(Double expend) {
		this.expend = expend;
	}

	public String getCrt() {
		return this.crt;
	}

	public void setCrt(String crt) {
		this.crt = crt;
	}

	public Date getCrttime() {
		return this.crttime;
	}

	public void setCrttime(Date crttime) {
		this.crttime = crttime;
	}

	public String getCon() {
		return this.con;
	}

	public void setCon(String con) {
		this.con = con;
	}

	public Date getContime() {
		return this.contime;
	}

	public void setContime(Date contime) {
		this.contime = contime;
	}

	public String getCo() {
		return this.co;
	}

	public void setCo(String co) {
		this.co = co;
	}

	public Date getCotime() {
		return this.cotime;
	}

	public void setCotime(Date cotime) {
		this.cotime = cotime;
	}

	public String getRefer1() {
		return this.refer1;
	}

	public void setRefer1(String refer1) {
		this.refer1 = refer1;
	}

	public String getRefer2() {
		return this.refer2;
	}

	public void setRefer2(String refer2) {
		this.refer2 = refer2;
	}

	public String getRefer3() {
		return this.refer3;
	}

	public void setRefer3(String refer3) {
		this.refer3 = refer3;
	}

	public String getRefer4() {
		return this.refer4;
	}

	public void setRefer4(String refer4) {
		this.refer4 = refer4;
	}

	public String getLogid() {
		return this.logid;
	}

	public void setLogid(String logid) {
		this.logid = logid;
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

	public String getLogip() {
		return this.logip;
	}

	public void setLogip(String logip) {
		this.logip = logip;
	}

	public BigDecimal getLogmark() {
		return this.logmark;
	}

	public void setLogmark(BigDecimal logmark) {
		this.logmark = logmark;
	}

	public Date getLogupdate() {
		return this.logupdate;
	}

	public void setLogupdate(Date logupdate) {
		this.logupdate = logupdate;
	}

}