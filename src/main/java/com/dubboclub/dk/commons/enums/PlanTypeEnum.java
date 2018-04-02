package com.dubboclub.dk.commons.enums;

/**
 * 调理计划方案类型
 * @author JWY
 *
 */
public enum PlanTypeEnum {
    OLD_PLAN(1, "老方案"),
    NEW_PLAN(2, "新方案");

    private int code;

    private String msg;

    private PlanTypeEnum(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

}
