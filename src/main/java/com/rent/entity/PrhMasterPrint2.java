package com.rent.entity;

/**
 * PrhMasterPrint2 entity. @author MyEclipse Persistence Tools
 */

public class PrhMasterPrint2 implements java.io.Serializable {

	// Fields

	private Integer id;
	private String accnt;
	private String cat;
	private String v1;
	private String v2;
	private String v3;
	private String v4;
	private String v5;
	private String v6;
	private String v7;
	private String v8;
	private String v9;

	// Constructors

	/** default constructor */
	public PrhMasterPrint2() {
	}

	/** minimal constructor */
	public PrhMasterPrint2(String accnt, String cat) {
		this.accnt = accnt;
		this.cat = cat;
	}

	/** full constructor */
	public PrhMasterPrint2(String accnt, String cat, String v1, String v2,
			String v3, String v4, String v5, String v6, String v7, String v8,
			String v9) {
		this.accnt = accnt;
		this.cat = cat;
		this.v1 = v1;
		this.v2 = v2;
		this.v3 = v3;
		this.v4 = v4;
		this.v5 = v5;
		this.v6 = v6;
		this.v7 = v7;
		this.v8 = v8;
		this.v9 = v9;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getAccnt() {
		return this.accnt;
	}

	public void setAccnt(String accnt) {
		this.accnt = accnt;
	}

	public String getCat() {
		return this.cat;
	}

	public void setCat(String cat) {
		this.cat = cat;
	}

	public String getV1() {
		return this.v1;
	}

	public void setV1(String v1) {
		this.v1 = v1;
	}

	public String getV2() {
		return this.v2;
	}

	public void setV2(String v2) {
		this.v2 = v2;
	}

	public String getV3() {
		return this.v3;
	}

	public void setV3(String v3) {
		this.v3 = v3;
	}

	public String getV4() {
		return this.v4;
	}

	public void setV4(String v4) {
		this.v4 = v4;
	}

	public String getV5() {
		return this.v5;
	}

	public void setV5(String v5) {
		this.v5 = v5;
	}

	public String getV6() {
		return this.v6;
	}

	public void setV6(String v6) {
		this.v6 = v6;
	}

	public String getV7() {
		return this.v7;
	}

	public void setV7(String v7) {
		this.v7 = v7;
	}

	public String getV8() {
		return this.v8;
	}

	public void setV8(String v8) {
		this.v8 = v8;
	}

	public String getV9() {
		return this.v9;
	}

	public void setV9(String v9) {
		this.v9 = v9;
	}

}