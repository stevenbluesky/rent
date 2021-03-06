package com.rent.entity;

import java.util.Date;
import java.util.List;

public class Role {
	
	
	
    public Role() {
		super();
	}

	public Role(Integer id, String name, String modules) {
		super();
		this.id = id;
		this.name = name;
		this.modules = modules;
	}

	private Boolean isHouseManager=null;
	private List<Module> moduleList;
	
	
	
	
	public Boolean getIsHouseManager() {
		return isHouseManager;
	}

	public void setIsHouseManager(Boolean isHouseManager) {
		this.isHouseManager = isHouseManager;
	}

	/**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ROLE.ID
     *
     * @mbggenerated Sun Jun 04 17:36:23 CST 2017
     */
    private Integer id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ROLE.NAME
     *
     * @mbggenerated Sun Jun 04 17:36:23 CST 2017
     */
    private String name;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ROLE.MODULES
     *
     * @mbggenerated Sun Jun 04 17:36:23 CST 2017
     */
    private String modules;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ROLE.CREATEUSER
     *
     * @mbggenerated Sun Jun 04 17:36:23 CST 2017
     */
    private String createuser;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ROLE.CREATETIME
     *
     * @mbggenerated Sun Jun 04 17:36:23 CST 2017
     */
    private Date createtime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ROLE.UPDATEUSER
     *
     * @mbggenerated Sun Jun 04 17:36:23 CST 2017
     */
    private String updateuser;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ROLE.UPDATETIME
     *
     * @mbggenerated Sun Jun 04 17:36:23 CST 2017
     */
    private Date updatetime;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ROLE.ID
     *
     * @return the value of ROLE.ID
     *
     * @mbggenerated Sun Jun 04 17:36:23 CST 2017
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ROLE.ID
     *
     * @param id the value for ROLE.ID
     *
     * @mbggenerated Sun Jun 04 17:36:23 CST 2017
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ROLE.NAME
     *
     * @return the value of ROLE.NAME
     *
     * @mbggenerated Sun Jun 04 17:36:23 CST 2017
     */
    public String getName() {
        return name;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ROLE.NAME
     *
     * @param name the value for ROLE.NAME
     *
     * @mbggenerated Sun Jun 04 17:36:23 CST 2017
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ROLE.MODULES
     *
     * @return the value of ROLE.MODULES
     *
     * @mbggenerated Sun Jun 04 17:36:23 CST 2017
     */
    public String getModules() {
        return modules;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ROLE.MODULES
     *
     * @param modules the value for ROLE.MODULES
     *
     * @mbggenerated Sun Jun 04 17:36:23 CST 2017
     */
    public void setModules(String modules) {
        this.modules = modules;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ROLE.CREATEUSER
     *
     * @return the value of ROLE.CREATEUSER
     *
     * @mbggenerated Sun Jun 04 17:36:23 CST 2017
     */
    public String getCreateuser() {
        return createuser;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ROLE.CREATEUSER
     *
     * @param createuser the value for ROLE.CREATEUSER
     *
     * @mbggenerated Sun Jun 04 17:36:23 CST 2017
     */
    public void setCreateuser(String createuser) {
        this.createuser = createuser;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ROLE.CREATETIME
     *
     * @return the value of ROLE.CREATETIME
     *
     * @mbggenerated Sun Jun 04 17:36:23 CST 2017
     */
    public Date getCreatetime() {
        return createtime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ROLE.CREATETIME
     *
     * @param createtime the value for ROLE.CREATETIME
     *
     * @mbggenerated Sun Jun 04 17:36:23 CST 2017
     */
    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ROLE.UPDATEUSER
     *
     * @return the value of ROLE.UPDATEUSER
     *
     * @mbggenerated Sun Jun 04 17:36:23 CST 2017
     */
    public String getUpdateuser() {
        return updateuser;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ROLE.UPDATEUSER
     *
     * @param updateuser the value for ROLE.UPDATEUSER
     *
     * @mbggenerated Sun Jun 04 17:36:23 CST 2017
     */
    public void setUpdateuser(String updateuser) {
        this.updateuser = updateuser;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ROLE.UPDATETIME
     *
     * @return the value of ROLE.UPDATETIME
     *
     * @mbggenerated Sun Jun 04 17:36:23 CST 2017
     */
    public Date getUpdatetime() {
        return updatetime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ROLE.UPDATETIME
     *
     * @param updatetime the value for ROLE.UPDATETIME
     *
     * @mbggenerated Sun Jun 04 17:36:23 CST 2017
     */
    public void setUpdatetime(Date updatetime) {
        this.updatetime = updatetime;
    }

	public List<Module> getModuleList() {
		return moduleList;
	}

	public void setModuleList(List<Module> moduleList) {
		this.moduleList = moduleList;
	}
    
}