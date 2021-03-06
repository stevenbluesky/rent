package com.rent.entity;

import org.apache.shiro.SecurityUtils;

import org.apache.shiro.session.InvalidSessionException;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;



import java.util.ArrayList;
import java.util.Date;
import java.util.List;



public class Users  {
	
	public Users() {
		super();
	}

	public Users(Integer id, String name, String pwd, Short sex, String phone, String address, Integer estateId,
			String roles, String createuser, Date createtime, String updateuser, Date updatetime) {
		super();
		this.id = id;
		this.name = name;
		this.pwd = pwd;
		this.sex = sex;
		this.phone = phone;
		this.address = address;
		this.estateId = estateId;
		this.roles = roles;
		this.createuser = createuser;
		this.createtime = createtime;
		this.updateuser = updateuser;
		this.updatetime = updatetime;
	}

	

	private Estate estate;
	private List<Role> rolesList = new ArrayList<Role>();

	public Estate getEstate() {
		return estate;
	}

	public void setEstate(Estate estate) {
		this.estate = estate;
	}

	public List<Role> getRolesList() {
		return rolesList;
	}

	public void setRolesList(List<Role> rolesList) {
		this.rolesList = rolesList;
	}

	public Integer getDel() {
		return del;
	}

	public void setDel(Integer del) {
		this.del = del;
	}



	private Integer del;
	
	
	
	
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to
	 * the database column USERS.ID
	 *
	 * @mbggenerated Sun Jun 04 17:36:23 CST 2017
	 */
	private Integer id;

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to
	 * the database column USERS.NAME
	 *
	 * @mbggenerated Sun Jun 04 17:36:23 CST 2017
	 */
	private String name;

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to
	 * the database column USERS.PWD
	 *
	 * @mbggenerated Sun Jun 04 17:36:23 CST 2017
	 */
	private String pwd;

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to
	 * the database column USERS.SEX
	 *
	 * @mbggenerated Sun Jun 04 17:36:23 CST 2017
	 */
	private Short sex;

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to
	 * the database column USERS.PHONE
	 *
	 * @mbggenerated Sun Jun 04 17:36:23 CST 2017
	 */
	private String phone;

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to
	 * the database column USERS.ADDRESS
	 *
	 * @mbggenerated Sun Jun 04 17:36:23 CST 2017
	 */
	private String address;

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to
	 * the database column USERS.ESTATE_ID
	 *
	 * @mbggenerated Sun Jun 04 17:36:23 CST 2017
	 */
	private Integer estateId;

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to
	 * the database column USERS.ROLES
	 *
	 * @mbggenerated Sun Jun 04 17:36:23 CST 2017
	 */
	private String roles;

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to
	 * the database column USERS.CREATEUSER
	 *
	 * @mbggenerated Sun Jun 04 17:36:23 CST 2017
	 */
	private String createuser;

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to
	 * the database column USERS.CREATETIME
	 *
	 * @mbggenerated Sun Jun 04 17:36:23 CST 2017
	 */
	private Date createtime;

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to
	 * the database column USERS.UPDATEUSER
	 *
	 * @mbggenerated Sun Jun 04 17:36:23 CST 2017
	 */
	private String updateuser;

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to
	 * the database column USERS.UPDATETIME
	 *
	 * @mbggenerated Sun Jun 04 17:36:23 CST 2017
	 */
	private Date updatetime;

	/**
	 * This method was generated by MyBatis Generator. This method returns the
	 * value of the database column USERS.ID
	 *
	 * @return the value of USERS.ID
	 *
	 * @mbggenerated Sun Jun 04 17:36:23 CST 2017
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the
	 * value of the database column USERS.ID
	 *
	 * @param id
	 *            the value for USERS.ID
	 *
	 * @mbggenerated Sun Jun 04 17:36:23 CST 2017
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the
	 * value of the database column USERS.NAME
	 *
	 * @return the value of USERS.NAME
	 *
	 * @mbggenerated Sun Jun 04 17:36:23 CST 2017
	 */
	public String getName() {
		return name;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the
	 * value of the database column USERS.NAME
	 *
	 * @param name
	 *            the value for USERS.NAME
	 *
	 * @mbggenerated Sun Jun 04 17:36:23 CST 2017
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the
	 * value of the database column USERS.PWD
	 *
	 * @return the value of USERS.PWD
	 *
	 * @mbggenerated Sun Jun 04 17:36:23 CST 2017
	 */
	public String getPwd() {
		return pwd;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the
	 * value of the database column USERS.PWD
	 *
	 * @param pwd
	 *            the value for USERS.PWD
	 *
	 * @mbggenerated Sun Jun 04 17:36:23 CST 2017
	 */
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the
	 * value of the database column USERS.SEX
	 *
	 * @return the value of USERS.SEX
	 *
	 * @mbggenerated Sun Jun 04 17:36:23 CST 2017
	 */
	public Short getSex() {
		return sex;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the
	 * value of the database column USERS.SEX
	 *
	 * @param sex
	 *            the value for USERS.SEX
	 *
	 * @mbggenerated Sun Jun 04 17:36:23 CST 2017
	 */
	public void setSex(Short sex) {
		this.sex = sex;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the
	 * value of the database column USERS.PHONE
	 *
	 * @return the value of USERS.PHONE
	 *
	 * @mbggenerated Sun Jun 04 17:36:23 CST 2017
	 */
	public String getPhone() {
		return phone;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the
	 * value of the database column USERS.PHONE
	 *
	 * @param phone
	 *            the value for USERS.PHONE
	 *
	 * @mbggenerated Sun Jun 04 17:36:23 CST 2017
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the
	 * value of the database column USERS.ADDRESS
	 *
	 * @return the value of USERS.ADDRESS
	 *
	 * @mbggenerated Sun Jun 04 17:36:23 CST 2017
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the
	 * value of the database column USERS.ADDRESS
	 *
	 * @param address
	 *            the value for USERS.ADDRESS
	 *
	 * @mbggenerated Sun Jun 04 17:36:23 CST 2017
	 */
	public void setAddress(String address) {
		this.address = address;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the
	 * value of the database column USERS.ESTATE_ID
	 *
	 * @return the value of USERS.ESTATE_ID
	 *
	 * @mbggenerated Sun Jun 04 17:36:23 CST 2017
	 */
	public Integer getEstateId() {
		return estateId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the
	 * value of the database column USERS.ESTATE_ID
	 *
	 * @param estateId
	 *            the value for USERS.ESTATE_ID
	 *
	 * @mbggenerated Sun Jun 04 17:36:23 CST 2017
	 */
	public void setEstateId(Integer estateId) {
		this.estateId = estateId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the
	 * value of the database column USERS.ROLES
	 *
	 * @return the value of USERS.ROLES
	 *
	 * @mbggenerated Sun Jun 04 17:36:23 CST 2017
	 */
	public String getRoles() {
		return roles;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the
	 * value of the database column USERS.ROLES
	 *
	 * @param roles
	 *            the value for USERS.ROLES
	 *
	 * @mbggenerated Sun Jun 04 17:36:23 CST 2017
	 */
	public void setRoles(String roles) {
		this.roles = roles;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the
	 * value of the database column USERS.CREATEUSER
	 *
	 * @return the value of USERS.CREATEUSER
	 *
	 * @mbggenerated Sun Jun 04 17:36:23 CST 2017
	 */
	public String getCreateuser() {
		return createuser;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the
	 * value of the database column USERS.CREATEUSER
	 *
	 * @param createuser
	 *            the value for USERS.CREATEUSER
	 *
	 * @mbggenerated Sun Jun 04 17:36:23 CST 2017
	 */
	public void setCreateuser(String createuser) {
		this.createuser = createuser;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the
	 * value of the database column USERS.CREATETIME
	 *
	 * @return the value of USERS.CREATETIME
	 *
	 * @mbggenerated Sun Jun 04 17:36:23 CST 2017
	 */
	public Date getCreatetime() {
		return createtime;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the
	 * value of the database column USERS.CREATETIME
	 *
	 * @param createtime
	 *            the value for USERS.CREATETIME
	 *
	 * @mbggenerated Sun Jun 04 17:36:23 CST 2017
	 */
	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the
	 * value of the database column USERS.UPDATEUSER
	 *
	 * @return the value of USERS.UPDATEUSER
	 *
	 * @mbggenerated Sun Jun 04 17:36:23 CST 2017
	 */
	public String getUpdateuser() {
		return updateuser;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the
	 * value of the database column USERS.UPDATEUSER
	 *
	 * @param updateuser
	 *            the value for USERS.UPDATEUSER
	 *
	 * @mbggenerated Sun Jun 04 17:36:23 CST 2017
	 */
	public void setUpdateuser(String updateuser) {
		this.updateuser = updateuser;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the
	 * value of the database column USERS.UPDATETIME
	 *
	 * @return the value of USERS.UPDATETIME
	 *
	 * @mbggenerated Sun Jun 04 17:36:23 CST 2017
	 */
	public Date getUpdatetime() {
		return updatetime;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the
	 * value of the database column USERS.UPDATETIME
	 *
	 * @param updatetime
	 *            the value for USERS.UPDATETIME
	 *
	 * @mbggenerated Sun Jun 04 17:36:23 CST 2017
	 */
	public void setUpdatetime(Date updatetime) {
		this.updatetime = updatetime;
	}
	

	public static Session getSession(){
		try{
			Subject subject = SecurityUtils.getSubject();
			Session session = subject.getSession(false);
			if (session == null){
				session = subject.getSession();
			}
			if (session != null){
				return session;
			}
//			subject.logout();
		}catch (InvalidSessionException e){
			
		}
		return null;
	}
	public static Object getCache(String key, Object defaultValue) {

		Object obj = getSession().getAttribute(key);
		return obj==null?defaultValue:obj;
	}

	public static void putCache(String key, Object value) {

		getSession().setAttribute(key, value);
	}

	public static void removeCache(String key) {
		
		getSession().removeAttribute(key);
	}
}