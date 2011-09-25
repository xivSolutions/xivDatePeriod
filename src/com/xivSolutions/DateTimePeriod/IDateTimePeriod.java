package com.xivSolutions.DateTimePeriod;

import java.util.Calendar;


/**
 * Extends the IDatePeriod interface to include mutability of the date and time 
 * components of a date period. 
 * @author John
 *
 */
public interface IDateTimePeriod extends IDatePeriod
{

	/**
	 * Setter for StartDate Property:
	 * @param startDate
	 */
	public abstract void setStartDate(Calendar startDate);

	
	
	/**
	 * Setter for StartDate Property:
	 * @param startDate
	 */
	public abstract void setStartDate(Calendar startDate, int hour);

	
	
	/**
	 * Setter for StartDate Property:
	 * @param startDate
	 */
	public abstract void setStartDate(Calendar startDate, int hour, int minute);

	
	
	/**
	 * Overloaded setter for the startDate property includes parameters for time. All
	 * other overloads for the startDate setter method implemented in the DateTime class
	 * call this protected method. 
	 * 
	 * This overloaded method can be called by derived classes to utilize the time component
	 * of the start date. 
	 * 
	 * By default, the DatePeriod class sets the time components of the start date to 12:00:00:000 AM. 
	 * 
	 * NOTE: Adjustments to the time component will affect temporal comparison operations. 
	 * 
	 * @param startDate
	 * @param hourOfDay
	 * @param minutes
	 * @param seconds
	 */
	public abstract void setStartDate(Calendar startDate, int hourOfDay,
			int minutes, int seconds); 
	// END METHOD setStartDateAndTime

	
	
	/**
	 * Setter for EndDate Property. Using this overload will set the end date with 
	 * a granularity of an entire day. Hours, minutes and seconds will be set to 23:59:59 respectively:
	 * @param endDate
	 */
	public abstract void setEndDate(Calendar endDate)
			throws DatePeriodPrecedenceException; 
	// END METHOD setEndDate

	
	
	/**
	 * Setter for EndDate Property. Using this overload will result in a granularity of one hour.
	 * Minutes and Seconds will be set to 59:59 respectively for the hour specified:
	 * @param endDate
	 */
	public abstract void setEndDate(Calendar endDate, int hour)
			throws DatePeriodPrecedenceException; 
	// END METHOD setEndDate

	
	
	/**
	 * Setter for EndDate Property. Using this overload will result in a granularity of one minute.
	 * Seconds will be set to the 59th second of the minute parameter passed in:
	 * @param endDate
	 */
	public abstract void setEndDate(Calendar endDate, int hour, int minute)
			throws DatePeriodPrecedenceException; 
	// END METHOD setEndDate

	
	
	/**
	 * Protected setter for the endDate property includes parameters for time. All
	 * other overloads for the endDate setter method implemented in the DateTime class
	 * call this protected method. 
	 * 
	 * This overloaded method can be called by derived classes to utilize the time component
	 * of the endDate. 
	 * 
	 * By default, the DatePeriod class sets the time components of the end date to 11:59:59:999 PM. 
	 * 
	 * NOTE: Adjustments to the time component will affect temporal comparison operations. 
	 * 
	 * @param startDate
	 * @param hourOfDay
	 * @param minutes
	 * @param seconds
	 */
	public abstract void setEndDate(Calendar endDate, int hourOfDay,
			int minutes, int seconds) throws DatePeriodPrecedenceException; 
	// END METHOD setEndDateAndTime
	
	
	/**
	 * Set the start time to the hour indicated, and sets the minutes
	 * and seconds to 00:00. Milliseconds are set to 000. If the new 
	 * start time falls beyond the current end time, the end time will be adjusted 
	 * to fall 1 hour after the start time. 
	 */
	public abstract void setStartTime(int hour);
	
	
	/**
	 * Set the start time to the hour and minute indicated, and sets the 
	 * seconds and Milliseconds to 00:000. If the new 
	 * start time falls beyond the current end time, the end time will be adjusted 
	 * to fall 1 minute (00:59) after the start time. 
	 */
	public abstract void setStartTime(int hour, int minute);
		
	/**
	 * Set the start time to the hour, minute, and second indicated, and sets the 
	 * Milliseconds to 000. If the new 
	 * start time falls beyond the current end time, the end time will be adjusted 
	 * to fall 1 second (00:00:999) after the start time. 
	 */
	public abstract void setStartTime(int hour, int minute, int second);
	
	
	/**
	 * Set the end time to the hour indicated, and sets the minutes
	 * and seconds to 59:59. Milliseconds are set to 999. If the new 
	 * end time precedes the current start time, a datePeriodPrecedenceException
	 * will be thrown. 
	 */
	public abstract void setEndTime(int hour) throws DatePeriodPrecedenceException; 
	
	
	/**
	 * Set the end time to the hour and minute indicated, and sets the 
	 * seconds and Milliseconds to 59:999 If the new end time precedes 
	 * the current start time, a datePeriodPrecedenceException will be thrown. 
	 */
	public abstract void setEndTime(int hour, int minute) throws DatePeriodPrecedenceException; 
	
	
	/**
	 * Set the end time to the hour, minute and second indicated, and sets the 
	 * Milliseconds to 999 If the new end time precedes 
	 * the current start time, a datePeriodPrecedenceException will be thrown. 
	 */
	public abstract void setEndTime(int hour, int minute, int second) throws DatePeriodPrecedenceException; 
	
	
	/**
	 * Increments or decrements the specified component of the start date by the 
	 * amount indicated. This method employs the .add method of the Java Calendar
	 * class, such that adjustments to individual date components are reflected in the entire
	 * value of the Calendar backing field. If the adjustment causes the start date to fall after the
	 * current end date, the end date value will be adjusted at the same level of granularity, by the 
	 * same value.  
	 * @param datePart
	 * @param value
	 */
	public abstract void adjustStartDate(DatePeriodTypes datePart, int value);
	
	
	/**
	 * Increments or decrements the specified component of the end date by the 
	 * amount indicated. This method employs the .add method of the Java Calendar
	 * class, such that adjustments to individual date components are reflected in the entire
	 * value of the Calendar backing field. If the adjustment causes the end date to precede the
	 * current start date, a DatePeriodPrecedenceException is thrown.  
	 * @param datePart
	 * @param value
	 */
	public abstract void adjustEndDate(DatePeriodTypes datePart, int value) 
			throws DatePeriodPrecedenceException; 
	
	
	/**
	 * Increments or decrements the specified component of both the 
	 * start date and the end date by the value indicated. 
	 * @param datePart
	 * @param value
	 */
	public abstract void adjustPeriod(DatePeriodTypes datePart, int value);

}