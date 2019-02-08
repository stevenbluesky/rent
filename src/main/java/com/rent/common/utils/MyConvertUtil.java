package com.rent.common.utils;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import com.rent.common.config.Global;
import com.rent.entity.Users;

/**
 * 自定义类型转换
 * 
 * @author 崔少岩
 *
 */
public class MyConvertUtil {
	
	public static String getEquipType(String id) {
		Integer i=Integer.valueOf(id);
		String type = "";
		
		switch (i) {
		case 1:
			type= "钥匙";
			break;
		case 2:
			type = "室内家具";
			break;
		case 3:
			type = "室内电器";
			break;
		}
		return type;
	}
	
	public static String getEquipType(int id) {
		String type = "";
		
		switch (id) {
		case 1:
			type= "钥匙";
			break;
		case 2:
			type = "室内家具";
			break;
		case 3:
			type = "室内电器";
			break;
		}
		return type;
	}
	
	public static String getAttachmentType(int id) {
		String type = "";
		
		switch (id) {
		case 1:
			type= "合同";
			break;
		case 2:
			type = "身份证";
			break;
		case 3:
			type = "其他";
			break;
		}
		return type;
	}
	
	
	/**
	 * 根据id获取付款方式组别名（现金、银行卡，支票、其它）
	 * 
	 * @param 付款方式组别id
	 * @return 付款方式组别名
	 */
	public static String getPayWayGroup(int id) {
		String groupNameString = "";
		switch (id) {
		case 0:
			groupNameString = "其它";
			break;
		case 1:
			groupNameString = "现金";
			break;
		case 2:
			groupNameString = "银行卡";
			break;
		case 3:
			groupNameString = "支票";
			break;
		}
		return groupNameString;
	}

	/**
	 * 根据id获取是否收取银行费用（不收，按比例，定额）
	 * 
	 * @param 是否收取银行费用id
	 * @return 是否收取银行费用
	 */
	public static String getIsPay(int id) {
		String isPay = "";
		switch (id) {
		case 1:
			isPay = "不收";
			break;
		case 2:
			isPay = "按比例";
			break;
		case 3:
			isPay = "定额";
			break;
		}
		return isPay;
	}

	public static String getProvince(int headId) {
		String pro = "";
		switch (headId) {
		case 0:
			break;
		case 11:
			pro = "北京";
			break;
		case 12:
			pro = "天津";
			break;
		case 13:
			pro = "河北";
			break;
		case 14:
			pro = "山西";
			break;
		case 15:
			pro = "内蒙古";
			break;
		case 21:
			pro = "辽宁";
			break;
		case 22:
			pro = "吉林";
			break;
		case 23:
			pro = "黑龙江";
			break;
		case 31:
			pro = "上海";
			break;
		case 32:
			pro = "江苏";
			break;
		case 33:
			pro = "浙江";
			break;
		case 34:
			pro = "安徽";
			break;
		case 35:
			pro = "福建";
			break;
		case 36:
			pro = "江西";
			break;
		case 37:
			pro = "山东";
			break;
		case 41:
			pro = "河南";
			break;
		case 42:
			pro = "湖北";
			break;
		case 43:
			pro = "湖南";
			break;
		case 44:
			pro = "广东";
			break;
		case 45:
			pro = "广西";
			break;
		case 46:
			pro = "海南";
			break;
		case 50:
			pro = "重庆";
			break;
		case 51:
			pro = "四川";
			break;
		case 52:
			pro = "贵州";
			break;
		case 53:
			pro = "云南";
			break;
		case 54:
			pro = "西藏";
			break;
		case 61:
			pro = "陕西";
			break;
		case 62:
			pro = "甘肃";
			break;
		case 63:
			pro = "青海";
			break;
		case 64:
			pro = "宁夏";
			break;
		case 65:
			pro = "新疆";
			break;
		case 71:
			pro = "台湾";
			break;
		case 81:
			pro = "香港";
			break;
		case 82:
			pro = "澳门";
			break;
		case 91:
			pro = "国外";
			break;
		}

		return pro;
	}

	/**
	 * 根据id获取租金支付方式模式（普通，月付，季付，特殊）
	 * 
	 * @param 租金支付方式id
	 * @return 租金支付方式
	 */
	public static String getRentPayWay(int id) {
		String rentPayWay = "";
		switch (id) {
		case 1:
			rentPayWay = "普通";
			break;
		case 2:
			rentPayWay = "月付";
			break;
		case 3:
			rentPayWay = "季付";
		case 4:
			rentPayWay = "特殊";
			break;
		}
		return rentPayWay;
	}

	/**
	 * 根据id获取房间状态（签约，登记，欠费，锁定，在租，可租，空置）
	 * 
	 * @param 租金支付方式id
	 * @return 租金支付方式
	 */
	public static String getHouseState(int id) {
		String rentPayWay = "";
		switch (id) {

		case 1:
			rentPayWay = "签约";
			break;
		case 2:
			rentPayWay = "登记";
			break;
		case 3:
			rentPayWay = "欠费";
			break;
		case 4:
			rentPayWay = "锁定";
			break;
		case 5:
			rentPayWay = "在租";
			break;
		case 6:
			rentPayWay = "可租";
			break;
		case 7:
			rentPayWay = "空置维修";
			break;

		case 8:
			rentPayWay = "在租维修";
			break;

		}
		return rentPayWay;
	}

	/**
	 * 根据id获取证件名（普通，月付，季付，特殊）
	 * 
	 * @param 证件id
	 * @return 证件名
	 */
	public static String getIdName(int id) {
		String rentPayWay = "";
		switch (id) {
		case 1:
			rentPayWay = "身份证";
			break;
		}
		return rentPayWay;
	}

	/**
	 * 根据id获取单号状态（普通，月付，季付，特殊）
	 * 
	 * @param
	 * @return 证件名
	 */
	public static String getMasterState(String id) {
		String state = "";
		if (id.equals("1")) {
			state = "在租"; // (包括房屋表中在租，欠费等状态)
		} else if (id.equals("2")) {
			state = "<span style='color:red;'>退租</span>申请中";
		} else if (id.equals("3")) { // 废弃
			state = "已验房";
		} else if (id.equals("4")) {
			state = "已退租";
		} else if (id.equals("5")) {
			state = "<span style='color:green;'>续租</span>申请中";
		} else if (id.equals("6")) {
			state = "在租";// 续租
		} else if (id.equals("7")) {
			state = "登记";
		} else if (id.equals("8")) {
			state = "签约";
		} else if (id.equals("9")) {
			state = "履行完成";
		}
		return state;
	}

	/**
	 * 根据id获取主单类别（是否是续租）
	 * 
	 * @param
	 * @return 证件名
	 */
	public static String getMasterType(String typeId) {
		if (typeId.equals("1")) {
			return "续租";
		} else if (typeId.equals("2")) {
			return "直租";
		}
		return null;
	}

	public static String getRenSta(String sta) {
		if (sta.equals("T")) {
			return "已付清";
		} else if (sta.equals("F")) {
			return "未付清";
		} else if (sta.equals("N")) {
			return "未付清(退)";
		} else if (sta.equals("M")) {
			return "已付清(退)";
		}
		return null;
	}

	/**
	 * 通过当前页和页大小获取当页起始位置
	 * 
	 * @param 当前页
	 * @param 页大小
	 * @return 起始位置和结束位置（下标0为起始位置，下标1为结束位置）
	 */
	public static Integer[] toPagedBeginEnd(int currpage, int size) {

		Integer begin = (currpage - 1) * size + 1;
		Integer end = begin + size - 1;
		Integer[] beginEnd = { begin, end };
		return beginEnd;
	}
	
	//根据session获取当前页
	public static Integer getCurrpageBySession(HttpSession session){
		Integer currpage = (Integer) session.getAttribute("currpage");
		return currpage;
	}
	
	//根据session获取当前登录用户
		public static Users getUserBySession(HttpSession session){
			Users user= (Users) session.getAttribute(Global.USER);
			return user;
		}
	
	// 去除重复数组
	public static String[] distinctArray(String[] array) {

		List<String> temp = new ArrayList<String>();
		for (String i : array) {
			if (!temp.contains(i)) {
				temp.add(i);
			}
		}
		Object[] object = temp.toArray();
		String[] newStrs = new String[object.length];

		for (int i = 0; i < newStrs.length; i++) {
			newStrs[i] = object[i].toString();
		}

		return newStrs;
	}
	
	//数组拼接成字符串
	public static String arrayToString(String [] array,String split){
		String str="";
		for (int i = 0; i < array.length; i++) {
			if (i!=0) {
				str+=split;
			}
			str+=array[i];
		}
		return str;
	}
	//删除数组中的元素
	public static String[] delArrayValues(String[] array,String str) {
		List<String> temp = new ArrayList<String>();
		for (String i : array) {
			if (!i.equals(str)) {
				temp.add(i);
			}
		}
		Object[] object = temp.toArray();
		String[] newStrs = new String[object.length];

		for (int i = 0; i < newStrs.length; i++) {
			newStrs[i] = object[i].toString();
		}
		return newStrs;

	}
	 /**
     * 身份证号替换，保留前四位和后四位
     *
     * 如果身份证号为空 或者 null ,返回null ；否则，返回替换后的字符串；
     *
     * @param idCard 身份证号
     * @return
     */
    public static String idCardReplaceWithStar(String idCard) {

        if (idCard.isEmpty() || idCard == null) {
            return null;
        } else {
            return replaceAction(idCard, "(?<=\\d{6})\\d(?=\\d{4})");
        }
    }
    
    public static String phoneReplaceWithStar(String phone) {

        if ( phone == null||phone.isEmpty()) {
            return null;
        } else {
            return replaceAction(phone, "(?<=\\d{3})\\d(?=\\d{3})");
        }
    }
    /**
     * 实际替换动作
     *
     * @param username username
     * @param regular  正则
     * @return
     */
    private static String replaceAction(String username, String regular) {
        return username.replaceAll(regular, "*");
    }
}
