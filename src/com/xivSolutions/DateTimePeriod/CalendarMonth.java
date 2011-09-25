package com.xivSolutions.DateTimePeriod;

import java.util.Calendar;

public class CalendarMonth extends DatePeriod {

	
	/**
	 * The CalendarMonth object is initialized using any date within the month. Based on 
	 * the date parameter provided, the CalendarMonth class will determine the start and
	 * end dates for the month within which the start date occurs. THe resulting CalendarMonth
	 * instance will begin at 12:00 AM on the first day of the month, and end at 11:59:59 on the 
	 * last day of the month. 
	 * @param dateInMonth
	 */
	public CalendarMonth(Calendar dateInMonth)
	{
		this.setMonthPeriod(dateInMonth);
	}
	

	
	private void setMonthPeriod(Calendar dateInMonth)
	{
		Calendar calendar = Calendar.getInstance();
		calendar = (Calendar) dateInMonth.clone();
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		
		super.setStartDate(calendar);
		
		calendar = Calendar.getInstance();
		calendar = (Calendar) periodStartDate.clone();
		calendar.add(Calendar.MONTH, 1);
		calendar.add(Calendar.DAY_OF_MONTH, -1);
		
		try {
			super.setEndDate(calendar);
		} catch (DatePeriodPrecedenceException e) {
			// Catch not necessary - the code in this method automatically sets the 
			// end date such that a precedence exception will not occur. 
			e.printStackTrace();
		}
		
	}
	
	


}
