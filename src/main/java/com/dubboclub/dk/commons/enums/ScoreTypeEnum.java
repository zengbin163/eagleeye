/*
*@Project: GZJK
*@Author: Administrator
*@Date: 2015年12月18日
*@Copyright: 2000-2015 CMCC . All rights reserved.
 */
package com.dubboclub.dk.commons.enums;

/** 
* @ClassName: ScoreTypeEnum 
* @Description: TODO
* @author lvxc
* @date 2015年12月18日 下午5:54:42 
*/
public enum ScoreTypeEnum {
    /**嗨豆获取大类型**/
    TYPE_PUNISHMENT(1, "惩罚", "1"),
    TYPE_DAILY(2, "日常任务", "2"),
    TYPE_SPECIAL(3, "特殊奖励", "3"),
    TYPE_MALL(4, "商城", "4"),;
    private ScoreTypeEnum(int key, String name, String value) {
        this.key = key;
        this.name = name;
        this.value = value;
    }

    //键
    private int key;
    //简称
    private String name;
    //映射值
    private String value;

    public int getKey() {
        return key;
    }

    public String getValue() {
        return value;
    }

    public String getName() {
        return name;
    }

    public static ScoreTypeEnum getScoreEnum(int source) {

        for (ScoreTypeEnum s : ScoreTypeEnum.values()) {
            if (s.key == source) {
                return s;
            }
        }
        return null;
    }
}
