/*
*@Project: GZJK
*@Author: Administrator
*@Date: 2015年12月18日
*@Copyright: 2000-2015 CMCC . All rights reserved.
 */
package com.dubboclub.dk.commons.enums;

/** 
* @ClassName: ScoreEnum 
* @Description: 嗨豆积分枚举,此key与数据库表t_dim_score id相对应
* @author lvxc
* @date 2015年12月18日 下午4:02:27 
*/
public enum ScoreEnum {

    /**嗨豆获取大类型下(惩罚)的小类型**/
    SOURCE_HABIT_TIE_DELETE(1, "习惯心情贴被举报删除", "1,14,21"),
    SOURCE_MODEL_TIE_DELETE(14, "模块心情贴被举报删除", "1,14,21"),
    SOURCE_PLAN_TIE_DELETE(21, "贴吧心情贴被举报删除", "1,14,21"),

    /**嗨豆获取大类型下(日常任务)的小类型**/
    SOURCE_PLAN_SIGN(2, "调理计划打卡", "2"),
    SOURCE_HABIT_ADD(3, "习惯添加", "3"),
    SOURCE_HABIT_SIGN(4, "习惯打卡", "4"),
    SOURCE_SELF_SIGN(5, "个人签到", "5"),
    SOURCE_HABIT_TIE_ADD(6, "发表习惯心情贴", "6,15"),
    SOURCE_HABIT_TIE_REPLY(7, "回复习惯心情贴", "7,16"),
    SOURCE_APP_SHARE(8, "分享到第三方平台", "8"),

    SOURCE_MODEL_TIE_ADD(15, "发表模块心情贴", "6,15"),
    SOURCE_MODEL_TIE_REPLY(16, "回复模块心情贴", "7,16"),

    /**嗨豆获取大类型下(特殊奖励)的小类型**/
    SOURCE_PLAN_ADD(9, "添加调理计划", "9"),
    SOURCE_HABIT_TIE_HOT(10, "习惯心情贴被追加为热门", "10,17"),
    SOURCE_INVITE_CODE_SHARE(11, "分享邀请码（需被绑定）", "11"),
    SOURCE_INVITE_CODE_BIND(12, "绑定好友分享的邀请码", "12"),
    SOURCE_ACTIVITY(22, "刮刮乐活动嗨豆", "22"),
    SOURCE_INVITEREWORDS_ACTIVITY(23, "邀请有礼活动嗨豆", "23"),
    SOURCE_NEW_USER(24, "新用户注册", "24"),

    SOURCE_MODEL_TIE_HOT(17, "模块心情贴被追加为热门", "10,17"),

    /**嗨豆获取大类型下(商城)的小类型**/
    SOURCE_MALL_EXCHANGE(13, "商城兑换", "13"),

    ;

    private ScoreEnum(int key, String name, String value) {
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

    public static ScoreEnum getScoreEnum(int source) {

        for (ScoreEnum s : ScoreEnum.values()) {
            if (s.key == source) {
                return s;
            }
        }
        return null;
    }
}
