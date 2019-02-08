package com.rent.entity;

import java.util.Date;

/**
 * PrhBal22 entity. @author MyEclipse Persistence Tools
 */

public class PrhBal22 implements java.io.Serializable {

	// Fields

	private Integer id;
	private String accnt;
	private String roomno;
	private Integer year;
	private Integer month;
	private Date bdate;
	private Date edate;
	private Double rate;
	private String bsta;
	private Double deposit;
	private Double ratem;
	private Double rate0;
	private Double rate1;
	private Double paid;
	private Double paid0;
	private Double paid1;
	private Double bal;
	private Double bal0;
	private Double bal1;

	// Constructors

	/** default constructor */
	public PrhBal22() {
	}

	/** minimal constructor */
	public PrhBal22(String accnt, String roomno, Integer year, Integer month,
			Date bdate, Date edate, Double rate, Double deposit, Double ratem,
			Double rate0, Double rate1, Double paid, Double paid0,
			Double paid1, Double bal, Double bal0, Double bal1) {
		this.accnt = accnt;
		this.roomno = roomno;
		this.year = year;
		this.month = month;
		this.bdate = bdate;
		this.edate = edate;
		this.rate = rate;
		this.deposit = deposit;
		this.ratem = ratem;
		this.rate0 = rate0;
		this.rate1 = rate1;
		this.paid = paid;
		this.paid0 = paid0;
		this.paid1 = paid1;
		this.bal = bal;
		this.bal0 = bal0;
		this.bal1 = bal1;
	}

	/** full constructor */
	public PrhBal22(String accnt, String roomno, Integer year, Integer month,
			Date bdate, Date edate, Double rate, String bsta, Double deposit,
			Double ratem, Double rate0, Double rate1, Double paid,
			Double paid0, Double paid1, Double bal, Double bal0, Double bal1) {
		this.accnt = accnt;
		this.roomno = roomno;
		this.year = year;
		this.month = month;
		this.bdate = bdate;
		this.edate = edate;
		this.rate = rate;
		this.bsta = bsta;
		this.deposit = deposit;
		this.ratem = ratem;
		this.rate0 = rate0;
		this.rate1 = rate1;
		this.paid = paid;
		this.paid0 = paid0;
		this.paid1 = paid1;
		this.bal = bal;
		this.bal0 = bal0;
		this.bal1 = bal1;
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

	public String getBsta() {
		return this.bsta;
	}

	public void setBsta(String bsta) {
		this.bsta = bsta;
	}

	public Double getDeposit() {
		return this.deposit;
	}

	public void setDeposit(Double deposit) {
		this.deposit = deposit;
	}

	public Double getRatem() {
		return this.ratem;
	}

	public void setRatem(Double ratem) {
		this.ratem = ratem;
	}

	public Double getRate0() {
		return this.rate0;
	}

	public void setRate0(Double rate0) {
		this.rate0 = rate0;
	}

	public Double getRate1() {
		return this.rate1;
	}

	public void setRate1(Double rate1) {
		this.rate1 = rate1;
	}

	public Double getPaid() {
		return this.paid;
	}

	public void setPaid(Double paid) {
		this.paid = paid;
	}

	public Double getPaid0() {
		return this.paid0;
	}

	public void setPaid0(Double paid0) {
		this.paid0 = paid0;
	}

	public Double getPaid1() {
		return this.paid1;
	}

	public void setPaid1(Double paid1) {
		this.paid1 = paid1;
	}

	public Double getBal() {
		return this.bal;
	}

	public void setBal(Double bal) {
		this.bal = bal;
	}

	public Double getBal0() {
		return this.bal0;
	}

	public void setBal0(Double bal0) {
		this.bal0 = bal0;
	}

	public Double getBal1() {
		return this.bal1;
	}

	public void setBal1(Double bal1) {
		this.bal1 = bal1;
	}

}