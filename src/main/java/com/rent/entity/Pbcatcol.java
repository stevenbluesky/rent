package com.rent.entity;

/**
 * Pbcatcol entity. @author MyEclipse Persistence Tools
 */

public class Pbcatcol implements java.io.Serializable {

	// Fields

	private Integer id;
	private String pbcTnam;
	private Integer pbcTid;
	private String pbcOwnr;
	private String pbcCnam;
	private Integer pbcCid;
	private String pbcLabl;
	private Integer pbcLpos;
	private String pbcHdr;
	private Integer pbcHpos;
	private Integer pbcJtfy;
	private String pbcMask;
	private Integer pbcCase;
	private Integer pbcHght;
	private Integer pbcWdth;
	private String pbcPtrn;
	private String pbcBmap;
	private String pbcInit;
	private String pbcCmnt;
	private String pbcEdit;
	private String pbcTag;

	// Constructors

	/** default constructor */
	public Pbcatcol() {
	}

	/** minimal constructor */
	public Pbcatcol(String pbcTnam, Integer pbcTid, String pbcOwnr,
			String pbcCnam, Integer pbcCid) {
		this.pbcTnam = pbcTnam;
		this.pbcTid = pbcTid;
		this.pbcOwnr = pbcOwnr;
		this.pbcCnam = pbcCnam;
		this.pbcCid = pbcCid;
	}

	/** full constructor */
	public Pbcatcol(String pbcTnam, Integer pbcTid, String pbcOwnr,
			String pbcCnam, Integer pbcCid, String pbcLabl, Integer pbcLpos,
			String pbcHdr, Integer pbcHpos, Integer pbcJtfy, String pbcMask,
			Integer pbcCase, Integer pbcHght, Integer pbcWdth, String pbcPtrn,
			String pbcBmap, String pbcInit, String pbcCmnt, String pbcEdit,
			String pbcTag) {
		this.pbcTnam = pbcTnam;
		this.pbcTid = pbcTid;
		this.pbcOwnr = pbcOwnr;
		this.pbcCnam = pbcCnam;
		this.pbcCid = pbcCid;
		this.pbcLabl = pbcLabl;
		this.pbcLpos = pbcLpos;
		this.pbcHdr = pbcHdr;
		this.pbcHpos = pbcHpos;
		this.pbcJtfy = pbcJtfy;
		this.pbcMask = pbcMask;
		this.pbcCase = pbcCase;
		this.pbcHght = pbcHght;
		this.pbcWdth = pbcWdth;
		this.pbcPtrn = pbcPtrn;
		this.pbcBmap = pbcBmap;
		this.pbcInit = pbcInit;
		this.pbcCmnt = pbcCmnt;
		this.pbcEdit = pbcEdit;
		this.pbcTag = pbcTag;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getPbcTnam() {
		return this.pbcTnam;
	}

	public void setPbcTnam(String pbcTnam) {
		this.pbcTnam = pbcTnam;
	}

	public Integer getPbcTid() {
		return this.pbcTid;
	}

	public void setPbcTid(Integer pbcTid) {
		this.pbcTid = pbcTid;
	}

	public String getPbcOwnr() {
		return this.pbcOwnr;
	}

	public void setPbcOwnr(String pbcOwnr) {
		this.pbcOwnr = pbcOwnr;
	}

	public String getPbcCnam() {
		return this.pbcCnam;
	}

	public void setPbcCnam(String pbcCnam) {
		this.pbcCnam = pbcCnam;
	}

	public Integer getPbcCid() {
		return this.pbcCid;
	}

	public void setPbcCid(Integer pbcCid) {
		this.pbcCid = pbcCid;
	}

	public String getPbcLabl() {
		return this.pbcLabl;
	}

	public void setPbcLabl(String pbcLabl) {
		this.pbcLabl = pbcLabl;
	}

	public Integer getPbcLpos() {
		return this.pbcLpos;
	}

	public void setPbcLpos(Integer pbcLpos) {
		this.pbcLpos = pbcLpos;
	}

	public String getPbcHdr() {
		return this.pbcHdr;
	}

	public void setPbcHdr(String pbcHdr) {
		this.pbcHdr = pbcHdr;
	}

	public Integer getPbcHpos() {
		return this.pbcHpos;
	}

	public void setPbcHpos(Integer pbcHpos) {
		this.pbcHpos = pbcHpos;
	}

	public Integer getPbcJtfy() {
		return this.pbcJtfy;
	}

	public void setPbcJtfy(Integer pbcJtfy) {
		this.pbcJtfy = pbcJtfy;
	}

	public String getPbcMask() {
		return this.pbcMask;
	}

	public void setPbcMask(String pbcMask) {
		this.pbcMask = pbcMask;
	}

	public Integer getPbcCase() {
		return this.pbcCase;
	}

	public void setPbcCase(Integer pbcCase) {
		this.pbcCase = pbcCase;
	}

	public Integer getPbcHght() {
		return this.pbcHght;
	}

	public void setPbcHght(Integer pbcHght) {
		this.pbcHght = pbcHght;
	}

	public Integer getPbcWdth() {
		return this.pbcWdth;
	}

	public void setPbcWdth(Integer pbcWdth) {
		this.pbcWdth = pbcWdth;
	}

	public String getPbcPtrn() {
		return this.pbcPtrn;
	}

	public void setPbcPtrn(String pbcPtrn) {
		this.pbcPtrn = pbcPtrn;
	}

	public String getPbcBmap() {
		return this.pbcBmap;
	}

	public void setPbcBmap(String pbcBmap) {
		this.pbcBmap = pbcBmap;
	}

	public String getPbcInit() {
		return this.pbcInit;
	}

	public void setPbcInit(String pbcInit) {
		this.pbcInit = pbcInit;
	}

	public String getPbcCmnt() {
		return this.pbcCmnt;
	}

	public void setPbcCmnt(String pbcCmnt) {
		this.pbcCmnt = pbcCmnt;
	}

	public String getPbcEdit() {
		return this.pbcEdit;
	}

	public void setPbcEdit(String pbcEdit) {
		this.pbcEdit = pbcEdit;
	}

	public String getPbcTag() {
		return this.pbcTag;
	}

	public void setPbcTag(String pbcTag) {
		this.pbcTag = pbcTag;
	}

}