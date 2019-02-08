package com.rent.entity;

import java.util.Date;

/**
 * Mail entity. @author MyEclipse Persistence Tools
 */

public class Mail implements java.io.Serializable {

	// Fields

	private String no;
	private String fromno;
	private String type;
	private String tono;
	private Date sendDate;
	private String sendIp;
	private Date inceptDate;
	private String subject;
	private String receipt;
	private String import_;
	private String sta;
	private String content;

	// Constructors

	/** default constructor */
	public Mail() {
	}

	/** minimal constructor */
	public Mail(String fromno, String tono, Date sendDate) {
		this.fromno = fromno;
		this.tono = tono;
		this.sendDate = sendDate;
	}

	/** full constructor */
	public Mail(String fromno, String type, String tono, Date sendDate,
			String sendIp, Date inceptDate, String subject, String receipt,
			String import_, String sta, String content) {
		this.fromno = fromno;
		this.type = type;
		this.tono = tono;
		this.sendDate = sendDate;
		this.sendIp = sendIp;
		this.inceptDate = inceptDate;
		this.subject = subject;
		this.receipt = receipt;
		this.import_ = import_;
		this.sta = sta;
		this.content = content;
	}

	// Property accessors

	public String getNo() {
		return this.no;
	}

	public void setNo(String no) {
		this.no = no;
	}

	public String getFromno() {
		return this.fromno;
	}

	public void setFromno(String fromno) {
		this.fromno = fromno;
	}

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getTono() {
		return this.tono;
	}

	public void setTono(String tono) {
		this.tono = tono;
	}

	public Date getSendDate() {
		return this.sendDate;
	}

	public void setSendDate(Date sendDate) {
		this.sendDate = sendDate;
	}

	public String getSendIp() {
		return this.sendIp;
	}

	public void setSendIp(String sendIp) {
		this.sendIp = sendIp;
	}

	public Date getInceptDate() {
		return this.inceptDate;
	}

	public void setInceptDate(Date inceptDate) {
		this.inceptDate = inceptDate;
	}

	public String getSubject() {
		return this.subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getReceipt() {
		return this.receipt;
	}

	public void setReceipt(String receipt) {
		this.receipt = receipt;
	}

	public String getImport_() {
		return this.import_;
	}

	public void setImport_(String import_) {
		this.import_ = import_;
	}

	public String getSta() {
		return this.sta;
	}

	public void setSta(String sta) {
		this.sta = sta;
	}

	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

}