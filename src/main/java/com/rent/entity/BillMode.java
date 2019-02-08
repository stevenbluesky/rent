package com.rent.entity;

/**
 * BillMode entity. @author MyEclipse Persistence Tools
 */

public class BillMode implements java.io.Serializable {

	// Fields

	private Integer id;
	private String code;
	private String descript;
	private String descript1;
	private String printtype;
	private String modu;
	private String halt;
	private Integer sequence;
	private String extctrl;

	// Constructors

	/** default constructor */
	public BillMode() {
	}

	/** minimal constructor */
	public BillMode(String code) {
		this.code = code;
	}

	/** full constructor */
	public BillMode(String code, String descript, String descript1,
			String printtype, String modu, String halt, Integer sequence,
			String extctrl) {
		this.code = code;
		this.descript = descript;
		this.descript1 = descript1;
		this.printtype = printtype;
		this.modu = modu;
		this.halt = halt;
		this.sequence = sequence;
		this.extctrl = extctrl;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
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

	public String getPrinttype() {
		return this.printtype;
	}

	public void setPrinttype(String printtype) {
		this.printtype = printtype;
	}

	public String getModu() {
		return this.modu;
	}

	public void setModu(String modu) {
		this.modu = modu;
	}

	public String getHalt() {
		return this.halt;
	}

	public void setHalt(String halt) {
		this.halt = halt;
	}

	public Integer getSequence() {
		return this.sequence;
	}

	public void setSequence(Integer sequence) {
		this.sequence = sequence;
	}

	public String getExtctrl() {
		return this.extctrl;
	}

	public void setExtctrl(String extctrl) {
		this.extctrl = extctrl;
	}

}