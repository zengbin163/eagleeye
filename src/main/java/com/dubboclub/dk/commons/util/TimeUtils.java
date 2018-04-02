package com.dubboclub.dk.commons.util;

import java.security.InvalidParameterException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.apache.commons.lang.time.DateUtils;

// TODO Auto-generated Javadoc
/**
 * The Class TimeUtils.
 */
public class TimeUtils {

    /** The Constant MILLIS_IN_A_SECOND. */
    private static final long MILLIS_IN_A_SECOND = 1000;

    /** The Constant SECONDS_IN_A_MINUTE. */
    private static final long SECONDS_IN_A_MINUTE = 60;

    /** The Constant MINUTES_IN_AN_HOUR. */
    private static final long MINUTES_IN_AN_HOUR = 60;

    /** The Constant HOURS_IN_A_DAY. */
    private static final long HOURS_IN_A_DAY = 24;

    /** The Constant DAYS_IN_A_WEEK. */
    private static final int DAYS_IN_A_WEEK = 7;

    /** The Constant MONTHS_IN_A_YEAR. */
    private static final int MONTHS_IN_A_YEAR = 12;

    /** 最小日期，设定为1000年1月1日. */
    public static final Date MIN_DATE = date(1000, 1, 1);

    /** 最大日期，设定为8888年1月1日. */
    public static final Date MAX_DATE = date(8888, 1, 1);

    /** The Constant FORMAT_DATE. */
    public final static String FORMAT_DATE = "yyyy-MM-dd";

    /** The Constant FORMAT_TIME. */
    public final static String FORMAT_TIME = "hh:mm";

    /** The Constant FORMAT_DATE_TIME. */
    public final static String FORMAT_DATE_TIME = "yyyy-MM-dd hh:mm";

    /** The Constant FORMAT_MONTH_DAY_TIME. */
    public final static String FORMAT_MONTH_DAY_TIME = "MM月dd日 hh:mm";

    /** The Constant YEAR. */
    private static final int YEAR = 365 * 24 * 60 * 60;// 年

    /** The Constant MONTH. */
    private static final int MONTH = 30 * 24 * 60 * 60;// 月

    /** The Constant DAY. */
    private static final int DAY = 24 * 60 * 60;// 天

    /** The Constant HOUR. */
    private static final int HOUR = 60 * 60;// 小时

    /** The Constant MINUTE. */
    private static final int MINUTE = 60;// 分钟

    /**
     * 根据时间戳获取描述性时间，如3分钟前，1天前.
     *
     * @param timestamp            时间戳 单位为毫秒
     * @return 时间字符串
     */
    public static String getDescriptionTimeFromTimestamp(long timestamp) {
        long currentTime = System.currentTimeMillis();
        long timeGap = (currentTime - timestamp) / 1000;// 与现在时间相差秒数
        System.out.println("timeGap: " + timeGap);
        String timeStr = null;
        if (timeGap > YEAR) {
            timeStr = timeGap / YEAR + "年前";
        } else if (timeGap > MONTH) {
            timeStr = timeGap / MONTH + "个月前";
        } else if (timeGap > DAY) {// 1天以上
            timeStr = timeGap / DAY + "天前";
        } else if (timeGap > HOUR) {// 1小时-24小时
            timeStr = timeGap / HOUR + "小时前";
        } else if (timeGap > MINUTE) {// 1分钟-59分钟
            timeStr = timeGap / MINUTE + "分钟前";
        } else {// 1秒钟-59秒钟
            timeStr = "刚刚";
        }
        return timeStr;
    }

    /**
     * 根据时间戳获取指定格式的时间，如2011-11-30 08:40.
     *
     * @param timestamp            时间戳 单位为毫秒
     * @param format            指定格式 如果为null或空串则使用默认格式"yyyy-MM-dd HH:MM"
     * @return the format time from timestamp
     */
    public static String getFormatTimeFromTimestamp(long timestamp, String format) {
        SimpleDateFormat sdf = new SimpleDateFormat();
        if (format == null || "".equals(format.trim())) {
            sdf.applyPattern(FORMAT_DATE);
            int currentYear = Calendar.getInstance().get(Calendar.YEAR);
            int year = Integer.parseInt(sdf.format(new Date(timestamp)).substring(0, 4));
            if (currentYear == year) {// 如果为今年则不显示年份
                sdf.applyPattern(FORMAT_MONTH_DAY_TIME);
            } else {
                sdf.applyPattern(FORMAT_DATE_TIME);
            }
        } else {
            sdf.applyPattern(format);
        }
        Date date = new Date(timestamp);
        return sdf.format(date);
    }

    /**
     * 根据时间戳获取时间字符串，并根据指定的时间分割数partionSeconds来自动判断返回描述性时间还是指定格式的时间.
     *
     * @param timestamp            时间戳 单位是毫秒
     * @param partionSeconds            时间分割线，当现在时间与指定的时间戳的秒数差大于这个分割线时则返回指定格式时间，否则返回描述性时间
     * @param format the format
     * @return the mix time from timestamp
     */
    public static String getMixTimeFromTimestamp(long timestamp, long partionSeconds, String format) {
        long currentTime = System.currentTimeMillis();
        long timeGap = (currentTime - timestamp) / 1000;// 与现在时间相差秒数
        if (timeGap <= partionSeconds) {
            return getDescriptionTimeFromTimestamp(timestamp);
        } else {
            return getFormatTimeFromTimestamp(timestamp, format);
        }
    }

    /**
     * 获取当前日期的指定格式的字符串.
     *
     * @param format            指定的日期时间格式，若为null或""则使用指定的格式"yyyy-MM-dd HH:MM"
     * @return the current time
     */
    public static String getCurrentTime(String format) {
        SimpleDateFormat sdf = new SimpleDateFormat();
        if (format == null || "".equals(format.trim())) {
            sdf.applyPattern(FORMAT_DATE_TIME);
        } else {
            sdf.applyPattern(format);
        }
        return sdf.format(new Date());
    }

    /**
     * 将日期字符串以指定格式转换为Date.
     *
     * @param timeStr the time str
     * @param format            指定的日期格式，若为null或""则使用指定的格式"yyyy-MM-dd HH:MM"
     * @return Date or new Date()
     */
    public static Date getTimeFromString(String timeStr, String format) {
        SimpleDateFormat sdf = new SimpleDateFormat();
        if (format == null || "".equals(format.trim())) {
            sdf.applyPattern(FORMAT_DATE_TIME);
        } else {
            sdf.applyPattern(format);
        }
        try {
            return sdf.parse(timeStr);
        } catch (ParseException e) {
            return new Date();
        }
    }

    /**
     * 将日期字符串以指定格式转换为Date（不捕获异常）.
     *
     * @param timeStr the time str
     * @param format            指定的日期格式，若为null或""则使用指定的格式"yyyy-MM-dd HH:MM"
     * @return Date or ParseException
     * @throws ParseException the parse exception
     */
    public static Date getDateFromString(String timeStr, String format) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat();
        if (format == null || "".equals(format.trim())) {
            sdf.applyPattern(FORMAT_DATE_TIME);
        } else {
            sdf.applyPattern(format);
        }
        return sdf.parse(timeStr);
    }

    /**
     * 将Date以指定格式转换为日期时间字符串.
     *
     * @param time the time
     * @param format            指定的日期时间格式，若为null或""则使用指定的格式"yyyy-MM-dd HH:MM"
     * @return the string from time
     */
    public static String getStringFromTime(Date time, String format) {
        SimpleDateFormat sdf = new SimpleDateFormat();
        if (format == null || "".equals(format.trim())) {
            sdf.applyPattern(FORMAT_DATE_TIME);
        } else {
            sdf.applyPattern(format);
        }
        return sdf.format(time);
    }

    /**
     * 根据年月日构建日期对象。注意月份是从1开始计数的，即month为1代表1月份。.
     *
     * @param year            年
     * @param month            月。注意1代表1月份，依此类推。
     * @param date the date
     * @return the date
     */
    public static Date date(int year, int month, int date) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month - 1, date, 0, 0, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();
    }

    /**
     * 计算两个日期（不包括时间）之间相差的周年数.
     *
     * @param date1 the date 1
     * @param date2 the date 2
     * @return the year diff
     */
    public static int getYearDiff(Date date1, Date date2) {
        if (date1 == null || date2 == null) {
            throw new InvalidParameterException("date1 and date2 cannot be null!");
        }
        if (date1.after(date2)) {
            throw new InvalidParameterException("date1 cannot be after date2!");
        }

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date1);
        int year1 = calendar.get(Calendar.YEAR);
        int month1 = calendar.get(Calendar.MONTH);
        int day1 = calendar.get(Calendar.DATE);

        calendar.setTime(date2);
        int year2 = calendar.get(Calendar.YEAR);
        int month2 = calendar.get(Calendar.MONTH);
        int day2 = calendar.get(Calendar.DATE);

        int result = year2 - year1;
        if (month2 < month1) {
            result--;
        } else if (month2 == month1 && day2 < day1) {
            result--;
        }
        return result;
    }

    /**
     * 计算两个日期（不包括时间）之间相差的整月数.
     *
     * @param date1 the date 1
     * @param date2 the date 2
     * @return the month diff
     */
    public static int getMonthDiff(Date date1, Date date2) {
        if (date1 == null || date2 == null) {
            throw new InvalidParameterException("date1 and date2 cannot be null!");
        }
        if (date1.after(date2)) {
            throw new InvalidParameterException("date1 cannot be after date2!");
        }

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date1);
        int year1 = calendar.get(Calendar.YEAR);
        int month1 = calendar.get(Calendar.MONTH);
        int day1 = calendar.get(Calendar.DATE);

        calendar.setTime(date2);
        int year2 = calendar.get(Calendar.YEAR);
        int month2 = calendar.get(Calendar.MONTH);
        int day2 = calendar.get(Calendar.DATE);

        int months = 0;
        if (day2 >= day1) {
            months = month2 - month1;
        } else {
            months = month2 - month1 - 1;
        }
        return (year2 - year1) * MONTHS_IN_A_YEAR + months;
    }

    /**
     * 统计两个日期之间包含的天数。包含date1，但不包含date2.
     *
     * @param date1 the date 1
     * @param date2 the date 2
     * @return the day diff
     */
    public static int getDayDiff(Date date1, Date date2) {
        if (date1 == null || date2 == null) {
            throw new InvalidParameterException("date1 and date2 cannot be null!");
        }
        Date startDate = DateUtils.truncate(date1, Calendar.DATE);
        Date endDate = DateUtils.truncate(date2, Calendar.DATE);
        if (startDate.after(endDate)) {
            throw new InvalidParameterException("date1 cannot be after date2!");
        }
        long millSecondsInOneDay = HOURS_IN_A_DAY * MINUTES_IN_AN_HOUR * SECONDS_IN_A_MINUTE * MILLIS_IN_A_SECOND;
        return (int) ((endDate.getTime() - startDate.getTime()) / millSecondsInOneDay);
    }

    /**
     * 计算time2比time1晚多少分钟，忽略日期部分.
     *
     * @param time1 the time 1
     * @param time2 the time 2
     * @return the minute diff by time
     */
    public static int getMinuteDiffByTime(Date time1, Date time2) {
        long startMil = 0;
        long endMil = 0;
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(time1);
        calendar.set(1900, 1, 1);
        startMil = calendar.getTimeInMillis();
        calendar.setTime(time2);
        calendar.set(1900, 1, 1);
        endMil = calendar.getTimeInMillis();
        return (int) ((endMil - startMil) / MILLIS_IN_A_SECOND / SECONDS_IN_A_MINUTE);
    }

    /**
     * 计算指定日期的前一天.
     *
     * @param date the date
     * @return the prev day
     */
    public static Date getPrevDay(Date date) {
        return DateUtils.addDays(date, -1);
    }

    /**
     * 计算指定日期的后一天.
     *
     * @param date the date
     * @return the next day
     */
    public static Date getNextDay(Date date) {
        return DateUtils.addDays(date, 1);
    }

    /**
     * 判断date1是否在date2之后，忽略时间部分.
     *
     * @param date1 the date 1
     * @param date2 the date 2
     * @return true, if is date after
     */
    public static boolean isDateAfter(Date date1, Date date2) {
        Date theDate1 = DateUtils.truncate(date1, Calendar.DATE);
        Date theDate2 = DateUtils.truncate(date2, Calendar.DATE);
        return theDate1.after(theDate2);
    }

    /**
     * 判断date1是否在date2之前，忽略时间部分.
     *
     * @param date1 the date 1
     * @param date2 the date 2
     * @return true, if is date before
     */
    public static boolean isDateBefore(Date date1, Date date2) {
        return isDateAfter(date2, date1);
    }

    /**
     * 判断time1是否在time2之后，忽略日期部分.
     *
     * @param time1 the time 1
     * @param time2 the time 2
     * @return true, if is time after
     */
    public static boolean isTimeAfter(Date time1, Date time2) {
        Calendar calendar1 = Calendar.getInstance();
        calendar1.setTime(time1);
        calendar1.set(1900, 1, 1);
        Calendar calendar2 = Calendar.getInstance();
        calendar2.setTime(time2);
        calendar2.set(1900, 1, 1);
        return calendar1.after(calendar2);
    }

    /**
     * 判断time1是否在time2之前，忽略日期部分.
     *
     * @param time1 the time 1
     * @param time2 the time 2
     * @return true, if is time before
     */
    public static boolean isTimeBefore(Date time1, Date time2) {
        return isTimeAfter(time2, time1);
    }

    /**
     * 判断两个日期是否同一天（忽略时间部分）.
     *
     * @param date1 the date 1
     * @param date2 the date 2
     * @return true, if is same day
     */
    public static boolean isSameDay(Date date1, Date date2) {
        return DateUtils.isSameDay(date1, date2);
    }

    /**
     * 判断两个日历天是否同一天（忽略时间部分）.
     *
     * @param date1 the date 1
     * @param date2 the date 2
     * @return true, if is same day
     */
    public static boolean isSameDay(Calendar date1, Calendar date2) {
        return DateUtils.isSameDay(date1, date2);
    }

    /**
     * 将字符串形式的日期表示解析为日期对象.
     *
     * @param dateString the date string
     * @return the date
     */
    public static Date parseDate(String dateString) {
        try {
            return DateUtils.parseDate(dateString, new String[] { "yyyy-MM-dd", "yyyy-M-d", "yyyy-MM-d", "yyyy-M-dd" });
        } catch (ParseException e) {
            return null;
        }
    }

    /**
     * 将字符串形式的时间表示解析为日期时间对象.
     *
     * @param timeString the time string
     * @return the date
     */
    public static Date parseTime(String timeString) {
        try {
            return DateUtils.parseDate(timeString, new String[] { "hh:mm:ss", "h:m:s", "hh:mm", "h:m" });
        } catch (ParseException e) {
            return null;
        }
    }

    /**
     * 将字符串形式的日期时间表示解析为时间对象.
     *
     * @param timeString the time string
     * @return the date
     */
    public static Date parseDateTime(String timeString) {
        try {
            return DateUtils.parseDate(timeString, new String[] { "yyyy-MM-dd HH:mm:ss", "yyyy-M-d H:m:s", "yyyy-MM-dd H:m:s", "yyyy-M-d HH:mm:ss" });
        } catch (ParseException e) {
            return null;
        }
    }

    /**
     * 计算两个日期之间包含的星期X的天数。.
     *
     * @param fromDate            起始日期
     * @param toDate            结束日期
     * @param dayOfWeek            星期，例如星期三，星期四
     * @return the week days between
     */
    public static int getWeekDaysBetween(Date fromDate, Date toDate, int dayOfWeek) {
        int result = 0;
        Date firstDate = getFirstWeekdayBetween(fromDate, toDate, dayOfWeek);
        if (firstDate == null) {
            return 0;
        }
        Calendar aDay = Calendar.getInstance();
        aDay.setTime(firstDate);
        while (aDay.getTime().before(toDate)) {
            result++;
            aDay.add(Calendar.DATE, DAYS_IN_A_WEEK);
        }
        return result;
    }

    /**
     * 获取在两个日期之间的第一个星期X.
     *
     * @param fromDate            起始日期
     * @param toDate            结束日期
     * @param dayOfWeek            星期，例如星期三，星期四
     * @return the first weekday between
     */
    public static Date getFirstWeekdayBetween(Date fromDate, Date toDate, int dayOfWeek) {
        Calendar aDay = Calendar.getInstance();
        aDay.setTime(fromDate);
        while (aDay.getTime().before(toDate)) {
            if (aDay.get(Calendar.DAY_OF_WEEK) == dayOfWeek) {
                return aDay.getTime();
            }
            aDay.add(Calendar.DATE, 1);
        }
        return null;
    }

    /**
     * 取得参数year指定的年份的总天数.
     *
     * @param year the year
     * @return the days in year
     */
    public static int getDaysInYear(int year) {
        Calendar aDay = Calendar.getInstance();
        aDay.set(year, 1, 1);
        Date from = aDay.getTime();
        aDay.set(year + 1, 1, 1);
        Date to = aDay.getTime();
        return getDayDiff(from, to);
    }

    /**
     * 取得指定年月的总天数.
     *
     * @param year the year
     * @param month the month
     * @return the days in month
     */
    public static int getDaysInMonth(int year, int month) {
        Calendar aDay = Calendar.getInstance();
        aDay.set(year, month, 1);
        Date from = aDay.getTime();
        if (month == Calendar.DECEMBER) {
            aDay.set(year + 1, Calendar.JANUARY, 1);
        } else {
            aDay.set(year, month + 1, 1);
        }
        Date to = aDay.getTime();
        return getDayDiff(from, to);
    }

    /**
     * 获得指定日期的年份.
     *
     * @param date the date
     * @return the year
     */
    public static int getYear(Date date) {
        return getFieldValue(date, Calendar.YEAR);
    }

    /**
     * 获得指定日期的月份.
     *
     * @param date the date
     * @return the month
     */
    public static int getMonth(Date date) {
        return getFieldValue(date, Calendar.MONTH) + 1;
    }

    /**
     * 获得指定日期是当年的第几天.
     *
     * @param date the date
     * @return the day of year
     */
    public static int getDayOfYear(Date date) {
        return getFieldValue(date, Calendar.DAY_OF_YEAR);
    }

    /**
     * 获得指定日期是当月的第几天.
     *
     * @param date the date
     * @return the day of month
     */
    public static int getDayOfMonth(Date date) {
        return getFieldValue(date, Calendar.DAY_OF_MONTH);
    }

    /**
     * 获得指定日期是当周的第几天.
     *
     * @param date the date
     * @return the day of week
     */
    public static int getDayOfWeek(Date date) {
        return getFieldValue(date, Calendar.DAY_OF_WEEK);
    }

    /**
     * Gets the field value.
     *
     * @param date the date
     * @param field the field
     * @return the field value
     */
    private static int getFieldValue(Date date, int field) {
        if (date == null) {
            throw new InvalidParameterException("date cannot be null!");
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(field);
    }

    /**
     * 获得指定日期之后一段时期的日期。例如某日期之后3天的日期等。.
     *
     * @param origDate            基准日期
     * @param amount            时间数量
     * @param timeUnit            时间单位，如年、月、日等。用Calendar中的常量代表
     * @return the date
     */
    public static final Date dateAfter(Date origDate, int amount, int timeUnit) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(origDate);
        calendar.add(timeUnit, amount);
        return calendar.getTime();
    }

    /**
     * 获得指定日期之前一段时期的日期。例如某日期之前3天的日期等。.
     *
     * @param origDate            基准日期
     * @param amount            时间数量
     * @param timeUnit            时间单位，如年、月、日等。用Calendar中的常量代表
     * @return the date
     */
    public static final Date dateBefore(Date origDate, int amount, int timeUnit) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(origDate);
        calendar.add(timeUnit, -amount);
        return calendar.getTime();
    }

    /**
     * Change time bycurrent time.
     *
     * @author houyuliang
     * @param timeType 时间类型:yyyy,MM,dd,HH,mm,ss
     * @param changeTime 要改变的时间长度:正数表示往后,负数表示往前
     * @param format 时间格式
     * @return String
     * @Description 根据当前时间计算指定时间
     * @time 2015年4月3日 上午11:34:34
     */
    public static String changeTimeBycurrentTime(String timeType, int changeTime, String format) {
        String goalTime = null;
        Calendar c = Calendar.getInstance();
        if ("yyyy".equals(timeType)) {
            c.set(Calendar.YEAR, c.get(Calendar.YEAR) + changeTime);
        } else if ("MM".equals(timeType)) {
            c.set(Calendar.MONTH, c.get(Calendar.MONTH) + changeTime);
        } else if ("dd".equals(timeType)) {
            c.set(Calendar.DATE, c.get(Calendar.DATE) + changeTime);
        } else if ("HH".equals(timeType)) {
            c.set(Calendar.HOUR_OF_DAY, c.get(Calendar.HOUR_OF_DAY) + changeTime);
        } else if ("mm".equals(timeType)) {
            c.set(Calendar.MINUTE, c.get(Calendar.MINUTE) + changeTime);
        } else if ("ss".equals(timeType)) {
            c.set(Calendar.SECOND, c.get(Calendar.SECOND) + changeTime);
        }
        SimpleDateFormat dateFormat = new SimpleDateFormat(format);
        goalTime = dateFormat.format(c.getTime());
        return goalTime;
    }

}
