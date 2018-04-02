/*
*@Project: GZJK
*@Author: Administrator
*@Date: 2015年12月18日
*@Copyright: 2000-2015 CMCC . All rights reserved.
 */
package com.dubboclub.dk.commons.enums;

/** 
* @ClassName: ScoreRecordTypeEnum 
* @Description: TODO
* @author lvxc
* @date 2015年12月18日 下午5:56:30 
*/
public enum ScoreRecordTypeEnum {
    /**出入账类型**/
    RECORD_TYPE_OUT(0, "出账", "0"),
    RECORD_TYPE_IN(1, "入账", "1"),;

    private ScoreRecordTypeEnum(int key, String name, String value) {
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

    public static ScoreRecordTypeEnum getScoreEnum(int source) {

        for (ScoreRecordTypeEnum s : ScoreRecordTypeEnum.values()) {
            if (s.key == source) {
                return s;
            }
        }
        return null;
    }
}
