package com.rent.entity;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.sun.org.apache.bcel.internal.generic.NEW;

public class Subsidy {
   
	
	public Subsidy() {
		super();
	}
	
	
	public Subsidy(Integer id,
			Date beginDate, Date endDate, Integer perOneArea,
			Integer minSafeArea, Integer maxSafeArea, Integer maxArea,
			Double price, Integer inPercentId, Integer outPercentId,
			Integer estateId, Date updateTime, Integer updateUser) {
		super();
		this.id = id;
		this.beginDate = beginDate;
		this.endDate = endDate;
		this.perOneArea = perOneArea;
		this.minSafeArea = minSafeArea;
		this.maxSafeArea = maxSafeArea;
		this.maxArea = maxArea;
		this.price = price;
		this.inPercentId = inPercentId;
		this.outPercentId = outPercentId;
		this.estateId = estateId;
		this.updateTime = updateTime;
		this.updateUser = updateUser;
	}

	
	private Map<Integer, Double> in=new HashMap<Integer, Double>();
	private Map<Integer, Double> out=new HashMap<Integer, Double>();
	
	
	public Map<Integer, Double> getIn() {
		return in;
	}


	public void setIn(Map<Integer, Double> in) {
		this.in = in;
	}


	public Map<Integer, Double> getOut() {
		return out;
	}


	public void setOut(Map<Integer, Double> out) {
		this.out = out;
	}
	private Estate estate;
	private SubsidyPercent subsidyPercent;
	
	
	private int susidyTypeId;
	
	
    public int getSusidyTypeId() {
		return susidyTypeId;
	}


	public void setSusidyTypeId(int susidyTypeId) {
		this.susidyTypeId = susidyTypeId;
	}


	public Estate getEstate() {
		return estate;
	}

	public void setEstate(Estate estate) {
		this.estate = estate;
	}



	public SubsidyPercent getSubsidyPercent() {
		return subsidyPercent;
	}

	public void setSubsidyPercent(SubsidyPercent subsidyPercent) {
		this.subsidyPercent = subsidyPercent;
	}

	private Integer id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column SUBSIDY.BEGIN_DATE
     *
     * @mbggenerated Wed May 03 17:21:11 CST 2017
     */
    private Date beginDate;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column SUBSIDY.END_DATE
     *
     * @mbggenerated Wed May 03 17:21:11 CST 2017
     */
    private Date endDate;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column SUBSIDY.PER_ONE_AREA
     *
     * @mbggenerated Wed May 03 17:21:11 CST 2017
     */
    private Integer perOneArea;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column SUBSIDY.MIN_SAFE_AREA
     *
     * @mbggenerated Wed May 03 17:21:11 CST 2017
     */
    private Integer minSafeArea;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column SUBSIDY.MAX_SAFE_AREA
     *
     * @mbggenerated Wed May 03 17:21:11 CST 2017
     */
    private Integer maxSafeArea;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column SUBSIDY.MAX_AREA_PRICE
     *
     * @mbggenerated Wed May 03 17:21:11 CST 2017
     */
    private Integer maxArea;
    private Double price;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column SUBSIDY.IN_PERCENT_ID
     *
     * @mbggenerated Wed May 03 17:21:11 CST 2017
     */
    private Integer inPercentId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column SUBSIDY.OUT_PERCENT_ID
     *
     * @mbggenerated Wed May 03 17:21:11 CST 2017
     */
    private Integer outPercentId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column SUBSIDY.ESTATE_ID
     *
     * @mbggenerated Wed May 03 17:21:11 CST 2017
     */
    private Integer estateId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column SUBSIDY.UPDATE_TIME
     *
     * @mbggenerated Wed May 03 17:21:11 CST 2017
     */
    private Date updateTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column SUBSIDY.UPDATE_USER
     *
     * @mbggenerated Wed May 03 17:21:11 CST 2017
     */
    private Integer updateUser;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column SUBSIDY.ID
     *
     * @return the value of SUBSIDY.ID
     *
     * @mbggenerated Wed May 03 17:21:11 CST 2017
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column SUBSIDY.ID
     *
     * @param id the value for SUBSIDY.ID
     *
     * @mbggenerated Wed May 03 17:21:11 CST 2017
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column SUBSIDY.BEGIN_DATE
     *
     * @return the value of SUBSIDY.BEGIN_DATE
     *
     * @mbggenerated Wed May 03 17:21:11 CST 2017
     */
    public Date getBeginDate() {
        return beginDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column SUBSIDY.BEGIN_DATE
     *
     * @param beginDate the value for SUBSIDY.BEGIN_DATE
     *
     * @mbggenerated Wed May 03 17:21:11 CST 2017
     */
    public void setBeginDate(Date beginDate) {
        this.beginDate = beginDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column SUBSIDY.END_DATE
     *
     * @return the value of SUBSIDY.END_DATE
     *
     * @mbggenerated Wed May 03 17:21:11 CST 2017
     */
    public Date getEndDate() {
        return endDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column SUBSIDY.END_DATE
     *
     * @param endDate the value for SUBSIDY.END_DATE
     *
     * @mbggenerated Wed May 03 17:21:11 CST 2017
     */
    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column SUBSIDY.PER_ONE_AREA
     *
     * @return the value of SUBSIDY.PER_ONE_AREA
     *
     * @mbggenerated Wed May 03 17:21:11 CST 2017
     */
    public Integer getPerOneArea() {
        return perOneArea;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column SUBSIDY.PER_ONE_AREA
     *
     * @param perOneArea the value for SUBSIDY.PER_ONE_AREA
     *
     * @mbggenerated Wed May 03 17:21:11 CST 2017
     */
    public void setPerOneArea(Integer perOneArea) {
        this.perOneArea = perOneArea;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column SUBSIDY.MIN_SAFE_AREA
     *
     * @return the value of SUBSIDY.MIN_SAFE_AREA
     *
     * @mbggenerated Wed May 03 17:21:11 CST 2017
     */
    public Integer getMinSafeArea() {
        return minSafeArea;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column SUBSIDY.MIN_SAFE_AREA
     *
     * @param minSafeArea the value for SUBSIDY.MIN_SAFE_AREA
     *
     * @mbggenerated Wed May 03 17:21:11 CST 2017
     */
    public void setMinSafeArea(Integer minSafeArea) {
        this.minSafeArea = minSafeArea;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column SUBSIDY.MAX_SAFE_AREA
     *
     * @return the value of SUBSIDY.MAX_SAFE_AREA
     *
     * @mbggenerated Wed May 03 17:21:11 CST 2017
     */
    public Integer getMaxSafeArea() {
        return maxSafeArea;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column SUBSIDY.MAX_SAFE_AREA
     *
     * @param maxSafeArea the value for SUBSIDY.MAX_SAFE_AREA
     *
     * @mbggenerated Wed May 03 17:21:11 CST 2017
     */
    public void setMaxSafeArea(Integer maxSafeArea) {
        this.maxSafeArea = maxSafeArea;
    }
    
    
    
    public Integer getMaxArea() {
		return maxArea;
	}

	public void setMaxArea(Integer maxArea) {
		this.maxArea = maxArea;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Integer getInPercentId() {
        return inPercentId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column SUBSIDY.IN_PERCENT_ID
     *
     * @param inPercentId the value for SUBSIDY.IN_PERCENT_ID
     *
     * @mbggenerated Wed May 03 17:21:11 CST 2017
     */
    public void setInPercentId(Integer inPercentId) {
        this.inPercentId = inPercentId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column SUBSIDY.OUT_PERCENT_ID
     *
     * @return the value of SUBSIDY.OUT_PERCENT_ID
     *
     * @mbggenerated Wed May 03 17:21:11 CST 2017
     */
    public Integer getOutPercentId() {
        return outPercentId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column SUBSIDY.OUT_PERCENT_ID
     *
     * @param outPercentId the value for SUBSIDY.OUT_PERCENT_ID
     *
     * @mbggenerated Wed May 03 17:21:11 CST 2017
     */
    public void setOutPercentId(Integer outPercentId) {
        this.outPercentId = outPercentId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column SUBSIDY.ESTATE_ID
     *
     * @return the value of SUBSIDY.ESTATE_ID
     *
     * @mbggenerated Wed May 03 17:21:11 CST 2017
     */
    public Integer getEstateId() {
        return estateId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column SUBSIDY.ESTATE_ID
     *
     * @param estateId the value for SUBSIDY.ESTATE_ID
     *
     * @mbggenerated Wed May 03 17:21:11 CST 2017
     */
    public void setEstateId(Integer estateId) {
        this.estateId = estateId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column SUBSIDY.UPDATE_TIME
     *
     * @return the value of SUBSIDY.UPDATE_TIME
     *
     * @mbggenerated Wed May 03 17:21:11 CST 2017
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column SUBSIDY.UPDATE_TIME
     *
     * @param updateTime the value for SUBSIDY.UPDATE_TIME
     *
     * @mbggenerated Wed May 03 17:21:11 CST 2017
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column SUBSIDY.UPDATE_USER
     *
     * @return the value of SUBSIDY.UPDATE_USER
     *
     * @mbggenerated Wed May 03 17:21:11 CST 2017
     */
    public Integer getUpdateUser() {
        return updateUser;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column SUBSIDY.UPDATE_USER
     *
     * @param updateUser the value for SUBSIDY.UPDATE_USER
     *
     * @mbggenerated Wed May 03 17:21:11 CST 2017
     */
    public void setUpdateUser(Integer updateUser) {
        this.updateUser = updateUser;
    }
}