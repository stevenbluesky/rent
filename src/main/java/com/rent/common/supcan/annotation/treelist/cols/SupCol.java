/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.rent.common.supcan.annotation.treelist.cols;

import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 硕正Col注解
 * @author WangZhen
 * @version 2013-11-12
 * @see 在get方法上添加注解，应用实例�?
 * 
 * 		@SupCol(text="归属公司", sort = 10)
 */
@Target({ ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface SupCol {

	///////////////////////////////////// 主要 //////////////////////////////////////
	
	/**
	 * 列名（默认当前字段名�?
	 */
	String name() default "";

	/**
	* 内容是否允许重复 true/false 
	*/
	String isUnique() default "";
	
	/**
	* 是否允许为空 true/false 
	*/
	String nullAble() default "";
	
	/**
	* 默认�? �?, 用于新插入行操作时的初始, 支持�? "=" �?头的表达式，例如 defaultValue="=now( )", 表示将日期型默认值设为当�? (�?)   
	* 		�?: 仅对新插入的行有�? 
	*/
	String defaultValue() default "";
	
	/**
	* 数据类型 �? string / int / double / bool / date / datetime 这几�? 
	*/
	String dataType() default "";
	
	/**
	* 小数位数 -1�?8, 仅用�? double 型�?? -1表示小数位数不确�?, 可以�?0位和8位之间可以任意输�?, 默认�? 2 
	*/
	String decimal() default "";
	
	/**
	* 是否超链接列 true/false false 
	*/
	String isHyperlink() default "";
	
	/**
	* 是否隐藏, true - 隐藏() default "";
	* 	false - 显示() default "";
	* 	absHide �? absTrue - 绝对隐藏，不会被鼠标右键菜单选择() default "";
	* 	absShow �? absFalse - 绝对显示，不会被鼠标右键菜单选择() default ""; 
	*/
	String isHide() default "";
	
	/**
	* 点击列标题是否执行排�? true/false ,默认：true   
	* 	�?: 如果<Properties>中的sortAble设为false, 则本sortAble无效(false) 
	*/
	String sortAble() default "";
	
	/**
	* 是否允许列的拖动操作 ,默认：true   
	* 	�?: 如果<Properties>中的moveAble设为false, 则本moveAble无效(false) 
	*/
	String moveAble() default "";
	
	/**
	* 是否允许被粘�? ,默认：supcan.xml
	* 	true - 允许() default "";
	* 	false - 不允�?() default "";
	* 	supcan.xml - 通常是允许，但是当列不可编辑�?(包括隐藏�?)，将以supcan.xml中的 <pasteAbleWhenUnEditAble> 的设定为�?() default "";
	*/
	String pasteAble() default "";
	
	/**
	* 指向另一列的列名，显示的内容存放在该列中，是另类 key-value�? 的简易字典解决方�?
	*/
	String textId() default "";
	
	///////////////////////////////////// 外观 //////////////////////////////////////
	
	/**
	* 是否以千位符分隔显示 true/false 默认：true   
	* 	�?: 仅用�? datatype �? int �? double �? 
	*/
	String isThousandSeparat() default "";
	
	/**
	* 列宽 整数 - 绝对宽度 (单位:像素�?)() default "";
	* 	百分�? - 窗口宽度的百分比, 如：20%() default "";
	* 	为小�?1的分�? - 比例因子，用于分配剩余的宽度, �?0.2() default "";
	* 	fitHeader �? header - 自动伸展到能使表头标题能够完整显�?() default ""; 
	*/
	String width() default "";
	
	/**
	* 列的�?小宽�? 像素�? 10 
	*/
	String minWidth() default "";
	
	/**
	* 水平对齐 left / center / right 
	*/
	String align() default "";
	
	/**
	* 垂直对齐 top / vcenter(或middle) / bottom vcenter 
	*/
	String vAlign() default "";
	
	/**
	* 列标题文字的对齐 left / center / right center 
	*/
	String alignHeader() default "";
	
	/**
	* 采用的字�? 数字，指向在<Fonts>中定义的字体的顺序号, �?0�?始计�?, 等级高于<Properties>中的同名属�?? -1 
	*/
	String fontIndex() default "";
	
	/**
	* 列标题采用的字体 数字，指向在<Fonts>中定义的字体的顺序号, �?0�?始计�?, 等级高于<Properties>中的同名属�?? -1 
	*/
	String headerFontIndex() default "";
	
	/**
	* 列标题文字颜�? 颜色�? #000000 
	*/
	String headerTextColor() default "";
	
	/**
	* 列标题旁边的小图�? 可以是图标的URL, 也可以是如下格式的串: 
	*	�?�?url=[?]() default "";pos=[?]
	*	pos用于指定图标的位�?, 是水�?(left/right)�? 垂直(top/middle/bottom)方向的组�?. 举例如下:
	*	�?�?url=../ac.png() default "";pos=right,bottom
	*	建议采用png或ico这类透明的图�? 
	*/
	String headerIcon() default "";
	
	/**
	* 鼠标点击上述小图标时弹出的提示文�? 文字�?, 如果不定义这个串，鼠标点击小图标时将触发Clicked事件 
	*/
	String headerIconTip() default "";
	
	/**
	* 用于显示的格式掩码表达式 表达式的内容包括显示内容�? 背景色�?? 文字色�?? 左图�? 右图. 请详见Treelist帮助文档�?"3.几个重要的属�?" 
	* 	formatDate(data,'YYYY-MM-DD')
	* 	=if(data=='1','关闭',if(data=='2','已完�?',if(data=='3','未接�?','进行�?')))
	*/
	String displayMask() default "";
	
	/**
	* 位于多层表头的层位置 数字，从0�?�? 
	*/
	String atLayer() default "";
	
	/**
	* 位于多层表头的层位置 数字，从0�?�? 
	*/
	String extentRows() default "";
	
	/**
	* 下拉�?(droplis, droptreelist)单元格的文字显示方式 0 - 仅显示文字部�?() default "";
	* 	1 - 仅显示键�?(即key)部分() default "";
	* 	2 - 键�??+" - "+文字() default ""; 
	*/
	String dropDisplayType() default "";
	
	/**
	* 虚拟列的数据分隔�? 串，比如"/", 如果设了这个串，在加载数据后，程序将按这个分隔符自动对各行数据进行处理，使其看上去呈现多列的效果。此�?, 各个段中纵向、横向如果有连续相同的数据，将自动呈现纵、横的合并效�? (�?)  
	* 	�?1: 仅改变显示效果，并不改变数据() default "";
	* 	�?2: VColSep列有诸多限制，例如只能用于string型�?�不能作为树的排序列等等() default "";
	*/
	String VColSep() default "";
	
	/**
	* VColSepStyle 虚拟列的合并方式 row - 只允许跨行的垂直合并() default "";
	* col - 只允许跨列的横向合并() default "";
	* row,col - 跨行、跨列自动合�?() default ""; 
	*/
	String VColSepStyle() default "";
	
	/**
	* 合计表达�?
	*/
	String totalExpress() default "";
	
	/**
	* 小计表达�?
	*/
	String subTotalExpress() default "";
	
	/**
	 * 列的显示名称
	 */
	String text() default "";
	
	/**
	 * 归属组ID
	 */
	String groupId() default "";

	/**
	 * 排序（升序）
	 */
	int sort() default 0;
}
