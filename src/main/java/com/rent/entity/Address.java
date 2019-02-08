package com.rent.entity;

import java.util.Date;

/**
 * Address entity. @author MyEclipse Persistence Tools
 */

public class Address implements java.io.Serializable {

	// Fields

	private Integer id;
	private String sno;
	private String sta;
	private String type;
	private String tag;
	private String name;
	private String ename;
	private String sex;
	private Date birth;
	private String mobile;
	private String phone;
	private String mobile1;
	private String phone1;
	private String fax;
	private String email;
	private String qq;
	private String msn;
	private String country;
	private String city;
	private String address;
	private String zipcode;
	private String company;
	private String dept;
	private String duty;
	private String title;
	private String refer;
	private String crtid;
	private Date crtdate;
	private String logid;
	private Date logdate;

	// Constructors

	/** default constructor */
	public Address() {
	}

	/** full constructor */
	public Address(String sno, String sta, String type, String tag,
			String name, String ename, String sex, Date birth, String mobile,
			String phone, String mobile1, String phone1, String fax,
			String email, String qq, String msn, String country, String city,
			String address, String zipcode, String company, String dept,
			String duty, String title, String refer, String crtid,
			Date crtdate, String logid, Date logdate) {
		this.sno = sno;
		this.sta = sta;
		this.type = type;
		this.tag = tag;
		this.name = name;
		this.ename = ename;
		this.sex = sex;
		this.birth = birth;
		this.mobile = mobile;
		this.phone = phone;
		this.mobile1 = mobile1;
		this.phone1 = phone1;
		this.fax = fax;
		this.email = email;
		this.qq = qq;
		this.msn = msn;
		this.country = country;
		this.city = city;
		this.address = address;
		this.zipcode = zipcode;
		this.company = company;
		this.dept = dept;
		this.duty = duty;
		this.title = title;
		this.refer = refer;
		this.crtid = crtid;
		this.crtdate = crtdate;
		this.logid = logid;
		this.logdate = logdate;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getSno() {
		return this.sno;
	}

	public void setSno(String sno) {
		this.sno = sno;
	}

	public String getSta() {
		return this.sta;
	}

	public void setSta(String sta) {
		this.sta = sta;
	}

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getTag() {
		return this.tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEname() {
		return this.ename;
	}

	public void setEname(String ename) {
		this.ename = ename;
	}

	public String getSex() {
		return this.sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public Date getBirth() {
		return this.birth;
	}

	public void setBirth(Date birth) {
		this.birth = birth;
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

	public String getMobile1() {
		return this.mobile1;
	}

	public void setMobile1(String mobile1) {
		this.mobile1 = mobile1;
	}

	public String getPhone1() {
		return this.phone1;
	}

	public void setPhone1(String phone1) {
		this.phone1 = phone1;
	}

	public String getFax() {
		return this.fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getQq() {
		return this.qq;
	}

	public void setQq(String qq) {
		this.qq = qq;
	}

	public String getMsn() {
		return this.msn;
	}

	public void setMsn(String msn) {
		this.msn = msn;
	}

	public String getCountry() {
		return this.country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getCity() {
		return this.city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getZipcode() {
		return this.zipcode;
	}

	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}

	public String getCompany() {
		return this.company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getDept() {
		return this.dept;
	}

	public void setDept(String dept) {
		this.dept = dept;
	}

	public String getDuty() {
		return this.duty;
	}

	public void setDuty(String duty) {
		this.duty = duty;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getRefer() {
		return this.refer;
	}

	public void setRefer(String refer) {
		this.refer = refer;
	}

	public String getCrtid() {
		return this.crtid;
	}

	public void setCrtid(String crtid) {
		this.crtid = crtid;
	}

	public Date getCrtdate() {
		return this.crtdate;
	}

	public void setCrtdate(Date crtdate) {
		this.crtdate = crtdate;
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

}