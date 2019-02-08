package com.rent.entity;

import java.math.BigDecimal;
import java.util.Date;

/**
 * PrhRental2 entity. @author MyEclipse Persistence Tools
 */

public class PrhRental2 implements java.io.Serializable {

	// Fields

	private Integer id;
	private String accnt;
	private Integer inumber;
	private String flag;
	private String orgid;
	private String prtno;
	private String name;
	private String guestno;
	private String roomno;
	private String vroomno;
	private String rmtype;
	private Double rate;
	private String fileno;
	private Double number0;
	private Double deposit;
	private Double number;
	private Double charge;
	private Double charge1;
	private Double charge2;
	private Date bdate;
	private Date edate;
	private Double amount;
	private String ref1;
	private String ref2;
	private String ref3;
	private String ref4;
	private String ref5;
	private String prtid;
	private String prtname;
	private Date prtdate;
	private BigDecimal prttimes;
	private String sta;
	private String cashier;
	private String cashname;
	private Date cashdate;
	private Date sysdate;
	private String audit1;
	private String logid1;
	private Date audit1time;
	private String audit2;
	private String logid2;
	private Date audittime;
	private String iflag;
	private String ino;
	private String ilogid;
	private String ilogname;
	private Date isysdate;
	private Date ilogdate;
	private String method;
	private String oroomno;
	private Integer days;
	private Integer fmonths;
	private Integer fdays;
	private Double famount;
	private String cusno;
	private Date logupdate;

	// Constructors

	/** default constructor */
	public PrhRental2() {
	}

	/** minimal constructor */
	public PrhRental2(String accnt, Integer inumber, String orgid,
			String prtno, String name, String roomno, String vroomno,
			Double rate, Double number0, Double deposit, Double number,
			Double charge, Double charge1, Double charge2, Date bdate,
			Date edate, Double amount, BigDecimal prttimes, String sta) {
		this.accnt = accnt;
		this.inumber = inumber;
		this.orgid = orgid;
		this.prtno = prtno;
		this.name = name;
		this.roomno = roomno;
		this.vroomno = vroomno;
		this.rate = rate;
		this.number0 = number0;
		this.deposit = deposit;
		this.number = number;
		this.charge = charge;
		this.charge1 = charge1;
		this.charge2 = charge2;
		this.bdate = bdate;
		this.edate = edate;
		this.amount = amount;
		this.prttimes = prttimes;
		this.sta = sta;
	}

	/** full constructor */
	public PrhRental2(String accnt, Integer inumber, String flag, String orgid,
			String prtno, String name, String guestno, String roomno,
			String vroomno, String rmtype, Double rate, String fileno,
			Double number0, Double deposit, Double number, Double charge,
			Double charge1, Double charge2, Date bdate, Date edate,
			Double amount, String ref1, String ref2, String ref3, String ref4,
			String ref5, String prtid, String prtname, Date prtdate,
			BigDecimal prttimes, String sta, String cashier, String cashname,
			Date cashdate, Date sysdate, String audit1, String logid1,
			Date audit1time, String audit2, String logid2, Date audittime,
			String iflag, String ino, String ilogid, String ilogname,
			Date isysdate, Date ilogdate, String method, String oroomno,
			Integer days, Integer fmonths, Integer fdays, Double famount,
			String cusno, Date logupdate) {
		this.accnt = accnt;
		this.inumber = inumber;
		this.flag = flag;
		this.orgid = orgid;
		this.prtno = prtno;
		this.name = name;
		this.guestno = guestno;
		this.roomno = roomno;
		this.vroomno = vroomno;
		this.rmtype = rmtype;
		this.rate = rate;
		this.fileno = fileno;
		this.number0 = number0;
		this.deposit = deposit;
		this.number = number;
		this.charge = charge;
		this.charge1 = charge1;
		this.charge2 = charge2;
		this.bdate = bdate;
		this.edate = edate;
		this.amount = amount;
		this.ref1 = ref1;
		this.ref2 = ref2;
		this.ref3 = ref3;
		this.ref4 = ref4;
		this.ref5 = ref5;
		this.prtid = prtid;
		this.prtname = prtname;
		this.prtdate = prtdate;
		this.prttimes = prttimes;
		this.sta = sta;
		this.cashier = cashier;
		this.cashname = cashname;
		this.cashdate = cashdate;
		this.sysdate = sysdate;
		this.audit1 = audit1;
		this.logid1 = logid1;
		this.audit1time = audit1time;
		this.audit2 = audit2;
		this.logid2 = logid2;
		this.audittime = audittime;
		this.iflag = iflag;
		this.ino = ino;
		this.ilogid = ilogid;
		this.ilogname = ilogname;
		this.isysdate = isysdate;
		this.ilogdate = ilogdate;
		this.method = method;
		this.oroomno = oroomno;
		this.days = days;
		this.fmonths = fmonths;
		this.fdays = fdays;
		this.famount = famount;
		this.cusno = cusno;
		this.logupdate = logupdate;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getAccnt() {
		return this.accnt;
	}

	public void setAccnt(String accnt) {
		this.accnt = accnt;
	}

	public Integer getInumber() {
		return this.inumber;
	}

	public void setInumber(Integer inumber) {
		this.inumber = inumber;
	}

	public String getFlag() {
		return this.flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public String getOrgid() {
		return this.orgid;
	}

	public void setOrgid(String orgid) {
		this.orgid = orgid;
	}

	public String getPrtno() {
		return this.prtno;
	}

	public void setPrtno(String prtno) {
		this.prtno = prtno;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getGuestno() {
		return this.guestno;
	}

	public void setGuestno(String guestno) {
		this.guestno = guestno;
	}

	public String getRoomno() {
		return this.roomno;
	}

	public void setRoomno(String roomno) {
		this.roomno = roomno;
	}

	public String getVroomno() {
		return this.vroomno;
	}

	public void setVroomno(String vroomno) {
		this.vroomno = vroomno;
	}

	public String getRmtype() {
		return this.rmtype;
	}

	public void setRmtype(String rmtype) {
		this.rmtype = rmtype;
	}

	public Double getRate() {
		return this.rate;
	}

	public void setRate(Double rate) {
		this.rate = rate;
	}

	public String getFileno() {
		return this.fileno;
	}

	public void setFileno(String fileno) {
		this.fileno = fileno;
	}

	public Double getNumber0() {
		return this.number0;
	}

	public void setNumber0(Double number0) {
		this.number0 = number0;
	}

	public Double getDeposit() {
		return this.deposit;
	}

	public void setDeposit(Double deposit) {
		this.deposit = deposit;
	}

	public Double getNumber() {
		return this.number;
	}

	public void setNumber(Double number) {
		this.number = number;
	}

	public Double getCharge() {
		return this.charge;
	}

	public void setCharge(Double charge) {
		this.charge = charge;
	}

	public Double getCharge1() {
		return this.charge1;
	}

	public void setCharge1(Double charge1) {
		this.charge1 = charge1;
	}

	public Double getCharge2() {
		return this.charge2;
	}

	public void setCharge2(Double charge2) {
		this.charge2 = charge2;
	}

	public Date getBdate() {
		return this.bdate;
	}

	public void setBdate(Date bdate) {
		this.bdate = bdate;
	}

	public Date getEdate() {
		return this.edate;
	}

	public void setEdate(Date edate) {
		this.edate = edate;
	}

	public Double getAmount() {
		return this.amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public String getRef1() {
		return this.ref1;
	}

	public void setRef1(String ref1) {
		this.ref1 = ref1;
	}

	public String getRef2() {
		return this.ref2;
	}

	public void setRef2(String ref2) {
		this.ref2 = ref2;
	}

	public String getRef3() {
		return this.ref3;
	}

	public void setRef3(String ref3) {
		this.ref3 = ref3;
	}

	public String getRef4() {
		return this.ref4;
	}

	public void setRef4(String ref4) {
		this.ref4 = ref4;
	}

	public String getRef5() {
		return this.ref5;
	}

	public void setRef5(String ref5) {
		this.ref5 = ref5;
	}

	public String getPrtid() {
		return this.prtid;
	}

	public void setPrtid(String prtid) {
		this.prtid = prtid;
	}

	public String getPrtname() {
		return this.prtname;
	}

	public void setPrtname(String prtname) {
		this.prtname = prtname;
	}

	public Date getPrtdate() {
		return this.prtdate;
	}

	public void setPrtdate(Date prtdate) {
		this.prtdate = prtdate;
	}

	public BigDecimal getPrttimes() {
		return this.prttimes;
	}

	public void setPrttimes(BigDecimal prttimes) {
		this.prttimes = prttimes;
	}

	public String getSta() {
		return this.sta;
	}

	public void setSta(String sta) {
		this.sta = sta;
	}

	public String getCashier() {
		return this.cashier;
	}

	public void setCashier(String cashier) {
		this.cashier = cashier;
	}

	public String getCashname() {
		return this.cashname;
	}

	public void setCashname(String cashname) {
		this.cashname = cashname;
	}

	public Date getCashdate() {
		return this.cashdate;
	}

	public void setCashdate(Date cashdate) {
		this.cashdate = cashdate;
	}

	public Date getSysdate() {
		return this.sysdate;
	}

	public void setSysdate(Date sysdate) {
		this.sysdate = sysdate;
	}

	public String getAudit1() {
		return this.audit1;
	}

	public void setAudit1(String audit1) {
		this.audit1 = audit1;
	}

	public String getLogid1() {
		return this.logid1;
	}

	public void setLogid1(String logid1) {
		this.logid1 = logid1;
	}

	public Date getAudit1time() {
		return this.audit1time;
	}

	public void setAudit1time(Date audit1time) {
		this.audit1time = audit1time;
	}

	public String getAudit2() {
		return this.audit2;
	}

	public void setAudit2(String audit2) {
		this.audit2 = audit2;
	}

	public String getLogid2() {
		return this.logid2;
	}

	public void setLogid2(String logid2) {
		this.logid2 = logid2;
	}

	public Date getAudittime() {
		return this.audittime;
	}

	public void setAudittime(Date audittime) {
		this.audittime = audittime;
	}

	public String getIflag() {
		return this.iflag;
	}

	public void setIflag(String iflag) {
		this.iflag = iflag;
	}

	public String getIno() {
		return this.ino;
	}

	public void setIno(String ino) {
		this.ino = ino;
	}

	public String getIlogid() {
		return this.ilogid;
	}

	public void setIlogid(String ilogid) {
		this.ilogid = ilogid;
	}

	public String getIlogname() {
		return this.ilogname;
	}

	public void setIlogname(String ilogname) {
		this.ilogname = ilogname;
	}

	public Date getIsysdate() {
		return this.isysdate;
	}

	public void setIsysdate(Date isysdate) {
		this.isysdate = isysdate;
	}

	public Date getIlogdate() {
		return this.ilogdate;
	}

	public void setIlogdate(Date ilogdate) {
		this.ilogdate = ilogdate;
	}

	public String getMethod() {
		return this.method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	public String getOroomno() {
		return this.oroomno;
	}

	public void setOroomno(String oroomno) {
		this.oroomno = oroomno;
	}

	public Integer getDays() {
		return this.days;
	}

	public void setDays(Integer days) {
		this.days = days;
	}

	public Integer getFmonths() {
		return this.fmonths;
	}

	public void setFmonths(Integer fmonths) {
		this.fmonths = fmonths;
	}

	public Integer getFdays() {
		return this.fdays;
	}

	public void setFdays(Integer fdays) {
		this.fdays = fdays;
	}

	public Double getFamount() {
		return this.famount;
	}

	public void setFamount(Double famount) {
		this.famount = famount;
	}

	public String getCusno() {
		return this.cusno;
	}

	public void setCusno(String cusno) {
		this.cusno = cusno;
	}

	public Date getLogupdate() {
		return this.logupdate;
	}

	public void setLogupdate(Date logupdate) {
		this.logupdate = logupdate;
	}

}