/*
 * @Project: GZJK
 * @Author: bin
 * @Date: 2015年4月21日
 * @Copyright: 2000-2015 CMCC . All rights reserved.
 */
package com.dubboclub.dk.commons.enums;

/**
 * @ClassName: BanDeviceTypeEnum
 * @Description: 计步设备类型
 * @author zengbin
 * @date 2015年4月21日 下午2:31:54
 */
public enum BanDeviceTypeEnum {

	PHONE(1, "手机"), BAND(2, "手环"), HEARTBAND(3, "心率手环");
	private Integer code;

	private String string;

	private BanDeviceTypeEnum(Integer code, String string) {
		this.code = code;
		this.string = string;
	}

	public static BanDeviceTypeEnum getEnum(Integer code) {
		for (BanDeviceTypeEnum messEnum : BanDeviceTypeEnum.values()) {
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
