/*
*@Project: GZJK
*@Author: yesh
*@Date: 2015年3月31日
*@Copyright: 2000-2015 CMCC . All rights reserved.
 */
package com.dubboclub.dk.commons.enums;

/** 
* @ClassName: ModelUtils 
* @Description: 模块管理类
* @author yesh
* @date 2015年3月31日 上午9:47:52 
*/
public enum ModelEnumUtils {

    TEST_BY_SELF_MODEL(1, "自测工具"),
    HEALTH_TEST_SELF_MODEL(2, "健康自查"),
    HEALTH_NEWS_MODEL(3, "健康资讯"),
    MEDICINE_HELPER_MODEL(4, "用药助手"),
    CALORIE_SEARCH_MODEL(5, "卡路里查询"),
    ILLNESS_KNOWLEDGE_MODEL(6, "疾病知识"),
    HABIT_MODEL(7, "习惯"),
    SUDOKU_MODEL(8, "数独"),

    ;

    private ModelEnumUtils(int code, String name) {
        this.code = code;
        this.name = name;
    }

    /**
     * 模块编号
     */
    private int code;

    /**
     * 模块名称
     */
    private String name;

    public int getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

}
