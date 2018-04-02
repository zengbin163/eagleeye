/*
 * @Project: GZJK
 * @Author: bin
 * @Date: 2015年4月21日
 * @Copyright: 2000-2015 CMCC . All rights reserved.
 */
package com.dubboclub.dk.commons.enums;

/**
 * @ClassName: DeviceTypeEnum
 * @Description: 设备类型
 * @author bin
 * @date 2015年4月21日 下午2:31:54
 */
public enum DeviceTypeEnum {

	WEB(1, "网页"), PC(2, "PC"), Android(3, "安卓"), IOS(4, "苹果"), WP(5, "WP");
	private Integer code;

	private String string;

	private DeviceTypeEnum(Integer code, String string) {
		this.code = code;
		this.string = string;
	}

	public static DeviceTypeEnum getEnum(Integer code) {
		for (DeviceTypeEnum messEnum : DeviceTypeEnum.values()) {
			if (messEnum.getCode().equals(code)) {
				return messEnum;
			}
		}
		return null;
	}

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public String getString() {
		return string;
	}

	public void setString(String string) {
		this.string = string;
	}
}
