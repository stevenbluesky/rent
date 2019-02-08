package com.rent.entity;

import java.util.Date;

/**
 * Picture entity. @author MyEclipse Persistence Tools
 */

public class Picture implements java.io.Serializable {

	// Fields

	private Integer id;
	private String no;
	private Integer sort;
	private String name;
	private String bmp;
	private String logid;
	private Date logdate;
	private String ext;

	// Constructors

	/** default constructor */
	public Picture() {
	}

	/** minimal constructor */
	public Picture(String no) {
		this.no = no;
	}

	/** full constructor */
	public Picture(String no, Integer sort, String name, String bmp,
			String logid, Date logdate, String ext) {
		this.no = no;
		this.sort = sort;
		this.name = name;
		this.bmp = bmp;
		this.logid = logid;
		this.logdate = logdate;
		this.ext = ext;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNo() {
		return this.no;
	}

	public void setNo(String no) {
		this.no = no;
	}

	public Integer getSort() {
		return this.sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getBmp() {
		return this.bmp;
	}

	public void setBmp(String bmp) {
		this.bmp = bmp;
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

	public String getExt() {
		return this.ext;
	}

	public void setExt(String ext) {
		this.ext = ext;
	}

}