package com.rent.entity;

import java.util.Date;

/**
 * PrhMasterAdd entity. @author MyEclipse Persistence Tools
 */

public class PrhMasterAdd implements java.io.Serializable {

	// Fields

	private Integer id;
	private String accnt;
	private String sta;
	private String addtype;
	private String code;
	private String refer;
	private Double mone1;
	private Double mone2;
	private String tag1;
	private String tag2;
	private Integer int1;
	private Integer int2;
	private Date date1;
	private Date date2;
	private Date date4;
	private Date date3;
	private String no1;
	private String no2;

	// Constructors

	/** default constructor */
	public PrhMasterAdd() {
	}

	/** minimal constructor */
	public PrhMasterAdd(String accnt, String addtype, String code) {
		this.accnt = accnt;
		this.addtype = addtype;
		this.code = code;
	}

	/** full constructor */
	public PrhMasterAdd(String accnt, String sta, String addtype, String code,
			String refer, Double mone1, Double mone2, String tag1, String tag2,
			Integer int1, Integer int2, Date date1, Date date2, Date date4,
			Date date3, String no1, String no2) {
		this.accnt = accnt;
		this.sta = sta;
		this.addtype = addtype;
		this.code = code;
		this.refer = refer;
		this.mone1 = mone1;
		this.mone2 = mone2;
		this.tag1 = tag1;
		this.tag2 = tag2;
		this.int1 = int1;
		this.int2 = int2;
		this.date1 = date1;
		this.date2 = date2;
		this.date4 = date4;
		this.date3 = date3;
		this.no1 = no1;
		this.no2 = no2;
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

	public String getSta() {
		return this.sta;
	}

	public void setSta(String sta) {
		this.sta = sta;
	}

	public String getAddtype() {
		return this.addtype;
	}

	public void setAddtype(String addtype) {
		this.addtype = addtype;
	}

	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getRefer() {
		return this.refer;
	}

	public void setRefer(String refer) {
		this.refer = refer;
	}

	public Double getMone1() {
		return this.mone1;
	}

	public void setMone1(Double mone1) {
		this.mone1 = mone1;
	}

	public Double getMone2() {
		return this.mone2;
	}

	public void setMone2(Double mone2) {
		this.mone2 = mone2;
	}

	public String getTag1() {
		return this.tag1;
	}

	public void setTag1(String tag1) {
		this.tag1 = tag1;
	}

	public String getTag2() {
		return this.tag2;
	}

	public void setTag2(String tag2) {
		this.tag2 = tag2;
	}

	public Integer getInt1() {
		return this.int1;
	}

	public void setInt1(Integer int1) {
		this.int1 = int1;
	}

	public Integer getInt2() {
		return this.int2;
	}

	public void setInt2(Integer int2) {
		this.int2 = int2;
	}

	public Date getDate1() {
		return this.date1;
	}

	public void setDate1(Date date1) {
		this.date1 = date1;
	}

	public Date getDate2() {
		return this.date2;
	}

	public void setDate2(Date date2) {
		this.date2 = date2;
	}

	public Date getDate4() {
		return this.date4;
	}

	public void setDate4(Date date4) {
		this.date4 = date4;
	}

	public Date getDate3() {
		return this.date3;
	}

	public void setDate3(Date date3) {
		this.date3 = date3;
	}

	public String getNo1() {
		return this.no1;
	}

	public void setNo1(String no1) {
		this.no1 = no1;
	}

	public String getNo2() {
		return this.no2;
	}

	public void setNo2(String no2) {
		this.no2 = no2;
	}

}