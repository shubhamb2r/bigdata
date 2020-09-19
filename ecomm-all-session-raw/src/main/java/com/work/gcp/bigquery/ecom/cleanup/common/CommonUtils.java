package com.work.gcp.bigquery.ecom.cleanup.common;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class CommonUtils {
	
	private static final Double ZERO = 0D;

	private CommonUtils() {}
	
	public static String convertStringToOutputString(Object input) {

		if(null == input || "null".equals(input) 
				|| "not available in demo dataset".equals(input) 
				|| "(not set)".equals(input)) {
			return "N/A";
		} else {
			return input.toString().trim();
		}
	}

	public static Double convertStringToOutputDouble(Object input) {

		if(null == input || "null".equals(input)) {
			return ZERO;
		} else {
			return Double.valueOf(input.toString());
		}
	}

	public static Integer convertStringToOutputInteger(Object input) {

		if(null == input || "null".equals(input)) {
			return 0;
		} else {
			return Integer.valueOf(input.toString());
		}
	}
	
	public static Long convertStringToOutputLong(Object input) {

		if(null == input || "null".equals(input)) {
			return 0L;
		} else {
			return Long.parseLong(input.toString());
		}
	}
	
	public static String convertToDate(Object input) {
		String dateInString = null;
		try {
			if (null != input) {
				Date date = new SimpleDateFormat("yyyyMMdd", Locale.ENGLISH).parse(input.toString());
				dateInString = new SimpleDateFormat("YYYY-MM-dd").format(date);
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return dateInString;
	}
	
	public static String createUniqueSessionId(Object fullVisitorId, Object visitId) {
		String uniqueSessionId = null;

		if (null != fullVisitorId && null != visitId)
			uniqueSessionId = fullVisitorId.toString()+ "-"+visitId.toString();

		return uniqueSessionId;
	}
	
	
}


