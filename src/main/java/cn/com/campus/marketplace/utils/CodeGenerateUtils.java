package cn.com.campus.marketplace.utils;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class CodeGenerateUtils {
    public static String generateProductCode() {
        long nanoPart = System.nanoTime() % 100000L;
        if (nanoPart < 10000L) {
            nanoPart += 10000L;
        }
        long randomPart = (long) (Math.random() * (90000) + 10000);
        String code = "0" + String.valueOf((new BigDecimal(nanoPart).multiply(new BigDecimal(randomPart))));
        return code.substring(code.length() - 10);
    }

    public static String generateOrderSn(long id) {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        year = year % 10;
        if (year == 0) year = 10;
        int month = calendar.get(Calendar.MONTH) + 1;
        int yearMonth = year * month;
        String yearMonthPart = "0" + yearMonth;
        yearMonthPart = yearMonthPart.substring(yearMonthPart.length() - 2);

        int day = calendar.get(Calendar.DAY_OF_MONTH);
        int dayNum = (int) ((id % 3.33) * day);
        String dayPart = "0" + dayNum;
        dayPart = dayPart.substring(dayPart.length() - 2);

        String timestampPart = "" + (Math.random() * 10000) * (System.currentTimeMillis() / 10000);
        timestampPart = timestampPart.replace(".", "").replace("E", "");
        timestampPart = timestampPart.substring(0, 6);
        return yearMonthPart + dayPart + timestampPart;
    }

    public static String generateUnionPaySn() {
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddhhmm");
        String dateTime = dateFormat.format(calendar.getTime());
        dateTime = dateTime.substring(2);
        String timestampPart = "" + (Math.random() * 10000) * (System.currentTimeMillis() / 10000);
        timestampPart = timestampPart.replace(".", "").replace("E", "");
        timestampPart = timestampPart.substring(0, 5);
        return dateTime + timestampPart;
    }
}
