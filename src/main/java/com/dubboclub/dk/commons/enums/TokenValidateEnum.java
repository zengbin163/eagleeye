/*
 * @Project: GZJK
 * @Author: bin
 * @Date: 2015年4月21日
 * @Copyright: 2000-2015 CMCC . All rights reserved.
 */
package com.dubboclub.dk.commons.enums;

/** 
* @ClassName: DeviceTypeEnum 
* @Description: 设备类型
* @author bin
* @date 2015年4月21日 下午2:31:54 
*/
public enum TokenValidateEnum {

    KOMECT(1, "贯众运动"),
    JKACCESS(2, "健康管理");
    private Integer code;

    private String string;

    private TokenValidateEnum(Integer code, String string) {
        this.code = code;
        this.string = string;
    }

    public static TokenValidateEnum getEnum(Integer code) {
        for (TokenValidateEnum messEnum : TokenValidateEnum.values()) {
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
