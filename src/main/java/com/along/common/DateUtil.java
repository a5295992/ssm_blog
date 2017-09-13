package com.along.common;

import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;

public class DateUtil {
 private static final String DATE_PATTERN = "yyyy-MM-dd HH:mm:ss";//二四小时
 
 public static Date toSqlDate(){
	 DateFormat dateFormat;
	    dateFormat = new SimpleDateFormat(DATE_PATTERN, Locale.ENGLISH);
	    String par = dateFormat.format(new java.util.Date());
	    dateFormat.setLenient(false);
	    java.util.Date timeDate = null;
		try {
			timeDate = dateFormat.parse(par);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}//util类型
	    java.sql.Date dateTime = new java.sql.Date(timeDate.getTime());//sql类型
	return dateTime;
 }

public static java.util.Date toUtilDate() {
	 DateFormat dateFormat;
	    dateFormat = new SimpleDateFormat(DATE_PATTERN, Locale.ENGLISH);
	    String par = dateFormat.format(new java.util.Date());
	    java.util.Date timeDate = null;
		try {
			timeDate = dateFormat.parse(par);
		} catch (ParseException e) {
			e.printStackTrace();
		}//util类型
	    java.util.Date dateTime = new java.util.Date(timeDate.getTime());//sql类型
	return dateTime;
}

public static java.util.Date toSqlDate(String createDate) {
	DateFormat dateFormat;
    dateFormat = new SimpleDateFormat(DATE_PATTERN, Locale.ENGLISH);
    String par = dateFormat.format(createDate);
    dateFormat.setLenient(false);
    java.util.Date timeDate = null;
	try {
		timeDate = dateFormat.parse(par);
	} catch (ParseException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}//util类型
    java.sql.Date dateTime = new java.sql.Date(timeDate.getTime());//sql类型
    return dateTime;
}
}
