package cn.com.emindsoft.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;


/**
 * 日期处理
 * @author tianyu
 */
public class DateUtils {
    /**
     * 时间格式(yyyy-MM-dd)
     */
	public final static String DATE_PATTERN = "yyyy-MM-dd";
    /**
     * 时间格式(yyyy-MM-dd HH:mm:ss)
     *
     */
	public final static String DATE_TIME_PATTERN = "yyyy-MM-dd HH:mm:ss";
	
	public static String format(Date date) {
        return format(date, DATE_PATTERN);
    }

    public static String format(Date date, String pattern) {
        if(date != null){
            SimpleDateFormat df = new SimpleDateFormat(pattern);
            return df.format(date);
        }
        return null;
    }

    /**
     * 格式化日期为全格式
     * @param date
     * @return
     */
    public static String formatDateToFullFormat(Date date) {
        if(date != null){
            SimpleDateFormat df = new SimpleDateFormat(DATE_TIME_PATTERN);
            return df.format(date);
        }
        return null;
    }

    /**
     * 格式化当前日期为全格式
     * @return
     */
    public static String formatNowToFullFormat() {
            SimpleDateFormat df = new SimpleDateFormat(DATE_TIME_PATTERN);
            return df.format(new Date());
    }

    public static String format(Date date,String pattern,Locale locale) {
        if(date != null){
            SimpleDateFormat df = new SimpleDateFormat(pattern,locale);
            return df.format(date);
        }
        return null;
    }

    /**
     * 日期转星期
     * @param datetime
     * @param lang  语言  zh-中文 en-英文
     * @return
     */
    public static String dateToWeek(String datetime,String lang) {
        SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
        String[] weekDays = { "星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六" };
        String[] weekDays_en = {"Sunday","Monday","Tuesday,","Wednesday","Thursday","Friday","Saturday"};
        Calendar cal = Calendar.getInstance(); // 获得一个日历
        Date datet = null;
        try {
            datet = f.parse(datetime);
            cal.setTime(datet);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        int w = cal.get(Calendar.DAY_OF_WEEK) - 1; // 指示一个星期中的某天。
        if (w < 0)
            w = 0;

        if(lang.equals("en"))
            return weekDays_en[w];
        else
            return weekDays[w];
    }


    /**
     * 将日期字符串转化为日期。失败返回null。
     * @param date 日期字符串 yyyy-MM-dd
     * @return 日期
     */
    public static Date StringToDate(String date) {
        return StringToDate(date, "yyyy-MM-dd");
    }

    public static Date StringToDateTime(String date) {
        return StringToDate(date, "yyyy-MM-dd HH:mm:ss");
    }

    /**
     * 将日期字符串转化为日期。失败返回null。
     * @param date 日期字符串
     * @param parttern 日期格式
     * @return 日期
     */
    public static Date StringToDate(String date, String parttern) {
        Date myDate = null;
        if (date != null) {
            try {
                SimpleDateFormat df = new SimpleDateFormat(parttern);
                myDate = df.parse(date);
            } catch (Exception e) {
                System.out.print("日期字符串转化为日期失败："+e.toString());
            }
        }
        return myDate;
    }

    /**
     * 时间戳
     * @param Date
     * @return
     */
    public static Integer StringToTimeStamp(String Date){
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Integer sd = 0;
        Date dateStart = null;
        try {
            dateStart = format.parse(Date);
            sd = (int) (dateStart.getTime() / 1000);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sd;
    }

}
