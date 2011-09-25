package com.xivSolutions.DateTimePeriod;

import java.util.Calendar;

public class CalendarYear extends DatePeriod {
	
	protected CalendarYear()
	{
		
	}

	/**
	 * Constructor will initialize a CalendarYear instance with a start
	 * date beginning on the first day of the first month of the year in which 
	 * the date provided falls (January 1 US),
	 * and ending on the last day of the calendar year (Decmeber 31 US). 
	 * @param DateinYear
	 */
	public CalendarYear(Calendar DateinYear)
	{
		this.setYearPeriod(DateinYear);
	}

	
	
	protected void setYearPeriod(Calendar startDate)
	{

		/*
		 * A. SET THE START DATE FOR THE CALENDAR YEAR INSTANCE (always the first day of the first month of the year):
		 */
		
		Calendar calendar = Calendar.getInstance();
		
		/*
		 * Set the year to the same year as the start date param:
		 */
		calendar.set(Calendar.YEAR, startDate.get(Calendar.YEAR));
		
		/*
		 * Set the month to the first month of the calendar year:
		 */
		calendar.set(Calendar.MONTH, Calendar.JANUARY);
		
		/*
		 * Set the day to the first day of the month:
		 */
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		
		/*
		 * Use the initialization defined in the superclass to set the start
		 * date for the current CalendarYear instance. The superclass initialization
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
		 * modify the clone by adding one year to the start date. This will place the 
		 * date represented by the calendar object on the first day of that month which is 
		 * 1 year away:
		 */
		calendar.add(Calendar.YEAR, 1);
		
		/*
		 * Then back out by one day, to arrive at the LAST DAY of the Year which is 
		 * 1 Year later then the first month (e.g. Jan 1 2010 + 1 year = Jan 1 2011 - 1 day = Dec 31 2010):
		 */
		calendar.add(Calendar.DAY_OF_MONTH, -1);
		
		/*
		 * Use the setter method defined on the super class to set the end date for the CalendarQuarter period. 
		 * The setter on the super will set the time of the end date for 11:59:59:999 on the last day of the quarter.
		 */
		try 
		{
			super.setEndDate(calendar);
		} 
		catch (DatePeriodPrecedenceException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	} // END METHOD setYearPeriod
	
	
}




