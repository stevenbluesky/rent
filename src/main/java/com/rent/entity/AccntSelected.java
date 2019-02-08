package com.rent.entity;

/**
 * AccntSelected entity. @author MyEclipse Persistence Tools
 */

public class AccntSelected implements java.io.Serializable {

	// Fields

	private Integer id;
	private String type;
	private String accnt;
	private Integer number;

	// Constructors

	/** default constructor */
	public AccntSelected() {
	}

	/** full constructor */
	public AccntSelected(String type, String accnt, Integer number) {
		this.type = type;
		this.accnt = accnt;
		this.number = number;
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

	public String getAccnt() {
		return this.accnt;
	}

	public void setAccnt(String accnt) {
		this.accnt = accnt;
	}

	public Integer getNumber() {
		return this.number;
	}

	public void setNumber(Integer number) {
		this.number = number;
	}

}