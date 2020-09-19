package com.work.gcp.bigquery.ecom.cleanup.common;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * 
 * A utility class for transformations 
 * of values 
 * 
 * @author spaldewar
 *
 */
public class CommonUtils {
	
	private CommonUtils() {}
	
	/**
	 * @param input
	 * @return String
	 * 
	 */
	public static String transformObjectToRequiredStringValue(Object input) {

		if(null == input || "null".equals(input) 
				|| "not available in demo dataset".equals(input) 
				|| "(not set)".equals(input)) {
			return "N/A";
		} else {
			return input.toString().trim();
		}
	}

	/**
	 * 
	 * Method transforms null object to 0D
	 * or to respective Double value
	 * 
	 * @param input
	 * @return Double
	 */
	public static Double transformObjectToRequiredDoubleValue(Object input) {

		if(null == input || "null".equals(input)) {
			return ApplicationConstants.ZERO;
		} else {
			return Double.valueOf(input.toString());
		}
	}

	/**
	 * Method transforms null object to 0
	 * or to respective Integer value
	 * 
	 * @param input
	 * @return Integer
	 */
	public static Integer transformObjectToRequiredIntegerValue(Object input) {

		if(null == input || "null".equals(input)) {
			return 0;
		} else {
			return Integer.valueOf(input.toString());
		}
	}
	
	
	/**
	 * Method transforms null object to 0L
	 * or to respective Long value
	 * 
	 * @param input
	 * @return Long
	 */
	public static Long transformObjectToRequiredLongValue(Object input) {

		if(null == input || "null".equals(input)) {
			return 0L;
		} else {
			return Long.parseLong(input.toString());
		}
	}
	
	/**
	 * Method formats string to bigquery required 
	 * date
	 * 
	 * @param input
	 * @return date in string
	 */
	public static String convertObjectToDateInString(Object input) {
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
	
	/**
	 * Method to create uniqueSessionId
	 * from fullVisitorId and visitId
	 * 
	 * @param fullVisitorId
	 * @param visitId
	 * @return uniqueSessionId
	 */
	public static String createUniqueSessionId(Object fullVisitorId, Object visitId) {
		String uniqueSessionId = null;

		if (null != fullVisitorId && null != visitId)
			uniqueSessionId = fullVisitorId.toString()+ "-"+visitId.toString();

		return uniqueSessionId;
	}
	
	
}


