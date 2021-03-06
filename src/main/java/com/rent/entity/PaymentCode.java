package com.rent.entity;

import java.math.BigDecimal;

public class PaymentCode {
  
	
	public PaymentCode() {
		super();
	}

	public PaymentCode(Integer id, String name, String group, String isPay,
			Double pay) {
		super();
		this.id = id;
		this.name = name;
		this.group = group;
		this.isPay = isPay;
		this.pay = pay;
	}

	/**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column PAYMENT_CODE.ID
     *
     * @mbggenerated Tue May 02 16:18:40 CST 2017
     */
    private Integer id;

    private String name;
    
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column PAYMENT_CODE.group
     *
     * @mbggenerated Tue May 02 16:18:40 CST 2017
     */
    
    
    private String group;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column PAYMENT_CODE.IS_PAY
     *
     * @mbggenerated Tue May 02 16:18:40 CST 2017
     */
    private String isPay;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column PAYMENT_CODE.PAY
     *
     * @mbggenerated Tue May 02 16:18:40 CST 2017
     */
    private Double pay;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column PAYMENT_CODE.ID
     *
     * @return the value of PAYMENT_CODE.ID
     *
     * @mbggenerated Tue May 02 16:18:40 CST 2017
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column PAYMENT_CODE.ID
     *
     * @param id the value for PAYMENT_CODE.ID
     *
     * @mbggenerated Tue May 02 16:18:40 CST 2017
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column PAYMENT_CODE.group
     *
     * @return the value of PAYMENT_CODE.group
     *
     * @mbggenerated Tue May 02 16:18:40 CST 2017
     */
    public String getGroup() {
        return group;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column PAYMENT_CODE.group
     *
     * @param group the value for PAYMENT_CODE.group
     *
     * @mbggenerated Tue May 02 16:18:40 CST 2017
     */
    public void setGroup(String group) {
        this.group = group;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column PAYMENT_CODE.IS_PAY
     *
     * @return the value of PAYMENT_CODE.IS_PAY
     *
     * @mbggenerated Tue May 02 16:18:40 CST 2017
     */
    public String getIsPay() {
        return isPay;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column PAYMENT_CODE.IS_PAY
     *
     * @param isPay the value for PAYMENT_CODE.IS_PAY
     *
     * @mbggenerated Tue May 02 16:18:40 CST 2017
     */
    public void setIsPay(String isPay) {
        this.isPay = isPay;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column PAYMENT_CODE.PAY
     *
     * @return the value of PAYMENT_CODE.PAY
     *
     * @mbggenerated Tue May 02 16:18:40 CST 2017
     */
    public Double getPay() {
        return pay;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column PAYMENT_CODE.PAY
     *
     * @param pay the value for PAYMENT_CODE.PAY
     *
     * @mbggenerated Tue May 02 16:18:40 CST 2017
     */
    public void setPay(Double pay) {
        this.pay = pay;
    }

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
    
}