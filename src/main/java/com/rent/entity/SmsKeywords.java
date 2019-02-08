package com.rent.entity;

/**
 * SmsKeywords entity. @author MyEclipse Persistence Tools
 */

public class SmsKeywords implements java.io.Serializable {

	// Fields

	private Integer id;
	private String words;

	// Constructors

	/** default constructor */
	public SmsKeywords() {
	}

	/** full constructor */
	public SmsKeywords(String words) {
		this.words = words;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getWords() {
		return this.words;
	}

	public void setWords(String words) {
		this.words = words;
	}

}