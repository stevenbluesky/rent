package com.rent.entity;

import java.math.BigDecimal;
import java.util.Date;

/**
 * BillData entity. @author MyEclipse Persistence Tools
 */

public class BillData implements java.io.Serializable {

	// Fields

	private Integer id;
	private Integer inumber;
	private String code;
	private String descript;
	private String descript1;
	private String unit;
	private Double number;
	private Double price;
	private Double charge;
	private Double credit;
	private String empno;
	private Date logdate;
	private String item;
	private String sort;
	private String char1;
	private String char2;
	private String char3;
	private String char4;
	private String char5;
	private String char6;
	private String char7;
	private String char8;
	private String char9;
	private String char10;
	private String char11;
	private String char12;
	private String char13;
	private String char14;
	private BigDecimal mone1;
	private BigDecimal mone2;
	private BigDecimal mone3;
	private BigDecimal mone4;
	private Date date1;
	private Date date2;
	private Date date3;
	private Date date4;
	private String sum1;
	private String sum2;
	private String sum3;
	private String sum4;
	private String sum5;
	private String sum6;
	private String sum7;
	private String sum8;
	private String sum9;

	// Constructors

	/** default constructor */
	public BillData() {
	}

	/** full constructor */
	public BillData(Integer inumber, String code, String descript,
			String descript1, String unit, Double number, Double price,
			Double charge, Double credit, String empno, Date logdate,
			String item, String sort, String char1, String char2, String char3,
			String char4, String char5, String char6, String char7,
			String char8, String char9, String char10, String char11,
			String char12, String char13, String char14, BigDecimal mone1,
			BigDecimal mone2, BigDecimal mone3, BigDecimal mone4, Date date1,
			Date date2, Date date3, Date date4, String sum1, String sum2,
			String sum3, String sum4, String sum5, String sum6, String sum7,
			String sum8, String sum9) {
		this.inumber = inumber;
		this.code = code;
		this.descript = descript;
		this.descript1 = descript1;
		this.unit = unit;
		this.number = number;
		this.price = price;
		this.charge = charge;
		this.credit = credit;
		this.empno = empno;
		this.logdate = logdate;
		this.item = item;
		this.sort = sort;
		this.char1 = char1;
		this.char2 = char2;
		this.char3 = char3;
		this.char4 = char4;
		this.char5 = char5;
		this.char6 = char6;
		this.char7 = char7;
		this.char8 = char8;
		this.char9 = char9;
		this.char10 = char10;
		this.char11 = char11;
		this.char12 = char12;
		this.char13 = char13;
		this.char14 = char14;
		this.mone1 = mone1;
		this.mone2 = mone2;
		this.mone3 = mone3;
		this.mone4 = mone4;
		this.date1 = date1;
		this.date2 = date2;
		this.date3 = date3;
		this.date4 = date4;
		this.sum1 = sum1;
		this.sum2 = sum2;
		this.sum3 = sum3;
		this.sum4 = sum4;
		this.sum5 = sum5;
		this.sum6 = sum6;
		this.sum7 = sum7;
		this.sum8 = sum8;
		this.sum9 = sum9;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getInumber() {
		return this.inumber;
	}

	public void setInumber(Integer inumber) {
		this.inumber = inumber;
	}

	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getDescript() {
		return this.descript;
	}

	public void setDescript(String descript) {
		this.descript = descript;
	}

	public String getDescript1() {
		return this.descript1;
	}

	public void setDescript1(String descript1) {
		this.descript1 = descript1;
	}

	public String getUnit() {
		return this.unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public Double getNumber() {
		return this.number;
	}

	public void setNumber(Double number) {
		this.number = number;
	}

	public Double getPrice() {
		return this.price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Double getCharge() {
		return this.charge;
	}

	public void setCharge(Double charge) {
		this.charge = charge;
	}

	public Double getCredit() {
		return this.credit;
	}

	public void setCredit(Double credit) {
		this.credit = credit;
	}

	public String getEmpno() {
		return this.empno;
	}

	public void setEmpno(String empno) {
		this.empno = empno;
	}

	public Date getLogdate() {
		return this.logdate;
	}

	public void setLogdate(Date logdate) {
		this.logdate = logdate;
	}

	public String getItem() {
		return this.item;
	}

	public void setItem(String item) {
		this.item = item;
	}

	public String getSort() {
		return this.sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}

	public String getChar1() {
		return this.char1;
	}

	public void setChar1(String char1) {
		this.char1 = char1;
	}

	public String getChar2() {
		return this.char2;
	}

	public void setChar2(String char2) {
		this.char2 = char2;
	}

	public String getChar3() {
		return this.char3;
	}

	public void setChar3(String char3) {
		this.char3 = char3;
	}

	public String getChar4() {
		return this.char4;
	}

	public void setChar4(String char4) {
		this.char4 = char4;
	}

	public String getChar5() {
		return this.char5;
	}

	public void setChar5(String char5) {
		this.char5 = char5;
	}

	public String getChar6() {
		return this.char6;
	}

	public void setChar6(String char6) {
		this.char6 = char6;
	}

	public String getChar7() {
		return this.char7;
	}

	public void setChar7(String char7) {
		this.char7 = char7;
	}

	public String getChar8() {
		return this.char8;
	}

	public void setChar8(String char8) {
		this.char8 = char8;
	}

	public String getChar9() {
		return this.char9;
	}

	public void setChar9(String char9) {
		this.char9 = char9;
	}

	public String getChar10() {
		return this.char10;
	}

	public void setChar10(String char10) {
		this.char10 = char10;
	}

	public String getChar11() {
		return this.char11;
	}

	public void setChar11(String char11) {
		this.char11 = char11;
	}

	public String getChar12() {
		return this.char12;
	}

	public void setChar12(String char12) {
		this.char12 = char12;
	}

	public String getChar13() {
		return this.char13;
	}

	public void setChar13(String char13) {
		this.char13 = char13;
	}

	public String getChar14() {
		return this.char14;
	}

	public void setChar14(String char14) {
		this.char14 = char14;
	}

	public BigDecimal getMone1() {
		return this.mone1;
	}

	public void setMone1(BigDecimal mone1) {
		this.mone1 = mone1;
	}

	public BigDecimal getMone2() {
		return this.mone2;
	}

	public void setMone2(BigDecimal mone2) {
		this.mone2 = mone2;
	}
	public BigDecimal getMone3() {
		return this.mone3;
	}

	public void setMone3(BigDecimal mone3) {
		this.mone3 = mone3;
	}

	public BigDecimal getMone4() {
		return this.mone4;
	}

	public void setMone4(BigDecimal mone4) {
		this.mone4 = mone4;
	}

	public Date getDate1() {
		return this.date1;
	}

	public void setDate1(Date date1) {
		this.date1 = date1;
	}

	public Date getDate2() {
		return this.date2;
	}

	public void setDate2(Date date2) {
		this.date2 = date2;
	}

	public Date getDate3() {
		return this.date3;
	}

	public void setDate3(Date date3) {
		this.date3 = date3;
	}

	public Date getDate4() {
		return this.date4;
	}

	public void setDate4(Date date4) {
		this.date4 = date4;
	}

	public String getSum1() {
		return this.sum1;
	}

	public void setSum1(String sum1) {
		this.sum1 = sum1;
	}

	public String getSum2() {
		return this.sum2;
	}

	public void setSum2(String sum2) {
		this.sum2 = sum2;
	}

	public String getSum3() {
		return this.sum3;
	}

	public void setSum3(String sum3) {
		this.sum3 = sum3;
	}

	public String getSum4() {
		return this.sum4;
	}

	public void setSum4(String sum4) {
		this.sum4 = sum4;
	}

	public String getSum5() {
		return this.sum5;
	}

	public void setSum5(String sum5) {
		this.sum5 = sum5;
	}

	public String getSum6() {
		return this.sum6;
	}

	public void setSum6(String sum6) {
		this.sum6 = sum6;
	}

	public String getSum7() {
		return this.sum7;
	}

	public void setSum7(String sum7) {
		this.sum7 = sum7;
	}

	public String getSum8() {
		return this.sum8;
	}

	public void setSum8(String sum8) {
		this.sum8 = sum8;
	}

	public String getSum9() {
		return this.sum9;
	}

	public void setSum9(String sum9) {
		this.sum9 = sum9;
	}

}