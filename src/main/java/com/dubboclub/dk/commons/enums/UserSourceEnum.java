/*
 *@Project: GZJK
 *@Author: sen
 *@Date: 2015年5月6日
 *@Copyright: 2000-2015 CMCC . All rights reserved.
 */
package com.dubboclub.dk.commons.enums;

/** 
* @ClassName: LoginSourceEnum 
* @Description: 登录来源
* @author sen
* @date 2015年5月6日 上午9:59:55 
*/
public enum UserSourceEnum {
    LOCAL_ACCOUNT(0, "贯纵运动账号");

    private int source;

    private String description;

    private UserSourceEnum(int source, String description) {
        this.source = source;
        this.description = description;
    }

    public static UserSourceEnum getUserSource(int source) {

        for (UserSourceEnum s : UserSourceEnum.values()) {
            if (s.source == source) {
                return s;
            }
        }
        return null;
    }

    public int getSource() {
        return source;
    }

    public String getDescription() {
        return description;
    }

}
