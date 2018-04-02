package com.dubboclub.dk.commons.util;

import static com.google.common.base.Preconditions.checkNotNull;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.Collection;

/**
 * MathUtil
 * 
 * @author liMengbiao
 *
 */
public class MathUtil {
    /**
     * 取最小值
     * 
     * @param values
     * @throws NullPointerException
     *             if (values == null || values.contain(null))
     * @return
     */
    public static <T extends Comparable<T>> T min(Collection<T> values) {
        checkNotNull(values);
        T min = null;

        for (T t : values) {
            checkNotNull(t);
            if (min == null) {
                min = t;
            } else {
                min = min.compareTo(t) < 0 ? min : t;
            }
        }
        return min;
    }

    /**
     * 字符串转换为int
     * 
     * @param str
     * @return
     */
    public static int getInt(String str) {
        if (str == null || "".equals(str)) {
            return 0;
        }
        int ret = 0;
        try {
            ret = Integer.parseInt(str);
        } catch (NumberFormatException e) {
            ret = 0;
        }
        return ret;
    }

    public static double getDouble(String str) {
        if (str == null || "".equals(str)) {
            return 0.0;
        }
        double ret = 0.0;
        try {
            ret = Double.parseDouble(str);
        } catch (NumberFormatException e) {
            ret = 0.0;
        }
        return getFormatDoubleNum(ret, 2);
    }

    /**
     * 格式化小数(保留几位小数)
     * 
     * @param num
     *            需要格式化的小数
     * @param decimalNum
     *            保留的小数位数
     * @return
     */
    public static double getFormatDoubleNum(double num, int decimalNum) {
        BigDecimal b = new BigDecimal(num);
        double d = b.setScale(decimalNum, BigDecimal.ROUND_HALF_UP).doubleValue();
        return d;
    }

    /**
     * 转化为k,M
     * 
     * @param number
     * @return
     */
    public static String getKiloNumber(int number) {
        if (number >= 0 && number < 10000) {
            return number + "";
        } else if (number < 1000000 && number >= 10000) {
            return div(number, 1000, 2) + "k";
        } else if (number >= 1000000) {
            return div(div(number, 1000, 2), 1000, 2) + "M";
        } else {
            throw new IllegalArgumentException("参数异常");
        }
    }

    /**
     * 格式化数值
     * 
     * @param number
     * @return
     */
    public static int kiloToNumber(String value) {
        int result = 0;
        if (value.contains("k")) {
            String val = value.substring(0, value.indexOf("k"));
            double d = getDouble(val) * 1000;
            result = (int) d;
        } else if (value.contains("M")) {
            String val = value.substring(0, value.indexOf("M"));
            double d = getDouble(val) * 1000 * 1000;
            result = (int) d;
        }else{
        	result = Integer.valueOf(value);
        }

        return result;
    }

    public static void main(String[] args) {
        System.out.println(kiloToNumber("341.9M"));
    }

    /**
     * 格式化数值
     * 
     * @param number
     * @return
     */
    public static String formatNumber(Integer number) {
        DecimalFormat df = new DecimalFormat("00000");
        return df.format(number);
    }

    /**
     * 除法运算，除不尽时四舍五入
     * 
     * @param val1被除数
     * @param val2除数
     * @param scale
     *            小数点后精确几位
     * @return
     */
    public static double div(double val1, double val2, int scale) {
        if (scale < 0) {
            throw new IllegalArgumentException("scale必须为正整数或0");
        }
        BigDecimal b1 = new BigDecimal(Double.toString(val1));
        BigDecimal b2 = new BigDecimal(Double.toString(val2));
        return b1.divide(b2, scale, BigDecimal.ROUND_HALF_UP).doubleValue();
    }

    /**
     * 算百分比
     * @param value
     * @return
     */
    public static String formatToPercent(double value) {
        if (value > 100) {
            throw new IllegalArgumentException("参数不能大于100");
        }
        double val = div(value, 100, 4);
        DecimalFormat df = new DecimalFormat("#.##%");
        return df.format(val);
    }

}
