package com.rent.entity;

/**
 * BillUnit entity. @author MyEclipse Persistence Tools
 */

public class BillUnit implements java.io.Serializable {

	// Fields

	private Integer id;
	private String printtype;
	private String language;
	private String descript;
	private String descript1;
	private Integer paperwidth;
	private Integer paperlength;
	private String papertype;
	private Integer detailrow;
	private String syntax;
	private Integer inumber;
	private String savemodi;
	private Integer paperzoom;
	private String worddot;
	private String extctrl;

	// Constructors

	/** default constructor */
	public BillUnit() {
	}

	/** minimal constructor */
	public BillUnit(String printtype, String language, String papertype,
			String savemodi, Integer paperzoom) {
		this.printtype = printtype;
		this.language = language;
		this.papertype = papertype;
		this.savemodi = savemodi;
		this.paperzoom = paperzoom;
	}

	/** full constructor */
	public BillUnit(String printtype, String language, String descript,
			String descript1, Integer paperwidth, Integer paperlength,
			String papertype, Integer detailrow, String syntax,
			Integer inumber, String savemodi, Integer paperzoom,
			String worddot, String extctrl) {
		this.printtype = printtype;
		this.language = language;
		this.descript = descript;
		this.descript1 = descript1;
		this.paperwidth = paperwidth;
		this.paperlength = paperlength;
		this.papertype = papertype;
		this.detailrow = detailrow;
		this.syntax = syntax;
		this.inumber = inumber;
		this.savemodi = savemodi;
		this.paperzoom = paperzoom;
		this.worddot = worddot;
		this.extctrl = extctrl;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getPrinttype() {
		return this.printtype;
	}

	public void setPrinttype(String printtype) {
		this.printtype = printtype;
	}

	public String getLanguage() {
		return this.language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public String getDescript() {
		return this.descript;
	}

	public void setDescript(String descript) {
		this.descript = descript;
	}

	public String getDescript1() {
		return this.descript1;
	}

	public void setDescript1(String descript1) {
		this.descript1 = descript1;
	}

	public Integer getPaperwidth() {
		return this.paperwidth;
	}

	public void setPaperwidth(Integer paperwidth) {
		this.paperwidth = paperwidth;
	}

	public Integer getPaperlength() {
		return this.paperlength;
	}

	public void setPaperlength(Integer paperlength) {
		this.paperlength = paperlength;
	}

	public String getPapertype() {
		return this.papertype;
	}

	public void setPapertype(String papertype) {
		this.papertype = papertype;
	}

	public Integer getDetailrow() {
		return this.detailrow;
	}

	public void setDetailrow(Integer detailrow) {
		this.detailrow = detailrow;
	}

	public String getSyntax() {
		return this.syntax;
	}

	public void setSyntax(String syntax) {
		this.syntax = syntax;
	}

	public Integer getInumber() {
		return this.inumber;
	}

	public void setInumber(Integer inumber) {
		this.inumber = inumber;
	}

	public String getSavemodi() {
		return this.savemodi;
	}

	public void setSavemodi(String savemodi) {
		this.savemodi = savemodi;
	}

	public Integer getPaperzoom() {
		return this.paperzoom;
	}

	public void setPaperzoom(Integer paperzoom) {
		this.paperzoom = paperzoom;
	}

	public String getWorddot() {
		return this.worddot;
	}

	public void setWorddot(String worddot) {
		this.worddot = worddot;
	}

	public String getExtctrl() {
		return this.extctrl;
	}

	public void setExtctrl(String extctrl) {
		this.extctrl = extctrl;
	}

}