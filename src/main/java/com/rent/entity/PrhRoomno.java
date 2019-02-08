package com.rent.entity;

import java.util.Date;

/**
 * PrhRoomno entity. @author MyEclipse Persistence Tools
 */

public class PrhRoomno implements java.io.Serializable {

	// Fields

	private Integer id;
	private String roomno;
	private String oroomno;
	private String vroomno;
	private String area;
	private String build;
	private String floor;
	private String type;
	private String feature;
	private Integer rooms;
	private String sta;
	private String status;
	private Double area0;
	private Double area1;
	private Double rate0;
	private Double rate1;
	private Double rate;
	private String tag1;
	private String tag2;
	private String tag3;
	private String tmp;
	private String ooo;
	private Date bdate;
	private Date edate;
	private String remark;
	private String accnt;
	private Integer x;
	private Integer y;
	private Integer width;
	private Integer height;
	private String logid;
	private String logip;
	private Date logdate;
	private Integer logmark;
	private Date logupdate;
	private String src;
	private String flag;
	private String accnt2;
	private String sta2;
	private Double setrate;

	// Constructors

	/** default constructor */
	public PrhRoomno() {
	}

	/** minimal constructor */
	public PrhRoomno(String roomno, String oroomno, String build, String floor,
			String type, String feature, Integer rooms, String sta,
			String status, Double area0, Double area1, Double rate0,
			Double rate1, Double rate) {
		this.roomno = roomno;
		this.oroomno = oroomno;
		this.build = build;
		this.floor = floor;
		this.type = type;
		this.feature = feature;
		this.rooms = rooms;
		this.sta = sta;
		this.status = status;
		this.area0 = area0;
		this.area1 = area1;
		this.rate0 = rate0;
		this.rate1 = rate1;
		this.rate = rate;
	}

	/** full constructor */
	public PrhRoomno(String roomno, String oroomno, String vroomno,
			String area, String build, String floor, String type,
			String feature, Integer rooms, String sta, String status,
			Double area0, Double area1, Double rate0, Double rate1,
			Double rate, String tag1, String tag2, String tag3, String tmp,
			String ooo, Date bdate, Date edate, String remark, String accnt,
			Integer x, Integer y, Integer width, Integer height, String logid,
			String logip, Date logdate, Integer logmark, Date logupdate,
			String src, String flag, String accnt2, String sta2, Double setrate) {
		this.roomno = roomno;
		this.oroomno = oroomno;
		this.vroomno = vroomno;
		this.area = area;
		this.build = build;
		this.floor = floor;
		this.type = type;
		this.feature = feature;
		this.rooms = rooms;
		this.sta = sta;
		this.status = status;
		this.area0 = area0;
		this.area1 = area1;
		this.rate0 = rate0;
		this.rate1 = rate1;
		this.rate = rate;
		this.tag1 = tag1;
		this.tag2 = tag2;
		this.tag3 = tag3;
		this.tmp = tmp;
		this.ooo = ooo;
		this.bdate = bdate;
		this.edate = edate;
		this.remark = remark;
		this.accnt = accnt;
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.logid = logid;
		this.logip = logip;
		this.logdate = logdate;
		this.logmark = logmark;
		this.logupdate = logupdate;
		this.src = src;
		this.flag = flag;
		this.accnt2 = accnt2;
		this.sta2 = sta2;
		this.setrate = setrate;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public String getArea() {
		return this.area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getBuild() {
		return this.build;
	}

	public void setBuild(String build) {
		this.build = build;
	}

	public String getFloor() {
		return this.floor;
	}

	public void setFloor(String floor) {
		this.floor = floor;
	}

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getFeature() {
		return this.feature;
	}

	public void setFeature(String feature) {
		this.feature = feature;
	}

	public Integer getRooms() {
		return this.rooms;
	}

	public void setRooms(Integer rooms) {
		this.rooms = rooms;
	}

	public String getSta() {
		return this.sta;
	}

	public void setSta(String sta) {
		this.sta = sta;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Double getArea0() {
		return this.area0;
	}

	public void setArea0(Double area0) {
		this.area0 = area0;
	}

	public Double getArea1() {
		return this.area1;
	}

	public void setArea1(Double area1) {
		this.area1 = area1;
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

	public Double getRate() {
		return this.rate;
	}

	public void setRate(Double rate) {
		this.rate = rate;
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

	public String getTag3() {
		return this.tag3;
	}

	public void setTag3(String tag3) {
		this.tag3 = tag3;
	}

	public String getTmp() {
		return this.tmp;
	}

	public void setTmp(String tmp) {
		this.tmp = tmp;
	}

	public String getOoo() {
		return this.ooo;
	}

	public void setOoo(String ooo) {
		this.ooo = ooo;
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

	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getAccnt() {
		return this.accnt;
	}

	public void setAccnt(String accnt) {
		this.accnt = accnt;
	}

	public Integer getX() {
		return this.x;
	}

	public void setX(Integer x) {
		this.x = x;
	}

	public Integer getY() {
		return this.y;
	}

	public void setY(Integer y) {
		this.y = y;
	}

	public Integer getWidth() {
		return this.width;
	}

	public void setWidth(Integer width) {
		this.width = width;
	}

	public Integer getHeight() {
		return this.height;
	}

	public void setHeight(Integer height) {
		this.height = height;
	}

	public String getLogid() {
		return this.logid;
	}

	public void setLogid(String logid) {
		this.logid = logid;
	}

	public String getLogip() {
		return this.logip;
	}

	public void setLogip(String logip) {
		this.logip = logip;
	}

	public Date getLogdate() {
		return this.logdate;
	}

	public void setLogdate(Date logdate) {
		this.logdate = logdate;
	}

	public Integer getLogmark() {
		return this.logmark;
	}

	public void setLogmark(Integer logmark) {
		this.logmark = logmark;
	}

	public Date getLogupdate() {
		return this.logupdate;
	}

	public void setLogupdate(Date logupdate) {
		this.logupdate = logupdate;
	}

	public String getSrc() {
		return this.src;
	}

	public void setSrc(String src) {
		this.src = src;
	}

	public String getFlag() {
		return this.flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public String getAccnt2() {
		return this.accnt2;
	}

	public void setAccnt2(String accnt2) {
		this.accnt2 = accnt2;
	}

	public String getSta2() {
		return this.sta2;
	}

	public void setSta2(String sta2) {
		this.sta2 = sta2;
	}

	public Double getSetrate() {
		return this.setrate;
	}

	public void setSetrate(Double setrate) {
		this.setrate = setrate;
	}

}