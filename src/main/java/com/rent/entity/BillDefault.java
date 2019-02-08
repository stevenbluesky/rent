package com.rent.entity;

/**
 * BillDefault entity. @author MyEclipse Persistence Tools
 */

public class BillDefault implements java.io.Serializable {

	// Fields

	private Integer id;
	private String modu;
	private String descript;
	private String descript1;
	private String code;

	// Constructors

	/** default constructor */
	public BillDefault() {
	}

	/** minimal constructor */
	public BillDefault(String modu, String code) {
		this.modu = modu;
		this.code = code;
	}

	/** full constructor */
	public BillDefault(String modu, String descript, String descript1,
			String code) {
		this.modu = modu;
		this.descript = descript;
		this.descript1 = descript1;
		this.code = code;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getModu() {
		return this.modu;
	}

	public void setModu(String modu) {
		this.modu = modu;
	}

	public String getDescript() {
		return this.descript;
	}

	public void setDescript(String descript) {
		this.descript = descript;
	}

	public String getDescript1() {
		return this.descript1;
	}

	public void setDescript1(String descript1) {
		this.descript1 = descript1;
	}

	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
	}

}