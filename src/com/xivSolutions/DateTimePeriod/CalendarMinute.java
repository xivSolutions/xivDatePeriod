package com.xivSolutions.DateTimePeriod;

import java.util.Calendar;

public class CalendarMinute extends DatePeriod 
{
	
	/**
	 * Initialize the CalendarMinute object as a one hour datePeriod. Unlike other fixed-period 
	 * subclasses of DatePeriod, the CalendarMinute will examine the Calendar object passed in
	 * and use the hour and minute of the time component to set the start and end times for hour:00:00 thru hour:50:59
	 * @param theDate
	 */
	public CalendarMinute(Calendar timeOfDay)
	{		
		int hourOfDay = timeOfDay.get(Calendar.HOUR);
		int minute = timeOfDay.get(Calendar.MINUTE);
		this.setCalendarMinute(timeOfDay, hourOfDay, minute);
	}
	

	
	/**
	 * Initialize the CalendarMinute object as a one minute datePeriod:
	 * @param theDate
	 */
	public CalendarMinute(Calendar timeOfDay, int hour, int minute)
	{
		this.setCalendarMinute(timeOfDay, hour, minute);
	}
	

	
	private void setCalendarMinute(Calendar timeOfDay, int hourOfDay, int minute)
	{
		/*
		 * Use the setStartTime method defined on the super class to set the 
		 * start time current instance to the proper date and hour. Minutes and seconds 
		 * will be set to 00:00, or the beginning of the hour:
		 */
		super.setStartDate(timeOfDay, hourOfDay, minute, DatePeriod.FIRST_SECOND_OF_MINUTE);
		try 
		{
			/*
			 * An end date will be set to exactly 00:59 from the start minute:
			 */
			Calendar endDate = (Calendar) timeOfDay.clone();
			
			/*
			 * Add one minute to the startDate:
			 */
			endDate.add(Calendar.MINUTE, 1);
			
			/*
			 * Subtract one millisecond, such the the endDate is start minute + 59 sec : 999 Millis:
			 */
			endDate.add(Calendar.MILLISECOND, -1);
			
			/*
			 * Use setEndDate Method defined on the super class to set the end date:
			 */
			super.setEndDate(endDate, endDate.get(Calendar.HOUR), endDate.get(Calendar.MINUTE), DatePeriod.LAST_SECOND_OF_MINUTE);
		} 
		catch (DatePeriodPrecedenceException e) 
		{
			/*
			 * Catch not necessary, as no precedence error can occur - the end date will 
			 * ALWAYS be 00:59:999 later than the start date:
			 */
			e.printStackTrace();
		}
	}
}
