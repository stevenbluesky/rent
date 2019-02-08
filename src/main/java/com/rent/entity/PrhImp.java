package com.rent.entity;

/**
 * PrhImp entity. @author MyEclipse Persistence Tools
 */

public class PrhImp implements java.io.Serializable {

	// Fields

	private String id;
	private String mode;
	private String name;
	private String idno;
	private String sex;
	private String mobile;
	private String mobile1;
	private String phone;
	private String company;
	private String phone1;
	private String street1;
	private String cusmaster;
	private String name2;
	private String sex2;
	private String idno2;
	private String guestno;

	// Constructors

	/** default constructor */
	public PrhImp() {
	}

	/** minimal constructor */
	public PrhImp(String mode, String name) {
		this.mode = mode;
		this.name = name;
	}

	/** full constructor */
	public PrhImp(String mode, String name, String idno, String sex,
			String mobile, String mobile1, String phone, String company,
			String phone1, String street1, String cusmaster, String name2,
			String sex2, String idno2, String guestno) {
		this.mode = mode;
		this.name = name;
		this.idno = idno;
		this.sex = sex;
		this.mobile = mobile;
		this.mobile1 = mobile1;
		this.phone = phone;
		this.company = company;
		this.phone1 = phone1;
		this.street1 = street1;
		this.cusmaster = cusmaster;
		this.name2 = name2;
		this.sex2 = sex2;
		this.idno2 = idno2;
		this.guestno = guestno;
	}

	// Property accessors

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getMode() {
		return this.mode;
	}

	public void setMode(String mode) {
		this.mode = mode;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIdno() {
		return this.idno;
	}

	public void setIdno(String idno) {
		this.idno = idno;
	}

	public String getSex() {
		return this.sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getMobile() {
		return this.mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getMobile1() {
		return this.mobile1;
	}

	public void setMobile1(String mobile1) {
		this.mobile1 = mobile1;
	}

	public String getPhone() {
		return this.phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getCompany() {
		return this.company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getPhone1() {
		return this.phone1;
	}

	public void setPhone1(String phone1) {
		this.phone1 = phone1;
	}

	public String getStreet1() {
		return this.street1;
	}

	public void setStreet1(String street1) {
		this.street1 = street1;
	}

	public String getCusmaster() {
		return this.cusmaster;
	}

	public void setCusmaster(String cusmaster) {
		this.cusmaster = cusmaster;
	}

	public String getName2() {
		return this.name2;
	}

	public void setName2(String name2) {
		this.name2 = name2;
	}

	public String getSex2() {
		return this.sex2;
	}

	public void setSex2(String sex2) {
		this.sex2 = sex2;
	}

	public String getIdno2() {
		return this.idno2;
	}

	public void setIdno2(String idno2) {
		this.idno2 = idno2;
	}

	public String getGuestno() {
		return this.guestno;
	}

	public void setGuestno(String guestno) {
		this.guestno = guestno;
	}

}