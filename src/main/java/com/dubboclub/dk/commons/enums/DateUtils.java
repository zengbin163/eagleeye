package com.dubboclub.dk.commons.enums;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Map;

import com.google.common.collect.Maps;

/**
 * DateUtils
 * 
 * @author liMengbiao
 *
 */
public class DateUtils {

    public final static int ONE_DAY_MINUTES = 1440;

    /**
     * getCurrentDate
     * 
     * @return
     */
    public static Date getCurrentDate() {
        return new Date(System.currentTimeMillis());
    }

    /**
     * getCurrentDate by lvxiaocheng 2015-10-27
     * 
     * @param format
     * @return
     */
    public static Date getCurrentDate(String format) {
        SimpleDateFormat formatter = new SimpleDateFormat(format);
        ParsePosition pos = new ParsePosition(0);
        Date date = formatter.parse(getCurrentStringDate(format), pos);
        return date;
    }

    /**
     * getCurrentStringDate by lvxiaocheng 2015-10-27
     * 
     * @param format
     * @return
     */
    public static String getCurrentStringDate(String format) {
        SimpleDateFormat formatter = new SimpleDateFormat(format);
        return formatter.format(new Date());
    }

    /**
     * by lvxiaocheng 2015-12-16 获取两个时间相差的天数
     * 
     * @param oldDate
     * @param newDate
     * @return
     */
    public static int getIntervalDays(Date oldDate, Date newDate) {
        if (oldDate.after(newDate)) {
            throw new IllegalArgumentException("时间先后顺序不对!");
        }
        // 将转换的两个时间对象转换成Calendard对象
        Calendar can1 = Calendar.getInstance();
        can1.setTime(oldDate);
        Calendar can2 = Calendar.getInstance();
        can2.setTime(newDate);
        // 拿出两个年份
        int year1 = can1.get(Calendar.YEAR);
        int year2 = can2.get(Calendar.YEAR);
        // 天数
        int days = 0;
        Calendar can = null;
        // 减去小的时间在这一年已经过了的天数
        // 加上大的时间已过的天数
        days -= can1.get(Calendar.DAY_OF_YEAR);
        days += can2.get(Calendar.DAY_OF_YEAR);
        can = can1;
        for (int i = 0; i < Math.abs(year2 - year1); i++) {
            // 获取小的时间当前年的总天数
            days += can.getActualMaximum(Calendar.DAY_OF_YEAR);
            // 再计算下一年。
            can.add(Calendar.YEAR, 1);
        }
        return days;
    }

    /**
     * 获取int型年月日
     * 
     * @param recommendTime
     * @return
     */
    public static String getStringShortDate(Date recommendTime) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");

        return formatter.format(recommendTime);
    }

    /**
     * 获取int型年月日
     * 
     * @param recommendTime
     * @return
     */
    public static int getIntCurrentDate() {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");

        String date = formatter.format(new Date());
        return Integer.parseInt(date);
    }

    /**
     * 获取现在时间
     * 
     * @return返回字符串格式 yyyy-MM-dd HH:mm:ss
     */
    public static String getStringDate() {
        Date currentTime = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateString = formatter.format(currentTime);
        return dateString;
    }

    /**
     * getStringAllDate
     * 
     * @return
     */
    public static String getStringAllDate() {
        Date currentTime = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss_SSS");
        String dateString = formatter.format(currentTime);
        return dateString;
    }

    /**
     * 获取现在时间
     * 
     * @return 返回短时间字符串格式yyyy-MM-dd
     */
    public static String getStringDateShort() {
        Date currentTime = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String dateString = formatter.format(currentTime);
        return dateString;
    }

    /**
     * 获取现在时间
     * 
     * @return 返回短时间字符串格式yyyy-MM-dd
     */
    public static String getStringDateShort(Date date) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String dateString = formatter.format(date);
        return dateString;
    }

    /**
     * 获取时间 小时:分;秒 HH:mm:ss
     * 
     * @return
     */
    public static String getTimeShort() {
        SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss");
        Date currentTime = new Date();
        String dateString = formatter.format(currentTime);
        return dateString;
    }

    /**
     * 获取时间 小时分秒 HHmmss
     * 
     * @return
     */
    public static String getTimeShort(Date recommendTime) {
        SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss");
        return formatter.format(recommendTime);
    }

    /**
     * 获取时间 小时分 HHmm
     * 
     * @return
     */
    public static String getStrTimeShortWithOutSecond(Date recommendTime) {
        SimpleDateFormat formatter = new SimpleDateFormat("HHmm");
        return formatter.format(recommendTime);
    }

    /**
     * 获取时间 小时分 HHmm
     * 
     * @return
     */
    public static int getIntTimeShortWithOutSecond(Date recommendTime) {
        SimpleDateFormat formatter = new SimpleDateFormat("HHmm");
        return Integer.parseInt(formatter.format(recommendTime));
    }

    /**
     * 获取秒时间
     * 
     * @param date
     * @return
     */
    public static long getSecondTime(Date date) {
        return date.getTime() / 1000;
    }

    /**
     * 将长时间格式字符串转换为时间 yyyy-MM-dd HH:mm:ss
     * 
     * @param strDate
     * @return
     */
    public static Date strToDateLong(String strDate) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        ParsePosition pos = new ParsePosition(0);
        Date strtodate = formatter.parse(strDate, pos);
        return strtodate;
    }

    /**
     * 将长时间格式时间转换为字符串 yyyy-MM-dd HH:mm:ss
     * 
     * @param dateDate
     * @return
     */
    public static String dateToStrLong(Date dateDate) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateString = formatter.format(dateDate);
        return dateString;
    }

    /**
     * 将短时间格式时间转换为字符串 yyyy-MM-dd
     * 
     * @param dateDate
     * @param k
     * @return
     */
    public static String dateToStr(Date date) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String dateString = formatter.format(date);
        return dateString;
    }

    /**
     * 将短时间格式字符串转换为时间 yyyy-MM-dd
     * 
     * @param strDate
     * @return
     */
    public static Date strToDate(String strDate) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        ParsePosition pos = new ParsePosition(0);
        Date strtodate = formatter.parse(strDate, pos);
        return strtodate;
    }

    /**
     * 当前时间是否在0点10分之前
     * 
     * @param date
     * @return
     */
    public static boolean isTimesmorning(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MINUTE, 10);
        cal.set(Calendar.MILLISECOND, 0);
        // 当前时间的秒数
        int currSecond = (int) (date.getTime() / 1000);
        // 0点10分的秒数
        int second = (int) (cal.getTimeInMillis() / 1000);

        return currSecond < second;
    }

    /**
     * 毫秒转为天，时，分，秒
     * 
     * <pre>
     * 
     * </pre>
     * 
     * @param ms
     *            毫秒数
     * @return
     */
    public static String formatTime(long ms) {
        int ss = 1000;
        int mi = ss * 60;
        int hh = mi * 60;
        int dd = hh * 24;

        long day = ms / dd;
        long hour = (ms - day * dd) / hh;
        long minute = (ms - day * dd - hour * hh) / mi;
        long second = (ms - day * dd - hour * hh - minute * mi) / ss;
        long milliSecond = ms - day * dd - hour * hh - minute * mi - second * ss;

        String strDay = "" + day; // 天
        String strHour = hour < 10 ? "0" + hour : "" + hour;// 小时
        String strMinute = minute < 10 ? "0" + minute : "" + minute;// 分钟
        String strSecond = second < 10 ? "0" + second : "" + second;// 秒
        String strMilliSecond = milliSecond < 10 ? "0" + milliSecond : "" + milliSecond;// 毫秒
        strMilliSecond = milliSecond < 100 ? "0" + strMilliSecond : "" + strMilliSecond;

        return strDay + "天" + strHour + "小时" + strMinute + "分钟" + strSecond + "秒";
    }

    /**
     * 获取当前时间是星期几
     * 
     * @return
     */
    public static int getWeek() {
        Date currentTime = new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(currentTime);
        int week = cal.get(Calendar.DAY_OF_WEEK);

        switch (week) {
            case 1:
                week = 7;
                break;
            case 2:
                week = 1;
                break;
            case 3:
                week = 2;
                break;
            case 4:
                week = 3;
                break;
            case 5:
                week = 4;
                break;
            case 6:
                week = 5;
                break;
            case 7:
                week = 6;
                break;
            default:
                break;

        }
        return week;
    }

    /**
     * 得到几天前/后的时间
     * 
     * @param d
     *            传入的日期
     * @param day
     *            相隔几天 day > 0 几天后，day < 0 几天前
     * @return
     */
    public static Date getDateAfter(Date d, int day) {
        Calendar now = Calendar.getInstance();
        now.setTime(d);
        now.set(Calendar.DATE, now.get(Calendar.DATE) + day);
        return now.getTime();
    }

    /**
     * 获取五分钟之前的时间
     * 
     * @param minute
     * @return
     */
    public static Date getTimeByMinute(int minute) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MINUTE, minute);
        return calendar.getTime();
    }

    /**
     * daysBetween
     * 
     * @param date1
     * @param date2
     * @return
     */
    public static int daysBetween(Date date1, Date date2) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date1);
        long time1 = cal.getTimeInMillis();
        cal.setTime(date2);
        long time2 = cal.getTimeInMillis();
        long betweenDays = (time2 - time1) / (1000 * 3600 * 24);

        return Integer.parseInt(String.valueOf(betweenDays));
    }

    /**
     * 获取一周的开始日期和结束日期，格式为：yyyy-MM-dd 注：周一为第一天
     * 
     * @return
     */
    public static Map<String, String> getWeekStartAndEnd() {

        Map<String, String> map = Maps.newHashMap();
        // 开始日期
        map.put("start", getCurrentMonday());
        // 结束日期
        map.put("end", getPreviousSunday());

        return map;
    }

    /**
     * 获得当前日期与本周一相差的天数
     * 
     * @return
     */
    public static int getMondayPlus() {
        Calendar cd = Calendar.getInstance();
        // 获得今天是一周的第几天，星期日是第一天，星期二是第二天......
        int dayOfWeek = cd.get(Calendar.DAY_OF_WEEK);
        if (dayOfWeek == 1) {
            return -6;
        } else {
            return 2 - dayOfWeek;
        }
    }

    /**
     * 获得当前周- 周一的日期
     * 
     * @return
     */
    public static String getCurrentMonday() {
        int mondayPlus = getMondayPlus();
        Calendar currentDate = Calendar.getInstance();
        currentDate.add(Calendar.DATE, mondayPlus);
        Date monday = currentDate.getTime();
        String preMonday = dateToStr(monday);
        return preMonday;
    }

    /**
     * 获得当前周- 周日 的日期
     * 
     * @return
     */
    public static String getPreviousSunday() {
        int mondayPlus = getMondayPlus();
        Calendar currentDate = Calendar.getInstance();
        currentDate.add(Calendar.DATE, mondayPlus + 6);
        Date sunday = currentDate.getTime();
        String preMonday = dateToStr(sunday);
        return preMonday;
    }

    /**
     * 返回日期 格式为 MM月dd日, 如10月8日
     * 
     * @param day
     *            距离今天的天数差，若是今天，值为0
     * @return
     */
    public static String getDateFormat(Integer day) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_MONTH, day);

        String date = calendar.get(Calendar.MONTH) + 1 + "月" + calendar.get(Calendar.DAY_OF_MONTH) + "日";
        return date;
    }

    /**
     * long时间转date时间
     * 
     * @param time
     * @return
     */
    public static Date longTime2Date(Long time) {

        return new Date(time);
    }

    /**
     * 将短时间格式字符串转换为时间 yyyy-MM
     * 
     * @param strDate
     * @return
     */
    public static Date strToDateShort(String strDate) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM");
        ParsePosition pos = new ParsePosition(0);
        Date strtodate = formatter.parse(strDate, pos);
        return strtodate;
    }

    /**
     * 将短时间格式时间转换为字符串 yyyy-MM
     * 
     * @param dateDate
     * @param k
     * @return
     */
    public static String dateToStrShort(Date date) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM");
        String dateString = formatter.format(date);
        return dateString;
    }

    /**
     * 转换成 yyyy-MM-dd HH:mm
     * 
     * @param strDate
     * @return
     */
    public static Date strToTime(String strDate) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        ParsePosition pos = new ParsePosition(0);
        Date strtodate = formatter.parse(strDate, pos);
        return strtodate;
    }

    /**
     * 转换字符串 yyyy-MM-dd HH:mm为MM月dd日HH:mm
     * 
     * @param strDate
     * @return
     */
    public static String strToChineseTime(Date date) {
        SimpleDateFormat formatter = new SimpleDateFormat("MM月dd日HH:mm");
        String dateString = formatter.format(date);
        return dateString;
    }

    /**
     * 格式转换 yyyy-MM-dd
     * 
     * @param date
     * @return
     */
    public static Date formatToDate(Date date) {
        return strToDate(dateToStr(date));

    }

    /**
     * 格式转换 yyyy-MM-dd HH:mm:ss
     * 
     * @param date
     * @return
     */
    public static Date formatToDateLong(Date date) {
        return strToDateLong(dateToStrLong(date));

    }

    /**
     * 格式转换 yyyy-MM
     * 
     * @param date
     * @return
     */
    public static Date formatToDateShort(Date date) {
        return strToDateShort(dateToStrShort(date));

    }

    /**
     * 第一天 yyyy-MM-dd
     * 
     * @param date
     * @return
     */
    public static Date firstDay(Date date) {
        Calendar calendar = convert(date);
        calendar.set(Calendar.DATE, 1);
        return calendar.getTime();
    }

    /**
     * 最后一天 yyyy-MM-dd
     * 
     * @param date
     * @return
     */
    public static Date lastDay(Date date) {
        Calendar calendar = convert(date);
        calendar.set(Calendar.DATE, calendar.getActualMaximum(Calendar.DATE));
        return calendar.getTime();
    }

    /**
     * @param date
     * @return
     */
    private static Calendar convert(Date date) {
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        return calendar;
    }

    /**
     * 获取小时
     * 
     * @param recommendTime
     * @return
     */
    public static int getHourTime(Date recommendTime) {
        SimpleDateFormat formatter = new SimpleDateFormat("HH");
        return Integer.parseInt(formatter.format(recommendTime));
    }

    /**
     * 根据时间获取当前周的开始和结束时间 yyyy-MM-dd
     * 
     * @param date
     * @return
     */
    public static Map<String, Date> getWeekStartAndEnd(Date date) {

        Map<String, Date> map = Maps.newHashMap();
        // 开始日期
        map.put("start", getDateMonday(date));
        // 结束日期
        map.put("end", getDateSunday(date));

        return map;
    }

    /**
     * 获取指定日期周一的时间
     * 
     * @param date
     * @return
     */
    public static Date getDateMonday(Date date) {
        int mondayPlus = getMondayPlus(date);
        Calendar currentDate = Calendar.getInstance();
        currentDate.setTime(date);
        currentDate.add(Calendar.DATE, mondayPlus);
        Date monday = currentDate.getTime();
        Date preMonday = formatToDate(monday);
        return preMonday;
    }

    /**
     * 获得指定日期与所在周周一相差的天数
     * 
     * @param date
     * @return
     */
    public static int getMondayPlus(Date date) {
        Calendar cd = Calendar.getInstance();
        cd.setTime(date);
        // 获得今天是一周的第几天，星期日是第一天，星期二是第二天......
        int dayOfWeek = cd.get(Calendar.DAY_OF_WEEK);
        if (dayOfWeek == 1) {
            return -6;
        } else {
            return 2 - dayOfWeek;
        }
    }

    /**
     * 获得制定日期周日的时间
     * 
     * @param date
     * @return
     */
    public static Date getDateSunday(Date date) {
        int mondayPlus = getMondayPlus(date);
        Calendar currentDate = Calendar.getInstance();
        currentDate.setTime(date);
        currentDate.add(Calendar.DATE, mondayPlus + 6);
        Date sunday = currentDate.getTime();
        Date preMonday = formatToDate(sunday);
        return preMonday;
    }

    /**
     * 得到几个月前/后的时间
     * 
     * @param d
     *            传入的日期
     * @param month
     *            相隔几个月 month > 0 几个月后，month < 0 几个月前
     * @return
     */
    public static Date getMonthAfter(Date d, int month) {
        Calendar now = Calendar.getInstance();
        now.setTime(d);
        now.add(Calendar.MONTH, month); // 设置为前3月
        return now.getTime();
    }

    /**
     * 获取两个日期字符串相差多少毫秒
     * 
     * @param startDate
     *            开始日期
     * @param endDate
     *            结束日期
     * @throws ParseException
     */
    public static Long getDateDiff(String startDate, String endDate) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date end = sdf.parse(endDate);
        Date begin = sdf.parse(startDate);
        return end.getTime() - begin.getTime();
    }

    public static Map<String, Date> getDateRangeForDay(Integer pageOffset, Integer pageSize) {
        Date currentDate = formatToDate(new Date());
        Date endDate = DateUtils.getDateAfter(currentDate, -pageOffset);
        Date startDate = DateUtils.getDateAfter(currentDate, 1 - (pageSize + pageOffset));
        Map<String, Date> map = Maps.newHashMap();
        map.put("startDate", startDate);
        map.put("endDate", endDate);
        return map;
    }
    
    /**
     * 查询前一天
     * @return
     */
    public static String getPreDateTime() {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		calendar.add(Calendar.DAY_OF_MONTH, -1);
		DateFormat format = new SimpleDateFormat("yyyyMMdd");
		return format.format(calendar.getTime());
	}
    
    /**
     * 查询前一天 00:00:00
     * @return
     */
    public static String getPreDateTimeBegin() {
    	Calendar calendar = Calendar.getInstance();
    	calendar.setTime(new Date());
    	calendar.add(Calendar.DAY_OF_MONTH, -1);
    	DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
    	return format.format(calendar.getTime()) + " 00:00:00";
    }
    
    /**
     * 查询前一天 23:59:59
     * @return
     */
    public static String getPreDateTimeEnd() {
    	Calendar calendar = Calendar.getInstance();
    	calendar.setTime(new Date());
    	calendar.add(Calendar.DAY_OF_MONTH, -1);
    	DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
    	return format.format(calendar.getTime()) + " 23:59:59";
    }
    
    
    
    public static String getMonthDesc(Date date) {
    	Calendar calendar = Calendar.getInstance();
    	calendar.setTime(date);
    	DateFormat format = new SimpleDateFormat("MM");
    	Integer month = Integer.valueOf(format.format(calendar.getTime()));
    	String desc = "一月";
		switch (month) {
			case 1:
				desc = "一月";
				break;
			case 2:
				desc = "二月";
				break;
			case 3:
				desc = "三月";
				break;
			case 4:
				desc = "四月";
				break;
			case 5:
				desc = "五月";
				break;
			case 6:
				desc = "六月";
				break;
			case 7:
				desc = "七月";
				break;
			case 8:
				desc = "八月";
				break;
			case 9:
				desc = "九月";
				break;
			case 10:
				desc = "十月";
				break;
			case 11:
				desc = "十一月";
				break;
			case 12:
				desc = "十二月";
				break;
			default:
				break;
		}
		return desc;
    }
    
    public static Integer getPreMonth() {
    	Calendar calendar = Calendar.getInstance();
    	calendar.setTime(new Date());
    	calendar.add(Calendar.MONTH, -1);
    	DateFormat format = new SimpleDateFormat("MM");
    	return Integer.valueOf(format.format(calendar.getTime()));
    }

}
