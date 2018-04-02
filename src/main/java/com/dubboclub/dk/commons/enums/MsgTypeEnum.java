package com.dubboclub.dk.commons.enums;

/**
 * 消息类型
 * 
 * @File: MsgTypeEnum.java
 * @Date: 2015年5月18日
 * @Author: zengbin
 * @Copyright: 版权所有 (C) 2015 中国移动 杭州研发中心.
 *
 * @注意：本内容仅限于中国移动内部传阅，禁止外泄以及用于其他的商业目的
 */
public enum MsgTypeEnum {
	OPERATE_TYPE(1, "操作类型"), NOTIFY_TYPE(2, "通知类型");

	private int code;

	private String msg;

	private MsgTypeEnum(int code, String msg) {
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
