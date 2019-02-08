package com.rent.entity;

/**
 * PrhRoomnoTmp entity. @author MyEclipse Persistence Tools
 */

public class PrhRoomnoTmp implements java.io.Serializable {

	// Fields

	private Integer id;
	private String build;
	private String roomno;
	private String flr;
	private String type;
	private Double area;
	private Double rate0;
	private Double rate;
	private String b1;
	private String b2;
	private String oroomno;
	private String vroomno;
	private String vtype;

	// Constructors

	/** default constructor */
	public PrhRoomnoTmp() {
	}

	/** minimal constructor */
	public PrhRoomnoTmp(String build, String roomno, String flr, String type,
			Double area, Double rate0, Double rate) {
		this.build = build;
		this.roomno = roomno;
		this.flr = flr;
		this.type = type;
		this.area = area;
		this.rate0 = rate0;
		this.rate = rate;
	}

	/** full constructor */
	public PrhRoomnoTmp(String build, String roomno, String flr, String type,
			Double area, Double rate0, Double rate, String b1, String b2,
			String oroomno, String vroomno, String vtype) {
		this.build = build;
		this.roomno = roomno;
		this.flr = flr;
		this.type = type;
		this.area = area;
		this.rate0 = rate0;
		this.rate = rate;
		this.b1 = b1;
		this.b2 = b2;
		this.oroomno = oroomno;
		this.vroomno = vroomno;
		this.vtype = vtype;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getBuild() {
		return this.build;
	}

	public void setBuild(String build) {
		this.build = build;
	}

	public String getRoomno() {
		return this.roomno;
	}

	public void setRoomno(String roomno) {
		this.roomno = roomno;
	}

	public String getFlr() {
		return this.flr;
	}

	public void setFlr(String flr) {
		this.flr = flr;
	}

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Double getArea() {
		return this.area;
	}

	public void setArea(Double area) {
		this.area = area;
	}

	public Double getRate0() {
		return this.rate0;
	}

	public void setRate0(Double rate0) {
		this.rate0 = rate0;
	}

	public Double getRate() {
		return this.rate;
	}

	public void setRate(Double rate) {
		this.rate = rate;
	}

	public String getB1() {
		return this.b1;
	}

	public void setB1(String b1) {
		this.b1 = b1;
	}

	public String getB2() {
		return this.b2;
	}

	public void setB2(String b2) {
		this.b2 = b2;
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

	public String getVtype() {
		return this.vtype;
	}

	public void setVtype(String vtype) {
		this.vtype = vtype;
	}

}