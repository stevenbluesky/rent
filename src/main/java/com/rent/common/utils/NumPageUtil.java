package com.rent.common.utils;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

public class NumPageUtil {
	private String name;
	private String url;
	private int total;
	private int currpage;

	private int pagesize = 5;
	private int pagecount;
	private int pagesplit = 7;
	private int bothnum = pagesplit / 2;
	private List list;
	private StringBuffer buff = new StringBuffer();

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public int getCurrpage() {
		return currpage;
	}

	public void setCurrpage(int currpage) {

		this.currpage = currpage;
	}

	public int getPagesize() {
		return pagesize;
	}

	public void setPagesize(int pagesize) {
		this.pagesize = pagesize;
	}

	public int getPagecount() {
		return pagecount;
	}

	public void setPagecount(int pagecount) {
		this.pagecount = pagecount;
	}

	public int getPagesplit() {
		return pagesplit;
	}

	public void setPagesplit(int pagesplit) {
		this.pagesplit = pagesplit;
	}

	public int getBothnum() {
		return bothnum;
	}

	public void setBothnum(int bothnum) {
		this.bothnum = bothnum;
	}

	public NumPageUtil() {

	}

	public NumPageUtil(String url, int total, int currpage) {
		this.url = url;
		this.total = total;
		this.currpage = currpage;
		this.pagecount = total % pagesize == 0 ? total / pagesize : total / pagesize + 1;
		this.checkCurrpage();

	}

	public NumPageUtil(String url, int total, int currpage, int pagesize) {
		this.url = url;
		this.total = total;
		this.currpage = currpage;
		this.pagesize = pagesize;
		if (total != 0) {
			this.pagecount = total % pagesize == 0 ? total / pagesize : total / pagesize + 1;
		} else {
			this.pagecount = 1;
		}

		this.checkCurrpage();

	}

	public NumPageUtil(String url, int total, Integer currpage, int size, List list, HttpSession session,
			HttpServletRequest request) {
		if (currpage==null) {
			currpage=1;
		}
		this.checkCurrpage();
		
		this.url = url;
		this.total = total;
		this.currpage = currpage;
		this.pagesize = size;
		if (total != 0) {
			this.pagecount = total % pagesize == 0 ? total / pagesize : total / pagesize + 1;
		} else {
			this.pagecount = 1;
		}

	

		this.setBothnum(3);
		String numpage = this.showNumPage();
		currpage = this.getCurrpage();

		session.setAttribute("currpage", currpage);
		request.setAttribute("numpage", numpage);
		this.setList(list);
	}

	/**
	 * 验证当前页的合法性
	 */
	private void checkCurrpage() {
		
		if (currpage <= 1)
			currpage = 1;
		if (currpage >= pagecount)
			currpage = pagecount;
	}

	/**
	 * 显示单独的某一个文本
	 * 
	 */
	private String showOneTextPage(int page, String str) {
		if (url.lastIndexOf(".") >= 0 && url.substring(url.lastIndexOf("."), url.length()).equals(".do")) {
			return "<li class='text'><a href='/rent/" + url + "?currpage=" + page + "' class='text'>" + str
					+ "</a></li>";
		} else {
			return "<li class='text'><a href='/rent/" + url + "&currpage=" + page + "' class='text'>" + str
					+ "</a></li>";
		}
	}

	/**
	 * 不能在按的按钮
	 * 
	 * @param str
	 * @return
	 */
	private String showOneDisabledPage(String str) {
		return "<li class='disabled'>" + str + "</li>";
	}

	/**
	 * 显示首页
	 */

	private String showIndex() {
		return this.showOneTextPage(1, "首页");
	}

	/**
	 * 显示尾页
	 */
	private String showLast() {
		return this.showOneTextPage(pagecount, "尾页");

	}

	/**
	 * 显示文本的上一页
	 */
	private String showTextPrev() {
		if (currpage == 1) {
			return this.showOneDisabledPage("");
		}
		return this.showOneTextPage(currpage - 1, "<<上一页");
	}

	/**
	 * 显示数字的上一页
	 */
	private String showNumPrev() {

		if (currpage == 1) {
			return this.showOneDisabledPage("PREV<<");
		}
		return this.showOneTextPage(currpage - 1, "PREV<<");

	}

	/**
	 * 显示文本的下一页
	 */

	private String showTextNext() {
		if (currpage == pagecount) {
			return this.showOneDisabledPage("");
		}

		return this.showOneTextPage(currpage + 1, "下一页>>");

	}

	/**
	 * 显示数字的下一页
	 */

	private String showNumNext() {
		if (currpage == pagecount) {
			return this.showOneDisabledPage("");
		}

		return this.showOneTextPage(currpage + 1, ">>NEXT");
	}

	/**
	 * 循环出所有的数字
	 * 
	 * @param start
	 * @param end
	 * @return
	 */
	private List<Integer> getNumList(int start, int end) {
		List<Integer> numList = new ArrayList<Integer>();

		for (int i = start; i <= end; i++) {
			numList.add(i);
		}

		return numList;
	}

	/**
	 * 验证判断 获取一个数字的集合
	 * 
	 * @return
	 */
	private List<Integer> showNumList() {
		List<Integer> numList = new ArrayList<Integer>();
		if (pagecount > pagesplit) {
			if (currpage - bothnum >= 1 && currpage + bothnum <= pagecount) {
				numList = this.getNumList(currpage - bothnum, currpage + bothnum);

			}

			if (currpage + bothnum > pagecount) {
				numList = this.getNumList(pagecount - pagesplit + 1, pagecount);

			}
			if (currpage - bothnum < 1) {
				numList = this.getNumList(1, pagesplit);

			}

		} else if (pagecount <= pagesplit) {
			numList = this.getNumList(1, pagecount);

		}

		return numList;

	}

	/**
	 * 获取一个数字分页
	 * 
	 * @param page
	 * @return
	 */
	private String showOneNumPage(int page) {

		//当前页数变色
		String pageStr=page+"";
		// <li> 变颜色
		String style="";
		if (page==currpage) {
			pageStr="<span style='color:red;'>"+page+"</span>";
			style="style='border-top:2px solid red;font-weight:bold;'";
		}

		if (currpage == page) {
			if (url.lastIndexOf(".") >= 0 && url.substring(url.lastIndexOf("."), url.length()).equals(".do")) {
				return "<li class='number' ><a "+style+" class='select' href='/rent/" + url + "?currpage=" + page + "'>" + pageStr
						+ "</a></li>";
			} else {
				return "<li class='number'><a "+style+" class='select' href='/rent/" + url + "&currpage=" + page + "'>" + pageStr
						+ "</a></li>";
			}
			
		}
		if (url.lastIndexOf(".") >= 0 && url.substring(url.lastIndexOf("."), url.length()).equals(".do")) {
			return "<li class='number'><a "+style+" class='number' href='/rent/" + url + "?currpage=" + page + "'>" + pageStr
					+ "</a></li>";
		} else {
			return "<li class='number'><a "+style+" class='number' href='/rent/" + url + "&currpage=" + page + "'>" + pageStr
					+ "</a></li>";
		}
	}

	/**
	 * 获取数字分页组合
	 * 
	 * @param numList
	 * @return
	 */
	private String getNumString(List<Integer> numList) {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < numList.size(); i++) {
			sb.append(this.showOneNumPage(numList.get(i)));
		}
		return sb.toString();
	}

	/**
	 * 显示文本分页
	 */

	public String showTextPage() {
		buff.append("<ul ><br/>");
		buff.append(this.showIndex());
		buff.append(this.showTextPrev());
		buff.append(this.showTextNext());
		buff.append(this.showLast());
		buff.append("</ul><br/>");

		return buff.toString();

	}

	/**
	 * 跳转
	 * 
	 * @return
	 */
	public String textSkip() {

		return "<span class='skip'>跳到第 <input type='text' id='textpage' value='" + currpage + "' /> 页</span>"
				+ "<span class='go' id='textjs'>GO</span>" + "</li>";

	}

	/**
	 * 显示数字分页
	 * 
	 * @return
	 */
	public String showNumPage() {
		buff.append("<ul>");
		buff.append(this.showIndex());
		buff.append(this.showTextPrev());
		buff.append(this.getNumString(this.showNumList()));
		buff.append(this.showTextNext());
		buff.append(this.showLast());
		buff.append(this.textInfo());
		// buff.append(this.textSkip());

		buff.append("</ul>");
		return buff.toString();
	}

	public String showpages() {

		buff.append("<ul>");
		buff.append(this.showIndex());
		buff.append(this.getNumString(this.showNumList()));

		buff.append(this.showLast());
		buff.append(this.textInfo());
		buff.append("</ul>");
		return buff.toString();
	}

	public String textInfo() {

		return "<li  class='textjs'>" + "<span class='currpage'>" + currpage + "/" + pagecount + "</span>"
				+ "<span class='pagecount'> 共" + pagecount + "页</span>";

	}

	public List getList() {
		return list;
	}

	public void setList(List list) {
		this.list = list;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}