package com.rent.entity;

/**
 * @author liwenxiang
 * Date:2019/2/25
 * Time:9:56
 */
public class Lock {
    private String _deviceid;
    private String name;
    private String _logicid;
    private boolean _isentity;
    private Integer _nodeid;
    private boolean _islogic;
    private String _nodetype;
    private Integer houseid;
    private String housedesc;
    private boolean ifBind;

    public String get_deviceid() {
        return _deviceid;
    }

    public void set_deviceid(String _deviceid) {
        this._deviceid = _deviceid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String get_logicid() {
        return _logicid;
    }

    public void set_logicid(String _logicid) {
        this._logicid = _logicid;
    }

    public boolean is_isentity() {
        return _isentity;
    }

    public void set_isentity(boolean _isentity) {
        this._isentity = _isentity;
    }

    public Integer get_nodeid() {
        return _nodeid;
    }

    public void set_nodeid(Integer _nodeid) {
        this._nodeid = _nodeid;
    }

    public boolean is_islogic() {
        return _islogic;
    }

    public void set_islogic(boolean _islogic) {
        this._islogic = _islogic;
    }

    public String get_nodetype() {
        return _nodetype;
    }

    public void set_nodetype(String _nodetype) {
        this._nodetype = _nodetype;
    }

    public Integer getHouseid() {
        return houseid;
    }

    public void setHouseid(Integer houseid) {
        this.houseid = houseid;
    }

    public String getHousedesc() {
        return housedesc;
    }

    public void setHousedesc(String housedesc) {
        this.housedesc = housedesc;
    }

    public boolean isIfBind() {
        return ifBind;
    }

    public void setIfBind(boolean ifBind) {
        this.ifBind = ifBind;
    }
}
