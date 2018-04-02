package com.dubboclub.dk.commons.enums;

/**
 * 角色类型
 * 
 * @Description: 1：普通管理员 2：超级管理员
 * @author yinhu
 * @date 2016年11月24日 下午15:32:35
 */
public enum BloodPressureEnum {

	LEVEL1(1, "偏低"), NORMAL(2, "正常"), LEVE13(3, "轻度"), LEVE14(4, "中度"), LEVE15(5, "重度");

	private Integer code;
	private String msg;

	private BloodPressureEnum(Integer code, String msg) {
		this.code = code;
		this.msg = msg;
	}

	public Integer getCode() {
		return code;
	}

	public String getMsg() {
		return msg;
	}

}
