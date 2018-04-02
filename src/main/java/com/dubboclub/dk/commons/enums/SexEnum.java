package com.dubboclub.dk.commons.enums;

/**
 *@Project: HJK
 *@Author: lizhibing
 *@Date: 2015-04-07
 *@Copyright: 2000-2015 CMCC . All rights reserved.
 */
public enum SexEnum {
    MAN(0, "男"),
    WOMAN(1, "女"),
    UNKOWN(2, "未知");
    private int code;

    private String string;

    private SexEnum(int code, String string) {
        this.code = code;
        this.string = string;
    }

    public static SexEnum getSexEnum(int code) {

        for (SexEnum sex : SexEnum.values()) {
            if (sex.getCode() == code) {
                return sex;
            }
        }
        return SexEnum.MAN;
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
    public String getString() {
        return string;
    }
}
