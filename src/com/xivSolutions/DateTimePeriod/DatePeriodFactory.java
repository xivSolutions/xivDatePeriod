package com.xivSolutions.DateTimePeriod;

import java.util.Calendar;

public class DatePeriodFactory 
{

	
	
	public static IDatePeriod newDatePeriod(Calendar startDate, DatePeriodTypes periodType)
	{
		try 
		{
			return DatePeriodFactory.newDatePeriod(startDate, startDate, periodType);
		} 
		catch (DatePeriodPrecedenceException e) 
		{
			//Catch is not necessary when the only parameter provided is the start date.
			e.printStackTrace();
			return null;
		}	
		
	} // END CONSTRUCTOR OVERLOAD DatePeriod
	
	
	public static IDatePeriod newDatePeriod(
			Calendar startDate, 
			Calendar endDate, 
			DatePeriodTypes periodType)
	throws DatePeriodPrecedenceException
	{
			return DatePeriodFactory.newDatePeriod(startDate, DatePeriod.FIRST_HOUR_OF_DAY, 
					DatePeriod.FIRST_MINUTE_OF_HOUR, DatePeriod.FIRST_SECOND_OF_MINUTE, endDate, 
					DatePeriod.LAST_HOUR_OF_DAY, DatePeriod.LAST_MINUTE_OF_HOUR, DatePeriod.LAST_SECOND_OF_MINUTE, 
					periodType);
		
	} // END CONSTRUCTOR OVERLOAD DatePeriod
	
	
	
	public static IDatePeriod newDatePeriod(
			Calendar startDate, int startHour, int startMinute, 
			Calendar endDate, int endHour, int endMinute, 
			DatePeriodTypes periodType)
	throws DatePeriodPrecedenceException
	{

		return DatePeriodFactory.newDatePeriod(startDate, startHour, startMinute, DatePeriod.FIRST_SECOND_OF_MINUTE, 
				endDate, endHour, endMinute, DatePeriod.LAST_SECOND_OF_MINUTE, periodType);

	} // END CONSTRUCTOR OVERLOAD DatePeriod
	
	
	
	
	public static IDatePeriod newDatePeriod(
			Calendar startDate, int startHour, int startMinute, int startSecond, 
			Calendar endDate, int endHour, int endMinute, int endSecond, 
			DatePeriodTypes periodType)
	throws DatePeriodPrecedenceException
	{
		
		
		IDatePeriod newDatePeriod = null;
		
		switch(periodType){
		
		case GENERIC:

				newDatePeriod = new DatePeriod(startDate, startHour, startMinute, DatePeriod.FIRST_SECOND_OF_MINUTE, 
						endDate, startHour, startMinute, DatePeriod.LAST_SECOND_OF_MINUTE);
				break;
				
		case DAY:
			
			newDatePeriod = new CalendarDay(startDate);
			break;
			
		case WEEK:
			
			newDatePeriod = new CalendarWeek(startDate);
			break;
			
		case MONTH:

			newDatePeriod = new CalendarMonth(startDate);
			break;
			
		case QUARTER:
			
			newDatePeriod = new CalendarQuarter(startDate);
			break;
			
		case YEAR:
			
			newDatePeriod = new CalendarYear(startDate);
			break;
			
		case FISCAL_YEAR:
			
			newDatePeriod = new FiscalYear(startDate);
			break;
			
		case HOUR:
			
			newDatePeriod = new CalendarHour(startDate);
			break;
			
		case MINUTE:
			newDatePeriod = new CalendarMinute(startDate);
			break;
			
	} // END SWITCH
		
		return newDatePeriod;
	
	}

}
