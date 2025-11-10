package com.bolink;

import java.util.*;

public class ProfitDemp {

    public static void main(String[] args) {

//        boolean crossMonth = isCrossMonth(1748743213 - 86400, 1748743213);
//        System.out.println(crossMonth);
        List<Map<String, Long>> monthRanges = getMonthRanges(1748743200 - 86400, 1748743200);
        System.out.println(monthRanges);
    }

    public static boolean isCrossMonth(long startTime, long endTime) {
        Calendar startCal = Calendar.getInstance();
        startCal.setTimeInMillis(startTime * 1000); // 转换为毫秒

        Calendar endCal = Calendar.getInstance();
        endCal.setTimeInMillis(endTime * 1000);

        return startCal.get(Calendar.YEAR) != endCal.get(Calendar.YEAR) ||
                startCal.get(Calendar.MONTH) != endCal.get(Calendar.MONTH);
    }

    public static List<Map<String, Long>> getMonthRanges(long startTime, long endTime) {
        List<Map<String, Long>> ranges = new ArrayList<>();

        Calendar startCal = Calendar.getInstance();
        startCal.setTimeInMillis(startTime * 1000);

        Calendar endCal = Calendar.getInstance();
        endCal.setTimeInMillis(endTime * 1000);

        // 复制开始时间，用于遍历每个月
        Calendar currentCal = (Calendar) startCal.clone();

        // 循环处理每个月，直到当前月超过结束月
        while (true) {
            // 计算当前月的开始时间（当月1日00:00:00）
            Calendar startOfMonthCal = (Calendar) currentCal.clone();
            startOfMonthCal.set(Calendar.DAY_OF_MONTH, 1);
            startOfMonthCal.set(Calendar.HOUR_OF_DAY, 0);
            startOfMonthCal.set(Calendar.MINUTE, 0);
            startOfMonthCal.set(Calendar.SECOND, 0);
            long startOfMonth = startOfMonthCal.getTimeInMillis() / 1000;

            // 计算当前月的结束时间
            Calendar endOfMonthCal = (Calendar) currentCal.clone();
            endOfMonthCal.set(Calendar.DAY_OF_MONTH, endOfMonthCal.getActualMaximum(Calendar.DAY_OF_MONTH));
            endOfMonthCal.set(Calendar.HOUR_OF_DAY, 23);
            endOfMonthCal.set(Calendar.MINUTE, 59);
            endOfMonthCal.set(Calendar.SECOND, 59);
            long endOfMonth = endOfMonthCal.getTimeInMillis() / 1000;

            // 如果是最后一个月，结束时间取用户传入的 endTime
            if (currentCal.get(Calendar.YEAR) == endCal.get(Calendar.YEAR) &&
                    currentCal.get(Calendar.MONTH) == endCal.get(Calendar.MONTH)) {
                endOfMonth = endTime;
            }

            // 添加当前月的时间范围
            Map<String, Long> range = new HashMap<>();
            range.put("startTime", startOfMonth);
            range.put("endTime", endOfMonth);
            ranges.add(range);

            // 如果已经处理到结束月，退出循环
            if (currentCal.get(Calendar.YEAR) == endCal.get(Calendar.YEAR) &&
                    currentCal.get(Calendar.MONTH) == endCal.get(Calendar.MONTH)) {
                break;
            }

            // 移动到下个月
            currentCal.add(Calendar.MONTH, 1);
        }

        return ranges;
    }

}
