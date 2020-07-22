package com.example.common.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

import org.apache.commons.lang.StringUtils;
/**
 *
 * <p>Title : DateHelper.java </p>
 * <p>Description  日期操作助手类 </p>
 */
public final class DateUtils{

	/**
	 * 按照yyyy-MM-dd格式获取当前日期
	 *
	 * @return
	 */
	public static Date getDate(){
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		try {
			return dateFormat.parse(dateFormat.format(new Date()));
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
	}

	public static String getDateFormat(){
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		try {
			return dateFormat.format(new Date());
		}catch (Exception e){
			e.printStackTrace();
			return  null;
		}
	}
	public static String getDateString(){
		SimpleDateFormat sdf = new SimpleDateFormat("yyMMddHHmmss");
		try {
			return sdf.format(new Date());
		}catch (Exception e){
			e.printStackTrace();
			return  null;
		}
	}

	/**
	 * 按照yyyy-MM-dd HH:mm:ss的格式获取系统当前时间
	 *
	 */
	public static Date getTime(){
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			return dateFormat.parse(dateFormat.format(new Date()));
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 按照yyyy-MM-dd HH:mm:ss的格式获取系统当前时间
	 *
	 * @param date
	 */
	public static Date getTime(String date){
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			return dateFormat.parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 按照指定格式将字符串转换为日期
	 *
	 * @param format
	 */
	public static Date getDate(String format, String source){
		SimpleDateFormat dateFormat = new SimpleDateFormat(format);
		try {
			if (StringUtils.isNotEmpty(source)){
				return dateFormat.parse(source);
			} else{
				return null;
			}
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 将yyyy-MM-dd格式的字符串转换为日期类型
	 *
	 * @param source
	 * @return
	 */
	public static Date getDate(String source){
		if (source != null){
			return getDate("yyyy-MM-dd", source);
		} else{
			return null;
		}
	}

	/**
	 * 将日期转换为字符串类型
	 *
	 * @return
	 */
	public static String date2Char(Date date){
		try{
			if (date != null){
				SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
				return format.format(date);
			}
		} catch (Exception e){
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 将日期转换为字符串类型的时间
	 *
	 * @return
	 */
	public static String time2Char(Date date){
		try{
			if (date != null){
				SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				return format.format(date);
			}
		} catch (Exception e){
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 将日期转换为字符串类型的时间
	 *
	 * @return
	 */
	public static String time2Char2(Date date){
		try{
			if (date != null){
				SimpleDateFormat format = new SimpleDateFormat("yyyy年MM月dd日HH点mm分");
				return format.format(date);
			}
		} catch (Exception e){
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 获取两个日期相差的天数
	 *
	 * @return
	 */
	public static Integer getDays(Date start, Date end){
		try{
			if (start == null || end == null){
				return null;
			}
			int days = (int)((end.getTime() - start.getTime()) / (60000 * 60 * 24));
			return days;
		} catch (Exception e){
			e.printStackTrace();
			throw new RuntimeException("get days is error");
		}
	}

	/**
	 * 获取日期之间相隔几个月
	 * @param start
	 * @param end
	 * @return
	 */
	public static int getBetweenMonths(Date start,Date end){
		//第一步看相隔多少年
		Calendar startTime = Calendar.getInstance();
		startTime.setTime(start);
		Calendar endTime = Calendar.getInstance();
		endTime.setTime(end);
		int startYear=startTime.get(Calendar.YEAR);
		int endYear=endTime.get(Calendar.YEAR);
		int startMonth=startTime.get(Calendar.MONTH);
		int endMonth=endTime.get(Calendar.MONTH);
		int startDay=startTime.get(Calendar.DATE);
		int endDay=endTime.get(Calendar.DATE);
		if(end.getTime()<start.getTime()){
			return 0;
		}else{
			int n=(endYear-startYear)*12+(endMonth-startMonth);
			n=endDay<startDay?(n-1):n;
			return n;
		}
	}

	/**
	 * 获取日期间除去正月后，还剩多少天
	 * @param start
	 * @param end
	 * @return
	 */
	public static long getBetweenDaysOffMonth(Date start, Date end){
		int betweenMonth=getBetweenMonths(start,end);
		Calendar startTime = Calendar.getInstance();
		startTime.setTime(start);
		startTime.add(Calendar.MONTH, betweenMonth);
		long n=getOverDayNum(startTime.getTime(), end);
		return n<0?0:n;
	}

	/**
	 * 获取两个日期相差的天数
	 *
	 * @return
	 */
	public static Integer getBetweenHours(Date start, Date end){
		try{
			if (start == null || end == null){
				return 0;
			}
			if(end.getTime()<=start.getTime()){
				return 0;
			}
			int hours = (int)((end.getTime() - start.getTime()) / (1000 * 60*60));
			return hours;
		} catch (Exception e){
			e.printStackTrace();
			throw new RuntimeException("getBetweenHours is error");
		}
	}

	/**
	 * 按照指定的格式返回当前日期的字符串表是形式
	 *
	 * @param format
	 * @return
	 */
	public static String getDateForChar(String format){
		try{
			SimpleDateFormat dateFormat = new SimpleDateFormat(format);
			return dateFormat.format(new Date());
		} catch (Exception e){
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 按照yyyy-MM-dd格式返回当前日期的字符串表是形式
	 *
	 * @return
	 */
	public static String getDateForChar(){
		return getDateForChar("yyyyMMdd");
	}

	public static Date addHours(Date source,int hours){
		long sourceTime = source.getTime();
		long endTime = sourceTime+hours*3600*1000;
		Date end = new Date(endTime);
		return end;
	}

	/**
	 * 为原日期添加指定的天数并返回添加后的日期，如果天数为负数则在原日期的基础上减去指定的天数
	 *
	 * @param source
	 * @param days
	 * @return
	 */
	public static Date addDays(Date source, int days){
		try{
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(source);
			calendar.add(Calendar.DAY_OF_YEAR, days);
			return calendar.getTime();
		} catch (Exception e){
			throw new RuntimeException("add days is error.");
		}
	}

	/**
	 * 为原日期添加指定的年数并返回添加后的日期，如果年数为负数则在原日期的基础上减去指定的年数
	 *
	 * @param source
	 * @return
	 */
	public static Date addYear(Date source, int year){
		try{
			SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
			format.parse(format.format(source));
			Calendar calendar = format.getCalendar();
			calendar.add(Calendar.YEAR, year);
			return calendar.getTime();
		} catch (Exception e){
			throw new RuntimeException("add days is error.");
		}
	}

	/**
     * 取出剩余天数
     *
     * @param startDate 开始时间
     * @param overDate 结束时间
     * @return day
     */
    public static long getOverDayNum(Date startDate,Date overDate)
    {
        long day=0;
        day=overDate.getTime()-startDate.getTime();
        day=day / 1000 / 60 / 60 / 24;

        return day<0?0:day;
    }

    /**
     * 取出当前年
     * @return
     */
    public static String getYear(){
    	Calendar cal = Calendar.getInstance();
		Integer year = cal.get(Calendar.YEAR);
    	return year.toString();
    }

    /**
     * 取出当前月
     * @return
     */
    public static String getMonth(){
    	Calendar cal = Calendar.getInstance();
    	Integer month = cal.get(Calendar.MONTH )+1;
    	return month<10?"0"+month : month.toString();
    }

    /**
     * 取出当前日
     * @return
     */
    public static String getDayOfMonth(){
    	Calendar cal = Calendar.getInstance();
    	Integer day = cal.get(Calendar.DAY_OF_MONTH);
    	return day<10?"0"+day : day.toString();
    }

    /**
     * 取得指定日期为星期几
     * @param date
     * @return 返回1是星期日、2是星期一、3是星期二、4是星期三、5是星期四、6是星期五、7是星期六
     */
    public static int getDayofWeek(Date date){
    	  Calendar cal = Calendar.getInstance();
    	  cal.setTime(date);
    	  return cal.get(Calendar.DAY_OF_WEEK);
   }

    /**
     * 返回自 1970 年 1 月 1 日 00:00:00 GMT 以来此 Date 对象表示的毫秒数
     * @return
     */
    public static String getTimeNum(){
    	return new Long(new Date().getTime()).toString();
    }

    /**
     * 取两个日期之间的所有日期
     * @param startDate 开始日期
     * @param endDate 结束日期
     * @return
     * @throws ParseException
     */
    public static List<Date> getBetweenDate(String startDate,String endDate) throws ParseException
    {
        List<Date> list=new ArrayList<Date>();
        SimpleDateFormat  sdf=new SimpleDateFormat("yyyy-MM-dd");
        GregorianCalendar gc1=new GregorianCalendar(),gc2=new GregorianCalendar();
        gc1.setTime(sdf.parse(startDate));
        gc2.setTime(sdf.parse(endDate));
        do{
            GregorianCalendar gc3=(GregorianCalendar)gc1.clone();
            String DateStr=gc3.get(Calendar.YEAR)+"-"+(gc3.get(Calendar.MONTH)+1)+"- "+  gc3.get(Calendar.DAY_OF_MONTH);
            list.add(getDate(DateStr));
            gc1.add(Calendar.DAY_OF_MONTH, 1);
         }while(!gc1.after(gc2));
        return list;
    }

    /**
     * 为日期添加月份
     * @param month
     * @return
     */
    public static String addMonth(String strDate,int month){
		 SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    	 Calendar calendar = new GregorianCalendar();
		try {
			calendar.setTime(sdf.parse(strDate));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		calendar.add(Calendar.MONTH,month);
         return sdf.format(calendar.getTime());
    }

	/**
	 * 返回指定增加多少月份的字符串格式
	 * @param month
	 * @return
	 */
	public static String addMonth(int month){
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(new Date());
		calendar.add(Calendar.MONTH,month);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return sdf.format(calendar.getTime());
	}
    /**
     * 将日期增加天数
     * @param date
     * @param dayNum
     * @return
     */
    public static Date dateAddDay(Date date,int dayNum){
         Calendar calendar = new GregorianCalendar();
         calendar.setTime(date);
         calendar.add(Calendar.DATE,dayNum);//把日期往后增加一天.整数往后推,负数往前移动
         date=calendar.getTime();   //这个时间就是日期往后推一天的结果
         return date;
    }

    /**
     * 将日期减天数
     * @param date
     * @param dayNum
     * @return
     */
    public static Date dateDecDay(Date date,int dayNum){
         Calendar calendar = new GregorianCalendar();
         calendar.setTime(date);
         calendar.add(Calendar.DATE,-dayNum);//把日期往后增加一天.整数往后推,负数往前移动
         date=calendar.getTime();   //这个时间就是日期往后推一天的结果
         return date;
    }

    /**
     * 取两个时间之间的豪秒数
     * @return
     */
    public static Long getBetweenMillis(Date startTime,Date endTime){
    	Long i= endTime.getTime()-startTime.getTime();
    	return i;
    }

	/**
	 * 比较连个日期大小
	 * @param date1
	 * @param date2
	 * @return
	 */
	public static boolean compareDate(String date1,String date2){
    	//如果date1为null,那么取当前系统日期
		boolean flag;
		if(null == date1){
			date1 = time2Char(new Date());
		}
		if(date1.compareTo(date2) >= 0){
			flag = false;
		}else {
			flag = true;
		}
		return  flag;
	}


	/**
	 * 原有时间上添加月份
	 * @param sourceDate
	 * @param month
	 * @return
	 */
	public static Date stepMonth(Date sourceDate, int month) {
		Calendar c = Calendar.getInstance();
		c.setTime(sourceDate);
		c.add(Calendar.MONTH, month);
		return c.getTime();
	}
	/*
	 * 将时间戳转换为时间
	 */
	public static String stampToDate(String s){
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        long date_temp = Long.valueOf(s);
        String date_string = sdf.format(new Date(date_temp * 1000L));
		return date_string;
	}

    public static void main(String[] args) throws Exception {
        System.out.println(stampToDate("1595285612"));
		/*SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
		Date date = new Date();
		System.out.println(df.format(date));// 当前系统时间        
		Date newDate = stepMonth(date, 1);
		System.out.println("当前时间前1个月的日期：" + df.format(newDate));
		System.out.println(UUID.randomUUID().toString());*/
    }
}
