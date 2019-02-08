package com.rent.entity;

import java.util.Date;

/**
 * PrhBal entity. @author MyEclipse Persistence Tools
 */

public class PrhBal implements java.io.Serializable {

	// Fields

	private Integer id;
	private String accnt;
	private String oroomno;
	private String roomno;
	private Integer year;
	private Integer month;
	private Date bdate;
	private Date edate;
	private Double rate;
	private Double deposit;
	private Double rate0;
	private Double paid;
	private Double bal;
	private String msta;
	private Double famount;
	private Double fuse;
	private Double fbal;

	// Constructors

	/** default constructor */
	public PrhBal() {
	}

	/** minimal constructor */
	public PrhBal(String accnt, String roomno, Integer year, Integer month,
			Date bdate, Date edate, Double rate, Double deposit, Double rate0,
			Double paid, Double bal) {
		this.accnt = accnt;
		this.roomno = roomno;
		this.year = year;
		this.month = month;
		this.bdate = bdate;
		this.edate = edate;
		this.rate = rate;
		this.deposit = deposit;
		this.rate0 = rate0;
		this.paid = paid;
		this.bal = bal;
	}

	/** full constructor */
	public PrhBal(String accnt, String oroomno, String roomno, Integer year,
			Integer month, Date bdate, Date edate, Double rate, Double deposit,
			Double rate0, Double paid, Double bal, String msta, Double famount,
			Double fuse, Double fbal) {
		this.accnt = accnt;
		this.oroomno = oroomno;
		this.roomno = roomno;
		this.year = year;
		this.month = month;
		this.bdate = bdate;
		this.edate = edate;
		this.rate = rate;
		this.deposit = deposit;
		this.rate0 = rate0;
		this.paid = paid;
		this.bal = bal;
		this.msta = msta;
		this.famount = famount;
		this.fuse = fuse;
		this.fbal = fbal;
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

	public String getOroomno() {
		return this.oroomno;
	}

	public void setOroomno(String oroomno) {
		this.oroomno = oroomno;
	}

	public String getRoomno() {
		return this.roomno;
	}

	public void setRoomno(String roomno) {
		this.roomno = roomno;
	}

	public Integer getYear() {
		return this.year;
	}

	public void setYear(Integer year) {
		this.year = year;
	}

	public Integer getMonth() {
		return this.month;
	}

	public void setMonth(Integer month) {
		this.month = month;
	}

	public Date getBdate() {
		return this.bdate;
	}

	public void setBdate(Date bdate) {
		this.bdate = bdate;
	}

	public Date getEdate() {
		return this.edate;
	}

	public void setEdate(Date edate) {
		this.edate = edate;
	}

	public Double getRate() {
		return this.rate;
	}

	public void setRate(Double rate) {
		this.rate = rate;
	}

	public Double getDeposit() {
		return this.deposit;
	}

	public void setDeposit(Double deposit) {
		this.deposit = deposit;
	}

	public Double getRate0() {
		return this.rate0;
	}

	public void setRate0(Double rate0) {
		this.rate0 = rate0;
	}

	public Double getPaid() {
		return this.paid;
	}

	public void setPaid(Double paid) {
		this.paid = paid;
	}

	public Double getBal() {
		return this.bal;
	}

	public void setBal(Double bal) {
		this.bal = bal;
	}

	public String getMsta() {
		return this.msta;
	}

	public void setMsta(String msta) {
		this.msta = msta;
	}

	public Double getFamount() {
		return this.famount;
	}

	public void setFamount(Double famount) {
		this.famount = famount;
	}

	public Double getFuse() {
		return this.fuse;
	}

	public void setFuse(Double fuse) {
		this.fuse = fuse;
	}

	public Double getFbal() {
		return this.fbal;
	}

	public void setFbal(Double fbal) {
		this.fbal = fbal;
	}

}