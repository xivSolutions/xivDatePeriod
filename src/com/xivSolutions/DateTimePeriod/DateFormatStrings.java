package com.xivSolutions.DateTimePeriod;

/**
 * Enumeration provides commonly used formatting string for use
 * with the java.text.SimpleDateFormat Class
 * @author John
  */
public enum DateFormatStrings {
	MM_DD_yyyy ("MM/dd/yyyy"), 					// 1/1/2011
	dd_MMM_yyyy ("dd-MMM-yyyy"), 				// 1-Jan-2011
	EEEE_MMM_dd_yyyy ("EEEE, MMMM dd yyyy"),	// Monday, January 1, 2011
	hh12_mm_am_pm ("hh:mm a"),					// 12:00 AM - 11:59 PM
	hh12_mm_ss ("hh:mm:ss a"),					// 12:00:00 AM - 11:59:00 PM
	hh12_mm_ss_milli ("hh:mm:ss:S a"),			// 00:00:00:000 - 11:59:59:999
	HH24_mm_ ("HH:mm"),							// 00:00 - 23:59
	HH24_mm_ss ("HH:mm:ss"),					// 00:00:00 - 23:59:00
	HH24_mm_ss_milli ("HH:mm:ss:S"),			// 00:00:00:000 - 23:59:59:999
	MM_DD_yyyy_hh_mm ("MM/dd/yyyy hh:mm a"), 				// 1/1/2011 1:00 PM
	MM_DD_yyyy_hh_mm_ss ("MM/dd/yyyy hh:mm:ss a"), 			// 1/1/2011 1:00:00 PM
	MM_DD_yyyy_hh_mm_ss_milli ("MM/dd/yyyy hh:mm:ss:S a"), 	// 1/1/2011 1:00:00:000 PM

	MM_DD_yyyy_HH24_mm ("MM/dd/yyyy hh:mm a"), 					// 1/1/2011 13:00 PM
	MM_DD_yyyy_HH24_mm_ss ("MM/dd/yyyy hh:mm:ss a"), 			// 1/1/2011 13:00:00 PM
	MM_DD_yyyy_HH24_mm_ss_milli ("MM/dd/yyyy hh:mm:ss:S a"); 	// 1/1/2011 13:00:00:000 PM

	private String formatString;
	
	
	
	DateFormatStrings(String dateFormatString)
	{
		formatString = dateFormatString;
	}
	
	/**
	 * Return a string format pattern compatible with the 
	 * java.text.SimpleDateFormat Class.
	 * @return
	 */
	public String getFormatString()
	{
		return formatString;
	}
	
}

