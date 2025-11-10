package com.es.wesays;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class CalendarUtil {

    public static void main(String[] args) throws ParseException {
        Integer sum = getWorkMinutes("2022-11-20", "2022-11-25");
        System.out.println("sum = " + sum);
        Integer workDates = getWorkDays(2022, 11);
        System.out.println("workDates = " + workDates);
    }

    public static Integer getWorkMinutes(String startDate, String endDate) throws ParseException {
        List<Date> list = new ArrayList<>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar start = Calendar.getInstance();
        start.setTime(sdf.parse(startDate));
        Calendar end = Calendar.getInstance();
        end.setTime(sdf.parse(endDate));

        while (start.getTimeInMillis() <= end.getTimeInMillis()) {
            int day = start.get(Calendar.DAY_OF_WEEK);
            if (!(day == Calendar.SUNDAY || day == Calendar.SATURDAY)) {
                list.add((Date) start.getTime().clone());
            }
            start.add(Calendar.DATE, 1);
        }
        return list.size() * 8;
    }

    // 返回当月工作日数量
    public static Integer getWorkDays(int year, int month) {
        List<Date> dates = new ArrayList<>();
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, year);
        cal.set(Calendar.MONTH, month - 1);
        cal.set(Calendar.DATE, 1);
        while (cal.get(Calendar.YEAR) == year && cal.get(Calendar.MONTH) < month) {
            int day = cal.get(Calendar.DAY_OF_WEEK);
            if (!(day == Calendar.SUNDAY || day == Calendar.SATURDAY)) {
                dates.add((Date) cal.getTime().clone());
            }
            cal.add(Calendar.DATE, 1);
        }
        return dates.size();
    }
}