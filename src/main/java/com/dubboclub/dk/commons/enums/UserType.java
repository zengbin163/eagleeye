package com.dubboclub.dk.commons.enums;

/**
 * 角色类型
 * @Description: 1：普通管理员  2：超级管理员
 * @author yinhu
 * @date 2016年11月24日 下午15:32:35
 */
public enum UserType {

    SUPERMANAGER(1, "超级管理员"),
    GENERALMANAGER(2, "普通管理员");

    private Integer code;
    private String msg;

    private UserType(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

}
