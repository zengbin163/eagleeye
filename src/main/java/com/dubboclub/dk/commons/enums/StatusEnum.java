package com.dubboclub.dk.commons.enums;

/**
 * 记录状态
 * @File: StatusUtils.java
 * @Date: 2015年3月31日
 * @Author: wwei
 * @Copyright: 版权所有 (C) 2015 中国移动 杭州研发中心.
 *
 * @注意：本内容仅限于中国移动内部传阅，禁止外泄以及用于其他的商业目的
 */
public enum StatusEnum {
    INVALID(0, "无效"),
    VALID(1, "有效");

    private int code;

    private String msg;

    private StatusEnum(int code, String msg) {
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
