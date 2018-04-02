package com.dubboclub.dk.commons.enums;

/**
 * 用户登录活跃度来源
 * @Description: 0：嗨健康   1：用药助手  2：健康资讯  3：健康自查 4：疾病查询  5：饮食管家  6：调理方案  7：cms内容管理  8：支付   9：app SDK
 * @author Go
 * @date 2016年10月20日 下午3:32:35
 */
public enum UserLoginSourceEnum {
    HIIFIT(0, "嗨健康"),
    HEALTHCARE(1, "用药助手"),
    HEALTHNEWS(2, "健康资讯"),
    HEALTHCHECK(3, "健康自查"),
    DISEASESEARCH(4, "疾病查询"),
    FOODMANAGE(5, "饮食管理"),
    PAY(8, "支付");
    private Integer code;

    private String msg;

    private UserLoginSourceEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    /**
     * @return the code
     */
    public Integer getCode() {
        return code;
    }

    /**
     * @return the msg
     */
    public String getMsg() {
        return msg;
    }

}
