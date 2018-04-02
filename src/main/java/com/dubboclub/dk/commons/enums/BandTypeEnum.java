package com.dubboclub.dk.commons.enums;

/**
 * 绑定设备类型枚举
 * 
 * @author liMengbiao
 *
 */
public enum BandTypeEnum {
	PHONE(1, "手机"), BAND(2, "手环"), HEARTBAND(3, "心率手环");

	private Integer code;

	private String string;

	private BandTypeEnum(Integer code, String string) {
		this.code = code;
		this.string = string;
	}

	public static BandTypeEnum getEnum(Integer code) {
		for (BandTypeEnum messEnum : BandTypeEnum.values()) {
			if (messEnum.getCode().equals(code)) {
				return messEnum;
			}
		}
		return null;
	}

	public Integer getCode() {
		return code;
	}

	public String getString() {
		return string;
	}
}
