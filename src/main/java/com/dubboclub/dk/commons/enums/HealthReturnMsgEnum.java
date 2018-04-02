package com.dubboclub.dk.commons.enums;

/**
 * 角色类型
 * 
 * @Description: 1：普通管理员 2：超级管理员
 * @author yinhu
 * @date 2016年11月24日 下午15:32:35
 */
public enum HealthReturnMsgEnum {

	SUCCESS("1000000", "成功"), SYSTEM_ERR("5000001", "系统错误"), ROLE_FAIL("5200000", "权限校验失败"), PARAM_FAIL("5100000",
			"参数校验失败"), SESSIONID_FAIL("5101001", "session不存在");

	private String code;
	private String msg;

	private HealthReturnMsgEnum(String code, String msg) {
		this.code = code;
		this.msg = msg;
	}

	public static HealthReturnMsgEnum getEnum(String code) {
		for (HealthReturnMsgEnum messEnum : HealthReturnMsgEnum.values()) {
			if (messEnum.getCode().equals(code)) {
				return messEnum;
			}
		}
		return null;
	}

	public String getCode() {
		return code;
	}

	public String getMsg() {
		return msg;
	}

}
