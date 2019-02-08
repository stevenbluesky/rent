package com.rent.entity;

/**
 * Pbcatedt entity. @author MyEclipse Persistence Tools
 */

public class Pbcatedt implements java.io.Serializable {

	// Fields

	private Integer id;
	private String pbeName;
	private String pbeEdit;
	private Integer pbeType;
	private Integer pbeCntr;
	private Integer pbeSeqn;
	private Integer pbeFlag;
	private String pbeWork;

	// Constructors

	/** default constructor */
	public Pbcatedt() {
	}

	/** minimal constructor */
	public Pbcatedt(String pbeName, String pbeEdit, Integer pbeType,
			Integer pbeSeqn) {
		this.pbeName = pbeName;
		this.pbeEdit = pbeEdit;
		this.pbeType = pbeType;
		this.pbeSeqn = pbeSeqn;
	}

	/** full constructor */
	public Pbcatedt(String pbeName, String pbeEdit, Integer pbeType,
			Integer pbeCntr, Integer pbeSeqn, Integer pbeFlag, String pbeWork) {
		this.pbeName = pbeName;
		this.pbeEdit = pbeEdit;
		this.pbeType = pbeType;
		this.pbeCntr = pbeCntr;
		this.pbeSeqn = pbeSeqn;
		this.pbeFlag = pbeFlag;
		this.pbeWork = pbeWork;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getPbeName() {
		return this.pbeName;
	}

	public void setPbeName(String pbeName) {
		this.pbeName = pbeName;
	}

	public String getPbeEdit() {
		return this.pbeEdit;
	}

	public void setPbeEdit(String pbeEdit) {
		this.pbeEdit = pbeEdit;
	}

	public Integer getPbeType() {
		return this.pbeType;
	}

	public void setPbeType(Integer pbeType) {
		this.pbeType = pbeType;
	}

	public Integer getPbeCntr() {
		return this.pbeCntr;
	}

	public void setPbeCntr(Integer pbeCntr) {
		this.pbeCntr = pbeCntr;
	}

	public Integer getPbeSeqn() {
		return this.pbeSeqn;
	}

	public void setPbeSeqn(Integer pbeSeqn) {
		this.pbeSeqn = pbeSeqn;
	}

	public Integer getPbeFlag() {
		return this.pbeFlag;
	}

	public void setPbeFlag(Integer pbeFlag) {
		this.pbeFlag = pbeFlag;
	}

	public String getPbeWork() {
		return this.pbeWork;
	}

	public void setPbeWork(String pbeWork) {
		this.pbeWork = pbeWork;
	}

}