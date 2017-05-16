package com.zlxtk.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 工程日期处理辅助
 */
public class DateUtil {

	/**
	 * 日期格式
	 */
	//向数据库中插入和更改数据时的时间格式
	/**
	 * yyyy-MM-dd HH:mm:ss
	 */
	public static final String DATE_FORMT_001 = "yyyy-MM-dd HH:mm:ss";
	
	/**
	 * yyyy年MM月dd日-HH时mm分
	 */
	public static final String DATE_FORMT_002 = "yyyy年MM月dd日-HH时mm分";
	/**
	 * yyyy.MM.dd  HH:mm
	 */
	public static final String DATE_FORMT_003 = "yyyy.MM.dd  HH:mm";
	/**
	 * yyyy-MM-dd
	 */
	public static final String DATE_FORMT_004 = "yyyy-MM-dd";
	/**
	 * yyyy-MM-dd日
	 */
	public static final String DATE_FORMT_005 = "yyyy年MM月dd日";

	/**
	 * yyyy年MM月dd
	 */
	public static String getDate001() {
		SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMT_001);
		String date = sdf.format(new Date());
		return date;
	}

	
	/**
	 * 返回规定格式时间
	 */
	/**
	 * yyyy-MM-dd HH:mm:ss
	 * @param date
	 * @return
	 */
	public static String getDateString(Date date,String formtString) {
		SimpleDateFormat sdf = new SimpleDateFormat(formtString);
		String d = sdf.format(date);
		return d;
	}


}
