package com.dubboclub.dk.commons.enums;

/**
 * 用户调理计划操作类型
 * @author JWY
 *
 */
public enum PlanOperateEnum {
    PLAN_JOIN(1, "加入调理计划"),
    PLAN_QUIT(2, "退出调理计划");

    private int code;

    private String msg;

    private PlanOperateEnum(int code, String msg) {
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
