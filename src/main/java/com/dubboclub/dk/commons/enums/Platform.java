/*
 *@Project: GZJK
 *@Author: bin
 *@Date: 2015年4月20日
 *@Copyright: 2000-2015 CMCC . All rights reserved.
 */
package com.dubboclub.dk.commons.enums;

/**
 * @ClassName: Platform
 * @Description: 平台枚举
 * @author zengbin
 * @date 2016年11月09日 下午7:11:37
 */
public enum Platform {
    KOMECT_APP("KOMECT_APP", "贯众运动手机app"),

    KOMECT_CMS("KOMECT_CMS", "贯众运动cms"),

    KOMECT_WEB("KOMECT_WEB", "贯众运动web");

    private String platName;

    private String platDesc;

    private Platform(String platName, String platDesc) {
        this.platName = platName;
        this.platDesc = platDesc;
    }

    public static Platform getPlatform(String platName) {
        for (Platform platform : Platform.values()) {
            if (platform.getPlatName().equals(platName)) {
                return platform;
            }
        }
        return null;
    }

    public String getPlatName() {
        return platName;
    }

    public void setPlatName(String platName) {
        this.platName = platName;
    }

    public String getPlatDesc() {
        return platDesc;
    }

    public void setPlatDesc(String platDesc) {
        this.platDesc = platDesc;
    }
}
