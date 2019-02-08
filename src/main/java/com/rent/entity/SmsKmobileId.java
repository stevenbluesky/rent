package com.rent.entity;

import java.util.Date;

/**
 * SmsKmobileId entity. @author MyEclipse Persistence Tools
 */

public class SmsKmobileId implements java.io.Serializable {

	// Fields

	private Integer pid;
	private String logid;
	private String mobile;
	private Date logdate;
	private String sta;

	// Constructors

	/** default constructor */
	public SmsKmobileId() {
	}

	/** minimal constructor */
	public SmsKmobileId(Integer pid, String logid, String mobile) {
		this.pid = pid;
		this.logid = logid;
		this.mobile = mobile;
	}

	/** full constructor */
	public SmsKmobileId(Integer pid, String logid, String mobile, Date logdate,
			String sta) {
		this.pid = pid;
		this.logid = logid;
		this.mobile = mobile;
		this.logdate = logdate;
		this.sta = sta;
	}

	// Property accessors

	public Integer getPid() {
		return this.pid;
	}

	public void setPid(Integer pid) {
		this.pid = pid;
	}

	public String getLogid() {
		return this.logid;
	}

	public void setLogid(String logid) {
		this.logid = logid;
	}

	public String getMobile() {
		return this.mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public Date getLogdate() {
		return this.logdate;
	}

	public void setLogdate(Date logdate) {
		this.logdate = logdate;
	}

	public String getSta() {
		return this.sta;
	}

	public void setSta(String sta) {
		this.sta = sta;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof SmsKmobileId))
			return false;
		SmsKmobileId castOther = (SmsKmobileId) other;

		return ((this.getPid() == castOther.getPid()) || (this.getPid() != null
				&& castOther.getPid() != null && this.getPid().equals(
				castOther.getPid())))
				&& ((this.getLogid() == castOther.getLogid()) || (this
						.getLogid() != null && castOther.getLogid() != null && this
						.getLogid().equals(castOther.getLogid())))
				&& ((this.getMobile() == castOther.getMobile()) || (this
						.getMobile() != null && castOther.getMobile() != null && this
						.getMobile().equals(castOther.getMobile())))
				&& ((this.getLogdate() == castOther.getLogdate()) || (this
						.getLogdate() != null && castOther.getLogdate() != null && this
						.getLogdate().equals(castOther.getLogdate())))
				&& ((this.getSta() == castOther.getSta()) || (this.getSta() != null
						&& castOther.getSta() != null && this.getSta().equals(
						castOther.getSta())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result
				+ (getPid() == null ? 0 : this.getPid().hashCode());
		result = 37 * result
				+ (getLogid() == null ? 0 : this.getLogid().hashCode());
		result = 37 * result
				+ (getMobile() == null ? 0 : this.getMobile().hashCode());
		result = 37 * result
				+ (getLogdate() == null ? 0 : this.getLogdate().hashCode());
		result = 37 * result
				+ (getSta() == null ? 0 : this.getSta().hashCode());
		return result;
	}

}