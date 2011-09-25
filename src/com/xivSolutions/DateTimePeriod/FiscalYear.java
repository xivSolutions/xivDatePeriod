package com.xivSolutions.DateTimePeriod;

import java.util.Calendar;

public class FiscalYear extends DatePeriod {

	
	/**
	 * Constructor will initialize a CalendarQuarter instance with a start
	 * date beginning on the first day of the month in which the start date falls. 
	 * @param startDate
	 */
	public FiscalYear(Calendar DateinYear)
	{
		this.setYearPeriod(DateinYear);
	}
	
	
	
	/**
	 * Overloaded constructor disregards the provided end date, and initializes
	 * a fiscal year object based upon the month represented by the start date. The 
	 * resulting object will begin on the first day of the month represented by the 
	 * start date, and end on the last day of the 12th month from that date. 
	 * @param startDate
	 * @param endDate
	 */
	public FiscalYear(Calendar startDate, Calendar endDate)
	{
		this.setYearPeriod(startDate);
	}
	
	
	
	protected void setYearPeriod(Calendar startDate)
	{

		/*
		 * A. SET THE START DATE FOR THE CALENDAR YEAR INSTANCE (always the first day of the month
		 * represented by the startDate param:
		 */
		
		Calendar calendar = Calendar.getInstance();
		
		/*
		 * Set the year to the same year as the start date param:
		 */
		calendar.set(Calendar.YEAR, startDate.get(Calendar.YEAR));
		
		/*
		 * Set the month to the month of represented by the start date:
		 */
		calendar.set(Calendar.MONTH, startDate.get(Calendar.MONTH));
		
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
		 * B. SET THE END DATE FOR THE CALENDAR QUARTER INSTANCE (Always the last day of the 12th month
		 * following the month in which the start date occurs):
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
		try {
			super.setEndDate(calendar);
		} catch (DatePeriodPrecedenceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	} //END METHOD setYearPeriod
	
	
}
