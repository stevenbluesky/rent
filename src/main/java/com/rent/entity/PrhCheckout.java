package com.rent.entity;

import java.util.Date;

/**
 * PrhCheckout entity. @author MyEclipse Persistence Tools
 */

public class PrhCheckout implements java.io.Serializable {

	// Fields

	private Integer id;
	private String accnt;
	private Date sysdate;
	private String orgid;
	private String roomno;
	private String code;
	private String flag;
	private String pccode;
	private String item;
	private String unit;
	private String refer;
	private Double oprice;
	private Double price;
	private Double pvalue;
	private Double cvalue;
	private Double amount;
	private Double amount0;

	// Constructors

	/** default constructor */
	public PrhCheckout() {
	}

	/** minimal constructor */
	public PrhCheckout(String accnt, String code, String flag, String pccode,
			String item, Double oprice, Double price, Double pvalue,
			Double cvalue, Double amount) {
		this.accnt = accnt;
		this.code = code;
		this.flag = flag;
		this.pccode = pccode;
		this.item = item;
		this.oprice = oprice;
		this.price = price;
		this.pvalue = pvalue;
		this.cvalue = cvalue;
		this.amount = amount;
	}

	/** full constructor */
	public PrhCheckout(String accnt, Date sysdate, String orgid, String roomno,
			String code, String flag, String pccode, String item, String unit,
			String refer, Double oprice, Double price, Double pvalue,
			Double cvalue, Double amount, Double amount0) {
		this.accnt = accnt;
		this.sysdate = sysdate;
		this.orgid = orgid;
		this.roomno = roomno;
		this.code = code;
		this.flag = flag;
		this.pccode = pccode;
		this.item = item;
		this.unit = unit;
		this.refer = refer;
		this.oprice = oprice;
		this.price = price;
		this.pvalue = pvalue;
		this.cvalue = cvalue;
		this.amount = amount;
		this.amount0 = amount0;
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

	public Date getSysdate() {
		return this.sysdate;
	}

	public void setSysdate(Date sysdate) {
		this.sysdate = sysdate;
	}

	public String getOrgid() {
		return this.orgid;
	}

	public void setOrgid(String orgid) {
		this.orgid = orgid;
	}

	public String getRoomno() {
		return this.roomno;
	}

	public void setRoomno(String roomno) {
		this.roomno = roomno;
	}

	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getFlag() {
		return this.flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public String getPccode() {
		return this.pccode;
	}

	public void setPccode(String pccode) {
		this.pccode = pccode;
	}

	public String getItem() {
		return this.item;
	}

	public void setItem(String item) {
		this.item = item;
	}

	public String getUnit() {
		return this.unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public String getRefer() {
		return this.refer;
	}

	public void setRefer(String refer) {
		this.refer = refer;
	}

	public Double getOprice() {
		return this.oprice;
	}

	public void setOprice(Double oprice) {
		this.oprice = oprice;
	}

	public Double getPrice() {
		return this.price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Double getPvalue() {
		return this.pvalue;
	}

	public void setPvalue(Double pvalue) {
		this.pvalue = pvalue;
	}

	public Double getCvalue() {
		return this.cvalue;
	}

	public void setCvalue(Double cvalue) {
		this.cvalue = cvalue;
	}

	public Double getAmount() {
		return this.amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public Double getAmount0() {
		return this.amount0;
	}

	public void setAmount0(Double amount0) {
		this.amount0 = amount0;
	}

}