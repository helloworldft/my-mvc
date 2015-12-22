package com.name.project.common.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**  
 * 日期处理的工具类 
 * @author ft
 * @version 1.0
 * @date 2015年11月13日 下午9:04:11 
 *
 */
public class DateUtils {
	
	public static final String COMPACT_DATE_FORMAT = "yyyyMMdd";
	
    public static final String DATE_FORMAT = "yyyy-MM-dd";  
    
    public static final String YM = "yyyyMM";
    
    public static final String DATETIME_24HOUR_FORMAT = "yyyy-MM-dd HH:mm:ss"; 
    
    public static final String DATETIME_12HOUR_FORMAT = "yyyy-MM-dd hh:mm:ss";  
    
    public static final String COMPACT_DATETIME_24HOUR_FORMAT = "yyyyMMddHHmmss";   
    
    public static final String COMPACT_DATETIME_12HOUR_FORMAT = "yyyyMMddhhmmss"; 
    
    /**
     * 使用用户格式提取字符串日期
     * @param strDate 日期字符串
     * @param pattern 日期格式
     * @return
     */
    public static Date stringToDate(String strDate, String pattern) {
        SimpleDateFormat df = new SimpleDateFormat(pattern);
        try {
            return df.parse(strDate);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }
    
    /**
     * 把日期转换为字符串格式
     * @param paramDate 日期
     * @param paramString 日期格式
     * @return
     */
    public static String dateToString(Date paramDate,  
            String pattern) {  
        SimpleDateFormat localSimpleDateFormat = new SimpleDateFormat(  
        		pattern);  
        localSimpleDateFormat.setLenient(false);  
        return localSimpleDateFormat.format(paramDate);  
    }
    /**
     * 把日期字符串转为long类型
     * @param paramString
     * @return
     */
    public static Long strDateToNum(String paramString) {
        if (paramString == null) {
        	return null;
        }
        String[] arrayOfString = null;
        String str = "";
        if (paramString.indexOf("-") >= 0) {
            arrayOfString = paramString.split("-");
            for (int i = 0; i < arrayOfString.length; ++i)
                str = str + arrayOfString[i];
            return Long.valueOf(Long.parseLong(str));
        }
        return Long.valueOf(Long.parseLong(paramString));
    }
    
    /**
     * 获取系统当前时间
     * @return
     */
    public static String getNowTime(String pattern) {
        SimpleDateFormat df = new SimpleDateFormat(pattern);
        return df.format(new Date());
    }
    /**
     * 加年
     * @param date
     * @param years
     * @return
     */
    public static Date addYears(Date date , int years) {
    	return addDate(date, Calendar.YEAR, years);
    }
    /**
     * 加月份
     * @param date
     * @param months
     * @return
     */
    public static Date addMonths(Date date , int months) {
    	return addDate(date, Calendar.MONTH, months);
    }
    /**
     * 加星期
     * @param date
     * @param weeks
     * @return
     */
    public static Date addWeeks(Date date , int weeks) {
    	return addDate(date, Calendar.WEEK_OF_YEAR, weeks);
    }
    /**
     * 加天
     * @param date
     * @param days
     * @return
     */
    public static Date addDays(Date date , int days) {
    	return addDate(date, Calendar.DAY_OF_YEAR, days);
    }
    /**
     * 加小时
     * @param date
     * @param hours
     * @return
     */
    public static Date addHours(Date date, int hours) {
    	return addDate(date, Calendar.HOUR_OF_DAY, hours);
    }
    /**
     * 加分钟
     * @param date
     * @param minutes
     * @return
     */
    public static Date addMinutes(Date date , int minutes) {
    	return addDate(date, Calendar.MINUTE, minutes);
    }
    /**
     * 加秒
     * @param date
     * @param seconds
     * @return
     */
    public static Date addSeconds(Date date , int seconds) {
    	return addDate(date, Calendar.SECOND, seconds);
    }
    /**
     * 日期加减 请分别调用具体的日期加减方法
     * @param date
     * @param field
     * @param offset
     * @return
     */
    private static Date addDate(Date date, int field, int offset) {
    	Calendar cal = Calendar.getInstance();

    	cal.setTime(date);
    	
    	int fieldValue = cal.get(field);
    	
    	cal.set(field, fieldValue + offset);
    	
    	return cal.getTime();
    }
    
    public static void main (String [] args) {
    	System.out.println(DateUtils.addDate(new Date(), Calendar.WEEK_OF_MONTH, 15));
    	System.out.println(DateUtils.strDateToNum("20151114"));
    }
}
