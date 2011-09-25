package com.xivSolutions.DateTimePeriod;

import java.util.Calendar;


/**
 * Represents a strongly-typed 24 hour date period beginning at 
 * 12:00 AM on the start date, and ending at 11:59:59 PM on the same date. 
 * @author John
 *
 */
public class CalendarDay extends DatePeriod {

		
	
	/**
	 * Initialize the CalendarDay object as a twenty-four hour datePeriod:
	 * @param theDate
	 */
	public CalendarDay(Calendar theDate)
	{
		this.setDayPeriod(theDate);

	}
	
	
	/**
	 * Overridable method sets the day period
	 * using a call to the superclass.
	 */
	protected void setDayPeriod(Calendar theDate)
	{
		super.setStartDate(theDate);
	}	
	
	
}
