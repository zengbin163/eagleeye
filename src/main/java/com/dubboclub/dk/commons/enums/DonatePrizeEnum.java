/*
 * Project: akso-base
 * 
 * File Created at 2016年12月8日
 * 
 * Copyright 2016 CMCC Corporation Limited.
 * All rights reserved.
 *
 * This software is the confidential and proprietary information of
 * ZYHY Company. ("Confidential Information").  You shall not
 * disclose such Confidential Information and shall use it only in
 * accordance with the terms of the license.
 */
package com.dubboclub.dk.commons.enums;

/**
 * @Type TurnPrizeEnum.java
 * @Desc
 * @author Go
 * @date 2016年12月8日 下午4:06:47
 * @version
 */
public enum DonatePrizeEnum {
	BAND(3, "贯众心率手环1只"), FLOWPACK(20, "1G全国流量礼包");

	private Integer code;
	private String msg;
	private final static Integer PRIZE_NUM = 7;

	private DonatePrizeEnum(Integer code, String msg) {
		this.code = code;
		this.msg = msg;
	}

	public static DonatePrizeEnum getEnum(Integer code) {
		for (DonatePrizeEnum messEnum : DonatePrizeEnum.values()) {
			if (messEnum.getCode().equals(code)) {
				return messEnum;
			}
		}
		return null;
	}

	/**
	 * @return the code
	 */
	public Integer getCode() {
		return code;
	}

	/**
	 * @return the msg
	 */
	public String getMsg() {
		return msg;
	}

	public static Integer getPrizeNum() {
		return PRIZE_NUM;
	}

}

/**
 * Revision history
 * -------------------------------------------------------------------------
 * 
 * Date Author Note
 * -------------------------------------------------------------------------
 * 2016年12月8日 Go creat
 */
