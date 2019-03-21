package com.rent.entity;

import java.util.Date;

/**
 * @author liwenxiang
 * Date:2019/2/28
 * Time:16:47
 */
public class DoorlockUser {

    private Integer doorlockuserid;
    private Integer usertype;
    private Integer usercode;
    private String username;
    private String phonenumber;
    private String password;
    private Date validfrom;
    private Date validthrough;
    private Date createtime;
    private Integer weekday;
    private String starttime;
    private String endtime;
    private Integer houseid;
    private Integer masterid;
    private String deviceid;
    private Integer status;
    private Integer synstatus;
    private String receipt;
    private String guestno;
    private String reason;

    /*doorlockUser.setUsername(userName);
    doorlockUser.setPhonenumber(mobilePhone);
    doorlockUser.setPassword(AES.encrypt2Str(password));
    doorlockUser.setValidfrom(begin);
    doorlockUser.setValidthrough(end);
    doorlockUser.setCreatetime(new Date());
    doorlockUser.setUsercode(usercode);
    doorlockUser.setUsertype(Global.USERTYPE_PASSWORD);
    doorlockUser.setHouseid(master.getHouseId());
    doorlockUser.setMasterid(masterId);
    doorlockUser.setStatus(Global.STATUS_SENDING);
    doorlockUser.setSynstatus(Global.SYN_STATUS_TO_BE_SYNCHRONIZED);
    doorlockUser.setDeviceid(house.getAssociatedlock());
    doorlockUser.setReceipt(receipt);*/

    public DoorlockUser() {}

    public DoorlockUser(Integer usertype, Integer usercode, String username, String phonenumber, String password, Date validfrom, Date validthrough,
                        Date createtime, Integer houseid, Integer masterid, String deviceid, Integer status, Integer synstatus, String receipt, String guestno) {
        this.usertype = usertype;
        this.usercode = usercode;
        this.username = username;
        this.phonenumber = phonenumber;
        this.password = password;
        this.validfrom = validfrom;
        this.validthrough = validthrough;
        this.createtime = createtime;
        this.houseid = houseid;
        this.masterid = masterid;
        this.deviceid = deviceid;
        this.status = status;
        this.synstatus = synstatus;
        this.receipt = receipt;
        this.guestno = guestno;
    }

    public Integer getDoorlockuserid() {
        return doorlockuserid;
    }

    public void setDoorlockuserid(Integer doorlockuserid) {
        this.doorlockuserid = doorlockuserid;
    }

    public Integer getUsertype() {
        return usertype;
    }

    public void setUsertype(Integer usertype) {
        this.usertype = usertype;
    }

    public Integer getUsercode() {
        return usercode;
    }

    public void setUsercode(Integer usercode) {
        this.usercode = usercode;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getValidfrom() {
        return validfrom;
    }

    public void setValidfrom(Date validfrom) {
        this.validfrom = validfrom;
    }

    public Date getValidthrough() {
        return validthrough;
    }

    public void setValidthrough(Date validthrough) {
        this.validthrough = validthrough;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public Integer getWeekday() {
        return weekday;
    }

    public void setWeekday(Integer weekday) {
        this.weekday = weekday;
    }

    public String getStarttime() {
        return starttime;
    }

    public void setStarttime(String starttime) {
        this.starttime = starttime;
    }

    public String getEndtime() {
        return endtime;
    }

    public void setEndtime(String endtime) {
        this.endtime = endtime;
    }

    public Integer getHouseid() {
        return houseid;
    }

    public void setHouseid(Integer houseid) {
        this.houseid = houseid;
    }

    public Integer getMasterid() {
        return masterid;
    }

    public void setMasterid(Integer masterid) {
        this.masterid = masterid;
    }

    public String getDeviceid() {
        return deviceid;
    }

    public void setDeviceid(String deviceid) {
        this.deviceid = deviceid;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getSynstatus() {
        return synstatus;
    }

    public void setSynstatus(Integer synstatus) {
        this.synstatus = synstatus;
    }

    public String getReceipt() {
        return receipt;
    }

    public void setReceipt(String receipt) {
        this.receipt = receipt;
    }

    public String getGuestno() {
        return guestno;
    }

    public void setGuestno(String guestno) {
        this.guestno = guestno;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }
}
