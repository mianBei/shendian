package com.example.common.util;

import java.util.Calendar;

public final class DatePathUtil {
    public static String getDatePath() {
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH) + 1;
        String monthStr = month + "";
        if (month < 10) {
            monthStr = "0" + month;
        }
        int day = cal.get(Calendar.DAY_OF_MONTH);
        String dayStr = day + "";
        if (day < 10) {
            dayStr = "0" + day;
        }
        return "/" + year + "/" + monthStr +"/"+ dayStr + "/";
    }
}
