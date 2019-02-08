/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.rent.common.supcan.treelist.cols;

import com.rent.common.supcan.annotation.treelist.cols.SupCol;
import com.rent.common.utils.ObjectUtils;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;
import com.thoughtworks.xstream.annotations.XStreamConverter;
import com.thoughtworks.xstream.annotations.XStreamOmitField;
import com.thoughtworks.xstream.converters.extended.ToAttributedValueConverter;

/**
 * 硕正TreeList Cols Col
 * @author WangZhen
 * @version 2013-11-04
 */
@XStreamAlias("Col")
@XStreamConverter(value = ToAttributedValueConverter.class, strings = {"text"})
public class Col {

	///////////////////////////////////// 主要 //////////////////////////////////////
	/**
	 * 列名 �?, 相当于字段名 
	 */
	@XStreamAsAttribute
	private String name;

	/**
	 * 内容是否允许重复 true/false 
	 */
	@XStreamAsAttribute
	private String isUnique = "false";
	
	/**
	 * 是否允许为空 true/false 
	 */
	@XStreamAsAttribute
	private String nullAble = "true";

	/**
	 * 默认�? �?, 用于新插入行操作时的初始, 支持�? "=" �?头的表达式，例如 defaultValue="=now( )", 表示将日期型默认值设为当�? (�?)   
	 * 		�?: 仅对新插入的行有�? 
	 */
	@XStreamAsAttribute
	private String defaultValue;

	/**
	 * 数据类型 �? string / int / double / bool / date / datetime 这几�? 
	 */
	@XStreamAsAttribute
	private String dataType;
	
	/**
	 * 小数位数 -1�?8, 仅用�? double 型�?? -1表示小数位数不确�?, 可以�?0位和8位之间可以任意输�?, 默认�? 2 
	 */
	@XStreamAsAttribute
	private String decimal;
	
	/**
	 * 是否超链接列 true/false false 
	 */
	@XStreamAsAttribute
	private String isHyperlink;

	/**
	 * 是否隐藏, true - 隐藏;
	 * 	false - 显示;
	 * 	absHide �? absTrue - 绝对隐藏，不会被鼠标右键菜单选择;
	 * 	absShow �? absFalse - 绝对显示，不会被鼠标右键菜单选择; 
	 */
	@XStreamAsAttribute
	private String isHide;
	
	/**
	 * 点击列标题是否执行排�? true/false ,默认：true   
	 * 	�?: 如果<Properties>中的sortAble设为false, 则本sortAble无效(false) 
	 */
	@XStreamAsAttribute
	private String sortAble;
	
	/**
	 * 是否允许列的拖动操作 ,默认：true   
	 * 	�?: 如果<Properties>中的moveAble设为false, 则本moveAble无效(false) 
	 */
	@XStreamAsAttribute
	private String moveAble;
	
	/**
	 * 是否允许被粘�? ,默认：supcan.xml
	 * 	true - 允许;
	 * 	false - 不允�?;
	 * 	supcan.xml - 通常是允许，但是当列不可编辑�?(包括隐藏�?)，将以supcan.xml中的 <pasteAbleWhenUnEditAble> 的设定为�?;
	 */
	@XStreamAsAttribute
	private String pasteAble;

	/**
	 * 指向另一列的列名，显示的内容存放在该列中，是另类 key-value�? 的简易字典解决方�?
	 */
	@XStreamAsAttribute
	private String textId;
	
	///////////////////////////////////// 外观 //////////////////////////////////////
	
	/**
	 * 是否以千位符分隔显示 true/false 默认：true   
	 * 	�?: 仅用�? datatype �? int �? double �? 
	 */
	@XStreamAsAttribute
	private String isThousandSeparat;
	
	/**
	 * 列宽 整数 - 绝对宽度 (单位:像素�?);
	 * 	百分�? - 窗口宽度的百分比, 如：20%;
	 * 	为小�?1的分�? - 比例因子，用于分配剩余的宽度, �?0.2;
	 * 	fitHeader �? header - 自动伸展到能使表头标题能够完整显�?; 
	 */
	@XStreamAsAttribute
	private String width;
	
	/**
	 * 列的�?小宽�? 像素�? 10 
	 */
	@XStreamAsAttribute
	private String minWidth;
	
	/**
	 * 水平对齐 left / center / right 
	 */
	@XStreamAsAttribute
	private String align;
	
	/**
	 * 垂直对齐 top / vcenter(或middle) / bottom vcenter 
	 */
	@XStreamAsAttribute
	private String vAlign;
	
	/**
	 * 列标题文字的对齐 left / center / right center 
	 */
	@XStreamAsAttribute
	private String alignHeader;
	
	/**
	 * 采用的字�? 数字，指向在<Fonts>中定义的字体的顺序号, �?0�?始计�?, 等级高于<Properties>中的同名属�?? -1 
	 */
	@XStreamAsAttribute
	private String fontIndex;
	
	/**
	 * 列标题采用的字体 数字，指向在<Fonts>中定义的字体的顺序号, �?0�?始计�?, 等级高于<Properties>中的同名属�?? -1 
	 */
	@XStreamAsAttribute
	private String headerFontIndex;
	
	/**
	 * 列标题文字颜�? 颜色�? #000000 
	 */
	@XStreamAsAttribute
	private String headerTextColor;
	
	/**
	 * 列标题旁边的小图�? 可以是图标的URL, 也可以是如下格式的串: 
	 *	�?�?url=[?];pos=[?]
	 *	pos用于指定图标的位�?, 是水�?(left/right)�? 垂直(top/middle/bottom)方向的组�?. 举例如下:
	 *	�?�?url=../ac.png;pos=right,bottom
	 *	建议采用png或ico这类透明的图�? 
	 */
	@XStreamAsAttribute
	private String headerIcon;
	
	/**
	 * 鼠标点击上述小图标时弹出的提示文�? 文字�?, 如果不定义这个串，鼠标点击小图标时将触发Clicked事件 
	 */
	@XStreamAsAttribute
	private String headerIconTip;

	/**
	 * 用于显示的格式掩码表达式 表达式的内容包括显示内容�? 背景色�?? 文字色�?? 左图�? 右图. 请详见Treelist帮助文档�?"3.几个重要的属�?" 
	 * 	formatDate(data,'YYYY-MM-DD')
	 * 	=if(data=='1','关闭',if(data=='2','已完�?',if(data=='3','未接�?','进行�?')))
	 */
	@XStreamAsAttribute
	private String displayMask;
	
	/**
	 * 位于多层表头的层位置 数字，从0�?�? 
	 */
	@XStreamAsAttribute
	private String atLayer;
	
	/**
	 * 位于多层表头的层位置 数字，从0�?�? 
	 */
	@XStreamAsAttribute
	private String extentRows;
	
	/**
	 * 下拉�?(droplis, droptreelist)单元格的文字显示方式 0 - 仅显示文字部�?;
	 * 	1 - 仅显示键�?(即key)部分;
	 * 	2 - 键�??+" - "+文字; 
	 */
	@XStreamAsAttribute
	private String dropDisplayType;
	
	/**
	 * 虚拟列的数据分隔�? 串，比如"/", 如果设了这个串，在加载数据后，程序将按这个分隔符自动对各行数据进行处理，使其看上去呈现多列的效果。此�?, 各个段中纵向、横向如果有连续相同的数据，将自动呈现纵、横的合并效�? (�?)  
	 * 	�?1: 仅改变显示效果，并不改变数据;
	 * 	�?2: VColSep列有诸多限制，例如只能用于string型�?�不能作为树的排序列等等;
	 */
	@XStreamAsAttribute
	private String VColSep;
	
	/**
	 * VColSepStyle 虚拟列的合并方式 row - 只允许跨行的垂直合并;
	 * col - 只允许跨列的横向合并;
	 * row,col - 跨行、跨列自动合�?; 
	 */
	@XStreamAsAttribute
	private String VColSepStyle;
	
	/**
	 * 合计表达�?
	 */
	@XStreamAsAttribute
	private String totalExpress;

	/**
	 * 小计表达�?
	 */
	@XStreamAsAttribute
	private String subTotalExpress;
	
	/**
	 * 列的显示名称
	 */
	private String text;
	
	/**
	 * 归属组ID
	 */
	@XStreamOmitField
	private String groupId;
	
	/**
	 * 字段排序，注解定义时有效
	 */
	@XStreamOmitField
	private int sort;
	
	public Col() {
		
	}

	public Col(String name) {
		this.name = name;
	}
	
	public Col(String name, String text) {
		this(name);
		this.text = text;
	}
	
	public Col(SupCol supCol){
		ObjectUtils.annotationToObject(supCol, this);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIsUnique() {
		return isUnique;
	}

	public void setIsUnique(String isUnique) {
		this.isUnique = isUnique;
	}

	public String getNullAble() {
		return nullAble;
	}

	public void setNullAble(String nullAble) {
		this.nullAble = nullAble;
	}

	public String getDefaultValue() {
		return defaultValue;
	}

	public void setDefaultValue(String defaultValue) {
		this.defaultValue = defaultValue;
	}

	public String getDataType() {
		return dataType;
	}

	public void setDataType(String dataType) {
		this.dataType = dataType;
	}

	public String getDecimal() {
		return decimal;
	}

	public void setDecimal(String decimal) {
		this.decimal = decimal;
	}

	public String getIsHyperlink() {
		return isHyperlink;
	}

	public void setIsHyperlink(String isHyperlink) {
		this.isHyperlink = isHyperlink;
	}

	public String getIsHide() {
		return isHide;
	}

	public void setIsHide(String isHide) {
		this.isHide = isHide;
	}

	public String getSortAble() {
		return sortAble;
	}

	public void setSortAble(String sortAble) {
		this.sortAble = sortAble;
	}

	public String getMoveAble() {
		return moveAble;
	}

	public void setMoveAble(String moveAble) {
		this.moveAble = moveAble;
	}

	public String getPasteAble() {
		return pasteAble;
	}

	public void setPasteAble(String pasteAble) {
		this.pasteAble = pasteAble;
	}

	public String getTextId() {
		return textId;
	}

	public void setTextId(String textId) {
		this.textId = textId;
	}

	public String getIsThousandSeparat() {
		return isThousandSeparat;
	}

	public void setIsThousandSeparat(String isThousandSeparat) {
		this.isThousandSeparat = isThousandSeparat;
	}

	public String getWidth() {
		return width;
	}

	public void setWidth(String width) {
		this.width = width;
	}

	public String getMinWidth() {
		return minWidth;
	}

	public void setMinWidth(String minWidth) {
		this.minWidth = minWidth;
	}

	public String getAlign() {
		return align;
	}

	public void setAlign(String align) {
		this.align = align;
	}

	public String getvAlign() {
		return vAlign;
	}

	public void setvAlign(String vAlign) {
		this.vAlign = vAlign;
	}

	public String getAlignHeader() {
		return alignHeader;
	}

	public void setAlignHeader(String alignHeader) {
		this.alignHeader = alignHeader;
	}

	public String getFontIndex() {
		return fontIndex;
	}

	public void setFontIndex(String fontIndex) {
		this.fontIndex = fontIndex;
	}

	public String getHeaderFontIndex() {
		return headerFontIndex;
	}

	public void setHeaderFontIndex(String headerFontIndex) {
		this.headerFontIndex = headerFontIndex;
	}

	public String getHeaderTextColor() {
		return headerTextColor;
	}

	public void setHeaderTextColor(String headerTextColor) {
		this.headerTextColor = headerTextColor;
	}

	public String getHeaderIcon() {
		return headerIcon;
	}

	public void setHeaderIcon(String headerIcon) {
		this.headerIcon = headerIcon;
	}

	public String getHeaderIconTip() {
		return headerIconTip;
	}

	public void setHeaderIconTip(String headerIconTip) {
		this.headerIconTip = headerIconTip;
	}

	public String getDisplayMask() {
		return displayMask;
	}

	public void setDisplayMask(String displayMask) {
		this.displayMask = displayMask;
	}

	public String getAtLayer() {
		return atLayer;
	}

	public void setAtLayer(String atLayer) {
		this.atLayer = atLayer;
	}

	public String getExtentRows() {
		return extentRows;
	}

	public void setExtentRows(String extentRows) {
		this.extentRows = extentRows;
	}

	public String getDropDisplayType() {
		return dropDisplayType;
	}

	public void setDropDisplayType(String dropDisplayType) {
		this.dropDisplayType = dropDisplayType;
	}

	public String getVColSep() {
		return VColSep;
	}

	public void setVColSep(String vColSep) {
		VColSep = vColSep;
	}

	public String getVColSepStyle() {
		return VColSepStyle;
	}

	public void setVColSepStyle(String vColSepStyle) {
		VColSepStyle = vColSepStyle;
	}

	public String getTotalExpress() {
		return totalExpress;
	}

	public void setTotalExpress(String totalExpress) {
		this.totalExpress = totalExpress;
	}

	public String getSubTotalExpress() {
		return subTotalExpress;
	}

	public void setSubTotalExpress(String subTotalExpress) {
		this.subTotalExpress = subTotalExpress;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public int getSort() {
		return sort;
	}

	public void setSort(int sort) {
		this.sort = sort;
	}

	public String getGroupId() {
		return groupId;
	}

	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}
	
}
