package com.rent.entity;

import java.util.Date;

/**
 * SmsKfolioId entity. @author MyEclipse Persistence Tools
 */

public class SmsKfolioId implements java.io.Serializable {

	// Fields

	private Integer pid;
	private Integer kid;
	private String logid;
	private String content;
	private Integer mobiles;
	private Date logdate;
	private String send;
	private Date senddate;
	private String sendid;
	private String sendref;

	// Constructors

	/** default constructor */
	public SmsKfolioId() {
	}

	/** minimal constructor */
	public SmsKfolioId(Integer pid, Integer kid, String logid, String content,
			Date logdate, String send) {
		this.pid = pid;
		this.kid = kid;
		this.logid = logid;
		this.content = content;
		this.logdate = logdate;
		this.send = send;
	}

	/** full constructor */
	public SmsKfolioId(Integer pid, Integer kid, String logid, String content,
			Integer mobiles, Date logdate, String send, Date senddate,
			String sendid, String sendref) {
		this.pid = pid;
		this.kid = kid;
		this.logid = logid;
		this.content = content;
		this.mobiles = mobiles;
		this.logdate = logdate;
		this.send = send;
		this.senddate = senddate;
		this.sendid = sendid;
		this.sendref = sendref;
	}

	// Property accessors

	public Integer getPid() {
		return this.pid;
	}

	public void setPid(Integer pid) {
		this.pid = pid;
	}

	public Integer getKid() {
		return this.kid;
	}

	public void setKid(Integer kid) {
		this.kid = kid;
	}

	public String getLogid() {
		return this.logid;
	}

	public void setLogid(String logid) {
		this.logid = logid;
	}

	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Integer getMobiles() {
		return this.mobiles;
	}

	public void setMobiles(Integer mobiles) {
		this.mobiles = mobiles;
	}

	public Date getLogdate() {
		return this.logdate;
	}

	public void setLogdate(Date logdate) {
		this.logdate = logdate;
	}

	public String getSend() {
		return this.send;
	}

	public void setSend(String send) {
		this.send = send;
	}

	public Date getSenddate() {
		return this.senddate;
	}

	public void setSenddate(Date senddate) {
		this.senddate = senddate;
	}

	public String getSendid() {
		return this.sendid;
	}

	public void setSendid(String sendid) {
		this.sendid = sendid;
	}

	public String getSendref() {
		return this.sendref;
	}

	public void setSendref(String sendref) {
		this.sendref = sendref;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof SmsKfolioId))
			return false;
		SmsKfolioId castOther = (SmsKfolioId) other;

		return ((this.getPid() == castOther.getPid()) || (this.getPid() != null
				&& castOther.getPid() != null && this.getPid().equals(
				castOther.getPid())))
				&& ((this.getKid() == castOther.getKid()) || (this.getKid() != null
						&& castOther.getKid() != null && this.getKid().equals(
						castOther.getKid())))
				&& ((this.getLogid() == castOther.getLogid()) || (this
						.getLogid() != null && castOther.getLogid() != null && this
						.getLogid().equals(castOther.getLogid())))
				&& ((this.getContent() == castOther.getContent()) || (this
						.getContent() != null && castOther.getContent() != null && this
						.getContent().equals(castOther.getContent())))
				&& ((this.getMobiles() == castOther.getMobiles()) || (this
						.getMobiles() != null && castOther.getMobiles() != null && this
						.getMobiles().equals(castOther.getMobiles())))
				&& ((this.getLogdate() == castOther.getLogdate()) || (this
						.getLogdate() != null && castOther.getLogdate() != null && this
						.getLogdate().equals(castOther.getLogdate())))
				&& ((this.getSend() == castOther.getSend()) || (this.getSend() != null
						&& castOther.getSend() != null && this.getSend()
						.equals(castOther.getSend())))
				&& ((this.getSenddate() == castOther.getSenddate()) || (this
						.getSenddate() != null
						&& castOther.getSenddate() != null && this
						.getSenddate().equals(castOther.getSenddate())))
				&& ((this.getSendid() == castOther.getSendid()) || (this
						.getSendid() != null && castOther.getSendid() != null && this
						.getSendid().equals(castOther.getSendid())))
				&& ((this.getSendref() == castOther.getSendref()) || (this
						.getSendref() != null && castOther.getSendref() != null && this
						.getSendref().equals(castOther.getSendref())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result
				+ (getPid() == null ? 0 : this.getPid().hashCode());
		result = 37 * result
				+ (getKid() == null ? 0 : this.getKid().hashCode());
		result = 37 * result
				+ (getLogid() == null ? 0 : this.getLogid().hashCode());
		result = 37 * result
				+ (getContent() == null ? 0 : this.getContent().hashCode());
		result = 37 * result
				+ (getMobiles() == null ? 0 : this.getMobiles().hashCode());
		result = 37 * result
				+ (getLogdate() == null ? 0 : this.getLogdate().hashCode());
		result = 37 * result
				+ (getSend() == null ? 0 : this.getSend().hashCode());
		result = 37 * result
				+ (getSenddate() == null ? 0 : this.getSenddate().hashCode());
		result = 37 * result
				+ (getSendid() == null ? 0 : this.getSendid().hashCode());
		result = 37 * result
				+ (getSendref() == null ? 0 : this.getSendref().hashCode());
		return result;
	}

}