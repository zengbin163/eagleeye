package com.dubboclub.dk.commons.enums;

/**
 * 角色类型
 * 
 * @Description: 1：普通管理员 2：超级管理员
 * @author yinhu
 * @date 2016年11月24日 下午15:32:35
 */
public enum HealthTypeEnum {

	BLOOD_PRESSURE(1, "mmHg"), BLOOD_OXYGEN(2, ""), WEIGHT(3, "kg"), STEP(4, "步");

	private Integer code;
	private String unit;

	private HealthTypeEnum(Integer code, String unit) {
		this.code = code;
		this.unit = unit;
	}

	public static HealthTypeEnum getEnum(Integer code) {
		for (HealthTypeEnum messEnum : HealthTypeEnum.values()) {
			if (messEnum.getCode().equals(code)) {
				return messEnum;
			}
		}
		return null;
	}

	public Integer getCode() {
		return code;
	}

	public String getUnit() {
		return unit;
	}

}
