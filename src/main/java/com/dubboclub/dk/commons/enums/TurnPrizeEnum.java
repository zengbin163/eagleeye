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
public enum TurnPrizeEnum {
    COUPON100(1, "优惠券100元"),
    COUPON90(2, "优惠券90元"),
    COUPON70(3, "优惠券70元"),
    GIFTPACKS(4, "新春礼包（春联1幅，台历1个）"),
    SCARF(5, "头巾"),
    BAND(6, "贯众运动心率手环+2条彩色腕带"),
    THANKS(7, "谢谢参与");

    private Integer code;
    private String msg;
    private final static Integer PRIZE_NUM = 7;

    private TurnPrizeEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public static TurnPrizeEnum getEnum(Integer code) {
        for (TurnPrizeEnum messEnum : TurnPrizeEnum.values()) {
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
    
    public static Integer getDefaultPrizeCode() {
        return THANKS.getCode();
    }
    
    public static String getDefaultPrizeName() {
        return THANKS.getMsg();
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
