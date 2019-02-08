package com.rent.entity;

import java.util.Date;

/**
 * PrhCode entity. @author MyEclipse Persistence Tools
 */

public class PrhCode implements java.io.Serializable {

	// Fields

	private Integer id;
	private String class_;
	private String codeId;
	private String descript1;
	private String descript2;
	private String remark;
	private String grp;
	private Integer len;
	private String system;
	private String flag;
	private String tag1;
	private String tag2;
	private String tag3;
	private Integer int1;
	private Integer int2;
	private Integer int3;
	private Double mone1;
	private Double mone2;
	private Double mone3;
	private Date date1;
	private Date date2;
	private Date date3;
	private String logid;
	private Date logdate;
	private String logip;
	private Integer sequence;
	private Date logupdate;

	// Constructors

	/** default constructor */
	public PrhCode() {
	}

	/** minimal constructor */
	public PrhCode(String class_, String codeId, String descript1, Integer len) {
		this.class_ = class_;
		this.codeId = codeId;
		this.descript1 = descript1;
		this.len = len;
	}

	/** full constructor */
	public PrhCode(String class_, String codeId, String descript1,
			String descript2, String remark, String grp, Integer len,
			String system, String flag, String tag1, String tag2, String tag3,
			Integer int1, Integer int2, Integer int3, Double mone1,
			Double mone2, Double mone3, Date date1, Date date2, Date date3,
			String logid, Date logdate, String logip, Integer sequence,
			Date logupdate) {
		this.class_ = class_;
		this.codeId = codeId;
		this.descript1 = descript1;
		this.descript2 = descript2;
		this.remark = remark;
		this.grp = grp;
		this.len = len;
		this.system = system;
		this.flag = flag;
		this.tag1 = tag1;
		this.tag2 = tag2;
		this.tag3 = tag3;
		this.int1 = int1;
		this.int2 = int2;
		this.int3 = int3;
		this.mone1 = mone1;
		this.mone2 = mone2;
		this.mone3 = mone3;
		this.date1 = date1;
		this.date2 = date2;
		this.date3 = date3;
		this.logid = logid;
		this.logdate = logdate;
		this.logip = logip;
		this.sequence = sequence;
		this.logupdate = logupdate;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getClass_() {
		return this.class_;
	}

	public void setClass_(String class_) {
		this.class_ = class_;
	}

	public String getCodeId() {
		return this.codeId;
	}

	public void setCodeId(String codeId) {
		this.codeId = codeId;
	}

	public String getDescript1() {
		return this.descript1;
	}

	public void setDescript1(String descript1) {
		this.descript1 = descript1;
	}

	public String getDescript2() {
		return this.descript2;
	}

	public void setDescript2(String descript2) {
		this.descript2 = descript2;
	}

	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getGrp() {
		return this.grp;
	}

	public void setGrp(String grp) {
		this.grp = grp;
	}

	public Integer getLen() {
		return this.len;
	}

	public void setLen(Integer len) {
		this.len = len;
	}

	public String getSystem() {
		return this.system;
	}

	public void setSystem(String system) {
		this.system = system;
	}

	public String getFlag() {
		return this.flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
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

	public Integer getInt3() {
		return this.int3;
	}

	public void setInt3(Integer int3) {
		this.int3 = int3;
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

	public Double getMone3() {
		return this.mone3;
	}

	public void setMone3(Double mone3) {
		this.mone3 = mone3;
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

	public Date getDate3() {
		return this.date3;
	}

	public void setDate3(Date date3) {
		this.date3 = date3;
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

	public Integer getSequence() {
		return this.sequence;
	}

	public void setSequence(Integer sequence) {
		this.sequence = sequence;
	}

	public Date getLogupdate() {
		return this.logupdate;
	}

	public void setLogupdate(Date logupdate) {
		this.logupdate = logupdate;
	}

}