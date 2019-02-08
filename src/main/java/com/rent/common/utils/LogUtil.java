package com.rent.common.utils;

import org.apache.log4j.Logger;


/**
 * 日志工具类
 * 
 * @author zzg
 * @version 1.0.0
 * @since 2017-02-06
 */	
public class LogUtil {
	/**
	 * 开始执行方法
	 */
	public <T> void startMethod(Logger logger) {
		StackTraceElement[] stacks = Thread.currentThread().getStackTrace();
		logger.info("\n开始执行方法：" + stacks[2].getClassName() + "------"
				+ stacks[2].getMethodName() + "(" + stacks[2].getLineNumber()
				+ ")");
	}

	/**
	 * 错误执行方法
	 * 
	 * @param e
	 *            异常对象
	 */
	public <T> void errorMethod(Exception e, Logger logger) {
		StackTraceElement[] stacks = Thread.currentThread().getStackTrace();
		logger.error("\n错误执行方法：" + stacks[2].getClassName() + "------"
				+ stacks[2].getMethodName() + "(" + stacks[2].getLineNumber()
				+ ")");
		logger.error("错误信息：" + e.getMessage());
	}

	/**
	 * 结束执行方法
	 */
	public <T> void endMethod(Logger logger) {
		StackTraceElement[] stacks = Thread.currentThread().getStackTrace();
		logger.info("\n结束执行方法：" + stacks[2].getClassName() + "------"
				+ stacks[2].getMethodName() + "(" + stacks[2].getLineNumber()
				+ ")");
	}
}
