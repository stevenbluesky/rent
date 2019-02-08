package com.rent.entity;

import java.util.Date;

/**
 * TableLog entity. @author MyEclipse Persistence Tools
 */

public class TableLog implements java.io.Serializable {

	// Fields

	private Integer id;
	private String tablename;
	private String columname;
	private String colum;
	private String accnt;
	private Date logDate;
	private String logId;
	private String old;
	private String new_;
	private String refer;

	// Constructors

	/** default constructor */
	public TableLog() {
	}

	/** minimal constructor */
	public TableLog(String tablename, String columname, String colum,
			String accnt, Date logDate) {
		this.tablename = tablename;
		this.columname = columname;
		this.colum = colum;
		this.accnt = accnt;
		this.logDate = logDate;
	}

	/** full constructor */
	public TableLog(String tablename, String columname, String colum,
			String accnt, Date logDate, String logId, String old, String new_,
			String refer) {
		this.tablename = tablename;
		this.columname = columname;
		this.colum = colum;
		this.accnt = accnt;
		this.logDate = logDate;
		this.logId = logId;
		this.old = old;
		this.new_ = new_;
		this.refer = refer;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTablename() {
		return this.tablename;
	}

	public void setTablename(String tablename) {
		this.tablename = tablename;
	}

	public String getColumname() {
		return this.columname;
	}

	public void setColumname(String columname) {
		this.columname = columname;
	}

	public String getColum() {
		return this.colum;
	}

	public void setColum(String colum) {
		this.colum = colum;
	}

	public String getAccnt() {
		return this.accnt;
	}

	public void setAccnt(String accnt) {
		this.accnt = accnt;
	}

	public Date getLogDate() {
		return this.logDate;
	}

	public void setLogDate(Date logDate) {
		this.logDate = logDate;
	}

	public String getLogId() {
		return this.logId;
	}

	public void setLogId(String logId) {
		this.logId = logId;
	}

	public String getOld() {
		return this.old;
	}

	public void setOld(String old) {
		this.old = old;
	}

	public String getNew_() {
		return this.new_;
	}

	public void setNew_(String new_) {
		this.new_ = new_;
	}

	public String getRefer() {
		return this.refer;
	}

	public void setRefer(String refer) {
		this.refer = refer;
	}

}