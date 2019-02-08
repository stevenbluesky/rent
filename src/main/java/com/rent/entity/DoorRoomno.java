package com.rent.entity;

import java.math.BigDecimal;
import java.util.Date;

/**
 * DoorRoomno entity. @author MyEclipse Persistence Tools
 */

public class DoorRoomno implements java.io.Serializable {

	// Fields

	private Integer id;
	private String sys;
	private String area;
	private String roomno;
	private String oroomno;
	private String vroomno;
	private String droomno;
	private String lockno;
	private String cardno;
	private BigDecimal numbers;
	private Date arr;

	// Constructors

	/** default constructor */
	public DoorRoomno() {
	}

	/** minimal constructor */
	public DoorRoomno(String sys, String roomno, String oroomno,
			String vroomno, BigDecimal numbers) {
		this.sys = sys;
		this.roomno = roomno;
		this.oroomno = oroomno;
		this.vroomno = vroomno;
		this.numbers = numbers;
	}

	/** full constructor */
	public DoorRoomno(String sys, String area, String roomno, String oroomno,
			String vroomno, String droomno, String lockno, String cardno,
			BigDecimal numbers, Date arr) {
		this.sys = sys;
		this.area = area;
		this.roomno = roomno;
		this.oroomno = oroomno;
		this.vroomno = vroomno;
		this.droomno = droomno;
		this.lockno = lockno;
		this.cardno = cardno;
		this.numbers = numbers;
		this.arr = arr;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getSys() {
		return this.sys;
	}

	public void setSys(String sys) {
		this.sys = sys;
	}

	public String getArea() {
		return this.area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getRoomno() {
		return this.roomno;
	}

	public void setRoomno(String roomno) {
		this.roomno = roomno;
	}

	public String getOroomno() {
		return this.oroomno;
	}

	public void setOroomno(String oroomno) {
		this.oroomno = oroomno;
	}

	public String getVroomno() {
		return this.vroomno;
	}

	public void setVroomno(String vroomno) {
		this.vroomno = vroomno;
	}

	public String getDroomno() {
		return this.droomno;
	}

	public void setDroomno(String droomno) {
		this.droomno = droomno;
	}

	public String getLockno() {
		return this.lockno;
	}

	public void setLockno(String lockno) {
		this.lockno = lockno;
	}

	public String getCardno() {
		return this.cardno;
	}

	public void setCardno(String cardno) {
		this.cardno = cardno;
	}

	public BigDecimal getNumbers() {
		return this.numbers;
	}

	public void setNumbers(BigDecimal numbers) {
		this.numbers = numbers;
	}

	public Date getArr() {
		return this.arr;
	}

	public void setArr(Date arr) {
		this.arr = arr;
	}

}