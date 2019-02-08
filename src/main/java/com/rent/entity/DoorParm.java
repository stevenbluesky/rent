package com.rent.entity;

import java.util.Date;

/**
 * DoorParm entity. @author MyEclipse Persistence Tools
 */

public class DoorParm implements java.io.Serializable {

	// Fields

	private Integer id;
	private String type;
	private String code;
	private String value;
	private String remark;
	private String logid;
	private Integer sequence;
	private Date logdate;

	// Constructors

	/** default constructor */
	public DoorParm() {
	}

	/** minimal constructor */
	public DoorParm(String type, String code) {
		this.type = type;
		this.code = code;
	}

	/** full constructor */
	public DoorParm(String type, String code, String value, String remark,
			String logid, Integer sequence, Date logdate) {
		this.type = type;
		this.code = code;
		this.value = value;
		this.remark = remark;
		this.logid = logid;
		this.sequence = sequence;
		this.logdate = logdate;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getValue() {
		return this.value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getLogid() {
		return this.logid;
	}

	public void setLogid(String logid) {
		this.logid = logid;
	}

	public Integer getSequence() {
		return this.sequence;
	}

	public void setSequence(Integer sequence) {
		this.sequence = sequence;
	}

	public Date getLogdate() {
		return this.logdate;
	}

	public void setLogdate(Date logdate) {
		this.logdate = logdate;
	}

}