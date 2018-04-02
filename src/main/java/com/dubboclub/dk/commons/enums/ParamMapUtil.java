/*
*@Project: GZJK
*@Author: cmcc
*@Date: 2015年4月29日
*@Copyright: 2000-2015 CMCC . All rights reserved.
 */
/**
*@Project: GZJK
*@Author: cmcc
*@Date: 2015年4月29日
*@Copyright: 2000-2015 CMCC . All rights reserved.
 */
package com.dubboclub.dk.commons.enums;

import java.util.Map;

/**
* @ClassName: ParamMapUtil
* @Description:
* @author cmcc
* @date 2015年4月29日 下午2:16:32 
*/
public class ParamMapUtil {
    public static Integer getInitParameter(Map<String, Object> map, String key, Integer defaultValue) {
        if (null == map) {
            return defaultValue;
        }
        String value = (String) map.get(key);
        Integer retValue;
        if (value != null && !"".equals(value)) {
            try {
                retValue = Integer.parseInt(value);
            } catch (Exception e) {
                retValue = defaultValue;
            }
        } else {
            retValue = defaultValue;
        }

        return retValue;
    }

    public static Double getDoubleParameter(Map<String, Object> map, String key, Double defaultValue) {
        if (null == map) {
            return defaultValue;
        }
        String value = (String) map.get(key);
        Double retValue;
        if (value != null && !"".equals(value)) {
            try {
                retValue = Double.parseDouble(value);
            } catch (Exception e) {
                retValue = defaultValue;
            }
        } else {
            retValue = defaultValue;
        }

        return retValue;
    }

    public static Long getLongParameter(Map<String, Object> map, String key, Long defaultValue) {
        if (null == map) {
            return defaultValue;
        }
        String value = (String) map.get(key);
        Long retValue;
        if (value != null && !"".equals(value)) {
            try {
                retValue = Long.parseLong(value);
            } catch (Exception e) {
                retValue = defaultValue;
            }
        } else {
            retValue = defaultValue;
        }

        return retValue;
    }

    public static String getStrParameter1(Map<String, Object> map, String key, String defaultValue) {
        if (null == map) {
            return defaultValue;
        }
        String value = (String) map.get(key);
        String retValue;
        if (value != null) {
            retValue = value;
        } else {
            retValue = defaultValue;
        }

        return retValue;
    }

    public static String getStrParameter(Map<String, Object> map, String key, String defaultValue) {
        if (null == map) {
            return defaultValue;
        }
        String value = (String) map.get(key);
        String retValue;
        if (value != null && !"".equals(value)) {
            retValue = value;
        } else {
            retValue = defaultValue;
        }

        return retValue;
    }

    /**
     * 从Map<String,Object>中取出整型数值
     * 
     * @param paramMap
     * @param key
     * @return
     */
    public static Integer getIntegerValue(Map<String, Object> paramMap, String key) {
        Integer value = null;
        if (null != paramMap && !paramMap.isEmpty()) {
            value = Integer.valueOf(paramMap.get(key).toString());
        }
        return value;
    }
}
