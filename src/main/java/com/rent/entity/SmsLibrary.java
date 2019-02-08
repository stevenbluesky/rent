package com.rent.entity;

import java.util.Date;

/**
 * SmsLibrary entity. @author MyEclipse Persistence Tools
 */

public class SmsLibrary implements java.io.Serializable {

	// Fields

	private Integer id;
	private String class_;
	private String content;
	private String logid;
	private Date logdate;

	// Constructors

	/** default constructor */
	public SmsLibrary() {
	}

	/** minimal constructor */
	public SmsLibrary(Date logdate) {
		this.logdate = logdate;
	}

	/** full constructor */
	public SmsLibrary(String class_, String content, String logid, Date logdate) {
		this.class_ = class_;
		this.content = content;
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

	public String getClass_() {
		return this.class_;
	}

	public void setClass_(String class_) {
		this.class_ = class_;
	}

	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
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