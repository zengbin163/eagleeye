/*
 *@Project: GZJK
 *@Author: sen
 *@Date: 2015年6月1日
 *@Copyright: 2000-2015 CMCC . All rights reserved.
 */
package com.dubboclub.dk.commons.util;

import java.util.HashMap;
import java.util.Map;

import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

/** 
* @ClassName: Base64Util 
* @Description: TODO
* @author zengbin
* @date 2015年6月1日 上午9:50:04 
*/
public class ParamUtils {

    private static final String KEY_SEPARATOR = "&";

    private static final String VALUE_SEPARATOR = "=";
    
    /**
     * 和家亲设备类型
     */
	public static final Integer[] WEIGHT_DEVICES = { 10091, 20701, 20705, 20706 }; // 体重设备
	public static final Integer[] BLOOD_DEVICES = { 10092, 20702, 20703, 20704 }; // 血压血氧设备

    /**
     * 解密参数
     * @param behaviorInfo
     * @return
     */
    public static Map<String, Object> decodeParam(String data) {
        Map<String, Object> params = new HashMap<>();
        try {
            Assert.isTrue(!StringUtils.isEmpty(data), "参数不能为空");
            // DES解密
            String paramStr = DesUtils.decrypt(data);
            // 获取两个参数（1：明文des后的参数，2：明文md5后的参数）
            Map<String, Object> map = split(paramStr);
            String desParam = DesUtils.decrypt((String) map.get("param1"));
            String md5Param = (String) map.get("param2");
            // 判断参数是否被修改过
            Assert.isTrue(MD5Util.md5(desParam).equals(md5Param), "明密文参数不一致!");

            // 抽出实际明文参数
            params = split(desParam);
        } catch (Exception e) {
            throw new IllegalArgumentException(e);
        }

        return params;
    }

    private static Map<String, Object> split(String data) {
        Map<String, Object> params = new HashMap<>();
        String[] array = data.split(KEY_SEPARATOR);
        for (String str : array) {
            if (StringUtils.hasText(VALUE_SEPARATOR)) {
                String[] param = str.split(VALUE_SEPARATOR);
                if (2 == param.length) {
                    params.put(param[0], param[1]);
                }
            }
        }
        return params;
    }

    public static void main(String[] args) throws Exception {
        String params = "amount=3&productId=1&&modifyUser=wangwei";
        String desParams = "param1=" + DesUtils.encrypt(params);
        String md5Params = "param2=" + MD5Util.md5(params);
        String newParams = DesUtils.encrypt(desParams + KEY_SEPARATOR + md5Params);
        System.out.println(newParams);
        System.out.println(decodeParam(newParams));
    }
}
