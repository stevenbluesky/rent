package com.rent.entity;

import java.util.Date;

/**
 * SmsHis entity. @author MyEclipse Persistence Tools
 */

public class SmsHis implements java.io.Serializable {

	// Fields

	private Integer id;
	private Integer pid;
	private String type;
	private String phone;
	private String messages;
	private String name;
	private String template;
	private String accnt;
	private String guestno;
	private String logid;
	private Date logdate;
	private Date agreedate;
	private String sta;
	private Date senddate;
	private String sendcode;
	private String phone1;
	private String phone2;
	private String phone3;
	private String faillist;

	// Constructors

	/** default constructor */
	public SmsHis() {
	}

	/** minimal constructor */
	public SmsHis(Integer pid, String type, String phone, String messages,
			String sta) {
		this.pid = pid;
		this.type = type;
		this.phone = phone;
		this.messages = messages;
		this.sta = sta;
	}

	/** full constructor */
	public SmsHis(Integer pid, String type, String phone, String messages,
			String name, String template, String accnt, String guestno,
			String logid, Date logdate, Date agreedate, String sta,
			Date senddate, String sendcode, String phone1, String phone2,
			String phone3, String faillist) {
		this.pid = pid;
		this.type = type;
		this.phone = phone;
		this.messages = messages;
		this.name = name;
		this.template = template;
		this.accnt = accnt;
		this.guestno = guestno;
		this.logid = logid;
		this.logdate = logdate;
		this.agreedate = agreedate;
		this.sta = sta;
		this.senddate = senddate;
		this.sendcode = sendcode;
		this.phone1 = phone1;
		this.phone2 = phone2;
		this.phone3 = phone3;
		this.faillist = faillist;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getPid() {
		return this.pid;
	}

	public void setPid(Integer pid) {
		this.pid = pid;
	}

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getPhone() {
		return this.phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getMessages() {
		return this.messages;
	}

	public void setMessages(String messages) {
		this.messages = messages;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTemplate() {
		return this.template;
	}

	public void setTemplate(String template) {
		this.template = template;
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

	public Date getAgreedate() {
		return this.agreedate;
	}

	public void setAgreedate(Date agreedate) {
		this.agreedate = agreedate;
	}

	public String getSta() {
		return this.sta;
	}

	public void setSta(String sta) {
		this.sta = sta;
	}

	public Date getSenddate() {
		return this.senddate;
	}

	public void setSenddate(Date senddate) {
		this.senddate = senddate;
	}

	public String getSendcode() {
		return this.sendcode;
	}

	public void setSendcode(String sendcode) {
		this.sendcode = sendcode;
	}

	public String getPhone1() {
		return this.phone1;
	}

	public void setPhone1(String phone1) {
		this.phone1 = phone1;
	}

	public String getPhone2() {
		return this.phone2;
	}

	public void setPhone2(String phone2) {
		this.phone2 = phone2;
	}

	public String getPhone3() {
		return this.phone3;
	}

	public void setPhone3(String phone3) {
		this.phone3 = phone3;
	}

	public String getFaillist() {
		return this.faillist;
	}

	public void setFaillist(String faillist) {
		this.faillist = faillist;
	}

}