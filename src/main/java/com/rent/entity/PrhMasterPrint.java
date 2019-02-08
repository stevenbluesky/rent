package com.rent.entity;

import java.util.Date;

/**
 * PrhMasterPrint entity. @author MyEclipse Persistence Tools
 */

public class PrhMasterPrint implements java.io.Serializable {

	// Fields

	private Integer id;
	private String accnt;
	private String guestno;
	private String name;
	private String sex;
	private String idno;
	private String mobile;
	private String street;
	private String zip;
	private String company;
	private String roomno;
	private String vroomno;
	private String rtype;
	private Double rarea;
	private Integer rate;
	private String brate;
	private Integer deposit;
	private String bdeposit;
	private Date bdate;
	private Date edate;
	private Date dep1;
	private Date dep;
	private Date today;
	private Integer int1;
	private String rentcode;
	private Date rentdate;
	private String bankflag;
	private String bank;
	private String bankno;
	private String r1;
	private String n1;
	private String i1;
	private String r2;
	private String n2;
	private String i2;
	private String r3;
	private String n3;
	private String i3;
	private String r4;
	private String n4;
	private String i4;
	private String r5;
	private String n5;
	private String i5;

	// Constructors

	/** default constructor */
	public PrhMasterPrint() {
	}

	/** minimal constructor */
	public PrhMasterPrint(String accnt, String guestno, String roomno,
			String rtype) {
		this.accnt = accnt;
		this.guestno = guestno;
		this.roomno = roomno;
		this.rtype = rtype;
	}

	/** full constructor */
	public PrhMasterPrint(String accnt, String guestno, String name,
			String sex, String idno, String mobile, String street, String zip,
			String company, String roomno, String vroomno, String rtype,
			Double rarea, Integer rate, String brate, Integer deposit,
			String bdeposit, Date bdate, Date edate, Date dep1, Date dep,
			Date today, Integer int1, String rentcode, Date rentdate,
			String bankflag, String bank, String bankno, String r1, String n1,
			String i1, String r2, String n2, String i2, String r3, String n3,
			String i3, String r4, String n4, String i4, String r5, String n5,
			String i5) {
		this.accnt = accnt;
		this.guestno = guestno;
		this.name = name;
		this.sex = sex;
		this.idno = idno;
		this.mobile = mobile;
		this.street = street;
		this.zip = zip;
		this.company = company;
		this.roomno = roomno;
		this.vroomno = vroomno;
		this.rtype = rtype;
		this.rarea = rarea;
		this.rate = rate;
		this.brate = brate;
		this.deposit = deposit;
		this.bdeposit = bdeposit;
		this.bdate = bdate;
		this.edate = edate;
		this.dep1 = dep1;
		this.dep = dep;
		this.today = today;
		this.int1 = int1;
		this.rentcode = rentcode;
		this.rentdate = rentdate;
		this.bankflag = bankflag;
		this.bank = bank;
		this.bankno = bankno;
		this.r1 = r1;
		this.n1 = n1;
		this.i1 = i1;
		this.r2 = r2;
		this.n2 = n2;
		this.i2 = i2;
		this.r3 = r3;
		this.n3 = n3;
		this.i3 = i3;
		this.r4 = r4;
		this.n4 = n4;
		this.i4 = i4;
		this.r5 = r5;
		this.n5 = n5;
		this.i5 = i5;
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

	public String getGuestno() {
		return this.guestno;
	}

	public void setGuestno(String guestno) {
		this.guestno = guestno;
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

	public String getIdno() {
		return this.idno;
	}

	public void setIdno(String idno) {
		this.idno = idno;
	}

	public String getMobile() {
		return this.mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
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

	public String getCompany() {
		return this.company;
	}

	public void setCompany(String company) {
		this.company = company;
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

	public String getRtype() {
		return this.rtype;
	}

	public void setRtype(String rtype) {
		this.rtype = rtype;
	}

	public Double getRarea() {
		return this.rarea;
	}

	public void setRarea(Double rarea) {
		this.rarea = rarea;
	}

	public Integer getRate() {
		return this.rate;
	}

	public void setRate(Integer rate) {
		this.rate = rate;
	}

	public String getBrate() {
		return this.brate;
	}

	public void setBrate(String brate) {
		this.brate = brate;
	}

	public Integer getDeposit() {
		return this.deposit;
	}

	public void setDeposit(Integer deposit) {
		this.deposit = deposit;
	}

	public String getBdeposit() {
		return this.bdeposit;
	}

	public void setBdeposit(String bdeposit) {
		this.bdeposit = bdeposit;
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

	public Date getDep1() {
		return this.dep1;
	}

	public void setDep1(Date dep1) {
		this.dep1 = dep1;
	}

	public Date getDep() {
		return this.dep;
	}

	public void setDep(Date dep) {
		this.dep = dep;
	}

	public Date getToday() {
		return this.today;
	}

	public void setToday(Date today) {
		this.today = today;
	}

	public Integer getInt1() {
		return this.int1;
	}

	public void setInt1(Integer int1) {
		this.int1 = int1;
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

	public String getBankflag() {
		return this.bankflag;
	}

	public void setBankflag(String bankflag) {
		this.bankflag = bankflag;
	}

	public String getBank() {
		return this.bank;
	}

	public void setBank(String bank) {
		this.bank = bank;
	}

	public String getBankno() {
		return this.bankno;
	}

	public void setBankno(String bankno) {
		this.bankno = bankno;
	}

	public String getR1() {
		return this.r1;
	}

	public void setR1(String r1) {
		this.r1 = r1;
	}

	public String getN1() {
		return this.n1;
	}

	public void setN1(String n1) {
		this.n1 = n1;
	}

	public String getI1() {
		return this.i1;
	}

	public void setI1(String i1) {
		this.i1 = i1;
	}

	public String getR2() {
		return this.r2;
	}

	public void setR2(String r2) {
		this.r2 = r2;
	}

	public String getN2() {
		return this.n2;
	}

	public void setN2(String n2) {
		this.n2 = n2;
	}

	public String getI2() {
		return this.i2;
	}

	public void setI2(String i2) {
		this.i2 = i2;
	}

	public String getR3() {
		return this.r3;
	}

	public void setR3(String r3) {
		this.r3 = r3;
	}

	public String getN3() {
		return this.n3;
	}

	public void setN3(String n3) {
		this.n3 = n3;
	}

	public String getI3() {
		return this.i3;
	}

	public void setI3(String i3) {
		this.i3 = i3;
	}

	public String getR4() {
		return this.r4;
	}

	public void setR4(String r4) {
		this.r4 = r4;
	}

	public String getN4() {
		return this.n4;
	}

	public void setN4(String n4) {
		this.n4 = n4;
	}

	public String getI4() {
		return this.i4;
	}

	public void setI4(String i4) {
		this.i4 = i4;
	}

	public String getR5() {
		return this.r5;
	}

	public void setR5(String r5) {
		this.r5 = r5;
	}

	public String getN5() {
		return this.n5;
	}

	public void setN5(String n5) {
		this.n5 = n5;
	}

	public String getI5() {
		return this.i5;
	}

	public void setI5(String i5) {
		this.i5 = i5;
	}

}