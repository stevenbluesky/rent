package com.rent.entity;

/**
 * BillPcprint entity. @author MyEclipse Persistence Tools
 */

public class BillPcprint implements java.io.Serializable {

	// Fields

	private Integer id;
	private String printtype;
	private String printer;
	private String printer1;

	// Constructors

	/** default constructor */
	public BillPcprint() {
	}

	/** full constructor */
	public BillPcprint(String printtype, String printer, String printer1) {
		this.printtype = printtype;
		this.printer = printer;
		this.printer1 = printer1;
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

	public String getPrinter() {
		return this.printer;
	}

	public void setPrinter(String printer) {
		this.printer = printer;
	}

	public String getPrinter1() {
		return this.printer1;
	}

	public void setPrinter1(String printer1) {
		this.printer1 = printer1;
	}

}