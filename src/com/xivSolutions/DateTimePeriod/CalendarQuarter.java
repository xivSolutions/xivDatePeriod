package com.xivSolutions.DateTimePeriod;

import java.util.Calendar;

/**
 * The CalendarQuarter Class represents a three month period. The start date will always
 * be the first day of the first month in the period. If the object is initialized with a date other than 
 * the first day of the month passed to the constructor, initialization will still set the beginning
 * of the CalendarQuarter to the first day of the month in which the initialization date occurs. 
 * @author John Atten
 *
 */
public class CalendarQuarter extends DatePeriod {

	/**
	 * Constructor will initialize a CalendarQuarter instance with a start
	 * date beginning on the first day of the month in which the start date falls. 
	 * @param startDate
	 */
	public CalendarQuarter(Calendar startDate)
	{
		this.setQuarterPeriod(startDate);
	}
	
	
	
	/**
	 * Overloaded constructor disregards the end date and initializes
	 * a standard calendar quarter object based only upon the start date.
	 * @param startDate
	 * @param endDate
	 */
	public CalendarQuarter(Calendar startDate, Calendar endDate)
	{
		this.setQuarterPeriod(startDate);
	}
	
	@Override
	public void setStartDate(Calendar startDate)
	{
		this.setQuarterPeriod(startDate);
		
	} // END METHOD setStartDate
	

	
	private void setQuarterPeriod(Calendar startDate)
	{

		/*
		 * A. SET THE START DATE FOR THE CALENDAR QUARTER INSTANCE (always the first day of the first month):
		 */
		
		Calendar calendar = Calendar.getInstance();
		
		/*
		 * Set the year to the same year as the start date param:
		 */
		calendar.set(Calendar.YEAR, startDate.get(Calendar.YEAR));
		
		/*
		 * Set the month to the same month as the start date param:
		 */
		calendar.set(Calendar.MONTH, startDate.get(Calendar.MONTH));
		
		/*
		 * Set the day to the first day of the month:
		 */
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		
		/*
		 * Use the initialization defined in the superclass to set the start
		 * date for the current CalendarQuarter instance. The superclass initialization
		 * sets the time components to 12:00 AM on the start date:
		 */
		super.setStartDate(calendar);
		
		/*
		 * B. SET THE END DATE FOR THE CALENDAR QUARTER INSTANCE (Always the last day of the third month):
		 */
		
		/*
		 * Reset the Calendar Object:
		 */
		calendar = Calendar.getInstance();
		
		/*
		 * Initialize using a clone of the Protected periodStartDate variable defined in the super class:
		 */
		calendar = (Calendar) periodStartDate.clone();
		
		/*
		 * modify the clone by adding three months to the start date. This will place the 
		 * date represented by the calendar object on the first day of that month which is 
		 * FOUR months away:
		 */
		calendar.add(Calendar.MONTH, DatePeriod.MONTHS_IN_QUARTER);
		
		/*
		 * Then back out by one day, to arrive at the LAST DAY of the month which is 
		 * three months later then the first month (e.g. Jan 1 2010 + 3 Months = Apr 1 2010 - 1 day = Mar 31 2010):
		 */
		calendar.add(Calendar.DAY_OF_MONTH, -1);
		
		/*
		 * Use the setter method defined on the super class to set the end date for the CalendarQuarter period. 
		 * The setter on the super will set the time of the end date for 11:59:59:999 on the last day of the quarter.
		 */
		try {
			super.setEndDate(calendar);
		} catch (DatePeriodPrecedenceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	} // END METHOD setQuarterPeriod
	
	
} // END CLASS CalendarQuarter
