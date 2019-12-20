package org.aooshi.j.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateHelper {
        
    /**
	 * 返回当前时间的Unix Timestamp
     * @return
     *  结果
	 */
    public static int getTimestampToInt32()
    {
    	return (int)(System.currentTimeMillis() / 1000);
    }
    
	/**
	 * 返回当前时间的Unix Timestamp
     * @return
     *  结果
	 */
    public static long getTimestamp()
    {
    	return (long)(System.currentTimeMillis() / 1000);
    }
    
    /**
     * 获取前一天日期yyyyMMdd
     * @return 返回的日期格式为yyyyMMdd
     */
    public static String getYestoday(){
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, -1);
        return new SimpleDateFormat("yyyyMMdd").format(calendar.getTime());
    }
        
    /**
     * 获取当前的日期yyyyMMdd
     * @return
     *  结果
     */
    public static String getCurrentDate(){
        return new SimpleDateFormat("yyyyMMdd").format(new Date());
    }
        
    /**
     * 获取当前的时间yyyyMMddHHmmss
     * @return
     *  结果
     */
    public static String getCurrentTime(){
        return new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
    }
    
}
