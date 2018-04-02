package com.dubboclub.dk.commons.util;

/**
 * 手机端recode值
 * @author sen
 *
 */
public enum RecodeEnumUtils {

    ONLY_ONE_TIME(-17, "每人仅限一次"),
    SELF_INVITE_CODE(-18, "不能使用自己的邀请码"),
    OLD_USER(-19, "把机会让给新用户吧~"),
    INVALID_INVITE_CODE(-20, "邀请码无效"),
    VERIFY_CODE_INVALID(7, "验证码失效"),
    UN_PASS_VERIFY_CODE(8, "未通过验证码验证"),
    SEND_CODE(9, "发送验证码"),
    SEND_CODE_FAILED(-9, "发送验证码失败"),
    NEED_UPDATE(-4, "版本太低，请更新最新版本"),
    UN_COMPLETE_HEALTH_TEST(3, "未完成健康自测"),
    UN_FILL_USERBASEINFO(2, "未填写性别身高体重"),
    SUCCESS(1, "操作成功"),
    EXCEPTION(0, "异常"),
    ERROR(-1, "系统错误"),
    SYSTEM_BUSY(-3, "系统繁忙，请稍后重试"),
    DATA_ERROR(-2, ""),
    NEED_RETEST_PHYSIQUE(-11, "未能判断出您的体质，请重新测试"),
    PROHIBITION_LOGIN(-89, "此用户已被禁止"),
    REGISTER_FAILED(-90, "注册失败"),
    NOT_MATCH_REGISTER_MOBILE(-95, "该手机号码与注册手机号码不一致"),
    THIRD_PARTY_ACCOUNT_UNBOUND(-94, "该应用未绑定嗨健康账号"),
    THIRD_PARTY_ACCOUNT_REGISTERED(-96, "该账号已被绑定"),
    SESSION_NOT_EXIST(-97, "session不存在或已过期"),
    MOBILE_REGISTERED(-98, "该手机号已注册"),
    NO_LOGIN(-99, "用户未登录"),
    ACCOUNT_OFFLINE(-93, "账号已下线，请重新登录"),
    NO_REGISTER(-100, "用户未注册"),
    USER_OR_PWD_ERROR(-101, "用户名或密码错误"),
    PARAM_NULL(-102, "传入的参数为空"),
    PARAM_ERROR(-103, "传入的参数错误"),
    INSERT_ERROR(-104, "数据库新增失败"),
    UPDATE_ERROR(-105, "数据库更新失败"),
    DELETE_ERROR(-106, "数据库删除失败"),
    RECORD_EXISITS(-107, "记录已存在"),
    NO_INFORMATION(-108, "信息不完整"),
    NO_RESULT(-109, "未找到符合条件的记录"),
    CODE_WRONG(-110, "验证码错误"),
    REQUEST_WRONG(-111, "请求不合法"),
    IMGCONTENTTYPE_ERROR(-200, "图片格式不正确"),
    IMG_TYPE_ERROR(-201, "图片分类错误"),
    UNKNOW_EXCEPTION(-400, "未知异常"),
    THIRD_EXCEPTION(-500, "第三方接口异常"),
    NO_MODULE_CODE_EXISITS(-550, "组件表中未配置该组件编号"),
    SESSION_ID_NULL(-999, "sessionId 为空"),
    MEMBER_ID_NULL(-998, "memberId 为空"),
    BAD_REQUEST(-10000, "请求错误"),
    NO_AUTHORITY(-10001, "没权限"),
    REFERER_ERROR(-10002, "referer被篡改"),
    OFFICIAL_PASSPORT(10086, "官方账号"),
    RECORD_WEIBO_FAIL(-112, "亲，俺们的小编正在拼命的制作精美的表情包，期待ing！"),
    RECORD_WEIBO_COMMENT_FAIL(-113, "亲，俺们的小编正在拼命的制作精美的表情包，期待ing！"),
    SIGN_FAIL(-114, "签名不通过"),
    DEVICE_NOT_EXISTS(-115, "该用户未注册对应的设备"),
    WORLD_COUNT_ERROR(-118, "亲，习惯简介的字数太多了，改个言简意赅的句子吧！"),

    VERIFY_TIMES_OUT(-23301, "验证码错误次数过多，请明日再试！"),

    ERROR_PWD_TIMES_OUT(-23302, "登录失败次数过多，请明日再试！"),

    COOKIE_NOT_EXIST(-4040, "cookie不存在"),

    NO_FIND_PWD(-23303, "密码错误次数过多，请明日再试！"),
    REPEAT_PAY(-30331, "用户重新支付！"),
    ORDERNOTEXISTS_PAY(-30332, "订单不存在"),
    NO_JOIN_PLAN(-88888, "用户未加入调理计划"),
    NO_BAND_REAL_STOCK_NUM(-99999, "全部腕带剩余库存为0"),
    ERROR_BAND_BINDED(-111111, "手环已被绑定"),
    BAND_DADA_UNBIND_ERROR(-111112, "手环未绑定"),
    BAND_DADA_UNUSED_ERROR(-111113, "绑定当天之前的数据，作废处理定"),
    BAND_DADA_FREEZED_ERROR(-111114, "已经上传过当天的日冻结数据，作废处理"),
    BAND_REMOVE_FAIL_ERROR(-111115, "解绑失败，请稍后重试"),
    UNAVAILABLE_PASSWROD(-1218, "密码不符合规则，必须至少为字母、数字、特殊字符中的两种，长度为8~20位"),
    WEIXIN_ERROR(6, "微信服务器异常，请稍后重试"),
    DB_CONNECT_EXCEPTION(-6001, "数据库连接异常"),
    DELETE_OBJECT_NULL(-6002, "删除对象信息不存在"),
    MODIFY_OBJECT_NULL(-6004, "修改对象信息不存在"),
    PART_SUCCESSFUL(-6005, "部分成功"),
    DATA_TRANSFER_EXCEPTION(-6006, "数据转换异常"),
    DEVICE_OFFLINE(120000, "设备不在线"),
    BAD_FRIENDSHIP(120001, "好友关系有误"),
    BAD_MUSIC(120002, "音乐有误"),
    NO_MATCH_DID(120003, "设备不存在"),
    USER_OWN_TOO_MANY_MDEV(120004, "一个账号最多可绑定5个音箱"),
    MDEV_OWN_TOO_MANY_USER(120005, "一个音箱最多被10个账号绑定"),
    MDEV_OWN_TOO_MANY_SDEV(120006, "一个音箱最多被4个从设备绑定"),
    MDEV_QUITE_TIME(120007, "音箱处于勿扰模式"),
    IN_QUERY_STATE(120008, "正在查询中..."),
    //家庭健康分组
	USER_BELONG_GROUP(20000, "用户已加入过家庭健康组"),
	USER_UNBELONG_GROUP(20001, "用户未加入过家庭健康组"),
	USER_NOT_ADMIN(20002, "用户不是管理员"),
	CAN_NOT_INVITE_SELF(20003, "不能邀请自己"),
	CAN_NOT_REMOVE_SELF(20004, "不能邀请自己"),
	GROUP_UPPER_LIMIT(20005, "家庭健康组最多10人"),
	DEVICE_UNBELONG(20006, "设备不属于你，无权给他人绑定"),
	MUST_BE_INVITED(20007, "您必须先收到邀请才能同意"),
	API_KEY_IS_NULL(20008, "apiKey为空"),
	DATA_USER_MUST_SELF(20009, "数据修改人必须自己或者管理员"),
	MUST_FAMILY(20010, "不在一个家庭组"),
	;

    private int code;

    private String msg;

    private RecodeEnumUtils(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    /**
     * @return the code
     */
    public int getCode() {
        return code;
    }

    /**
     * @return the msg
     */
    public String getMsg() {
        return msg;
    }

}
