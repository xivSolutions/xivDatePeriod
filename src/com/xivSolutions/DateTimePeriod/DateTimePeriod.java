package com.xivSolutions.DateTimePeriod;

import java.util.Calendar;
import java.util.Date;

/**
 * Extends the DatePeriod implementation to include mutable date and time components. 
 * Note that, in manipulating the date and time properties of a datePeriod instance, 
 * hours/minutes/seconds must be specified separately when using the constructor or 
 * setter methods. However, when retrieving the start or end dates for a given DatePeriod
 * instance, the proper time will be incorporated into the Calendar object returned. 
 * 
 *
 */
public class DateTimePeriod extends DatePeriod implements IDateTimePeriod
{
	
	
	

	/**
	 * Protected default constructor allows the DatePeriod Class to serve as a 
	 * superclass while maintaining the Public initialization requirements
	 * specified by the public constructors.
	 */
	protected DateTimePeriod()
	{
		super();
	}
	
	
	
	/**
	 * Initialize a new DatePeriod instance with only a start date. The instance
	 * will represent a period beginning at 12:00 AM on the date specified, and ending
	 * at 11:59 PM on the same date.
	 * @param startDate
	 */
	public DateTimePeriod(Calendar startDate)
	{
		super.setStartDate(startDate);
		try 
		{
			super.setEndDate(startDate);
		} 
		catch (DatePeriodPrecedenceException e) 
		{
			e.printStackTrace();
		}
	} // END CONSTRUCTOR OVERLOAD DatePeriod
	

	
	/**
	 * Initialize a new DatePeriod instance with only a start date. The instance
	 * will represent a period beginning at the specified hour (0:00 thru 23:00) on the date specified, and ending
	 * at 11:59 PM on the same date.
	 * @param startDate
	 */
	public DateTimePeriod(Calendar startDate, int hour)
	{

		/* Set the start date, and set the appropriate hour. The monitues and seconds will be 
		 * set to 00:00 in the overloaded method call:
		 */
		super.setStartDate(startDate, hour, DatePeriod.LAST_MINUTE_OF_HOUR);
		
		/*
		 * Since only a single date and hour param were provided, the endDate will 
		 * be set for the same date, same hour, at the end of the hour. Minutes
		 * and seconds will be set to 59:59 in the overloaded method call:
		 */
		try 
		{
			super.setEndDate(startDate, hour, DatePeriod.LAST_MINUTE_OF_HOUR);
		} 
		catch (DatePeriodPrecedenceException e) 
		{
			e.printStackTrace();
		}
	} // END CONSTRUCTOR OVERLOAD DatePeriod
	
	
	
	/**
	 * Initialize a new DatePeriod instance with only a start date. The instance
	 * will represent a period beginning at the specified hour and minute (0:00 thru 23:00) 
	 * on the date specified, and ending at the same hour at the end of the specified minute
	 *  on the same date.
	 * @param startDate
	 */
	public DateTimePeriod(Calendar startDate, int hour, int minute)
	{
		/* Set the start date, and set the appropriate hour and mminute. Seconds will be 
		 * set to 00:00 in the overloaded method call:
		 */
		super.setStartDate(startDate, hour, minute);
		
		/*
		 * Since only a single date, hour, and minute were provided, the endDate will 
		 * be set for the same date, same hour and minute, at the end of the minute. 
		 * Seconds will be set to 59:59 in the overloaded method call:
		 */
		try 
		{
			super.setEndDate(startDate, hour, minute);
		} 
		catch (DatePeriodPrecedenceException e) 
		{
			e.printStackTrace();
		}
	} // END CONSTRUCTOR OVERLOAD DatePeriod
	
	
	
	
	/**
	 * Initialize a new DatePeriod instance with only a start date. The instance
	 * will represent a period beginning at the specified hour(0:00 thru 23:00), minute and second
	 * on the date specified, at the end of the specified second on the same date.
	 * @param startDate
	 */
	public DateTimePeriod(Calendar startDate, int hour, int minute, int second)
	{
		/* Set the start date, and set the appropriate hour, minute and second. Milliseconds
		 * will beset to 000:
		 */
		super.setStartDate(startDate, hour, minute, second);
		try 
		{
			/*
			 * Since only a single date, hour, minute, and second were provided, the endDate will 
			 * be set for the same date, same hour, minute and second, at the end of the second. 
			 * milliseconds will be set to 999 in the overloaded method call:
			 */
			super.setEndDate(startDate, hour, minute, second);
		} 
		catch (DatePeriodPrecedenceException e) 
		{
			e.printStackTrace();
		}
	} // END CONSTRUCTOR OVERLOAD DatePeriod

	
	
	
	/**
	 * Initialize with both a start date and an end date. A DatePeriodPrecedenceException
	 * is thrown if the end date precedes the start date. 
	 * @param startDate
	 * @param endDate
	 * @throws DatePeriodPrecedenceException
	 */
	public DateTimePeriod(Calendar startDate, Calendar endDate)  throws DatePeriodPrecedenceException
	{
			super.setStartDate(startDate);	
			super.setEndDate(endDate);
	} // END CONSTRUCTOR OVERLOAD DatePeriod
	
	
	
	/**
	 * Initialize with both a start date and and end date. A DatePeriodPrecedenceException
	 * is thrown if the end date precedes the start date. 
	 * @param startDate
	 * @param endDate
	 * @throws DatePeriodPrecedenceException
	 */
	public DateTimePeriod(Calendar startDate, int startHour, int startMinute, 
			Calendar endDate, int endHour, int endMinute)
	 throws DatePeriodPrecedenceException
	{
		super.setStartDate(startDate, startHour, startMinute, DatePeriod.FIRST_SECOND_OF_MINUTE);
		super.setEndDate(endDate, endHour, endMinute, DatePeriod.LAST_SECOND_OF_MINUTE);
	}
	
	
	
	/**
	 * Initialize with both a start date and and end date. A DatePeriodPrecedenceException
	 * is thrown if the end date precedes the start date. 
	 * @param startDate
	 * @param endDate
	 * @throws DatePeriodPrecedenceException
	 */
	public DateTimePeriod(Calendar startDate, int startHour, int startMinute, int startSecond, 
			Calendar endDate, int endHour, int endMinute, int endSecond)
	 throws DatePeriodPrecedenceException
	{
		super.setStartDate(startDate, startHour, startMinute, startSecond);
		super.setEndDate(endDate, endHour, endMinute, endSecond);
	}
	
	
	
	
	
	/**
	 * Will automatically reset the start date to 12:00:00:000 AM on the date specified. If the 
	 * new start date occurs after the current end date, the end date will be reset to one second after
	 * the new start date. 
	 */
	@Override
	public void setStartDate(Calendar startDate) 
	{
		super.setStartDate(startDate);

	}
	
	
	
	/**
	 * Will automatically reset the start date to the specified hour on the date specified. If the 
	 * new start date occurs after the current end date, the end date will be reset to one hour after
	 * the new start date. 
	 */
	@Override
	public void setStartDate(Calendar startDate, int hour) 
	{
		super.setStartDate(startDate, hour);

	}

	
	/**
	 * Will automatically reset the start date to the specified hour and minute on the date specified. If the 
	 * new start date and time occurs after the current end date and time, the end date will be reset to one minute after
	 * the new start date. 
	 */
	@Override
	public void setStartDate(Calendar startDate, int hour, int minute) 
	{
		super.setStartDate(startDate, hour, minute);

	}
	
	
	
	/**
	 * Will automatically reset the start date to 12:00:00:000 AM on the date specified. If the 
	 * new start date occurs after the current end date, the end date will be reset to one second after
	 * the new start date. 
	 */
	@Override
	public void setStartDate(Calendar startDate, int hourOfDay, int minutes, int seconds) 
	{

		super.setStartDate(startDate, hourOfDay, minutes, seconds);
		
	} // END METHOD setStartDateAndTime
	



	/* (non-Javadoc)
	 * @see com.xivSolutions.DatePeriod.IDateTimePeriod#setEndDate(java.util.Calendar)
	 */
	@Override
	public void setEndDate(Calendar endDate)  throws DatePeriodPrecedenceException 
	{
		super.setEndDate(endDate);
		
	} // END METHOD setEndDate
	
	
	
	/* (non-Javadoc)
	 * @see com.xivSolutions.DatePeriod.IDateTimePeriod#setEndDate(java.util.Calendar, int)
	 */
	@Override
	public void setEndDate(Calendar endDate, int hour)  throws DatePeriodPrecedenceException 
	{
		super.setEndDate(endDate, hour);
		
	} // END METHOD setEndDate

	
	
	/* (non-Javadoc)
	 * @see com.xivSolutions.DatePeriod.IDateTimePeriod#setEndDate(java.util.Calendar, int, int)
	 */
	@Override
	public void setEndDate(Calendar endDate, int hour, int minute)  throws DatePeriodPrecedenceException 
	{
		super.setEndDate(endDate, hour, minute);
		
	} // END METHOD setEndDate
	
	
	
	/* (non-Javadoc)
	 * @see com.xivSolutions.DatePeriod.IDateTimePeriod#setEndDate(java.util.Calendar, int, int, int)
	 */
	@Override
	public void setEndDate(Calendar endDate, int hourOfDay, int minutes, int seconds) throws DatePeriodPrecedenceException
	{
		super.setEndDate(endDate, hourOfDay, minutes, seconds);
		
	} // END METHOD setEndDateAndTime



	@Override
	public void setStartTime(int hour) 
	{
		this.setStartTime(hour - 1, DatePeriod.LAST_MINUTE_OF_HOUR, DatePeriod.LAST_SECOND_OF_MINUTE);
	}



	@Override
	public void setStartTime(int hour, int minute) 
	{
		this.setStartTime(hour, minute, DatePeriod.FIRST_SECOND_OF_MINUTE);
	}



	@Override
	public void setStartTime(int hour, int minute, int second) 
	{
		periodStartDate.set(Calendar.HOUR_OF_DAY, hour);
		periodStartDate.set(Calendar.MINUTE, minute);		
		periodStartDate.set(Calendar.SECOND, second);
		
		if(periodStartDate.after(periodEndDate))
		{
			periodEndDate.setTime((Date) periodStartDate.getTime().clone());
			periodEndDate.set(Calendar.HOUR_OF_DAY, hour);
			periodEndDate.set(Calendar.MINUTE, minute);		
			periodEndDate.set(Calendar.SECOND, second);
		}
		
	}



	@Override
	public void setEndTime(int hour) throws DatePeriodPrecedenceException 
	{
		this.setEndTime(hour - 1, DatePeriod.LAST_MINUTE_OF_HOUR, DatePeriod.LAST_SECOND_OF_MINUTE);
	}



	@Override
	public void setEndTime(int hour, int minute)
	throws DatePeriodPrecedenceException 
	{
		
		this.setEndTime(hour, minute - 1, DatePeriod.LAST_SECOND_OF_MINUTE);
		
	}



	@Override
	public void setEndTime(int hour, int minute, int second)
	throws DatePeriodPrecedenceException 
	{
		
		periodEndDate.set(Calendar.HOUR_OF_DAY, hour);
		periodEndDate.set(Calendar.MINUTE, minute);		
		periodEndDate.set(Calendar.SECOND, second);
		periodEndDate.set(Calendar.MILLISECOND, DatePeriod.LAST_MILLISECOND_OF_SECOND);
		
		if(periodEndDate.before(periodStartDate))
		{
			throw(new DatePeriodPrecedenceException(DATE_PERIOD_PRECEDENCE_MESSAGE));
		}
		
	}



	@Override
	public void adjustStartDate(DatePeriodTypes datePart, int value) {
		// TODO Auto-generated method stub
		
	}



	@Override
public void adjustEndDate(DatePeriodTypes datePart, int value)
			throws DatePeriodPrecedenceException {
		// TODO Auto-generated method stub
		
	}



	@Override
	public void adjustPeriod(DatePeriodTypes datePart, int value) {
		// TODO Auto-generated method stub
		
	}

	
	

}
