/*
 * Project: zjPedometer
 * 
 * File Created at 2016年4月15日
 * 
 * Copyright 2016 CMCC Corporation Limited.
 * All rights reserved.
 *
 * This software is the confidential and proprietary information of
 * ZYHY Company. ("Confidential Information").  You shall not
 * disclose such Confidential Information and shall use it only in
 * accordance with the terms of the license.
 */
package com.dubboclub.dk.commons.enums;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import net.sf.json.JSONObject;

// TODO Auto-generated Javadoc
/**
 * The Class JuheUtil.
 *
 * @author Alice
 * @version 
 * @Type JuheUtil.java
 * @Desc  利用绝活数据手机号码归属地API：https://www.juhe.cn/docs/api/id/11
 * @date 2016年4月15日 下午3:36:55
 */
public class JuheUtil {

    /** The Constant DEF_CHATSET. */
    public static final String DEF_CHATSET = "UTF-8";

    /** The Constant DEF_CONN_TIMEOUT. */
    public static final int DEF_CONN_TIMEOUT = 30000;

    /** The Constant DEF_READ_TIMEOUT. */
    public static final int DEF_READ_TIMEOUT = 30000;

    /** The user agent. */
    public static final String USER_AGENT = "Mozilla/5.0 (Windows NT 6.1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/29.0.1547.66 Safari/537.36";

    /** The Constant APPKEY. */
    //配置您申请的KEY
    public static final String APPKEY = "a1688ea6cc9c49905b034f6204b971fd";

    /**
     * Gets the request.
     *
     * @param memberPhone the member phone
     * @return the request
     */
    //1.手机归属地查询
    public static JSONObject getRequest(String memberPhone) {
        String result = null;
        JSONObject jsonObject = null;
        String url = "http://apis.juhe.cn/mobile/get";//请求接口地址
        Map<String, String> params = new HashMap<String, String>();//请求参数
        params.put("phone", memberPhone);//需要查询的手机号码或手机号码前7位
        params.put("key", APPKEY);//应用APPKEY(应用详细页查询)
        params.put("dtype", "json");//返回数据的格式,xml或json，默认json

        try {
            result = net(url, params, "GET");
            JSONObject object = JSONObject.fromObject(result);
            if (object.getInt("error_code") == 0) {
                jsonObject = (JSONObject) object.get("result");
            } else {
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return jsonObject;
    }

    /**
     * Net.
     *
     * @param strUrl 请求地址
     * @param params 请求参数
     * @param method 请求方法
     * @return  网络请求字符串
     * @throws Exception the exception
     */
    public static String net(String strUrl, Map<String, String> params, String method) throws Exception {
        HttpURLConnection conn = null;
        BufferedReader reader = null;
        String rs = null;
        try {
            StringBuffer sb = new StringBuffer();
            if (method == null || "GET".equals(method)) {
                strUrl = strUrl + "?" + urlencode(params);
            }
            URL url = new URL(strUrl);
            conn = (HttpURLConnection) url.openConnection();
            if (method == null || "GET".equals(method)) {
                conn.setRequestMethod("GET");
            } else {
                conn.setRequestMethod("POST");
                conn.setDoOutput(true);
            }
            conn.setRequestProperty("User-agent", USER_AGENT);
            conn.setUseCaches(false);
            conn.setConnectTimeout(DEF_CONN_TIMEOUT);
            conn.setReadTimeout(DEF_READ_TIMEOUT);
            conn.setInstanceFollowRedirects(false);
            conn.connect();
            DataOutputStream out = null;
            if (params != null && "POST".equals(method)) {
                try {
                    out = new DataOutputStream(conn.getOutputStream());
                    out.writeBytes(urlencode(params));
                } catch (Exception e) {
                    // TODO handle exception
                    e.printStackTrace();
                } finally {
                    if (null != out) {
                        out.close();
                    }
                }

            }
            InputStream is = conn.getInputStream();
            reader = new BufferedReader(new InputStreamReader(is, DEF_CHATSET));
            String strRead = null;
            while ((strRead = reader.readLine()) != null) {
                sb.append(strRead);
            }
            rs = sb.toString();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                reader.close();
            }
            if (conn != null) {
                conn.disconnect();
            }
        }
        return rs;
    }

    /**
     * Urlencode.
     *
     * @param data the data
     * @return the string
     */
    //将map型转为请求参数型
    public static String urlencode(Map<String, String> data) {
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<String, String> i : data.entrySet()) {
            try {
                sb.append(i.getKey()).append("=").append(URLEncoder.encode(i.getValue() + "", "UTF-8")).append("&");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        return sb.toString();
    }
}
