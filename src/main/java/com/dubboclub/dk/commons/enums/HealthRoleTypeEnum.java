package com.dubboclub.dk.commons.enums;

/**
 * 健康角色类型
 * 
 * @File: HealthRoleTypeEnum.java
 * @Date: 2015年3月31日
 * @Author: zengbin
 * @Copyright: 版权所有 (C) 2015 中国移动 杭州研发中心.
 *
 * @注意：本内容仅限于中国移动内部传阅，禁止外泄以及用于其他的商业目的
 */
public enum HealthRoleTypeEnum {
	CREATOR(1, "创建者"), NORMAL_USER(2, "普通用户"), OTHERS(3, "其他");

	private int code;

	private String msg;

	private HealthRoleTypeEnum(int code, String msg) {
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
