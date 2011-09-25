package com.xivSolutions.DateTimePeriod;
import java.util.Calendar;


/**
 * The CalendarWeek Class defines a period one week in length, 
 * beginning on the First Day of the Week (Sunday US, set Locale for
 * other regions) and ending at 11:59:59 PM on the last day of the 
 * week (Saturday US, set Locale for other regions). 
 * 
 * The CalendarWeek is initialized with a Calendar object set to any day
 * within the week. The First and last dates are set internally based upon 
 * the the date provided. 
 * @author John
 *
 */
public class CalendarWeek extends DatePeriod {


	/**
	 * The CalendarWeek object is initialized using any date within the week. Based on 
	 * the date parameter provided, the CalendarWeek class will determine the start and
	 * end dates for the Week within which the start date occurs. The resulting CalendarWeek
	 * instance will begin at 12:00 AM on the first day of the week (Sunday in the US), and end at 11:59:59 on the 
	 * last day of the week (Saturday in the US). 
	 * @param dateInWeek
	 */
	public CalendarWeek(Calendar dateInWeek) 
	{
		this.setWeekPeriod(dateInWeek);

	}
	
	
	
	/**
	 * Private method computed the dates associated with the 
	 * first day of the calendar week (Sunday US) and last day of the
	 * calendar week (Saturday US).
	 * @param dateInWeek
	 */
	private void setWeekPeriod(Calendar dateInWeek)
	{
		/*
		 * Initialize a new Calendar object and create a clone 
		 * of the dateInWeek parameter:
		 */
		Calendar calendar = Calendar.getInstance();
		calendar = (Calendar) dateInWeek.clone();
		
		/*
		 * Set the new calendar variable to the first day of the 
		 * week which contains the dateInWeek Parameter:
		 */
		calendar.set(Calendar.DAY_OF_WEEK, 1);
		
		/*
		 * Use this first day of the week as the startDate
		 * for the current instance. The setter on the super class will 
		 * set the time component to 12:00:00:000 AM:
		 */
		super.setStartDate(calendar);
		
		/*
		 * Re-initialize the calendar variable, and grab a 
		 * clone of the newly set periodStartDate
		 * Parameter (defined on the super class (protected):
		 */
		calendar = Calendar.getInstance();
		calendar = (Calendar) periodStartDate.clone();
		
		/*
		 * Add one week to find the beginning of the NEXT week,
		 * then subtract one day to find the LAST day of the current week:
		 */
		calendar.add(Calendar.WEEK_OF_MONTH, 1);
		calendar.add(Calendar.DAY_OF_WEEK, -1);
		

		/*
		 * Use this value as the end date for the current instance. The setter
		 * defined on the super class DatePeriod will set the time component to 
		 * 11:59:59:999 PM:
		 */
		try 
		{
			super.setEndDate(calendar);
		} 
		catch (DatePeriodPrecedenceException e) 
		{
			
			e.printStackTrace();
		}
		
	} // END METHOD setWeekPeriod

} // End Class CalendarWeek
