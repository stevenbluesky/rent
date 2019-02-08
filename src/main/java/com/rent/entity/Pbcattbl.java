package com.rent.entity;

/**
 * Pbcattbl entity. @author MyEclipse Persistence Tools
 */

public class Pbcattbl implements java.io.Serializable {

	// Fields

	private Integer id;
	private String pbtTnam;
	private Integer pbtTid;
	private String pbtOwnr;
	private Integer pbdFhgt;
	private Integer pbdFwgt;
	private String pbdFitl;
	private String pbdFunl;
	private Integer pbdFchr;
	private Integer pbdFptc;
	private String pbdFfce;
	private Integer pbhFhgt;
	private Integer pbhFwgt;
	private String pbhFitl;
	private String pbhFunl;
	private Integer pbhFchr;
	private Integer pbhFptc;
	private String pbhFfce;
	private Integer pblFhgt;
	private Integer pblFwgt;
	private String pblFitl;
	private String pblFunl;
	private Integer pblFchr;
	private Integer pblFptc;
	private String pblFfce;
	private String pbtCmnt;

	// Constructors

	/** default constructor */
	public Pbcattbl() {
	}

	/** minimal constructor */
	public Pbcattbl(String pbtTnam, Integer pbtTid, String pbtOwnr) {
		this.pbtTnam = pbtTnam;
		this.pbtTid = pbtTid;
		this.pbtOwnr = pbtOwnr;
	}

	/** full constructor */
	public Pbcattbl(String pbtTnam, Integer pbtTid, String pbtOwnr,
			Integer pbdFhgt, Integer pbdFwgt, String pbdFitl, String pbdFunl,
			Integer pbdFchr, Integer pbdFptc, String pbdFfce, Integer pbhFhgt,
			Integer pbhFwgt, String pbhFitl, String pbhFunl, Integer pbhFchr,
			Integer pbhFptc, String pbhFfce, Integer pblFhgt, Integer pblFwgt,
			String pblFitl, String pblFunl, Integer pblFchr, Integer pblFptc,
			String pblFfce, String pbtCmnt) {
		this.pbtTnam = pbtTnam;
		this.pbtTid = pbtTid;
		this.pbtOwnr = pbtOwnr;
		this.pbdFhgt = pbdFhgt;
		this.pbdFwgt = pbdFwgt;
		this.pbdFitl = pbdFitl;
		this.pbdFunl = pbdFunl;
		this.pbdFchr = pbdFchr;
		this.pbdFptc = pbdFptc;
		this.pbdFfce = pbdFfce;
		this.pbhFhgt = pbhFhgt;
		this.pbhFwgt = pbhFwgt;
		this.pbhFitl = pbhFitl;
		this.pbhFunl = pbhFunl;
		this.pbhFchr = pbhFchr;
		this.pbhFptc = pbhFptc;
		this.pbhFfce = pbhFfce;
		this.pblFhgt = pblFhgt;
		this.pblFwgt = pblFwgt;
		this.pblFitl = pblFitl;
		this.pblFunl = pblFunl;
		this.pblFchr = pblFchr;
		this.pblFptc = pblFptc;
		this.pblFfce = pblFfce;
		this.pbtCmnt = pbtCmnt;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getPbtTnam() {
		return this.pbtTnam;
	}

	public void setPbtTnam(String pbtTnam) {
		this.pbtTnam = pbtTnam;
	}

	public Integer getPbtTid() {
		return this.pbtTid;
	}

	public void setPbtTid(Integer pbtTid) {
		this.pbtTid = pbtTid;
	}

	public String getPbtOwnr() {
		return this.pbtOwnr;
	}

	public void setPbtOwnr(String pbtOwnr) {
		this.pbtOwnr = pbtOwnr;
	}

	public Integer getPbdFhgt() {
		return this.pbdFhgt;
	}

	public void setPbdFhgt(Integer pbdFhgt) {
		this.pbdFhgt = pbdFhgt;
	}

	public Integer getPbdFwgt() {
		return this.pbdFwgt;
	}

	public void setPbdFwgt(Integer pbdFwgt) {
		this.pbdFwgt = pbdFwgt;
	}

	public String getPbdFitl() {
		return this.pbdFitl;
	}

	public void setPbdFitl(String pbdFitl) {
		this.pbdFitl = pbdFitl;
	}

	public String getPbdFunl() {
		return this.pbdFunl;
	}

	public void setPbdFunl(String pbdFunl) {
		this.pbdFunl = pbdFunl;
	}

	public Integer getPbdFchr() {
		return this.pbdFchr;
	}

	public void setPbdFchr(Integer pbdFchr) {
		this.pbdFchr = pbdFchr;
	}

	public Integer getPbdFptc() {
		return this.pbdFptc;
	}

	public void setPbdFptc(Integer pbdFptc) {
		this.pbdFptc = pbdFptc;
	}

	public String getPbdFfce() {
		return this.pbdFfce;
	}

	public void setPbdFfce(String pbdFfce) {
		this.pbdFfce = pbdFfce;
	}

	public Integer getPbhFhgt() {
		return this.pbhFhgt;
	}

	public void setPbhFhgt(Integer pbhFhgt) {
		this.pbhFhgt = pbhFhgt;
	}

	public Integer getPbhFwgt() {
		return this.pbhFwgt;
	}

	public void setPbhFwgt(Integer pbhFwgt) {
		this.pbhFwgt = pbhFwgt;
	}

	public String getPbhFitl() {
		return this.pbhFitl;
	}

	public void setPbhFitl(String pbhFitl) {
		this.pbhFitl = pbhFitl;
	}

	public String getPbhFunl() {
		return this.pbhFunl;
	}

	public void setPbhFunl(String pbhFunl) {
		this.pbhFunl = pbhFunl;
	}

	public Integer getPbhFchr() {
		return this.pbhFchr;
	}

	public void setPbhFchr(Integer pbhFchr) {
		this.pbhFchr = pbhFchr;
	}

	public Integer getPbhFptc() {
		return this.pbhFptc;
	}

	public void setPbhFptc(Integer pbhFptc) {
		this.pbhFptc = pbhFptc;
	}

	public String getPbhFfce() {
		return this.pbhFfce;
	}

	public void setPbhFfce(String pbhFfce) {
		this.pbhFfce = pbhFfce;
	}

	public Integer getPblFhgt() {
		return this.pblFhgt;
	}

	public void setPblFhgt(Integer pblFhgt) {
		this.pblFhgt = pblFhgt;
	}

	public Integer getPblFwgt() {
		return this.pblFwgt;
	}

	public void setPblFwgt(Integer pblFwgt) {
		this.pblFwgt = pblFwgt;
	}

	public String getPblFitl() {
		return this.pblFitl;
	}

	public void setPblFitl(String pblFitl) {
		this.pblFitl = pblFitl;
	}

	public String getPblFunl() {
		return this.pblFunl;
	}

	public void setPblFunl(String pblFunl) {
		this.pblFunl = pblFunl;
	}

	public Integer getPblFchr() {
		return this.pblFchr;
	}

	public void setPblFchr(Integer pblFchr) {
		this.pblFchr = pblFchr;
	}

	public Integer getPblFptc() {
		return this.pblFptc;
	}

	public void setPblFptc(Integer pblFptc) {
		this.pblFptc = pblFptc;
	}

	public String getPblFfce() {
		return this.pblFfce;
	}

	public void setPblFfce(String pblFfce) {
		this.pblFfce = pblFfce;
	}

	public String getPbtCmnt() {
		return this.pbtCmnt;
	}

	public void setPbtCmnt(String pbtCmnt) {
		this.pbtCmnt = pbtCmnt;
	}

}