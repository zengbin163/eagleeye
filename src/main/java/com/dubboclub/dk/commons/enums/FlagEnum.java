package com.dubboclub.dk.commons.enums;

/**
 * 数据状态
 * 
 * @File: FlagEnum.java
 * @Date: 2015年5月18日
 * @Author: wwei
 * @Copyright: 版权所有 (C) 2015 中国移动 杭州研发中心.
 *
 * @注意：本内容仅限于中国移动内部传阅，禁止外泄以及用于其他的商业目的
 */
public enum FlagEnum {
	UNREAD(0, "未被读取"), READED(1, "已被读取"), DELETED(2, "逻辑删除");

	private int code;

	private String msg;

	private FlagEnum(int code, String msg) {
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
