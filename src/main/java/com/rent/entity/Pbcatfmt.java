package com.rent.entity;

/**
 * Pbcatfmt entity. @author MyEclipse Persistence Tools
 */

public class Pbcatfmt implements java.io.Serializable {

	// Fields

	private Integer id;
	private String pbfName;
	private String pbfFrmt;
	private Integer pbfType;
	private Integer pbfCntr;

	// Constructors

	/** default constructor */
	public Pbcatfmt() {
	}

	/** minimal constructor */
	public Pbcatfmt(String pbfName, String pbfFrmt, Integer pbfType) {
		this.pbfName = pbfName;
		this.pbfFrmt = pbfFrmt;
		this.pbfType = pbfType;
	}

	/** full constructor */
	public Pbcatfmt(String pbfName, String pbfFrmt, Integer pbfType,
			Integer pbfCntr) {
		this.pbfName = pbfName;
		this.pbfFrmt = pbfFrmt;
		this.pbfType = pbfType;
		this.pbfCntr = pbfCntr;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getPbfName() {
		return this.pbfName;
	}

	public void setPbfName(String pbfName) {
		this.pbfName = pbfName;
	}

	public String getPbfFrmt() {
		return this.pbfFrmt;
	}

	public void setPbfFrmt(String pbfFrmt) {
		this.pbfFrmt = pbfFrmt;
	}

	public Integer getPbfType() {
		return this.pbfType;
	}

	public void setPbfType(Integer pbfType) {
		this.pbfType = pbfType;
	}

	public Integer getPbfCntr() {
		return this.pbfCntr;
	}

	public void setPbfCntr(Integer pbfCntr) {
		this.pbfCntr = pbfCntr;
	}

}