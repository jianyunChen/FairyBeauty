package com.fairyBeauty.utils;

import org.apache.commons.lang3.StringUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * 日期帮助类
 */
public class DateUtils {
    private static final Integer MONTH_VALUE = 12;
    public static final String YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss";
    public static final String YYYY_MM_DD = "yyyy-MM-dd";
    public static final String YYYY_MM = "yyyy-MM";
    public static final String YYYY = "yyyy";

    /**
     * 将LocalDateTime转为自定义的时间格式的字符串
     * @param localDateTime
     * @param format
     * @return
     */
    public static String getDateTimeAsString(LocalDateTime localDateTime, String format) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
        return localDateTime.format(formatter);
    }
    /**
     * 将LocalDateTime转为自定义的时间格式的字符串
     * @param localDateTime
     * @param format
     * @return
     */
    public static String getDateTimeAsString(LocalDate localDateTime, String format) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
        return localDateTime.format(formatter);
    }

    /**
     * 将LocalDateTime转为 yyyy-MM-dd HH:mm:ss格式的字符串
     * @param time
     * @return
     */
    public static String getDateTimeAsString(LocalDateTime time) {
        return getDateTimeAsString(time, YYYY_MM_DD_HH_MM_SS);
    }
    /**
     * 将LocalDate转为 yyyy-MM-dd HH:mm:ss格式的字符串
     * @param time
     * @return
     */
    public static String getDateTimeAsString(LocalDate time) {
        return getDateTimeAsString(time, YYYY_MM_DD_HH_MM_SS);
    }
    /**
     * 将LocalDate转为 yyyy-MM-dd格式的字符串
     * @param time
     * @return
     */
    public static String getDateAsString(LocalDate time) {
        return getDateTimeAsString(time, YYYY_MM_DD);
    }

    /**
     * 将long类型的timestamp转为LocalDateTime
     * @param timestamp
     * @return
     */
    public static LocalDateTime getDateTimeOfTimestamp(long timestamp) {
        Instant instant = Instant.ofEpochMilli(timestamp);
        ZoneId zone = ZoneId.systemDefault();
        return LocalDateTime.ofInstant(instant, zone);
    }

    /**
     * 将long类型的timestamp转为LocalDateTime
     * @param timestamp
     * @return
     */
    public static String getDateTimeStringOfTimestamp(long timestamp) {
        return getDateTimeAsString(getDateTimeOfTimestamp(timestamp));
    }

    /**
     * 将LocalDateTime转为long类型的timestamp
     * @param localDateTime
     * @return
     */
    public static long getTimestampOfDateTime(LocalDateTime localDateTime) {
        ZoneId zone = ZoneId.systemDefault();
        Instant instant = localDateTime.atZone(zone).toInstant();
        return instant.toEpochMilli();
    }


    /**
     * 将某时间字符串转为自定义时间格式的LocalDateTime
     * @param time
     * @param format
     * @return
     */
    public static LocalDateTime parseStringToDateTime(String time, String format) {
        DateTimeFormatter df = DateTimeFormatter.ofPattern(format);
        return LocalDateTime.parse(time, df);
    }
    /**
     * 将LocalDateTime转为 yyyy-MM-dd HH:mm:ss格式的字符串
     * @param time
     * @return
     */
    public static LocalDateTime parseStringToDateTime(String time) {
        if (time.length() == 4) {
            time = time + "-01-01 00:00:00";
        } else if (time.length() == 7) {//yyyy-MM
            time = time + "-01 00:00:00";
        } else if (time.length() == 10) {//yyyy-MM-dd
            time = time + " 00:00:00";
        }else if (time.length() == 13) {//yyyy-MM-dd
            time = time + ":00:00";
        }else if (time.length() == 16) {//yyyy-MM-dd
            time = time + ":00";
        }
        return parseStringToDateTime(time, YYYY_MM_DD_HH_MM_SS);
    }

    /**
     * 将LocalDateTime转为 yyyy-MM-dd 格式的字符串
     * @param time
     * @return
     */
    public static LocalDate parseStringToDate(String time) {
        LocalDateTime localDateTime = null;
        if (time.length() == 4) {
            time = time + "-01-01 00:00:00";
        } else if (time.length() == 7) {//yyyy-MM
            time = time + "-01 00:00:00";
        } else if (time.length() == 10) {//yyyy-MM-dd
            time = time + " 00:00:00";
        }else if (time.length() == 16) {//yyyy-MM-dd
            time = time + ":00";
        }
        localDateTime = parseStringToDateTime(time, YYYY_MM_DD_HH_MM_SS);
        return localDateTime.toLocalDate();
    }


    /**
     * 公用方法，将时间戳转化为字符串时间("yyyy-MM-dd HH:mm:ss")
     *
     * @param date
     * @return
     */
    public static String formatDateToString(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat(YYYY_MM_DD_HH_MM_SS);
        return sdf.format(date);
    }

    public static String formatDateToStringYmd(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat(YYYY_MM_DD);
        return sdf.format(date);
    }

    /**
     * 获取当前时间
     * @return
     */
    public static String getNowDateTime() {
        return formatDateToString(new Date(System.currentTimeMillis()));
    }

    /**
     * 获取当前日期
     * @return
     */
    public static String getNowDate() {
        return formatDateToStringYmd(new Date(System.currentTimeMillis()));
    }

    /**
     *
     * 获取某年的第一天开始时间
     *
     * @param date (yyyy-MM-dd格式)
     * @return yyyy-MM-dd hh:mm:ss格式
     */
    public static String getFirstDayOfYear(String date) {
        int year = Integer.parseInt(date.split("-")[0]);
        Calendar calendar = Calendar.getInstance();
        calendar.clear();
        calendar.set(Calendar.YEAR, year);
        String firstDayOfYear = new SimpleDateFormat("yyyy-MM-dd").format(calendar.getTime())+" 00:00:00";
        return firstDayOfYear;
    }

    /**
     *
     * 获取某年的最后一天的结束时间
     *
     * @param date (yyyy-MM-dd格式)
     * @return yyyy-MM-dd hh:mm:ss格式
     */
    public static String getLastDayOfYear(String date) {
        int year = Integer.parseInt(date.split("-")[0]);
        Calendar calendar = Calendar.getInstance();
        calendar.clear();
        calendar.set(Calendar.YEAR, year);
        calendar.roll(Calendar.DAY_OF_YEAR, -1);
        String lastDayOfYear = new SimpleDateFormat("yyyy-MM-dd").format(calendar.getTime())+" 23:59:59";
        return lastDayOfYear;
    }

    /**
     * 格式化日期
     */
    public static Date getDateByStr(String dateStr,String pattern) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
        Date parseDate = null;
        try {
            parseDate = dateFormat.parse(dateStr);
        } catch (ParseException e) {
        }
        return parseDate;
    }

    /**
     * 获取开始时间
     * 默认时间格式为 yyyy-MM-dd HH:mm:ss
     *
     * @param date
     * @return
     */
    public static String getStartTime(String date) {
        if (date.length() == 4) {
            return getStartTimeOfYear(date);
        } else if (date.length() == 7) {//yyyy-MM
            return getStartTimeOfMonth(date);
        } else if (date.length() == 10) {//yyyy-MM-dd
            return getStartTimeOfDay(date);
        }
        return date;
    }

    /**
     * 格式化日期
     *
     * @param date
     * @return
     */
    public static String formatDate(String date) {
        if (date.length() <= 10) {
            return getStartTime(date);
        } else if (date.length() == 16) {
            return date + ":00";
        } else {
            return date;
        }
    }

    public static String formatDate(Date date, String patten) {
        SimpleDateFormat sdf = new SimpleDateFormat(patten);
        return sdf.format(date);
    }

    /**
     * 获取当年的开始时间
     *
     * @param dateY
     * @return
     */
    public static String getStartTimeOfYear(String dateY) {
        return dateY + "-01-01 00:00:00";
    }

    /**
     * 获取当月开始时间
     *
     * @param dateYM
     * @return
     */
    public static String getStartTimeOfMonth(String dateYM) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime ldt = parse(dateYM + "-01 00:00:00", "yyyy-MM-dd HH:mm:ss");
        LocalDateTime strartLdt = ldt.with(TemporalAdjusters.firstDayOfMonth());
        return strartLdt.format(formatter);
    }

    /**
     * 当天开始时间
     *
     * @param dateYMD
     * @return
     */
    public static String getStartTimeOfDay(String dateYMD) {
        return dateYMD + " 00:00:00";
    }

    /**
     * 当天开始时间
     *
     * @param dateYMD
     * @return
     */
    public static String getStartTimeOfDay(LocalDate dateYMD) {
        return dateYMD + " 00:00:00";
    }

    /**
     * 当天结束时间
     *
     * @param dateYMD
     * @return
     */
    public static String getEndTimeOfDay(LocalDate dateYMD) {
        return dateYMD + " 23:59:59";
    }

    public static LocalDateTime parse(String dateYMD, String formatStr) {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(formatStr);
        LocalDateTime ldt = LocalDateTime.parse(dateYMD, formatter);
        return ldt;
    }

    /**
     * 获取指定年份的月份集合
     * @return
     */
    public static List<String> getMonthsByYear(String year){
        if(StringUtils.isEmpty(year)){
            year = Integer.toString(LocalDate.now().getYear());
        }
        LocalDate begin = DateUtils.parseStringToDate(year + "-01-01");
        LocalDate end = DateUtils.parseStringToDate((Integer.parseInt(year) + 1) + "-01-01");
        List<String> days = new ArrayList<>();
        while (begin.isBefore(end)){
            days.add(begin.toString());
            begin = begin.plusMonths(1);
        }
        return days;
    }

    /**
     * 获取指定时间内的日期集合
     * @param begin
     * @param end
     * @return
     */
    public static List<String> getMonthDays(LocalDate begin,LocalDate end){
        List<String> days = new ArrayList<>();
        while (begin.isBefore(end)){
            days.add(begin.toString());
            begin = begin.plusDays(1);
        }
        return days;
    }
}
