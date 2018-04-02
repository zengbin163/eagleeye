package com.dubboclub.dk.commons.enums;

/**
 * 月报类型
 * 
 * @File: MonthReportTypeEnum.java
 * @Date: 2015年5月18日
 * @Author: zengbin
 * @Copyright: 版权所有 (C) 2015 中国移动 杭州研发中心.
 *
 * @注意：本内容仅限于中国移动内部传阅，禁止外泄以及用于其他的商业目的
 */
public enum MonthReportTypeEnum {
	STEP(1, "计步"), 
	WEIGHT(2, "体重"),
	PRESSURE(3, "血压"),
	SUGAR(4, "血糖"),
	OXYGEN(5, "血氧"),
	;

	private int code;

	private String msg;

	private MonthReportTypeEnum(int code, String msg) {
		this.code = code;
		this.msg = msg;
	}

	/**
	 * @return the code
	 */
	public int getCode() {
		return code;
	}

	/**
	 * @return the msg
	 */
	public String getMsg() {
		return msg;
	}
}
