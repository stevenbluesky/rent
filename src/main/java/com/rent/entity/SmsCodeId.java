package com.rent.entity;

import java.util.Date;

/**
 * SmsCodeId entity. @author MyEclipse Persistence Tools
 */

public class SmsCodeId implements java.io.Serializable {

	// Fields

	private String class_;
	private String id;
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

	// Constructors

	/** default constructor */
	public SmsCodeId() {
	}

	/** minimal constructor */
	public SmsCodeId(String class_, String id, String descript1, Integer len) {
		this.class_ = class_;
		this.id = id;
		this.descript1 = descript1;
		this.len = len;
	}

	/** full constructor */
	public SmsCodeId(String class_, String id, String descript1,
			String descript2, String remark, String grp, Integer len,
			String system, String flag, String tag1, String tag2, String tag3,
			Integer int1, Integer int2, Integer int3, Double mone1,
			Double mone2, Double mone3, Date date1, Date date2, Date date3,
			String logid, Date logdate, String logip, Integer sequence) {
		this.class_ = class_;
		this.id = id;
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
	}

	// Property accessors

	public String getClass_() {
		return this.class_;
	}

	public void setClass_(String class_) {
		this.class_ = class_;
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
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

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof SmsCodeId))
			return false;
		SmsCodeId castOther = (SmsCodeId) other;

		return ((this.getClass_() == castOther.getClass_()) || (this
				.getClass_() != null && castOther.getClass_() != null && this
				.getClass_().equals(castOther.getClass_())))
				&& ((this.getId() == castOther.getId()) || (this.getId() != null
						&& castOther.getId() != null && this.getId().equals(
						castOther.getId())))
				&& ((this.getDescript1() == castOther.getDescript1()) || (this
						.getDescript1() != null
						&& castOther.getDescript1() != null && this
						.getDescript1().equals(castOther.getDescript1())))
				&& ((this.getDescript2() == castOther.getDescript2()) || (this
						.getDescript2() != null
						&& castOther.getDescript2() != null && this
						.getDescript2().equals(castOther.getDescript2())))
				&& ((this.getRemark() == castOther.getRemark()) || (this
						.getRemark() != null && castOther.getRemark() != null && this
						.getRemark().equals(castOther.getRemark())))
				&& ((this.getGrp() == castOther.getGrp()) || (this.getGrp() != null
						&& castOther.getGrp() != null && this.getGrp().equals(
						castOther.getGrp())))
				&& ((this.getLen() == castOther.getLen()) || (this.getLen() != null
						&& castOther.getLen() != null && this.getLen().equals(
						castOther.getLen())))
				&& ((this.getSystem() == castOther.getSystem()) || (this
						.getSystem() != null && castOther.getSystem() != null && this
						.getSystem().equals(castOther.getSystem())))
				&& ((this.getFlag() == castOther.getFlag()) || (this.getFlag() != null
						&& castOther.getFlag() != null && this.getFlag()
						.equals(castOther.getFlag())))
				&& ((this.getTag1() == castOther.getTag1()) || (this.getTag1() != null
						&& castOther.getTag1() != null && this.getTag1()
						.equals(castOther.getTag1())))
				&& ((this.getTag2() == castOther.getTag2()) || (this.getTag2() != null
						&& castOther.getTag2() != null && this.getTag2()
						.equals(castOther.getTag2())))
				&& ((this.getTag3() == castOther.getTag3()) || (this.getTag3() != null
						&& castOther.getTag3() != null && this.getTag3()
						.equals(castOther.getTag3())))
				&& ((this.getInt1() == castOther.getInt1()) || (this.getInt1() != null
						&& castOther.getInt1() != null && this.getInt1()
						.equals(castOther.getInt1())))
				&& ((this.getInt2() == castOther.getInt2()) || (this.getInt2() != null
						&& castOther.getInt2() != null && this.getInt2()
						.equals(castOther.getInt2())))
				&& ((this.getInt3() == castOther.getInt3()) || (this.getInt3() != null
						&& castOther.getInt3() != null && this.getInt3()
						.equals(castOther.getInt3())))
				&& ((this.getMone1() == castOther.getMone1()) || (this
						.getMone1() != null && castOther.getMone1() != null && this
						.getMone1().equals(castOther.getMone1())))
				&& ((this.getMone2() == castOther.getMone2()) || (this
						.getMone2() != null && castOther.getMone2() != null && this
						.getMone2().equals(castOther.getMone2())))
				&& ((this.getMone3() == castOther.getMone3()) || (this
						.getMone3() != null && castOther.getMone3() != null && this
						.getMone3().equals(castOther.getMone3())))
				&& ((this.getDate1() == castOther.getDate1()) || (this
						.getDate1() != null && castOther.getDate1() != null && this
						.getDate1().equals(castOther.getDate1())))
				&& ((this.getDate2() == castOther.getDate2()) || (this
						.getDate2() != null && castOther.getDate2() != null && this
						.getDate2().equals(castOther.getDate2())))
				&& ((this.getDate3() == castOther.getDate3()) || (this
						.getDate3() != null && castOther.getDate3() != null && this
						.getDate3().equals(castOther.getDate3())))
				&& ((this.getLogid() == castOther.getLogid()) || (this
						.getLogid() != null && castOther.getLogid() != null && this
						.getLogid().equals(castOther.getLogid())))
				&& ((this.getLogdate() == castOther.getLogdate()) || (this
						.getLogdate() != null && castOther.getLogdate() != null && this
						.getLogdate().equals(castOther.getLogdate())))
				&& ((this.getLogip() == castOther.getLogip()) || (this
						.getLogip() != null && castOther.getLogip() != null && this
						.getLogip().equals(castOther.getLogip())))
				&& ((this.getSequence() == castOther.getSequence()) || (this
						.getSequence() != null
						&& castOther.getSequence() != null && this
						.getSequence().equals(castOther.getSequence())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result
				+ (getClass_() == null ? 0 : this.getClass_().hashCode());
		result = 37 * result + (getId() == null ? 0 : this.getId().hashCode());
		result = 37 * result
				+ (getDescript1() == null ? 0 : this.getDescript1().hashCode());
		result = 37 * result
				+ (getDescript2() == null ? 0 : this.getDescript2().hashCode());
		result = 37 * result
				+ (getRemark() == null ? 0 : this.getRemark().hashCode());
		result = 37 * result
				+ (getGrp() == null ? 0 : this.getGrp().hashCode());
		result = 37 * result
				+ (getLen() == null ? 0 : this.getLen().hashCode());
		result = 37 * result
				+ (getSystem() == null ? 0 : this.getSystem().hashCode());
		result = 37 * result
				+ (getFlag() == null ? 0 : this.getFlag().hashCode());
		result = 37 * result
				+ (getTag1() == null ? 0 : this.getTag1().hashCode());
		result = 37 * result
				+ (getTag2() == null ? 0 : this.getTag2().hashCode());
		result = 37 * result
				+ (getTag3() == null ? 0 : this.getTag3().hashCode());
		result = 37 * result
				+ (getInt1() == null ? 0 : this.getInt1().hashCode());
		result = 37 * result
				+ (getInt2() == null ? 0 : this.getInt2().hashCode());
		result = 37 * result
				+ (getInt3() == null ? 0 : this.getInt3().hashCode());
		result = 37 * result
				+ (getMone1() == null ? 0 : this.getMone1().hashCode());
		result = 37 * result
				+ (getMone2() == null ? 0 : this.getMone2().hashCode());
		result = 37 * result
				+ (getMone3() == null ? 0 : this.getMone3().hashCode());
		result = 37 * result
				+ (getDate1() == null ? 0 : this.getDate1().hashCode());
		result = 37 * result
				+ (getDate2() == null ? 0 : this.getDate2().hashCode());
		result = 37 * result
				+ (getDate3() == null ? 0 : this.getDate3().hashCode());
		result = 37 * result
				+ (getLogid() == null ? 0 : this.getLogid().hashCode());
		result = 37 * result
				+ (getLogdate() == null ? 0 : this.getLogdate().hashCode());
		result = 37 * result
				+ (getLogip() == null ? 0 : this.getLogip().hashCode());
		result = 37 * result
				+ (getSequence() == null ? 0 : this.getSequence().hashCode());
		return result;
	}

}