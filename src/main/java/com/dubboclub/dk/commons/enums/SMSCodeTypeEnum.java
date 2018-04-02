/*
 *@Project: GZJK
 *@Author: sen
 *@Date: 2015年4月22日
 *@Copyright: 2000-2015 CMCC . All rights reserved.
 */
package com.dubboclub.dk.commons.enums;

/** 
* @ClassName: SMSCodeTypeEnum 
* @Description: sms短信验证码类别
* @author sen
* @date 2015年4月22日 上午11:16:09 
*/
public enum SMSCodeTypeEnum {

    REGISTER_CODE_TYPE(1, "注册验证码类型"),
    FIND_PWD_CODE_TYPE(2, "找回密码验证码类型"),
    MOD_MOBILE_CODE_TYPE(3, "修改手机号码类型");

    private Integer codeType;

    private String description;

    private SMSCodeTypeEnum(Integer codeType, String description) {
        this.codeType = codeType;
        this.description = description;
    }

    public static SMSCodeTypeEnum getSMSCodeType(Integer codeType) {
        for (SMSCodeTypeEnum codeTypeEnum : SMSCodeTypeEnum.values()) {
            if (codeTypeEnum.getCodeType().equals(codeType)) {
                return codeTypeEnum;
            }
        }
        return null;
    }

    public Integer getCodeType() {
        return codeType;
    }

    public String getDescription() {
        return description;
    }

}
