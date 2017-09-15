package com.david.common.utils;

import org.apache.commons.lang3.time.DateFormatUtils;

import java.text.ParseException;
import java.util.Date;

/**
 * Date utils
 * @author ThinkGem
 * @version 2014-4-15
 */
public class DateUtils extends org.apache.commons.lang3.time.DateUtils {
	
	private static String[] parsePatterns = {
		"yyyy-MM-dd", "yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd HH:mm", "yyyy-MM", 
		"yyyy/MM/dd", "yyyy/MM/dd HH:mm:ss", "yyyy/MM/dd HH:mm", "yyyy/MM",
		"yyyy.MM.dd", "yyyy.MM.dd HH:mm:ss", "yyyy.MM.dd HH:mm", "yyyy.MM"};

	/**
	 * Get the current date string format (yyyy-MM-dd)
	 */
	public static String getDate() {
		return getDate("yyyy-MM-dd");
	}
	
	/**
	 * Get the current date string format (yyyy-MM-dd)
	 */
	public static String getDate(String pattern) {
		return DateFormatUtils.format(new Date(), pattern);
	}
	
	/**
	 * Get date string default format (yyyy-MM-dd)
	 */
	public static String formatDate(Date date, Object... pattern) {
		String formatDate = null;
		if (pattern != null && pattern.length > 0) {
			formatDate = DateFormatUtils.format(date, pattern[0].toString());
		} else {
			formatDate = DateFormatUtils.format(date, "yyyy-MM-dd");
		}
		return formatDate;
	}
	
	/**
	 * Get the date and time string, the conversion format (yyyy-MM-dd HH: mm: ss)
	 */
	public static String formatDateTime(Date date) {
		return formatDate(date, "yyyy-MM-dd HH:mm:ss");
	}

	/**
	 * Get the current time string format (HH: mm: ss)
	 */
	public static String getTime() {
		return formatDate(new Date(), "HH:mm:ss");
	}

	/**
	 * Get the current date and time string format (yyyy-MM-dd HH: mm: ss)
	 */
	public static String getDateTime() {
		return formatDate(new Date(), "yyyy-MM-dd HH:mm:ss");
	}

	/**
	 * Get the current year string format (yyyy)
	 */
	public static String getYear() {
		return formatDate(new Date(), "yyyy");
	}

	/**
	 * Get the current month string format (MM)
	 */
	public static String getMonth() {
		return formatDate(new Date(), "MM");
	}

	/**
	 * Get the day string format (dd)
	 */
	public static String getDay() {
		return formatDate(new Date(), "dd");
	}

	/**
	 * Get the current week string format (E) weeks
	 */
	public static String getWeek() {
		return formatDate(new Date(), "E");
	}
	
	/**
	 * The date string is converted to a date format
	 */
	public static Date parseDate(Object str) {
		if (str == null){
			return null;
		}
		try {
			return parseDate(str.toString(), parsePatterns);
		} catch (ParseException e) {
			return null;
		}
	}

	/**
	 * Get the number of days in the past
	 * @param date
	 * @return
	 */
	public static long pastDays(Date date) {
		long t = new Date().getTime()-date.getTime();
		return t/(24*60*60*1000);
	}

	/**
	 * Get past hours
	 * @param date
	 * @return
	 */
	public static long pastHour(Date date) {
		long t = new Date().getTime()-date.getTime();
		return t/(60*60*1000);
	}
	
	/**
	 * Get past minutes
	 * @param date
	 * @return
	 */
	public static long pastMinutes(Date date) {
		long t = new Date().getTime()-date.getTime();
		return t/(60*1000);
	}
	
	/**
	 * Convert to time (days, hours: minutes: seconds. Milliseconds)
	 * @param timeMillis
	 * @return
	 */
    public static String formatDateTime(long timeMillis){
		StringBuffer buffer = new StringBuffer();
		long day = timeMillis/(24*60*60*1000);
		long hour = (timeMillis/(60*60*1000)-day*24);
		long min = ((timeMillis/(60*1000))-day*24*60-hour*60);
		long s = (timeMillis/1000-day*24*60*60-hour*60*60-min*60);
		long sss = (timeMillis-day*24*60*60*1000-hour*60*60*1000-min*60*1000-s*1000);
		if(day>0){
			buffer.append(day+",");
		}
		if(hour>0){
			buffer.append(hour+":");
		}
		if(min>0){
			buffer.append(min+":");
		}
		if(s>0){
			buffer.append(s+".");
		}
		if(sss>0){
			buffer.append(sss);
		}
		return buffer.toString();
    }
	
	/**
	 * Get the number of days between two dates
	 * 
	 * @param before
	 * @param after
	 * @return
	 */
	public static double getDistanceOfTwoDate(Date before, Date after) {
		long beforeTime = before.getTime();
		long afterTime = after.getTime();
		return (afterTime - beforeTime) / (1000 * 60 * 60 * 24);
	}

}
