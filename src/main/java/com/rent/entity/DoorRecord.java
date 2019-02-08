package com.rent.entity;

import java.util.Date;

/**
 * DoorRecord entity. @author MyEclipse Persistence Tools
 */

public class DoorRecord implements java.io.Serializable {

	// Fields

	private Integer id;
	private String accnt;
	private String roomno;
	private String vroomno;
	private String name;
	private Date arr;
	private Date dep;
	private String cardType;
	private Integer number;
	private String done;
	private String logid;
	private Date logdate;
	private String logip;
	private Integer cardno;
	private Integer subno;
	private String scardno;
	private String ssubno;
	private String carddata;
	private String logid1;
	private Date logdate1;

	// Constructors

	/** default constructor */
	public DoorRecord() {
	}

	/** minimal constructor */
	public DoorRecord(String accnt, String roomno, String vroomno, String name,
			Date arr, Date dep, String cardType, Integer number, String done,
			String logid, Date logdate, Integer cardno, Integer subno) {
		this.accnt = accnt;
		this.roomno = roomno;
		this.vroomno = vroomno;
		this.name = name;
		this.arr = arr;
		this.dep = dep;
		this.cardType = cardType;
		this.number = number;
		this.done = done;
		this.logid = logid;
		this.logdate = logdate;
		this.cardno = cardno;
		this.subno = subno;
	}

	/** full constructor */
	public DoorRecord(String accnt, String roomno, String vroomno, String name,
			Date arr, Date dep, String cardType, Integer number, String done,
			String logid, Date logdate, String logip, Integer cardno,
			Integer subno, String scardno, String ssubno, String carddata,
			String logid1, Date logdate1) {
		this.accnt = accnt;
		this.roomno = roomno;
		this.vroomno = vroomno;
		this.name = name;
		this.arr = arr;
		this.dep = dep;
		this.cardType = cardType;
		this.number = number;
		this.done = done;
		this.logid = logid;
		this.logdate = logdate;
		this.logip = logip;
		this.cardno = cardno;
		this.subno = subno;
		this.scardno = scardno;
		this.ssubno = ssubno;
		this.carddata = carddata;
		this.logid1 = logid1;
		this.logdate1 = logdate1;
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

	public String getVroomno() {
		return this.vroomno;
	}

	public void setVroomno(String vroomno) {
		this.vroomno = vroomno;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getArr() {
		return this.arr;
	}

	public void setArr(Date arr) {
		this.arr = arr;
	}

	public Date getDep() {
		return this.dep;
	}

	public void setDep(Date dep) {
		this.dep = dep;
	}

	public String getCardType() {
		return this.cardType;
	}

	public void setCardType(String cardType) {
		this.cardType = cardType;
	}

	public Integer getNumber() {
		return this.number;
	}

	public void setNumber(Integer number) {
		this.number = number;
	}

	public String getDone() {
		return this.done;
	}

	public void setDone(String done) {
		this.done = done;
	}

	public String getLogid() {
		return this.logid;
	}

	public void setLogid(String logid) {
		this.logid = logid;
	}

	public Date getLogdate() {
		return this.logdate;
	}

	public void setLogdate(Date logdate) {
		this.logdate = logdate;
	}

	public String getLogip() {
		return this.logip;
	}

	public void setLogip(String logip) {
		this.logip = logip;
	}

	public Integer getCardno() {
		return this.cardno;
	}

	public void setCardno(Integer cardno) {
		this.cardno = cardno;
	}

	public Integer getSubno() {
		return this.subno;
	}

	public void setSubno(Integer subno) {
		this.subno = subno;
	}

	public String getScardno() {
		return this.scardno;
	}

	public void setScardno(String scardno) {
		this.scardno = scardno;
	}

	public String getSsubno() {
		return this.ssubno;
	}

	public void setSsubno(String ssubno) {
		this.ssubno = ssubno;
	}

	public String getCarddata() {
		return this.carddata;
	}

	public void setCarddata(String carddata) {
		this.carddata = carddata;
	}

	public String getLogid1() {
		return this.logid1;
	}

	public void setLogid1(String logid1) {
		this.logid1 = logid1;
	}

	public Date getLogdate1() {
		return this.logdate1;
	}

	public void setLogdate1(Date logdate1) {
		this.logdate1 = logdate1;
	}

}