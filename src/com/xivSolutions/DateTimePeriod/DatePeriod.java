package com.xivSolutions.DateTimePeriod;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * The datePeriod class provides a wrapper for creating and 
 * manipulating date and time periods which begin and end on fixed dates. 
 * 
 * While the class depends upon the Java Calendar class, the approach taken in the 
 * handling of date and time components differs in that the date components are represented
 * by the Calendar class, but time components are handled individually as integers. 
 * 
 * Instances of the datePeriod class are immutable. 
 * @author John
 *
 */
public class DatePeriod implements IDatePeriod {
	
	public static final String DATE_PERIOD_PRECEDENCE_MESSAGE = "The end date cannot precede the start date.";
	public static final String DATE_PERIOD_ZERO_DURATION_MASSAGE = "The start date and end date define a date period of zero duration.";

	
	public static final int FIRST_HOUR_OF_DAY = 0;
	public static final int FIRST_MINUTE_OF_HOUR = 0;
	public static final int FIRST_SECOND_OF_MINUTE = 0;
	public static final int FIRST_MILLISECOND_OF_SECOND = 0;

	public static final int LAST_HOUR_OF_DAY = 23;
	public static final int LAST_MINUTE_OF_HOUR = 59;
	public static final int LAST_SECOND_OF_MINUTE = 59;
	public static final int LAST_MILLISECOND_OF_SECOND = 999;
	
	public static final int DAYS_IN_YEAR = 365;
	public static final int WEEKS_IN_YEAR = 52;
	public static final int MONTHS_IN_YEAR = 12;
	public static final int DAYS_IN_MONTH = 30;
	public static final int MONTHS_IN_QUARTER = 3;
	public static final int DAYS_IN_WEEK = 7;
	
	public static final int MILLIS_PER_SECOND = 1000;
	public static final int SECONDS_PER_MINUTE = 60;
	public static final int MINUTES_PER_HOUR = 60;
	public static final int HOURS_PER_DAY = 24;

	protected Calendar periodStartDate;
	protected Calendar periodEndDate;
	
	
	
	/**
	 * Protected default constructor allows the DatePeriod Class to serve as a 
	 * superclass while maintaining the Public initialization requirements
	 * specified by the public constructors.
	 */
	protected DatePeriod(){}
	
	
	
	/**
	 * Initialize a new DatePeriod instance with only a start date. The instance
	 * will represent a period beginning at 12:00:00:000 AM on the date specified, and ending
	 * at 11:59:59:000 PM on the same date.
	 * 
	 * @param startDate
	 */
	public DatePeriod(Calendar startDate)
	{
		this.setStartDate(startDate);
		try 
		{
			this.setEndDate(startDate);
		} 
		catch (DatePeriodPrecedenceException e) 
		{
			/*
			 * This should never produce an exception, because the setStartDate and setEndDate
			 * methods used with no time components will always result in a 24 hour datePeriod
			 * instance beginning at 12:00:00:000 and ending at 11:59:59:999. 
			 */		
			e.printStackTrace();
		}
	} // END CONSTRUCTOR OVERLOAD DatePeriod
	

	
	/**
	 * Initialize a new DatePeriod instance with only a start date. The instance
	 * will represent a period beginning at the specified hour (0:00 thru 23:00) on the date specified, and ending
	 * at hour:59:59:000 on the same date.
	 * 
	 * @param startDate
	 */
	public DatePeriod(Calendar startDate, int hour)
	{

		/* Set the start date, and set the appropriate hour. The minutes and seconds will be 
		 * set to 00:00 in the overloaded method call:
		 */
		this.setStartDate(startDate, hour, DatePeriod.LAST_MINUTE_OF_HOUR);
		
		/*
		 * Since only a single date and hour param were provided, the endDate will 
		 * be set for the same date, same hour, at the end of the hour. Minutes
		 * and seconds will be set to 59:59 in the overloaded method call:
		 */
		try 
		{
			this.setEndDate(startDate, hour, DatePeriod.LAST_MINUTE_OF_HOUR);
		} 
		catch (DatePeriodPrecedenceException e) 
		{
			/*
			 * This should never produce an exception.
			 */	
			e.printStackTrace();
		}
	} // END CONSTRUCTOR OVERLOAD DatePeriod
	
	
	
	/**
	 * Initialize a new DatePeriod instance with only a start date. The instance
	 * will represent a period beginning at the specified hour and minute (0:00 thru 23:00) 
	 * on the date specified, and ending at the same hour at the end of the specified minute
	 *  on the same date.
	 *  
	 * @param startDate
	 */
	public DatePeriod(Calendar startDate, int hour, int minute)
	{
		/* Set the start date, and set the appropriate hour and minute. Seconds will be 
		 * set to 00:00 in the overloaded method call:
		 */
		this.setStartDate(startDate, hour, minute);
		
		/*
		 * Since only a single date, hour, and minute were provided, the endDate will 
		 * be set for the same date, same hour and minute, at the end of the minute. 
		 * Seconds will be set to 59:59 in the overloaded method call:
		 */
		try 
		{
			this.setEndDate(startDate, hour, minute);
		} 
		catch (DatePeriodPrecedenceException e) 
		{
			/*
			 * This should never produce an exception.
			 */	
			e.printStackTrace();
		}
	} // END CONSTRUCTOR OVERLOAD DatePeriod
	
	
	
	
	/**
	 * Initialize a new DatePeriod instance with only a start date. The instance
	 * will represent a period beginning at the specified hour(0:00 thru 23:00), minute and second
	 * on the date specified, at the end of the specified second on the same date.
	 * 
	 * @param startDate
	 */
	public DatePeriod(Calendar startDate, int hour, int minute, int second)
	{
		/* Set the start date, and set the appropriate hour, minute and second. Milliseconds
		 * will beset to 000:
		 */
		this.setStartDate(startDate, hour, minute, second);
		try 
		{
			/*
			 * Since only a single date, hour, minute, and second were provided, the endDate will 
			 * be set for the same date, same hour, minute and second, at the end of the second. 
			 * milliseconds will be set to 999 in the overloaded method call:
			 */
			this.setEndDate(startDate, hour, minute, second);
		} 
		catch (DatePeriodPrecedenceException e) 
		{
			/*
			 * This should never produce an exception.
			 */	
			e.printStackTrace();
		}
	} // END CONSTRUCTOR OVERLOAD DatePeriod

	
	
	
	/**
	 * Initialize with both a start date and an end date. A DatePeriodPrecedenceException
	 * is thrown if the end date precedes the start date. The resulting DatePeriod will 
	 * Begin at 12:00:00:000 AM on the start date and end at 11:59:59:000 on the end date. 
	 * 
	 * @param startDate
	 * @param endDate
	 * @throws DatePeriodPrecedenceException
	 */
	public DatePeriod(Calendar startDate, Calendar endDate)  throws DatePeriodPrecedenceException
	{
			this.setStartDate(startDate);	
			this.setEndDate(endDate);
			
	} // END CONSTRUCTOR OVERLOAD DatePeriod
	
	
	
	/**
	 * Initialize with both a start date and an end date. A DatePeriodPrecedenceException
	 * is thrown if the end date precedes the start date. The resulting DatePeriod will 
	 * begin at the specified hour and minute of the start date, and end at the specified
	 * hour and minute of the end date. Seconds will be set to 0 and 59, respectively. 
	 * 
	 * @param startDate
	 * @param endDate
	 * @throws DatePeriodPrecedenceException
	 */
	public DatePeriod(Calendar startDate, int startHour, int startMinute, 
			Calendar endDate, int endHour, int endMinute)
	 throws DatePeriodPrecedenceException
	{
		this.setStartDate(startDate, startHour, startMinute, DatePeriod.FIRST_SECOND_OF_MINUTE);
		this.setEndDate(endDate, endHour, endMinute, DatePeriod.LAST_SECOND_OF_MINUTE);
	}
	
	
	
	/**
	 * Initialize with both a start date and and end date. A DatePeriodPrecedenceException
	 * is thrown if the end date precedes the start date. 
	 * @param startDate
	 * @param endDate
	 * @throws DatePeriodPrecedenceException
	 */
	public DatePeriod(Calendar startDate, int startHour, int startMinute, int startSecond, 
			Calendar endDate, int endHour, int endMinute, int endSecond)
	 throws DatePeriodPrecedenceException
	{
		this.setStartDate(startDate, startHour, startMinute, startSecond);
		this.setEndDate(endDate, endHour, endMinute, endSecond);
	}
	
	
	
	/**
	 * Return a Java Calendar object defining the first day of a DatePeriod. By default, 
	 * the DatePeriod class sets the time components of the start date
	 * to 12:00:00:000 AM. 
	 */
	@Override
	public Calendar getStartDate() {return (Calendar) periodStartDate.clone();}
	
	
	
	/**
	 * Return a Java Calendar object defining the last day of a DatePeriod. By default, 
	 * the DatePeriod class sets the time components of the end date
	 * to 11:59:59:999 PM. The return instance is a clone of the member variable -
	 * the getter cannot be used to change the values represented within the current
	 * class instance.
	 */
	@Override
	public Calendar getEndDate() {return (Calendar) periodEndDate.clone();}

		
	
	/**
	 * Setter for StartDate Property:
	 * @param startDate
	 */
	protected void setStartDate(Calendar startDate) 
	{
		this.setStartDate(startDate, DatePeriod.FIRST_HOUR_OF_DAY, DatePeriod.FIRST_MINUTE_OF_HOUR, DatePeriod.FIRST_SECOND_OF_MINUTE);

	}
	
	
	
	/**
	 * Setter for StartDate Property:
	 * @param startDate
	 */
	protected void setStartDate(Calendar startDate, int hour) 
	{
		this.setStartDate(startDate, hour, DatePeriod.FIRST_MINUTE_OF_HOUR, DatePeriod.FIRST_SECOND_OF_MINUTE);

	}

	
	/**
	 * Setter for StartDate Property:
	 * @param startDate
	 */
	protected void setStartDate(Calendar startDate, int hour, int minute) 
	{
		this.setStartDate(startDate, hour, minute, DatePeriod.FIRST_SECOND_OF_MINUTE);

	}
		
	
	/**
	 * Protected setter for the startDate property includes parameters for time. All
	 * other overloads for the startDate setter method implemented in the DateTime class
	 * call this protected method. 
	 * 
	 * This overloaded method can be called by derived classes to utilize the time compenent
	 * of the start date. 
	 * 
	 * By default, the DatePeriod class sets the time components of the start date to 12:00:00:000 AM. 
	 * 
	 * NOTE: Adjustments to the time component will affect temporal comparison operations. 
	 * 
	 * @param startDate
	 * @param hourOfDay
	 * @param minutes
	 * @param seconds
	 */
	protected void setStartDate(Calendar startDate, int hourOfDay, int minutes, int seconds) 
	{
		periodStartDate = (Calendar) startDate.clone();
		periodStartDate.set(Calendar.HOUR_OF_DAY, hourOfDay);
		periodStartDate.set(Calendar.MINUTE, minutes);
		periodStartDate.set(Calendar.SECOND, seconds);
		periodStartDate.set(Calendar.MILLISECOND, DatePeriod.FIRST_MILLISECOND_OF_SECOND);			
		
		/*
		 * If the end date is null, or if the end date precedes the newly set start date, 
		 * set or modify the current end date value by passing the specified startdate 
		 * parameter to the setEndDatemethod. This will set the endDAte to the end of the day defined
		 * by the start date:
		 */
		if(periodEndDate == null || periodStartDate.getTimeInMillis() > periodEndDate.getTimeInMillis())
		{
			try 
			{
				// This call will automatically result in an end date which is 999 millis later than the start date:
				this.setEndDate(periodStartDate);
			}
			catch (DatePeriodPrecedenceException e) 
			{
				// Catch unnecessary - the setEndDateMethod will

				e.printStackTrace();
			}
			
		}	

	} // END METHOD setStartDateAndTime
	

	
	
	/**
	 * Setter for EndDate Property. Using this overload will set the end date with 
	 * a granularity of an entire day. Hours, minutes and seconds will be set to 23:59:59 respectively:
	 * @param endDate
	 */
	protected void setEndDate(Calendar endDate)  throws DatePeriodPrecedenceException 
	{
		this.setEndDate(endDate, DatePeriod.LAST_HOUR_OF_DAY, DatePeriod.LAST_MINUTE_OF_HOUR, DatePeriod.LAST_SECOND_OF_MINUTE);
		
	} // END METHOD setEndDate
	
	
	
	/**
	 * Setter for EndDate Property. Using this overload will result in a granularity of one hour.
	 * Minutes and Seconds will be set to 59:59 repsectively. :
	 * @param endDate
	 */
	protected void setEndDate(Calendar endDate, int hour)  throws DatePeriodPrecedenceException 
	{
		this.setEndDate(endDate, hour, DatePeriod.LAST_MINUTE_OF_HOUR, DatePeriod.LAST_SECOND_OF_MINUTE);
		
	} // END METHOD setEndDate

	
	
	/**
	 * Setter for EndDate Property. Using this overload will result in a granularity of one minute.
	 * Seconds will be set to the 59th second of the minute parameter passed in:
	 * @param endDate
	 */
	protected void setEndDate(Calendar endDate, int hour, int minute)  throws DatePeriodPrecedenceException 
	{
		this.setEndDate(endDate, hour, minute, DatePeriod.LAST_SECOND_OF_MINUTE);
		
	} // END METHOD setEndDate
	
	
	
	/**
	 * Protected setter for the endDate property includes parameters for time. All
	 * other overloads for the endDate setter method implemented in the DateTime class
	 * call this protected method. 
	 * 
	 * This overloaded method can be called by derived classes to utilize the time component
	 * of the endDate. 
	 * 
	 * By default, the DatePeriod class sets the time components of the end date to 11:59:59:999 PM. 
	 * 
	 * NOTE: Adjustments to the time component will affect temporal comparison operations. 
	 * 
	 * @param startDate
	 * @param hourOfDay
	 * @param minutes
	 * @param seconds
	 */
	protected void setEndDate(Calendar endDate, int hourOfDay, int minutes, int seconds) throws DatePeriodPrecedenceException
	{
		if(endDate.before(periodStartDate))
		{
			throw(new DatePeriodPrecedenceException(DATE_PERIOD_PRECEDENCE_MESSAGE));
		}
//		else if(endDate.getTimeInMillis() == periodStartDate.getTimeInMillis())
//		{
//			throw(new DatePeriodPrecedenceException(DATE_PERIOD_ZERO_DURATION_MASSAGE));	
//		}
		periodEndDate = (Calendar) endDate.clone();
		periodEndDate.set(Calendar.HOUR_OF_DAY, hourOfDay);
		periodEndDate.set(Calendar.MINUTE, minutes);
		periodEndDate.set(Calendar.SECOND, seconds);
		periodEndDate.set(Calendar.MILLISECOND, DatePeriod.LAST_MILLISECOND_OF_SECOND);	
		

		
	} // END METHOD setEndDateAndTime


	
	/**
	 * Return the number of days spanned by the current instance,
	 * including the start and end dates:
	 * @return
	 */
	public int getDaysInPeriodInclusive() 
	{
		Long returnValue;
		returnValue = (periodEndDate.getTimeInMillis() - periodStartDate.getTimeInMillis())/(24*3600*1000);
		
		//The subtraction operation leaves the output value one day short of including 
		//both the start date and the end date. Adjust the output by adding one:
		return returnValue.intValue() + 1;
		
	} // END METHOD getDaysInPeriodInclusive
	

	
	/**
	 * Return the number of days spanned by the current instance, 
	 * excluding the start and end dates:
	 * @return
	 */
	@Override
	public int getDaysInPeriodExclusive() 
	{
		return this.getDaysInPeriodInclusive() - 2;
	}

	
	
	/**
	 * Return the number of weeks spanned by the current instance. The result
	 * will return a decimal value for partial weeks.
	 * @return
	 */
	@Override
	public double getCountOfWeeks() {
		return Double.valueOf(this.getDaysInPeriodInclusive())/DatePeriod.DAYS_IN_WEEK;
	}

	
	
	/**
	 * Return the number of Months spanned by the current instance. The 
	 * calculation is based upon a 30 day month, and is approximate. The result
	 * will return a decimal value for partial months.
	 * @return
	 */
	@Override
	public double getCountOfMonths() {
		return Double.valueOf(this.getDaysInPeriodInclusive())/DatePeriod.DAYS_IN_MONTH;
	}

	
	
	/**
	 * Return the number of Years spanned by the current instance. The result will
	 * include a decimal value for partial years.
	 * @return
	 */
	@Override
	public double getCountOfYears() {
		return Double.valueOf(this.getDaysInPeriodInclusive())/DatePeriod.DAYS_IN_YEAR;
	}


	
	/**
	 * Returns true if any portion of the date period instance passed in 
	 * occurs within the period represented by the current instance.
	 * @return
	 */
	@Override
	public boolean hasIntersection(IDatePeriod otherPeriod) {

		if (otherPeriod.getEndDate().before(periodStartDate) || otherPeriod.getStartDate().after(periodEndDate)){
			return false;
			
		}
				
		return true;
	} // END METHOD PeriodDoesIntersect

	
	
	/**
	 * Returns an enum value describing the type of intersection
	 * between the current instance and the period passed in. 
	 * @return
	 */
	@Override
	public DatePeriodIntersectionTypes getIntersectionType(IDatePeriod otherPeriod)
	{

		/*
		 * If the other period has an intersection with the period defined by the 
		 * current instance:
		 */	
		if (this.hasIntersection(otherPeriod))
		{
			/*
			 * If the other period BEGINS BEFORE THE START DATE of the period defined by the current instance:
			 */
			if(otherPeriod.getStartDate().before(this.getStartDate()))
			{
				/*
				 * AND If the other period ENDS BEFORE THE END DATE of the period defined by the current instance:
				 */
				if (otherPeriod.getEndDate().before(this.getEndDate()))
				{
					/*
					 * The the other period is defined as being PRO_OUT
					 */
					return DatePeriodIntersectionTypes.OTHER_PERIOD_PRO_OUT;
				}
				else
				{
					if(otherPeriod.getEndDate().after(this.getEndDate()))
					{
						/*
						 * Then the other period contains the period defined by the current instance:
						 */
						return DatePeriodIntersectionTypes.OTHER_PERIOD_CONTAINS;
					}
				} // End Else  (Other period does NOT end BEFORE)
				
			} // End If (Other period begins BEFORE)
			else
			{
				/*
				 * The other period must begin before the end date of the period defined by the current instance:
				 */
				if(otherPeriod.getStartDate().before(this.getEndDate()))
				{
					/*
					 * If the other period ends before the end date of the period defined by the current instance,
					 * the other period is said the be CONTAINED:
					 */
					if(otherPeriod.getEndDate().before(this.getEndDate()))
					{
						return DatePeriodIntersectionTypes.OTHER_PERIOD_CONTAINED;
					}
					else
					{
						/*
						 * Otherwise, the other period begins within the period defined by the current instance, 
						 * and ends AFTER the end date of the current instance, and is said to be PRO_IN:
						 */
						return DatePeriodIntersectionTypes.OTHER_PERIOD_PRO_IN;
						
					} // END ELSE
					
				} // END IF
				
			} // END ELSE
			
		} // END IF (an intersection exists)
			
		else
		{
			if
			( 
				// If the current period begins after the other period ends:
				this.getStartDate().after(otherPeriod.getEndDate()) 
				
				// AND the difference between the start date of the current period and the end of the other period < 1000 Millis:
				&& (this.getStartDate().getTimeInMillis() - otherPeriod.getEndDate().getTimeInMillis() < DatePeriod.MILLIS_PER_SECOND)
			)
				
			// THEN:
			{
				return DatePeriodIntersectionTypes.OTHER_PERIOD_PRECEDES;
			}
			
			// OTHERWISE:
			
			if
			(
				// If the other period begins after the current period end date:
				otherPeriod.getStartDate().after(this.getEndDate())
				
				// AND the difference between the start date of the other period and the end of the other period < 1000 Millis:
				&& (otherPeriod.getStartDate().getTimeInMillis() - this.periodEndDate.getTimeInMillis() < DatePeriod.MILLIS_PER_SECOND)
			)
			
				// THEN:
			{
				return DatePeriodIntersectionTypes.OTHER_PERIOD_FOLLOWS;
			}

			return DatePeriodIntersectionTypes.NONE;
			
		} // END ELSE No intersection exists

		return DatePeriodIntersectionTypes.NONE;
		
	} // END METHOD getIntersectionType
	
	
	
	/**
	 * Returns an DatePeriod instance representing the intersection
	 * between the period passed in and the period spanned by the current
	 * instance. 
	 */
	@Override
	public IDatePeriod getIntersection(IDatePeriod otherPeriod)
	{

		DatePeriodIntersectionTypes intersectionType = this.getIntersectionType(otherPeriod);
		IDatePeriod output;
		Calendar start = null;
		Calendar end = null;
		
		
		switch(intersectionType){
		
		case NONE:		
		case OTHER_PERIOD_PRECEDES:			
		case OTHER_PERIOD_FOLLOWS:		
			output = null;
			return output;
		case OTHER_PERIOD_CONTAINS:
			
			start = this.getStartDate();
			end = this.getEndDate();
			break;

		case OTHER_PERIOD_CONTAINED:
			
			start = otherPeriod.getStartDate();
			end = otherPeriod.getEndDate();
			break;
			
		case OTHER_PERIOD_PRO_IN: 
			
			start = otherPeriod.getStartDate();
			end = this.getEndDate();
			break;
			
		case OTHER_PERIOD_PRO_OUT:
			
			start = this.getStartDate();
			end = otherPeriod.getEndDate();
			break;
			
		} // END SWITCH
		
		/*
		 * Set the time component for each of the local variables:
		 */
		int startHour = start.get(Calendar.HOUR_OF_DAY);
		int startMinute = start.get(Calendar.MINUTE);
		int startSecond = start.get(Calendar.SECOND);
		
		int endHour = end.get(Calendar.HOUR_OF_DAY);
		int endMinute = end.get(Calendar.MINUTE);
		int endSecond = end.get(Calendar.SECOND);
		
		try 
		{
			output = new DatePeriod(start, startHour, startMinute, startSecond, end, endHour, endMinute, endSecond);
			return output;
		} 
		catch (DatePeriodPrecedenceException e) 
		{
			e.printStackTrace();
		}
		
		return null;
		
	} // END METHOD getIntersection

	
	
	/**
	 * Returns true if any portion of the date period instance passed in 
	 * occurs within the period represented by the current instance.
	 * @return
	 */
	@Override
	public boolean hasUnion(IDatePeriod otherPeriod) {

		DatePeriodIntersectionTypes intersectionType = this.getIntersectionType(otherPeriod);
		if
		(
				this.hasIntersection(otherPeriod)
				||
				intersectionType == DatePeriodIntersectionTypes.OTHER_PERIOD_PRECEDES 
				||
				intersectionType == DatePeriodIntersectionTypes.OTHER_PERIOD_FOLLOWS
		)
		{
			return true;
		}
				
		return false;
		
	} // END METHOD hasUnion
	
	
	
	/**
	 * Returns a DatePeriod instance representing the union of the
	 * period passed in with the current instance. If two non-contiguous
	 * date ranges are joined, the method will return null.
	 * @param otherPeriod
	 * @return
	 */
	@Override
	public IDatePeriod getUnion(IDatePeriod otherPeriod) {

		DatePeriodIntersectionTypes intersectionType = this.getIntersectionType(otherPeriod);
		IDatePeriod output;
		Calendar start = null;
		Calendar end = null;
		switch(intersectionType){
		
		case NONE:	
			output = null;
			return output;
		case OTHER_PERIOD_CONTAINS:
			
			// The current period is contained by the other period:
			start = otherPeriod.getStartDate();
			end = otherPeriod.getEndDate();
			break;
		
		case OTHER_PERIOD_CONTAINED:
			
			// The other period is contained by the current period:
			start = this.getStartDate();
			end = this.getEndDate();
			break;

		case OTHER_PERIOD_PRO_IN: 
			
			// The other period begins within the current period, and ends AFTER the 
			// current period end date:
			start = this.getStartDate();
			end = otherPeriod.getEndDate();
			break;

		case OTHER_PERIOD_PRO_OUT:
			
			// The other period begins BEFORE the current period, and ends within the current period:
			start = otherPeriod.getStartDate();
			end = this.getEndDate();
			break;
			
		case OTHER_PERIOD_PRECEDES:
			
			// The other period begins BEFORE the current period, and abuts the current period:
			start = otherPeriod.getStartDate();
			end = this.getEndDate();
			break;
			
		case OTHER_PERIOD_FOLLOWS:
			
			// The current period begins BEFORE the other period, and abuts the other period:
			start = this.getStartDate();
			end = otherPeriod.getEndDate();
			break;
			
			
		} // END SWITCH
		
		/*
		 * Set the time component for each of the local variables:
		 */
		int startHour = start.get(Calendar.HOUR_OF_DAY);
		int startMinute = start.get(Calendar.MINUTE);
		int startSecond = start.get(Calendar.SECOND);
		
		int endHour = end.get(Calendar.HOUR_OF_DAY);
		int endMinute = end.get(Calendar.MINUTE);
		int endSecond = end.get(Calendar.SECOND);
		
		try {
			output = new DatePeriod(start, startHour, startMinute, startSecond, end, endHour, endMinute, endSecond);
			return output;
		} catch (DatePeriodPrecedenceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
		
	}

	
	
	/**
	 * Returns a DatePeriodColleciotn containing the sub-periods contained by 
	 * the current instance, of the specified subPeriodType
	 * @param subPeriodType
	 * @return
	 */
	@Override
	public DatePeriodCollection getSubPeriods(DatePeriodTypes subPeriodType)
	{
		/*
		 * Initialize a DatePeriodCollection as the output of this method:
		 */
		DatePeriodCollection output = new DatePeriodCollection();
		DatePeriodCollection fullPeriods = this.getFullCalenderPeriods(subPeriodType);
		
		if(!fullPeriods.isEmpty())
		{
			IDatePeriod newPeriod = null;
			
			/*
			 * Iterate through the collection of Full Periods:
			 */
			for (IDatePeriod wholePeriod : fullPeriods)
			{
				/*
				 * Obtain the intersection of between the Full Period and the period defined by the current instance:
				 */
				newPeriod = this.getIntersection(wholePeriod);
				if(newPeriod != null)
				{
					output.add(newPeriod);				
				} // End If
			} // End For
		} // End If
		
		return output;
		
	} // END METHOD getSubPeriods
	
	
	
	/**
	 * Private method examines the date range represented by the current instance
	 * and returns a date range collection of CalendarWeek objects. The collection 
	 * includes the FULL calendar week for each week spanned by the date range, 
	 * including the week in which the range begins, and the week in which the range ends. 
	 * @return
	 */
	private DatePeriodCollection getFullCalenderPeriods(DatePeriodTypes subPeriodType)
	{
		DatePeriodCollection output = new DatePeriodCollection();
		IDatePeriod firstFullPeriod = null;
		IDatePeriod addPeriod;
		Calendar startDate;
		int incrementPeriod = -1;
		
		switch(subPeriodType){
		
			case DAY:
				incrementPeriod = Calendar.DAY_OF_YEAR;
				break;
			case WEEK:
				incrementPeriod = Calendar.WEEK_OF_YEAR;
				break;
			case MONTH:
				incrementPeriod = Calendar.MONTH;
				break;				
			case QUARTER:
				incrementPeriod = Calendar.MONTH;
				break;
			case YEAR:
				incrementPeriod = Calendar.YEAR;
				break;
			case FISCAL_YEAR:
				incrementPeriod = Calendar.YEAR;
				break;
			case HOUR:
				incrementPeriod = Calendar.HOUR;
				break;
			case MINUTE:
				incrementPeriod = Calendar.MINUTE;
				break;
			case SECOND:
				incrementPeriod = Calendar.SECOND;
				break;
				
		} // END SWITCH
		
		if(incrementPeriod > 0)
		{

			firstFullPeriod = DatePeriodFactory.newDatePeriod(this.getStartDate(), subPeriodType);
			
			/*
			 * Use the StartDate of the first Full Period as the starting point for 
			 * creating a collection of Full Periods of the proper type:
			 */
			startDate = (Calendar) firstFullPeriod.getStartDate().clone();
			
			// ++++++++++++++++++++++++++++ DEBUG OUTPUT
				//			System.out.println("StartDate for Loop: " + firstFullPeriod.getStartDateString(DateFormatStrings.MM_DD_yyyy));
				//			System.out.println("StartTime for Loop: " + firstFullPeriod.getStartDateString(DateFormatStrings.hh12_mm_ss));
				//
				//			System.out.println("EndDate for Loop: " + firstFullPeriod.getEndDateString(DateFormatStrings.MM_DD_yyyy));
				//			System.out.println("EndTime for Loop: " + this.getEndDateString(DateFormatStrings.hh12_mm_ss));
				//			System.out.println("adding full periods to collection:");
				//			System.out.println("");
			// ++++++++++++++++++++++++++++ END DEBUG OUTPUT
		
			
		
			while(startDate.getTimeInMillis() < this.getEndDate().getTimeInMillis())
			{
				
				/*
				 * Use the DatePeriodFactory to create new instances of types which implement
				 * the IDatePeriod interface, and which correspond to the DatePeriodType passsed 
				 * as a parameter:
				 */
				addPeriod = DatePeriodFactory.newDatePeriod(startDate, subPeriodType);

				// ++++++++++++++++++++++++++++ DEBUG OUTPUT
					//				System.out.println("StartDate for new: " + addPeriod.getStartDateString(DateFormatStrings.MM_DD_yyyy));
					//				System.out.println("StartTime for new: " + addPeriod.getStartDateString(DateFormatStrings.hh12_mm_ss));
					//				System.out.println("EndDate for new: " + addPeriod.getStartDateString(DateFormatStrings.MM_DD_yyyy));
					//				System.out.println("EndTime for new: " + addPeriod.getStartDateString(DateFormatStrings.hh12_mm_ss));
					//				System.out.println("");
				// ++++++++++++++++++++++++++++ END DEBUG OUTPUT

				/*
				 * Add each new instance to the collection:
				 */
				output.add(addPeriod);
				
				/*
				 * Increment the iteration counter;
				 */
				startDate.add(incrementPeriod, 1);

				// ++++++++++++++++++++++++++++ DEBUG OUTPUT
					// 				SimpleDateFormat formatter = new SimpleDateFormat(DateFormatStrings.hh12_mm_ss.getFormatString());
					//				String str = formatter.format(startDate.getTime());
					//				System.out.println("Increment StartDate for Loop: " + str);
					//				System.out.println("");
					//	break;
				// ++++++++++++++++++++++++++++ END DEBUG OUTPUT

			} // END WHILE
			
		} // END if(incrementPeriod > 0)
		
		return output;
		
	} // END METHOD getFullCalenderPeriods
	

	
	/**
	 * Override the toString method to return a description of 
	 * the date span represented by the current instance:
	 * @return
	 */
	@Override
	public String toString()
	{
		// TODO Auto-generated method stub
		SimpleDateFormat formatter = new SimpleDateFormat(DateFormatStrings.MM_DD_yyyy_HH24_mm_ss_milli.getFormatString());
		String returnValue = 
			formatter.format(periodStartDate.getTime())
			+" thru " + 
			formatter.format(periodEndDate.getTime());
		return returnValue;
	}

	

	/**
	 * Returns a string representation of the start date
	 * in the format specified.
	 * @param formatOption
	 * @return
	 */
	@Override
	public String getStartDateString(DateFormatStrings formatOption) 
	{
		SimpleDateFormat formatter = new SimpleDateFormat(formatOption.getFormatString());
		return formatter.format(periodStartDate.getTime());
	}
	
	

	/**
	 * Returns a string representation of the end date
	 * in the format specified.
	 * @param formatOption
	 * @return
	 */
	@Override
	public String getEndDateString(DateFormatStrings formatOption) 
	{
		SimpleDateFormat formatter = new SimpleDateFormat(formatOption.getFormatString());
		return formatter.format(periodEndDate.getTime());
	}



} // End Class DatePeriod
