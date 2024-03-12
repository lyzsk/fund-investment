package cn.sichu.utils;

import org.json.JSONObject;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * @author sichu huang
 * @date 2024/03/09
 **/
public class TransactionDayUtil {
    /**
     * @param date date
     * @return boolean
     * @author sichu huang
     * @date 2024/03/09
     * @see "https://stackoverflow.com/questions/4308554/simplest-way-to-read-json-from-a-url-in-java"
     * <br/>
     * "https://github.com/Dreace/ChinaHolidayAPI?tab=readme-ov-file"
     **/
    public static boolean isTransactionDate(Date date) throws IOException {
        String formatedDate = new SimpleDateFormat("yyyy-MM-dd").format(date);
        JSONObject json = JsonUtil.readJsonFromUrl("https://holiday.dreace.top?date=" + formatedDate);
        return json.get("note").equals("普通工作日");
    }

    /**
     * @param date
     * @return java.util.Date
     * @author sichu huang
     * @date 2024/03/10
     **/
    public static Date getNextTransactionDate(Date date) throws IOException {
        Date newDate = new Date(date.getTime());
        do {
            newDate.setTime(newDate.getTime() + 24 * 60 * 60 * 1000L);
        } while (!isTransactionDate(newDate));
        return newDate;
    }

    /**
     * @param date
     * @param n
     * @return java.util.Date
     * @author sichu huang
     * @date 2024/03/10
     **/
    public static Date getNextNTransactionDate(Date date, Integer n) throws IOException {
        Date newDate = new Date(date.getTime());
        int count = 0;
        while (count < n) {
            newDate.setTime(newDate.getTime() + 24 * 60 * 60 * 1000L);
            if (isTransactionDate(newDate)) {
                ++count;
            }
        }
        return newDate;
    }

    /**
     * @param startDate
     * @param endDate
     * @return java.lang.Long
     * @author sichu huang
     * @date 2024/03/12
     **/
    public static Long getHeldDays(Date startDate, Date endDate) {
        long diff = startDate.getTime() - endDate.getTime();
        return TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
    }

    /**
     * @param startDate
     * @param endDate
     * @return java.lang.Long
     * @author sichu huang
     * @date 2024/03/12
     **/
    public static Long getHeldTransactionDays(Date startDate, Date endDate) throws IOException {
        long heldTransactionDays = 0;
        long startTime = startDate.getTime();
        long endTime = endDate.getTime();
        while (startTime < endTime) {
            long tempTime = startTime + 24 * 60 * 60 * 1000L;
            Date tempDate = new Date(tempTime);
            if (isTransactionDate(tempDate)) {
                ++heldTransactionDays;
            }
            startTime = tempTime;
        }
        return heldTransactionDays;
    }

    public static void main(String[] args) throws ParseException, IOException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date1 = sdf.parse("2024-03-08");
        Date date2 = sdf.parse("2024-03-12");
        System.out.println(getHeldTransactionDays(date1, date2));

    }
}