package com.rent.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Module {
	
	
	
    public Module() {
		super();
	}

	public Module(Integer id, String name, Integer menuId, Integer pid, Integer levels) {
		super();
		this.id = id;
		this.name = name;
		this.menuId = menuId;
		this.pid = pid;
		this.levels = levels;
	}
	
	private Module parentModule;
	
	private Integer rightsCountOfLevel1;//一级菜单下的权限数
	
	private List<Module> btnModules=new ArrayList<Module>();
	
	

	public List<Module> getBtnModules() {
		return btnModules;
	}

	public void setBtnModules(List<Module> btnModules) {
		this.btnModules = btnModules;
	}

	
	public Integer getRightsCountOfLevel1() {
		return rightsCountOfLevel1;
	}

	public void setRightsCountOfLevel1(Integer rightsCountOfLevel1) {
		this.rightsCountOfLevel1 = rightsCountOfLevel1;
	}

	/**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column MODULE.ID
     *
     * @mbggenerated Sun Jun 04 17:36:23 CST 2017
     */
    private Integer id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column MODULE.NAME
     *
     * @mbggenerated Sun Jun 04 17:36:23 CST 2017
     */
    private String name;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column MODULE.MENU_ID
     *
     * @mbggenerated Sun Jun 04 17:36:23 CST 2017
     */
    private Integer menuId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column MODULE.PID
     *
     * @mbggenerated Sun Jun 04 17:36:23 CST 2017
     */
    private Integer pid;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column MODULE.LEVELS
     *
     * @mbggenerated Sun Jun 04 17:36:23 CST 2017
     */
    private Integer levels;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column MODULE.CREATEUSER
     *
     * @mbggenerated Sun Jun 04 17:36:23 CST 2017
     */
    private String createuser;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column MODULE.CREATETIME
     *
     * @mbggenerated Sun Jun 04 17:36:23 CST 2017
     */
    private Date createtime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column MODULE.UPDATEUSER
     *
     * @mbggenerated Sun Jun 04 17:36:23 CST 2017
     */
    private String updateuser;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column MODULE.UPDATETIME
     *
     * @mbggenerated Sun Jun 04 17:36:23 CST 2017
     */
    private Date updatetime;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column MODULE.ID
     *
     * @return the value of MODULE.ID
     *
     * @mbggenerated Sun Jun 04 17:36:23 CST 2017
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column MODULE.ID
     *
     * @param id the value for MODULE.ID
     *
     * @mbggenerated Sun Jun 04 17:36:23 CST 2017
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column MODULE.NAME
     *
     * @return the value of MODULE.NAME
     *
     * @mbggenerated Sun Jun 04 17:36:23 CST 2017
     */
    public String getName() {
        return name;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column MODULE.NAME
     *
     * @param name the value for MODULE.NAME
     *
     * @mbggenerated Sun Jun 04 17:36:23 CST 2017
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column MODULE.MENU_ID
     *
     * @return the value of MODULE.MENU_ID
     *
     * @mbggenerated Sun Jun 04 17:36:23 CST 2017
     */
    public Integer getMenuId() {
        return menuId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column MODULE.MENU_ID
     *
     * @param menuId the value for MODULE.MENU_ID
     *
     * @mbggenerated Sun Jun 04 17:36:23 CST 2017
     */
    public void setMenuId(Integer menuId) {
        this.menuId = menuId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column MODULE.PID
     *
     * @return the value of MODULE.PID
     *
     * @mbggenerated Sun Jun 04 17:36:23 CST 2017
     */
    public Integer getPid() {
        return pid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column MODULE.PID
     *
     * @param pid the value for MODULE.PID
     *
     * @mbggenerated Sun Jun 04 17:36:23 CST 2017
     */
    public void setPid(Integer pid) {
        this.pid = pid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column MODULE.LEVELS
     *
     * @return the value of MODULE.LEVELS
     *
     * @mbggenerated Sun Jun 04 17:36:23 CST 2017
     */
    public Integer getLevels() {
        return levels;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column MODULE.LEVELS
     *
     * @param levels the value for MODULE.LEVELS
     *
     * @mbggenerated Sun Jun 04 17:36:23 CST 2017
     */
    public void setLevels(Integer levels) {
        this.levels = levels;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column MODULE.CREATEUSER
     *
     * @return the value of MODULE.CREATEUSER
     *
     * @mbggenerated Sun Jun 04 17:36:23 CST 2017
     */
    public String getCreateuser() {
        return createuser;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column MODULE.CREATEUSER
     *
     * @param createuser the value for MODULE.CREATEUSER
     *
     * @mbggenerated Sun Jun 04 17:36:23 CST 2017
     */
    public void setCreateuser(String createuser) {
        this.createuser = createuser;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column MODULE.CREATETIME
     *
     * @return the value of MODULE.CREATETIME
     *
     * @mbggenerated Sun Jun 04 17:36:23 CST 2017
     */
    public Date getCreatetime() {
        return createtime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column MODULE.CREATETIME
     *
     * @param createtime the value for MODULE.CREATETIME
     *
     * @mbggenerated Sun Jun 04 17:36:23 CST 2017
     */
    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column MODULE.UPDATEUSER
     *
     * @return the value of MODULE.UPDATEUSER
     *
     * @mbggenerated Sun Jun 04 17:36:23 CST 2017
     */
    public String getUpdateuser() {
        return updateuser;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column MODULE.UPDATEUSER
     *
     * @param updateuser the value for MODULE.UPDATEUSER
     *
     * @mbggenerated Sun Jun 04 17:36:23 CST 2017
     */
    public void setUpdateuser(String updateuser) {
        this.updateuser = updateuser;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column MODULE.UPDATETIME
     *
     * @return the value of MODULE.UPDATETIME
     *
     * @mbggenerated Sun Jun 04 17:36:23 CST 2017
     */
    public Date getUpdatetime() {
        return updatetime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column MODULE.UPDATETIME
     *
     * @param updatetime the value for MODULE.UPDATETIME
     *
     * @mbggenerated Sun Jun 04 17:36:23 CST 2017
     */
    public void setUpdatetime(Date updatetime) {
        this.updatetime = updatetime;
    }
	public Module getParentModule() {
		return parentModule;
	}

	public void setParentModule(Module parentModule) {
		this.parentModule = parentModule;
	}

}