package com.zxxk.demo.nutrition.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created with Android Studio.
 * Package_Name：com.zxxk.yygc.utils
 * Project_Name：yygc_android
 * User：郭鹏飞
 * Date：2015/12/30
 * Email：love518420@foxmail.com
 * Description：
 */
public class DateUtil {

    private static Calendar calendar = Calendar.getInstance();
    private static final int[] DAY_MONTH = {
            31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31
    };

    private DateUtil() {
    }

    public static String getYear(String suffix) {
        return calendar.get(Calendar.YEAR) + suffix;
    }

    public static String getMonth(String suffix) {
        return (calendar.get(Calendar.MONTH) + 1) + suffix;
    }

    public static String getDay(String suffix) {
        return calendar.get(Calendar.DAY_OF_MONTH) + suffix;
    }

    public static int getYear() {
        return calendar.get(Calendar.YEAR);
    }

    public static int getMonth() {
        return (calendar.get(Calendar.MONTH) + 1);
    }

    public static int getDay() {
        return calendar.get(Calendar.DAY_OF_MONTH);
    }

    public static String getWeek(String prefix) {
        return getWeek(prefix, getWeek());
    }

    public static int getWeek() {
        return calendar.get(Calendar.DAY_OF_WEEK);
    }

    public static String getWeek(String prefix, int week) {
        switch (week) {
            case 1:
                prefix += "日";
                break;
            case 2:
                prefix += "一";
                break;
            case 3:
                prefix += "二";
                break;
            case 4:
                prefix += "三";
                break;
            case 5:
                prefix += "四";
                break;
            case 6:
                prefix += "五";
                break;
            case 7:
                prefix += "六";
                break;
        }
        return prefix;
    }

    /**
     * 得到这个月指定日子的星期
     *
     * @param day 日子
     * @return 星期
     */
    public static int getWeekInMonth(int day) {
        int currentWeek = getWeek(); // 得到这个月当前日子的星期
        int currentDay = getDay();
        int distance = day - currentDay;
        int x = distance % 7;
        int y = x < 0 ? (7 + currentWeek + x) % 7 : (currentWeek + x) % 7;
        return y == 0 ? 7 : y;
    }

    public static int getDaysOfMonth(int month) {
        if (month < 1 || month > 12) {
            throw new RuntimeException("参数非法");
        }
        return month == 2 && isLeapYear(getYear()) ?
                DAY_MONTH[month - 1] + 1 : DAY_MONTH[month - 1];
    }

    public static boolean isLeapYear(int year) {
        return year % 4 == 0 && year % 100 != 0 || year % 400 == 0;
    }

    public static String getCurrentDateBy(long milliseconds, String split) {
        Date date = new Date(milliseconds);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy" +
                split + "MM" + split + "dd" + " HH:mm");
        return simpleDateFormat.format(date);
    }

}
