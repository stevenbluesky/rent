package com.rent.entity;

/**
 * Pbcatvld entity. @author MyEclipse Persistence Tools
 */

public class Pbcatvld implements java.io.Serializable {

	// Fields

	private Integer id;
	private String pbvName;
	private String pbvVald;
	private Integer pbvType;
	private Integer pbvCntr;
	private String pbvMsg;

	// Constructors

	/** default constructor */
	public Pbcatvld() {
	}

	/** minimal constructor */
	public Pbcatvld(String pbvName, String pbvVald, Integer pbvType) {
		this.pbvName = pbvName;
		this.pbvVald = pbvVald;
		this.pbvType = pbvType;
	}

	/** full constructor */
	public Pbcatvld(String pbvName, String pbvVald, Integer pbvType,
			Integer pbvCntr, String pbvMsg) {
		this.pbvName = pbvName;
		this.pbvVald = pbvVald;
		this.pbvType = pbvType;
		this.pbvCntr = pbvCntr;
		this.pbvMsg = pbvMsg;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getPbvName() {
		return this.pbvName;
	}

	public void setPbvName(String pbvName) {
		this.pbvName = pbvName;
	}

	public String getPbvVald() {
		return this.pbvVald;
	}

	public void setPbvVald(String pbvVald) {
		this.pbvVald = pbvVald;
	}

	public Integer getPbvType() {
		return this.pbvType;
	}

	public void setPbvType(Integer pbvType) {
		this.pbvType = pbvType;
	}

	public Integer getPbvCntr() {
		return this.pbvCntr;
	}

	public void setPbvCntr(Integer pbvCntr) {
		this.pbvCntr = pbvCntr;
	}

	public String getPbvMsg() {
		return this.pbvMsg;
	}

	public void setPbvMsg(String pbvMsg) {
		this.pbvMsg = pbvMsg;
	}

}