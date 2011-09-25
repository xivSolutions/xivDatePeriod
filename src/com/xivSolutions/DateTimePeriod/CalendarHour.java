package com.xivSolutions.DateTimePeriod;

import java.util.Calendar;

public class CalendarHour extends DatePeriod 
{
	
	/**
	 * Initialize the CalendarHour object as a one hour datePeriod. Unlike other fixed-period 
	 * subclasses of DatePeriod, the CalendarHour will examine the Calendar object passed in
	 * and use the hour of the time component to set the start and end times for hour:00:00 thru hour:50:59
	 * @param theDate
	 */
	public CalendarHour(Calendar timeOfDay)
	{		
		int hourOfDay = timeOfDay.get(Calendar.HOUR_OF_DAY);
		this.setCalendarHour(timeOfDay, hourOfDay);
	}
	
	
	/**
	 * Initialize the CalendarHour object as a one hour datePeriod:
	 * @param theDate
	 */
	public CalendarHour(Calendar date, int Hour)
	{
		this.setCalendarHour(date, Hour);
	}
	

	
	protected void setCalendarHour(Calendar timeOfDay, int hourOfDay)
	{
		/*
		 * Use the setStartTime method defined on the super class to set the 
		 * start time current instance to the proper date and hour. Minutes and seconds 
		 * will be set to 00:00, or the beginning of the hour:
		 */
		super.setStartDate(timeOfDay, hourOfDay, DatePeriod.FIRST_MINUTE_OF_HOUR, DatePeriod.FIRST_SECOND_OF_MINUTE);
		try 
		{
			/*
			 * An end date will be set to exactly 59:59 from the start hour:
			 */
			Calendar endDate = (Calendar) periodStartDate.clone();
			
			/*
			 * Add one hour to the startDate:
			 */
			endDate.add(Calendar.HOUR, 1);
			
			/*
			 * Subtract one millisecond, such the the endDate is start hour + 59 min : 59 sec : 999 Millis:
			 */
			endDate.add(Calendar.MILLISECOND, -1);
			
			/*
			 * Use setEndMethod defined on the super class to set the end date:
			 */
			super.setEndDate(endDate, endDate.get(Calendar.HOUR_OF_DAY), DatePeriod.LAST_MINUTE_OF_HOUR, DatePeriod.LAST_SECOND_OF_MINUTE);
		} 
		catch (DatePeriodPrecedenceException e) 
		{
			/*
			 * Catch not necessary, as no precedence error can occur - the end date will 
			 * ALWAYS be 59:59:999 later than the start date:
			 */
			e.printStackTrace();
		}
	}	
}
