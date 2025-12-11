package com.bolink.util;

import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import com.bolink.pojo.TimeRangeDTO;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

/**
 * 时间区间与分表工具类
 */
public class TimeRangeUtil {

    public static void main(String[] args) {
        String dateStr = "2025-12-02";
        long endTime = StrUtil.isNotEmpty(dateStr) ? TimeRangeUtil.buildTimeRange(dateStr).getEndSec() : System.currentTimeMillis() / 1000;
        System.out.println(endTime);
    }

    /**
     * dateStr返回当天开始时间和结束时间
     */
    public static TimeRangeDTO buildTimeRange(String dateStr) {
        DateTime date = new DateTime();
        if (StrUtil.isNotEmpty(dateStr)) {
            date = DateUtil.parse(dateStr, DatePattern.NORM_DATE_PATTERN);
        }
        long startMillis = DateUtil.beginOfDay(date).getTime();
        long endMillis = DateUtil.endOfDay(date).getTime();
        Date startDate = new Date(startMillis);
        Date endDate = new Date(endMillis);
        return new TimeRangeDTO(startMillis, endMillis, startMillis / 1000, endMillis / 1000, startDate, endDate);
    }

    /**
     * 处理时间参数，返回毫秒区间和秒区间
     */
    public static TimeRangeDTO buildTimeRange(Long startTime, Long endTime) {
        long nowSec = System.currentTimeMillis() / 1000;
        if (startTime == null && endTime == null) {
            endTime = nowSec;
            startTime = nowSec - 24 * 3600;
        }
        if (startTime == null) {
            startTime = endTime - 24 * 3600;
        }
        if (endTime == null) {
            endTime = startTime + 24 * 3600;
        }
        long startMillis = startTime * 1000;
        long endMillis = endTime * 1000;
        Date startDate = new Date(startMillis);
        Date endDate = new Date(endMillis);
        return new TimeRangeDTO(startMillis, endMillis, startTime, endTime, startDate, endDate);
    }

    /**
     * 根据毫秒时间戳获取分表后缀
     */
    public static String getSubFromMillis(long millis) {
        LocalDateTime dt = Instant.ofEpochMilli(millis).atZone(ZoneId.systemDefault()).toLocalDateTime();
        return String.format("%04d%02d", dt.getYear(), dt.getMonthValue());
    }

    public static String getSubFromDate(Date date) {
        return DateUtil.format(date, DatePattern.SIMPLE_MONTH_PATTERN);
    }

    /**
     * 获取该时间戳所在月的月末秒值
     */
    public static long getMonthEndSec(long millis) {
        LocalDateTime dt = Instant.ofEpochMilli(millis).atZone(ZoneId.systemDefault()).toLocalDateTime();
        LocalDateTime monthEnd = dt.withDayOfMonth(dt.toLocalDate().lengthOfMonth())
                .withHour(23).withMinute(59).withSecond(59).withNano(999_000_000);
        return monthEnd.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli() / 1000;
    }

    /**
     * 获取该时间戳所在月的月初秒值
     */
    public static long getMonthStartSec(long millis) {
        LocalDateTime dt = Instant.ofEpochMilli(millis).atZone(ZoneId.systemDefault()).toLocalDateTime();
        LocalDateTime monthStart = dt.withDayOfMonth(1).withHour(0).withMinute(0).withSecond(0).withNano(0);
        return monthStart.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli() / 1000;
    }
} 